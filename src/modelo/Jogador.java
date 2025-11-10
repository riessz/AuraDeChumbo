package modelo;

import java.util.ArrayList;
import java.util.List;

public class Jogador {
    private String nome;

    // RELACIONAMENTO 1:N com Personagem (COMPOSIÇÃO)
    private List<Personagem> personagens;

    // RELACIONAMENTO 1:1 com Personagem principal
    private Personagem personagemPrincipal;

    public Jogador(String nome) {
        this.nome = nome;
        this.personagens = new ArrayList<>();
    }

    // COMPOSIÇÃO: Personagem é criado pelo Jogador
    public Personagem criarPersonagem(String nome, TipoPersonagem tipo) {
        // Verificação de duplicidade
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

        // BIDIRECIONAL: Personagem conhece Jogador e vice-versa
        personagem.setJogador(this);
        personagens.add(personagem);

        if (personagemPrincipal == null) {
            personagemPrincipal = personagem;
        }

        System.out.println("✅ Personagem " + nome + " criado com sucesso!");
        return personagem;
    }

    // AGREGAÇÃO: Habilidade existe independentemente
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

    // GETTERS
    public String getNome() { return nome; }
    public List<Personagem> getPersonagens() { return new ArrayList<>(personagens); }
    public Personagem getPersonagemPrincipal() { return personagemPrincipal; }

    @Override
    public String toString() {
        return "Jogador: " + nome + " | Personagens: " + personagens.size();
    }
}