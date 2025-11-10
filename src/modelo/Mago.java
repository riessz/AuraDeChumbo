package modelo;

public class Mago extends Personagem {
    private int mana;

    public Mago(String nome) {
        super(nome, TipoPersonagem.MAGO, 80, 20, 8);
        this.mana = 100;
    }

    @Override
    public void usarHabilidadeEspecial() {
        if (mana >= 40) {
            System.out.println("🔮 " + getNome() + " lança BOLA DE FOGO! Dano triplicado!");
            mana -= 40;
        } else {
            System.out.println("❌ Mana insuficiente! Necessário: 40, Atual: " + mana);
        }
    }

    @Override
    public void atacar(Atacavel alvo) {
        System.out.println("✨ " + getNome() + " lança um feitiço!");
        super.atacar(alvo);
        mana = Math.min(100, mana + 5);
    }

    public int getMana() {
        return mana;
    }

    @Override
    public String toString() {
        return super.toString() + " - Mana: " + mana;
    }
}