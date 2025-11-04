package com.auradechumbo.entidades;

/**
 * Representa um alvo que pode ser atingido no jogo.
 * Pode ser estático ou móvel.
 */
public class Alvo {

    private String id;
    private Ponto posicao;
    private int pontosXP;
    private boolean atingido;
    private boolean movel;
    private double velocidade;
    private double direcao;
    private int resistencia;

    // Construtor para alvo estático
    public Alvo(String id, double posicaoX, double posicaoY, int pontosXP) {
        // TODO
    }

    // Construtor para alvo móvel
    public Alvo(String id, double posicaoX, double posicaoY, int pontosXP, double velocidade, double direcao) {
        // TODO
    }

    // Construtor completo
    public Alvo(String id, double posicaoX, double posicaoY, int pontosXP,
                boolean movel, double velocidade, double direcao, int resistencia) {
        // TODO
    }

    /**
     * Aplica o dano de um projétil.
     */
    public void serAtingido(Projetil projetil) {
        // TODO
    }

    /**
     * Move o alvo se for móvel.
     */
    public void mover(double tempoDelta) {
        // TODO
    }

    // Getters
    public Ponto getPosicao() { return posicao; }
    public double getPosicaoX() { return posicao != null ? posicao.getX() : 0; }
    public double getPosicaoY() { return posicao != null ? posicao.getY() : 0; }
    public int getPontosXP() { return pontosXP; }
    public boolean isAtingido() { return atingido; }
    public boolean isMovel() { return movel; }
}

