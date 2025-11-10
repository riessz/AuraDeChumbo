package modelo;

public class Monstro implements Atacavel {
    private String nome;
    private int vida;
    private int ataque;
    private int nivel;

    public Monstro(String nome, int nivel) {
        this.nome = nome;
        this.nivel = nivel;
        this.vida = 50 + (nivel * 15);
        this.ataque = 12 + (nivel * 3);
    }

    // IMPLEMENTAÇÃO DA INTERFACE Atacavel
    @Override
    public void atacar(Atacavel alvo) {
        System.out.println("👹 " + nome + " ataca " + alvo.getNome() + "!");
        alvo.receberDano(ataque);
    }

    @Override
    public void receberDano(int dano) {
        vida -= dano;
        if (vida < 0) vida = 0;
        System.out.println("💥 " + nome + " recebe " + dano + " de dano!");
    }

    @Override
    public boolean estaVivo() {
        return vida > 0;
    }

    @Override
    public String getNome() {
        return nome;
    }

    // GETTERS
    public int getVida() { return vida; }
    public int getAtaque() { return ataque; }
    public int getNivel() { return nivel; }

    @Override
    public String toString() {
        return nome + " [Monstro] Nível " + nivel + " - Vida: " + vida;
    }
}