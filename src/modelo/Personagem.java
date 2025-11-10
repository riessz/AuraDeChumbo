package modelo;

import java.util.ArrayList;
import java.util.List;

public abstract class Personagem implements Atacavel {
    private String nome;
    private int nivel;
    private int vida;
    private int vidaMaxima;
    private int ataque;
    private int defesa;
    private TipoPersonagem tipo;

    // RELACIONAMENTO N:N com Habilidade
    private List<Habilidade> habilidades;

    // RELACIONAMENTO BIDIRECIONAL com Jogador
    private Jogador jogador;

    public Personagem(String nome, TipoPersonagem tipo, int vida, int ataque, int defesa) {
        this.nome = nome;
        this.tipo = tipo;
        this.vida = vida;
        this.vidaMaxima = vida;
        this.ataque = ataque;
        this.defesa = defesa;
        this.nivel = 1;
        this.habilidades = new ArrayList<>();
    }

    // IMPLEMENTAÇÃO DA INTERFACE Atacavel
    @Override
    public void atacar(Atacavel alvo) {
        System.out.println(nome + " ataca " + alvo.getNome() + "!");
        alvo.receberDano(ataque);
    }

    @Override
    public void receberDano(int dano) {
        int danoReal = Math.max(0, dano - defesa);
        vida -= danoReal;
        if (vida < 0) vida = 0;
        System.out.println(nome + " recebe " + danoReal + " de dano!");
    }

    @Override
    public boolean estaVivo() {
        return vida > 0;
    }

    @Override
    public String getNome() {
        return nome;
    }

    // MÉTODO ABSTRATO para polimorfismo
    public abstract void usarHabilidadeEspecial();

    // RELACIONAMENTO N:N - Personagem pode ter várias Habilidades
    public void aprenderHabilidade(Habilidade habilidade) {
        if (!habilidades.contains(habilidade)) {
            habilidades.add(habilidade);
            System.out.println("✅ " + nome + " aprendeu: " + habilidade.getNome());
        }
    }

    public List<Habilidade> getHabilidades() {
        return new ArrayList<>(habilidades);
    }

    // RELACIONAMENTO BIDIRECIONAL com Jogador
    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void subirNivel() {
        nivel++;
        vidaMaxima += 10;
        vida = vidaMaxima;
        ataque += 2;
        defesa += 1;
        System.out.println("🎉 " + nome + " subiu para o nível " + nivel + "!");
    }

    public void curar(int quantidade) {
        vida = Math.min(vidaMaxima, vida + quantidade);
        System.out.println("❤️ " + nome + " curou " + quantidade + " de vida!");
    }

    // GETTERS (encapsulamento)
    public int getNivel() { return nivel; }
    public int getVida() { return vida; }
    public int getVidaMaxima() { return vidaMaxima; }
    public int getAtaque() { return ataque; }
    public int getDefesa() { return defesa; }
    public TipoPersonagem getTipo() { return tipo; }

    @Override
    public String toString() {
        return nome + " [" + tipo.getNome() + "] Nível " + nivel + " - Vida: " + vida + "/" + vidaMaxima;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Personagem that = (Personagem) obj;
        return nome.equals(that.nome);
    }

    @Override
    public int hashCode() {
        return nome.hashCode();
    }
}