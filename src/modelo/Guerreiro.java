package modelo;

// Tank corpo a corpo com sistema de fúria
public class Guerreiro extends Personagem {
    private int furia;

    public Guerreiro(String nome) {
        super(nome, TipoPersonagem.GUERREIRO, 120, 25, 15);  // tanque
        this.furia = 0;
    }

    @Override
    public void usarHabilidadeEspecial() {
        if (furia >= 30) {
            System.out.println("🔥 " + getNome() + " usa FÚRIA BERSERK! Prepare-se para o ataque devastador!");
            furia = 0;
        } else {
            System.out.println("❌ Fúria insuficiente! Necessário: 30, Atual: " + furia);
        }
    }
    
    @Override
    public void usarHabilidadeEspecial(Atacavel alvo) {
        if (furia >= 30) {
            System.out.println("🔥 " + getNome() + " usa FÚRIA BERSERK!");
            // Ataque com o triplo do dano
            int danoEspecial = getAtaque() * 3;
            alvo.receberDano(danoEspecial);
            System.out.println("💥 Dano devastador de " + danoEspecial + "!");
            furia = 0;
        } else {
            System.out.println("❌ Fúria insuficiente! Necessário: 30, Atual: " + furia);
        }
    }

    @Override
    public void atacar(Atacavel alvo) {
        int danoExtra = furia / 10;
        System.out.println("⚔️ " + getNome() + " ataca com espada!" +
                (danoExtra > 0 ? " Bônus de fúria: +" + danoExtra : ""));
        // Aplica dano base + bônus de fúria
        alvo.receberDano(getAtaque() + danoExtra);
        furia = Math.min(100, furia + 10);  // ganha fúria atacando (max 100)
    }

    public int getFuria() {
        return furia;
    }

    @Override
    public String toString() {
        return super.toString() + " - Fúria: " + furia;
    }
}