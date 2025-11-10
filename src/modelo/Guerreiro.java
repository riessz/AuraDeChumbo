package modelo;

public class Guerreiro extends Personagem {
    private int furia;

    public Guerreiro(String nome) {
        super(nome, TipoPersonagem.GUERREIRO, 120, 25, 15);
        this.furia = 0;
    }

    @Override
    public void usarHabilidadeEspecial() {
        if (furia >= 30) {
            System.out.println("🔥 " + getNome() + " usa FÚRIA BERSERK! Ataque duplicado!");
            furia = 0;
            // Em uma versão mais complexa, isso afetaria o próximo ataque
        } else {
            System.out.println("❌ Fúria insuficiente! Necessário: 30, Atual: " + furia);
        }
    }

    @Override
    public void atacar(Atacavel alvo) {
        // SOBRECARGA de comportamento
        int danoExtra = furia / 10;
        System.out.println("⚔️ " + getNome() + " ataca com espada!" +
                (danoExtra > 0 ? " Bônus de fúria: +" + danoExtra : ""));
        super.atacar(alvo);
        furia += 10;
    }

    public int getFuria() {
        return furia;
    }

    @Override
    public String toString() {
        return super.toString() + " - Fúria: " + furia;
    }
}