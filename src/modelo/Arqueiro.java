package modelo;

// Atirador de longo alcance com sistema de precisão
public class Arqueiro extends Personagem {
    private int precisao;

    public Arqueiro(String nome) {
        super(nome, TipoPersonagem.ARQUEIRO, 90, 22, 10);  // equilibrado
        this.precisao = 100;
    }

    @Override
    public void usarHabilidadeEspecial() {
        System.out.println("🎯 " + getNome() + " usa FOCO TOTAL! Precisão maximizada para o próximo ataque!");
        precisao = 100;
    }
    
    @Override
    public void usarHabilidadeEspecial(Atacavel alvo) {
        System.out.println("🎯 " + getNome() + " usa TIRO PERFURADOR!");
        // Ataque de precisão que sempre acerta criticamente
        int danoCritico = (int)(getAtaque() * 2.5);
        alvo.receberDano(danoCritico);
        System.out.println("🏹 Flecha perfurante causa " + danoCritico + " de dano!");
        precisao = Math.max(50, precisao - 20);
    }

    @Override
    public void atacar(Atacavel alvo) {
        boolean critico = precisao > 80;
        System.out.println("🏹 " + getNome() + " dispara uma flecha!" +
                (critico ? " ACERTO CRÍTICO!" : ""));
        // Aplica dano crítico se precisão > 80, senão dano normal
        int dano = critico ? (int)(getAtaque() * 1.5) : getAtaque();
        alvo.receberDano(dano);
        precisao = Math.max(50, precisao - 10);  // perde precisão ao atirar
    }

    public int getPrecisao() {
        return precisao;
    }

    @Override
    public String toString() {
        return super.toString() + " - Precisão: " + precisao + "%";
    }
}