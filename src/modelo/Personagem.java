package modelo;

import java.util.ArrayList;
import java.util.List;

// Base para todos os personagens jogáveis
public abstract class Personagem implements Atacavel {
    private String nome;
    private int nivel;
    private int vida;
    private int vidaMaxima;
    private int ataque;
    private int defesa;
    private TipoPersonagem tipo;
    private int experiencia;
    private int experienciaParaProximoNivel;

    private List<Habilidade> habilidades;
    private Jogador jogador;

    public Personagem(String nome, TipoPersonagem tipo, int vida, int ataque, int defesa) {
        this.nome = nome;
        this.tipo = tipo;
        this.vida = vida;
        this.vidaMaxima = vida;
        this.ataque = ataque;
        this.defesa = defesa;
        this.nivel = 1;
        this.experiencia = 0;
        this.experienciaParaProximoNivel = 100;
        this.habilidades = new ArrayList<>();
    }

    @Override
    public void atacar(Atacavel alvo) {
        System.out.println(nome + " ataca " + alvo.getNome() + "!");
        alvo.receberDano(ataque);
    }

    @Override
    public void receberDano(int dano) {
        int danoReal = Math.max(0, dano - defesa);  // defesa reduz dano
        vida -= danoReal;
        if (vida < 0) vida = 0;
        System.out.println(nome + " recebe " + danoReal + " de dano!");
    }
    
    // Recebe dano direto sem aplicar defesa (para ataques mágicos)
    public void receberDanoDireto(int dano) {
        vida -= dano;
        if (vida < 0) vida = 0;
        System.out.println(nome + " recebe " + dano + " de dano mágico!");
    }

    @Override
    public boolean estaVivo() {
        return vida > 0;
    }

    @Override
    public String getNome() {
        return nome;
    }

    // Cada classe tem sua própria habilidade especial
    public abstract void usarHabilidadeEspecial();
    public abstract void usarHabilidadeEspecial(Atacavel alvo);

    // Aprender novas habilidades
    public void aprenderHabilidade(Habilidade habilidade) {
        // Checa se a habilidade é da classe certa
        if (habilidade.getTipoRequerido() != this.tipo) {
            System.out.println("❌ " + nome + " não pode aprender " + habilidade.getNome() + 
                             "! Esta habilidade é exclusiva para " + habilidade.getTipoRequerido().getNome() + "s.");
            return;
        }
        
        // Checa se já aprendeu antes
        if (habilidades.contains(habilidade)) {
            System.out.println("❌ " + nome + " já conhece a habilidade: " + habilidade.getNome() + "!");
            return;
        }
        
        habilidades.add(habilidade);
        System.out.println("✅ " + nome + " aprendeu: " + habilidade.getNome());
    }

    public List<Habilidade> getHabilidades() {
        return new ArrayList<>(habilidades);
    }

    // Relacionamento com jogador
    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void subirNivel() {
        nivel++;
        vidaMaxima += 10;
        vida = vidaMaxima; // cura completa ao upar
        ataque += 2;
        defesa += 1;
        experienciaParaProximoNivel = nivel * 100;  // XP necessário aumenta
        System.out.println("🎉 " + nome + " subiu para o nível " + nivel + "!");
        System.out.println("📊 Novos atributos - Vida: " + vidaMaxima + " | Ataque: " + ataque + " | Defesa: " + defesa);
    }
    
    // Sistema de XP e progressão
    public void ganharExperiencia(int xp) {
        experiencia += xp;
        System.out.println("✨ " + nome + " ganhou " + xp + " XP! (" + experiencia + "/" + experienciaParaProximoNivel + ")");
        
        // Checa se ganhou nível (pode upar mais de um de vez)
        while (experiencia >= experienciaParaProximoNivel) {
            experiencia -= experienciaParaProximoNivel;
            subirNivel();
        }
    }

    public void curar(int quantidade) {
        if (vida >= vidaMaxima) {
            System.out.println("❤️ " + nome + " já está com vida máxima! (" + vida + "/" + vidaMaxima + ")");
            return;
        }
        
        int vidaAntes = vida;
        vida = Math.min(vidaMaxima, vida + quantidade);
        int curaReal = vida - vidaAntes;
        System.out.println("❤️ " + nome + " curou " + curaReal + " de vida! (" + vida + "/" + vidaMaxima + ")");
    }

    public int getNivel() { return nivel; }
    public int getVida() { return vida; }
    public int getVidaMaxima() { return vidaMaxima; }
    public int getAtaque() { return ataque; }
    public int getDefesa() { return defesa; }
    public TipoPersonagem getTipo() { return tipo; }
    public int getExperiencia() { return experiencia; }
    public int getExperienciaParaProximoNivel() { return experienciaParaProximoNivel; }

    @Override
    public String toString() {
        return nome + " [" + tipo.getNome() + "] Nível " + nivel + 
               " - Vida: " + vida + "/" + vidaMaxima + 
               " - XP: " + experiencia + "/" + experienciaParaProximoNivel;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Personagem that = (Personagem) obj;
        return nome.equals(that.nome);
    }

    @Override
    public int hashCode() {
        return nome.hashCode();
    }
}