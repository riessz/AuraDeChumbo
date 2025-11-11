package app;

import modelo.*;
import util.ComparadorNivel;
import java.util.*;

public class AppRoteiro {
    public static void main(String[] args) {
        System.out.println("🎮 AURA DE CHUMBO - DEMONSTRAÇÃO 🎮");
        System.out.println("====================================");

        // SEQUÊNCIA FIXA para demonstração
        demonstrarCriacao();
        demonstrarPolimorfismo();
        demonstrarRelacionamentos();
        demonstrarCollections();
        demonstrarBatalha();
    }

    private static void demonstrarCriacao() {
        System.out.println("\n1. DEMONSTRAÇÃO: CRIAÇÃO DE OBJETOS");

        Jogador jogador = new Jogador("João");

        // Criar personagens de diferentes tipos
        Personagem guerreiro = jogador.criarPersonagem("Thor", TipoPersonagem.GUERREIRO);
        Personagem mago = jogador.criarPersonagem("Merlin", TipoPersonagem.MAGO);
        Personagem arqueiro = jogador.criarPersonagem("Legolas", TipoPersonagem.ARQUEIRO);

        System.out.println("Jogador: " + jogador.getNome());
        System.out.println("Personagens criados: " + jogador.getPersonagens().size());
    }

    private static void demonstrarPolimorfismo() {
        System.out.println("\n2. DEMONSTRAÇÃO: POLIMORFISMO");

        // Coleção do tipo superclasse/interface contendo diferentes subclasses
        List<Atacavel> atacantes = new ArrayList<>();
        atacantes.add(new Guerreiro("Aragorn"));
        atacantes.add(new Mago("Gandalf"));
        atacantes.add(new Arqueiro("Oliver"));
        atacantes.add(new Monstro("Dragão", 3));

        Monstro alvo = new Monstro("Treinamento", 1);

        System.out.println("Chamadas polimórficas - cada um ataca diferente:");
        for (Atacavel atacante : atacantes) {
            atacante.atacar(alvo);
            alvo = new Monstro("Treinamento", 1); // Reset
        }

        // Sobrescrita de métodos
        System.out.println("\nSobrescrita - habilidades especiais:");
        List<Personagem> personagens = new ArrayList<>();
        personagens.add(new Guerreiro("Conan"));
        personagens.add(new Mago("Saruman"));

        for (Personagem p : personagens) {
            p.usarHabilidadeEspecial(); // Comportamento específico
        }
    }

    private static void demonstrarRelacionamentos() {
        System.out.println("\n3. DEMONSTRAÇÃO: RELACIONAMENTOS");

        Jogador jogador = new Jogador("Maria");
        Personagem personagem = jogador.criarPersonagem("Herói", TipoPersonagem.GUERREIRO);

        // RELACIONAMENTO 1:1 e BIDIRECIONAL
        System.out.println("Bidirecional - Jogador ↔ Personagem:");
        System.out.println("Jogador do personagem: " + personagem.getJogador().getNome());
        System.out.println("Personagem do jogador: " + jogador.getPersonagemPrincipal().getNome());

        // RELACIONAMENTO N:N
        Habilidade habilidade1 = new Habilidade("Corte Duplo", "Ataque duplo", 20, TipoPersonagem.GUERREIRO);
        Habilidade habilidade2 = new Habilidade("Cura", "Recupera vida", 25, TipoPersonagem.GUERREIRO);

        personagem.aprenderHabilidade(habilidade1);
        personagem.aprenderHabilidade(habilidade2);

        System.out.println("\nRelação N:N - Personagem ↔ Habilidade:");
        System.out.println("Habilidades de " + personagem.getNome() + ": " + personagem.getHabilidades());
    }

    private static void demonstrarCollections() {
        System.out.println("\n4. DEMONSTRAÇÃO: COLLECTIONS");

        List<Personagem> personagens = new ArrayList<>();
        personagens.add(new Guerreiro("Bob"));
        personagens.add(new Mago("Alice"));
        personagens.add(new Arqueiro("Carlos"));

        // Verificação de duplicidade
        Personagem duplicado = new Guerreiro("Bob");
        if (personagens.contains(duplicado)) {
            System.out.println("✅ Verificação de duplicidade funcionando!");
        }

        // Simular níveis diferentes
        personagens.get(0).subirNivel(); // Bob nível 2
        personagens.get(0).subirNivel(); // Bob nível 3
        personagens.get(1).subirNivel(); // Alice nível 2

        System.out.println("Antes da ordenação:");
        for (Personagem p : personagens) {
            System.out.println(" - " + p);
        }

        // ORDENAÇÃO
        Collections.sort(personagens, new ComparadorNivel());

        System.out.println("\nApós ordenação por nível:");
        for (Personagem p : personagens) {
            System.out.println(" - " + p);
        }
    }

    private static void demonstrarBatalha() {
        System.out.println("\n5. DEMONSTRAÇÃO: BATALHA E PROGRESSÃO");

        Personagem guerreiro = new Guerreiro("Herói");
        Monstro monstro = new Monstro("Goblin", 1);

        System.out.println("Batalha demonstrativa:");
        System.out.println(guerreiro);
        System.out.println(monstro);

        // Sequência determinística
        guerreiro.atacar(monstro);
        monstro.atacar(guerreiro);
        guerreiro.atacar(monstro);

        if (!monstro.estaVivo()) {
            System.out.println("\n⭐ Progressão de nível:");
            System.out.println("Antes: " + guerreiro);
            guerreiro.subirNivel();
            System.out.println("Depois: " + guerreiro);
        }
    }
}