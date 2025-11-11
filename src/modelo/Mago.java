package modelo;

// Conjurador com sistema de mana
public class Mago extends Personagem {
    private int mana;

    public Mago(String nome) {
        super(nome, TipoPersonagem.MAGO, 80, 20, 8);  // vidro canhão
        this.mana = 100;
    }

    @Override
    public void usarHabilidadeEspecial() {
        if (mana >= 40) {
            System.out.println("🔮 " + getNome() + " prepara uma BOLA DE FOGO! Mire com cuidado!");
            mana -= 40;
        } else {
            System.out.println("❌ Mana insuficiente! Necessário: 40, Atual: " + mana);
        }
    }
    
    @Override
    public void usarHabilidadeEspecial(Atacavel alvo) {
        if (mana >= 40) {
            System.out.println("🔮 " + getNome() + " lança BOLA DE FOGO!");
            int danoMagico = getAtaque() * 2 + 10;
            
            // Dano mágico ignora defesa de personagens
            if (alvo instanceof Personagem) {
                ((Personagem) alvo).receberDanoDireto(danoMagico);
            } else {
                alvo.receberDano(danoMagico);
            }
            
            System.out.println("🔥 Explosão mágica causa " + danoMagico + " de dano!");
            mana -= 40;
        } else {
            System.out.println("❌ Mana insuficiente! Necessário: 40, Atual: " + mana);
        }
    }

    @Override
    public void atacar(Atacavel alvo) {
        System.out.println("✨ " + getNome() + " lança um feitiço!");
        super.atacar(alvo);
        mana = Math.min(100, mana + 5);  // regenera mana
    }

    public int getMana() {
        return mana;
    }

    @Override
    public String toString() {
        return super.toString() + " - Mana: " + mana;
    }
}