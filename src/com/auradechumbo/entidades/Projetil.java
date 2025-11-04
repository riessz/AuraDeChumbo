package com.auradechumbo.entidades;

/**
 * Representa um projétil disparado por uma arma.
 */
public class Projetil {

    private String id;
    private Ponto posicao;
    private double direcao; // em graus
    private double velocidade;
    private int dano;
    private double alcanceRestante;

    public Projetil(String id, double posicaoX, double posicaoY, double direcao,
                    double velocidade, int dano, double alcanceMaximo) {
        // TODO: inicializar atributos
    }

    /**
     * Move o projétil no tempo especificado.
     */
    public void mover(double tempoDelta) {
        // TODO
    }

    /**
     * Verifica se o projétil ainda está ativo (alcance > 0).
     */
    public boolean estaAtivo() {
        // TODO
        return false;
    }

    // Getters
    public Ponto getPosicao() { return posicao; }
    public double getPosicaoX() { return posicao != null ? posicao.getX() : 0; }
    public double getPosicaoY() { return posicao != null ? posicao.getY() : 0; }
    public int getDano() { return dano; }
}
