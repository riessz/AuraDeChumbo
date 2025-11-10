package modelo;

public interface Atacavel {
    void atacar(Atacavel alvo);
    void receberDano(int dano);
    boolean estaVivo();
    String getNome();
}