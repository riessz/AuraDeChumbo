package modelo;

public class Arqueiro extends Personagem {
    private int precisao;

    public Arqueiro(String nome) {
        super(nome, TipoPersonagem.ARQUEIRO, 90, 22, 10);
        this.precisao = 100;
    }

    @Override
    public void usarHabilidadeEspecial() {
        System.out.println("🎯 " + getNome() + " usa FOCO TOTAL! Precisão maximizada!");
        precisao = 100;
    }

    @Override
    public void atacar(Atacavel alvo) {
        boolean critico = precisao > 80;
        System.out.println("🏹 " + getNome() + " dispara uma flecha!" +
                (critico ? " ACERTO CRÍTICO!" : ""));
        super.atacar(alvo);
        precisao = Math.max(50, precisao - 10);
    }

    public int getPrecisao() {
        return precisao;
    }

    @Override
    public String toString() {
        return super.toString() + " - Precisão: " + precisao + "%";
    }
}