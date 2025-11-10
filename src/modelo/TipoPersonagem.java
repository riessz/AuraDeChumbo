package modelo;

public enum TipoPersonagem {
    GUERREIRO("Guerreiro"),
    MAGO("Mago"),
    ARQUEIRO("Arqueiro");

    private String nome;

    TipoPersonagem(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}