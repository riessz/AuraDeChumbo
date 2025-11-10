package modelo;

import java.util.ArrayList;
import java.util.List;

public class Jogador {
    private String nome;
    private List<Personagem> personagens;
    private Personagem personagemPrincipal;

    public Jogador(String nome) {
        this.nome = nome;
        this.personagens = new ArrayList<>();
    }

    // Cria um personagem novo
    public Personagem criarPersonagem(String nome, TipoPersonagem tipo) {
        // Checa se já existe um com esse nome
        for (Personagem p : personagens) {
            if (p.getNome().equals(nome)) {
                System.out.println("❌ Já existe um personagem com este nome!");
                return null;
            }
        }

        Personagem personagem;
        switch (tipo) {
            case GUERREIRO:
                personagem = new Guerreiro(nome);
                break;
            case MAGO:
                personagem = new Mago(nome);
                break;
            case ARQUEIRO:
                personagem = new Arqueiro(nome);
                break;
            default:
                throw new IllegalArgumentException("Tipo inválido");
        }

        // Liga o personagem ao jogador
        personagem.setJogador(this);
        personagens.add(personagem);

        if (personagemPrincipal == null) {
            personagemPrincipal = personagem;
        }

        System.out.println("✅ Personagem " + nome + " criado com sucesso!");
        return personagem;
    }

    // Ensina uma habilidade pro personagem
    public void ensinarHabilidade(Personagem personagem, Habilidade habilidade) {
        if (personagens.contains(personagem)) {
            personagem.aprenderHabilidade(habilidade);
        } else {
            System.out.println("❌ Este personagem não pertence ao jogador!");
        }
    }

    public void setPersonagemPrincipal(Personagem personagem) {
        if (personagens.contains(personagem)) {
            this.personagemPrincipal = personagem;
            System.out.println("⭐ " + personagem.getNome() + " é agora o personagem principal!");
        }
    }

    public String getNome() { return nome; }
    public List<Personagem> getPersonagens() { return new ArrayList<>(personagens); }
    public Personagem getPersonagemPrincipal() { return personagemPrincipal; }

    @Override
    public String toString() {
        return "Jogador: " + nome + " | Personagens: " + personagens.size();
    }
}