package modelo;

public class Habilidade {
    private String nome;
    private String descricao;
    private int custo;
    private TipoPersonagem tipoRequerido; // Tipo de personagem que pode usar esta habilidade

    public Habilidade(String nome, String descricao, int custo, TipoPersonagem tipoRequerido) {
        this.nome = nome;
        this.descricao = descricao;
        this.custo = custo;
        this.tipoRequerido = tipoRequerido;
    }

    // GETTERS
    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }
    public int getCusto() { return custo; }
    public TipoPersonagem getTipoRequerido() { return tipoRequerido; }

    @Override
    public String toString() {
        return nome + " [" + tipoRequerido.getNome() + "] - " + descricao + " (Custo: " + custo + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Habilidade that = (Habilidade) obj;
        return nome.equals(that.nome);
    }

    @Override
    public int hashCode() {
        return nome.hashCode();
    }
}