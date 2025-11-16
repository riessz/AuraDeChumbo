package app;

import modelo.*;
import util.ComparadorNivel;
import java.util.*;

// Sistema de RPG interativo
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

    // Inicializa habilidades por classe
    private static void inicializarHabilidades() {
        // Guerreiro
        habilidadesDisponiveis.add(new Habilidade("Golpe Devastador", "Ataque poderoso corpo a corpo", 20, TipoPersonagem.GUERREIRO));
        habilidadesDisponiveis.add(new Habilidade("Investida Brutal", "Carrega contra o inimigo", 25, TipoPersonagem.GUERREIRO));
        habilidadesDisponiveis.add(new Habilidade("Escudo de Ferro", "Aumenta a defesa temporariamente", 30, TipoPersonagem.GUERREIRO));
        
        // Mago
        habilidadesDisponiveis.add(new Habilidade("Raio Arcano", "Dispara um raio mágico", 20, TipoPersonagem.MAGO));
        habilidadesDisponiveis.add(new Habilidade("Tempestade de Gelo", "Congela e danifica inimigos", 35, TipoPersonagem.MAGO));
        habilidadesDisponiveis.add(new Habilidade("Barreira Mágica", "Cria uma barreira de proteção", 30, TipoPersonagem.MAGO));
        
        // Arqueiro
        habilidadesDisponiveis.add(new Habilidade("Flecha Explosiva", "Flecha que explode ao impacto", 25, TipoPersonagem.ARQUEIRO));
        habilidadesDisponiveis.add(new Habilidade("Chuva de Flechas", "Dispara múltiplas flechas", 30, TipoPersonagem.ARQUEIRO));
        habilidadesDisponiveis.add(new Habilidade("Armadilha", "Coloca uma armadilha no campo", 20, TipoPersonagem.ARQUEIRO));
    }

    private static void inicializarJogo() {
        System.out.print("Digite seu nome: ");
        String nomeJogador = scanner.nextLine();
        jogador = new Jogador(nomeJogador);
        System.out.println("✅ Jogador " + jogador.getNome() + " criado!");
    }

    private static void menuPrincipal() {
        while (true) {
            if (jogador != null && jogador.estaMorto()) {
                System.out.println("\nJogo encerrado. Todos os seus personagens morreram.");
                return;
            }
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Criar Personagem");
            System.out.println("2. Listar Personagens");
            System.out.println("3. Ensinar Habilidade");
            System.out.println("4. Iniciar Batalha");
            System.out.println("5. Estatísticas");
            System.out.println("6. Sair");
            System.out.print("Escolha: ");

            int opcao;
            try {
                opcao = scanner.nextInt();
                scanner.nextLine(); // limpa buffer
            } catch (InputMismatchException e) {
                System.out.println("❌ Por favor, digite um número válido!");
                scanner.nextLine();
                continue;
            }

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
        String nome = scanner.nextLine().trim();
        
        // Valida nome vazio
        if (nome.isEmpty()) {
            System.out.println("❌ O nome não pode estar vazio!");
            return;
        }

        System.out.print("Tipo (GUERREIRO/MAGO/ARQUEIRO): ");
        String tipoStr = scanner.nextLine().toUpperCase().trim();

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

        // Ordena por nível
        List<Personagem> ordenados = new ArrayList<>(personagens);
        Collections.sort(ordenados, new ComparadorNivel());

        System.out.println("\n=== SEUS PERSONAGENS ===");
        for (int i = 0; i < ordenados.size(); i++) {
            System.out.println((i + 1) + ". " + ordenados.get(i));

            // Mostra habilidades que conhece
            List<Habilidade> habilidades = ordenados.get(i).getHabilidades();
            if (!habilidades.isEmpty()) {
                System.out.println("   Habilidades: " + habilidades);
            }
        }
    }

    /**
     * Ensina uma habilidade a um personagem
     * Valida entrada do usuário e filtra habilidades compatíveis com a classe
     * Previne aprendizado de habilidades duplicadas
     */
    private static void ensinarHabilidade() {
        List<Personagem> personagens = jogador.getPersonagens();

        if (personagens.isEmpty()) {
            System.out.println("❌ Crie um personagem primeiro!");
            return;
        }

        System.out.println("\n=== ENSINAR HABILIDADE ===");

        // Lista personagens
        for (int i = 0; i < personagens.size(); i++) {
            System.out.println((i + 1) + ". " + personagens.get(i).getNome() + " - " + personagens.get(i).getTipo().getNome());
        }
        System.out.print("Escolha o personagem: ");
        
        int escolhaPersonagem;
        try {
            escolhaPersonagem = scanner.nextInt() - 1;
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("❌ Por favor, digite um número válido!");
            scanner.nextLine();
            return;
        }

        if (escolhaPersonagem < 0 || escolhaPersonagem >= personagens.size()) {
            System.out.println("❌ Personagem inválido!");
            return;
        }

        Personagem personagem = personagens.get(escolhaPersonagem);
        
        // Filtra habilidades da classe do personagem
        List<Habilidade> habilidadesCompativeis = new ArrayList<>();
        for (Habilidade hab : habilidadesDisponiveis) {
            if (hab.getTipoRequerido() == personagem.getTipo()) {
                habilidadesCompativeis.add(hab);
            }
        }
        
        if (habilidadesCompativeis.isEmpty()) {
            System.out.println("❌ Nenhuma habilidade disponível para " + personagem.getTipo().getNome() + "!");
            return;
        }

        // Lista habilidades compatíveis
        System.out.println("\nHabilidades disponíveis para " + personagem.getTipo().getNome() + ":");
        for (int i = 0; i < habilidadesCompativeis.size(); i++) {
            System.out.println((i + 1) + ". " + habilidadesCompativeis.get(i));
        }
        System.out.print("Escolha a habilidade: ");
        
        int escolhaHabilidade;
        try {
            escolhaHabilidade = scanner.nextInt() - 1;
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("❌ Por favor, digite um número válido!");
            scanner.nextLine();
            return;
        }

        if (escolhaHabilidade < 0 || escolhaHabilidade >= habilidadesCompativeis.size()) {
            System.out.println("❌ Habilidade inválida!");
            return;
        }

        Habilidade habilidade = habilidadesCompativeis.get(escolhaHabilidade);
        jogador.ensinarHabilidade(personagem, habilidade);
    }

    /**
     * Inicia uma batalha por turnos entre o personagem escolhido e um monstro
     * O monstro é gerado com nível igual ao do personagem
     * Ao vencer, o personagem ganha XP baseado no nível do monstro
     */
    private static void iniciarBatalha() {
        List<Personagem> personagens = jogador.getPersonagens();

        if (personagens.isEmpty()) {
            System.out.println("❌ Crie um personagem primeiro!");
            return;
        }

        System.out.println("\n=== INICIAR BATALHA ===");

        // Lista personagens
        for (int i = 0; i < personagens.size(); i++) {
            System.out.println((i + 1) + ". " + personagens.get(i));
        }
        System.out.print("Escolha o personagem: ");
        
        int escolha;
        try {
            escolha = scanner.nextInt() - 1;
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("❌ Por favor, digite um número válido!");
            scanner.nextLine();
            return;
        }

        if (escolha < 0 || escolha >= personagens.size()) {
            System.out.println("❌ Escolha inválida!");
            return;
        }

        Personagem personagem = personagens.get(escolha);
        Monstro monstro = new Monstro("Goblin", personagem.getNivel());  // nível do monstro = nível do personagem

        System.out.println("\n⚔️ BATALHA: " + personagem.getNome() + " vs " + monstro.getNome() + " ⚔️");

        // Sistema de turnos
        while (personagem.estaVivo() && monstro.estaVivo()) {
            System.out.println("\n--- SEU TURNO ---");
            System.out.println("1. Atacar");
            System.out.println("2. Habilidade Especial");
            System.out.println("3. Curar (30 HP)");
            System.out.print("Escolha: ");
            
            int acao;
            try {
                acao = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("❌ Entrada inválida! Atacando automaticamente...");
                scanner.nextLine();
                acao = 1;
            }

            switch (acao) {
                case 1:
                    personagem.atacar(monstro);
                    break;
                case 2:
                    personagem.usarHabilidadeEspecial(monstro);
                    break;
                case 3:
                    personagem.curar(30);
                    break;
                default:
                    System.out.println("Ação inválida! Atacando...");
                    personagem.atacar(monstro);
            }

            // Checa se o monstro morreu
            if (!monstro.estaVivo()) {
                break;
            }

            // Checa se o personagem morreu e se o jogador morreu
            if (!personagem.estaVivo()) {
                jogador.checarMorte();
                if (jogador.estaMorto()) {
                    return;
                }
            }

            // Turno do monstro
            System.out.println("\n--- TURNO DO MONSTRO ---");
            monstro.atacar(personagem);

            // Status
            System.out.println("\n--- STATUS ---");
            System.out.println(personagem);
            System.out.println(monstro);
        }

        // Fim da batalha
        if (personagem.estaVivo()) {
            System.out.println("\n🎉 VITÓRIA! " + personagem.getNome() + " venceu!");
            int xpGanho = 50 + (monstro.getNivel() * 20);  // XP baseado no nível
            System.out.println("💰 Ganhou " + xpGanho + " XP!");
            personagem.ganharExperiencia(xpGanho);
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