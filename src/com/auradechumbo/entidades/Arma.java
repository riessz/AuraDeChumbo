package com.auradechumbo.entidades;

/**
 * Classe abstrata base para todas as armas do jogo.
 * Define atributos comuns e o método abstrato atirar().
 */
public abstract class Arma {

    private String nome;
    private int dano;
    private double alcanceMaximo;
    private int nivelMinimo;
    private double velocidadeProjetil;

    public Arma(String nome, int dano, double alcanceMaximo, int nivelMinimo, double velocidadeProjetil) {
        // TODO: inicializar atributos
    }

    /**
     * Cria um projétil disparado a partir de uma posição e direção.
     * Implementação concreta nas subclasses.
     */
    public abstract Projetil atirar(double origemX, double origemY, double direcao);

    // Getters
    public String getNome() { return nome; }
    public int getDano() { return dano; }
    public double getAlcanceMaximo() { return alcanceMaximo; }
    public int getNivelMinimo() { return nivelMinimo; }
    public double getVelocidadeProjetil() { return velocidadeProjetil; }

}

