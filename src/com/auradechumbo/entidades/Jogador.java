package com.auradechumbo.entidades;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa o jogador humano do jogo Aura de Chumbo.
 * Possui posição, experiência, nível e arsenal de armas.
 */
public class Jogador {

    private String id;
    private String nome;
    private Ponto posicao;
    private int xp;
    private int nivel;
    private List<Arma> armasEquipadas;
    private Arma armaAtual;

    /**
     * Construtor do jogador.
     */
    public Jogador(String id, String nome, double posicaoX, double posicaoY) {
        // TODO: inicializar atributos e criar Ponto
    }

    /**
     * Move o jogador numa direção e distância.
     */
    public void mover(double direcao, double distancia) {
        // TODO
    }

    /**
     * Ganha XP ao acertar um alvo.
     */
    public void ganharXp(int valor) {
        // TODO
    }

    /**
     * Sobe de nível caso tenha XP suficiente.
     */
    public void subirDeNivel() {
        // TODO
    }

    /**
     * Equipa uma arma se o nível for suficiente.
     */
    public boolean equiparArma(Arma arma) {
        // TODO
        return false;
    }

    /**
     * Dispara um projétil com base na arma atual.
     */
    public Projetil atirar(double direcao) {
        // TODO
        return null;
    }

    // Getters
    public Ponto getPosicao() { return posicao; }
    public double getPosicaoX() { return posicao != null ? posicao.getX() : 0; }
    public double getPosicaoY() { return posicao != null ? posicao.getY() : 0; }
    public int getNivel() { return nivel; }
    public int getXp() { return xp; }
    public Arma getArmaAtual() { return armaAtual; }
    public List<Arma> getArmasEquipadas() { return new ArrayList<>(armasEquipadas); }

}

