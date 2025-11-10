package app;

import modelo.*;
import util.ComparadorNivel;
import java.util.*;

public class AppInterativo {
    private static Scanner scanner = new Scanner(System.in);
    private static Jogador jogador;
    private static List<Habilidade> habilidadesDisponiveis = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("🎮 AURA DE CHUMBO - RPG SIMPLES 🎮");
        System.out.println("===================================");

        inicializarHabilidades();
        inicializarJogo();
        menuPrincipal();
    }

    private static void inicializarHabilidades() {
        habilidadesDisponiveis.add(new Habilidade("Corte Duplo", "Ataque rápido duas vezes", 20));
        habilidadesDisponiveis.add(new Habilidade("Cura Básica", "Recupera 30 de vida", 25));
        habilidadesDisponiveis.add(new Habilidade("Flecha Venenosa", "Causa dano contínuo", 30));
    }

    private static void inicializarJogo() {
        System.out.print("Digite seu nome: ");
        String nomeJogador = scanner.nextLine();
        jogador = new Jogador(nomeJogador);
        System.out.println("✅ Jogador " + jogador.getNome() + " criado!");
    }

    private static void menuPrincipal() {
        while (true) {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Criar Personagem");
            System.out.println("2. Listar Personagens");
            System.out.println("3. Ensinar Habilidade");
            System.out.println("4. Iniciar Batalha");
            System.out.println("5. Estatísticas");
            System.out.println("6. Sair");
            System.out.print("Escolha: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    criarPersonagem();
                    break;
                case 2:
                    listarPersonagens();
                    break;
                case 3:
                    ensinarHabilidade();
                    break;
                case 4:
                    iniciarBatalha();
                    break;
                case 5:
                    mostrarEstatisticas();
                    break;
                case 6:
                    System.out.println("Até logo!");
                    return;
                default:
                    System.out.println("❌ Opção inválida!");
            }
        }
    }

    private static void criarPersonagem() {
        System.out.println("\n=== CRIAR PERSONAGEM ===");
        System.out.println("Tipos disponíveis:");
        for (TipoPersonagem tipo : TipoPersonagem.values()) {
            System.out.println("- " + tipo.getNome());
        }

        System.out.print("Nome do personagem: ");
        String nome = scanner.nextLine();

        System.out.print("Tipo (GUERREIRO/MAGO/ARQUEIRO): ");
        String tipoStr = scanner.nextLine().toUpperCase();

        try {
            TipoPersonagem tipo = TipoPersonagem.valueOf(tipoStr);
            jogador.criarPersonagem(nome, tipo);
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Tipo inválido!");
        }
    }

    private static void listarPersonagens() {
        List<Personagem> personagens = jogador.getPersonagens();

        if (personagens.isEmpty()) {
            System.out.println("Nenhum personagem criado!");
            return;
        }

        // ORDENAÇÃO usando Collections.sort
        List<Personagem> ordenados = new ArrayList<>(personagens);
        Collections.sort(ordenados, new ComparadorNivel());

        System.out.println("\n=== SEUS PERSONAGENS ===");
        for (int i = 0; i < ordenados.size(); i++) {
            System.out.println((i + 1) + ". " + ordenados.get(i));

            // Mostrar habilidades (relacionamento N:N)
            List<Habilidade> habilidades = ordenados.get(i).getHabilidades();
            if (!habilidades.isEmpty()) {
                System.out.println("   Habilidades: " + habilidades);
            }
        }
    }

    private static void ensinarHabilidade() {
        List<Personagem> personagens = jogador.getPersonagens();

        if (personagens.isEmpty()) {
            System.out.println("❌ Crie um personagem primeiro!");
            return;
        }

        System.out.println("\n=== ENSINAR HABILIDADE ===");

        // Listar personagens
        for (int i = 0; i < personagens.size(); i++) {
            System.out.println((i + 1) + ". " + personagens.get(i).getNome());
        }
        System.out.print("Escolha o personagem: ");
        int escolhaPersonagem = scanner.nextInt() - 1;

        if (escolhaPersonagem < 0 || escolhaPersonagem >= personagens.size()) {
            System.out.println("❌ Personagem inválido!");
            return;
        }

        // Listar habilidades disponíveis
        System.out.println("\nHabilidades disponíveis:");
        for (int i = 0; i < habilidadesDisponiveis.size(); i++) {
            System.out.println((i + 1) + ". " + habilidadesDisponiveis.get(i));
        }
        System.out.print("Escolha a habilidade: ");
        int escolhaHabilidade = scanner.nextInt() - 1;
        scanner.nextLine();

        if (escolhaHabilidade < 0 || escolhaHabilidade >= habilidadesDisponiveis.size()) {
            System.out.println("❌ Habilidade inválida!");
            return;
        }

        Personagem personagem = personagens.get(escolhaPersonagem);
        Habilidade habilidade = habilidadesDisponiveis.get(escolhaHabilidade);

        // AGREGAÇÃO: Habilidade é passada para o Personagem
        jogador.ensinarHabilidade(personagem, habilidade);
    }

    private static void iniciarBatalha() {
        List<Personagem> personagens = jogador.getPersonagens();

        if (personagens.isEmpty()) {
            System.out.println("❌ Crie um personagem primeiro!");
            return;
        }

        System.out.println("\n=== INICIAR BATALHA ===");

        // Listar personagens
        for (int i = 0; i < personagens.size(); i++) {
            System.out.println((i + 1) + ". " + personagens.get(i));
        }
        System.out.print("Escolha o personagem: ");
        int escolha = scanner.nextInt() - 1;
        scanner.nextLine();

        if (escolha < 0 || escolha >= personagens.size()) {
            System.out.println("❌ Escolha inválida!");
            return;
        }

        Personagem personagem = personagens.get(escolha);
        Monstro monstro = new Monstro("Goblin", personagem.getNivel());

        System.out.println("\n⚔️ BATALHA: " + personagem.getNome() + " vs " + monstro.getNome() + " ⚔️");

        // BATALHA por turnos
        while (personagem.estaVivo() && monstro.estaVivo()) {
            // Turno do jogador
            System.out.println("\n--- SEU TURNO ---");
            System.out.println("1. Atacar");
            System.out.println("2. Habilidade Especial");
            System.out.println("3. Curar");
            System.out.print("Escolha: ");
            int acao = scanner.nextInt();
            scanner.nextLine();

            switch (acao) {
                case 1:
                    personagem.atacar(monstro);
                    break;
                case 2:
                    personagem.usarHabilidadeEspecial();
                    break;
                case 3:
                    personagem.curar(25);
                    break;
                default:
                    System.out.println("Ação inválida! Atacando...");
                    personagem.atacar(monstro);
            }

            // Turno do monstro (se ainda estiver vivo)
            if (monstro.estaVivo()) {
                System.out.println("\n--- TURNO DO MONSTRO ---");
                monstro.atacar(personagem);
            }

            // Status
            System.out.println("\n" + personagem);
            System.out.println(monstro);
        }

        // Resultado
        if (personagem.estaVivo()) {
            System.out.println("\n🎉 VITÓRIA! " + personagem.getNome() + " venceu!");
            personagem.subirNivel();
        } else {
            System.out.println("\n💀 DERROTA! " + monstro.getNome() + " foi muito forte...");
        }
    }

    private static void mostrarEstatisticas() {
        System.out.println("\n=== ESTATÍSTICAS ===");
        System.out.println(jogador);
        System.out.println("Total de Personagens: " + jogador.getPersonagens().size());

        if (jogador.getPersonagemPrincipal() != null) {
            System.out.println("Personagem Principal: " + jogador.getPersonagemPrincipal().getNome());
        }
    }
}