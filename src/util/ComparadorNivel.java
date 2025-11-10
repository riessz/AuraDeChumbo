package util;

import modelo.Personagem;
import java.util.Comparator;

public class ComparadorNivel implements Comparator<Personagem> {
    @Override
    public int compare(Personagem p1, Personagem p2) {
        // Ordena por nível (maior primeiro) e depois por nome
        int comparacaoNivel = Integer.compare(p2.getNivel(), p1.getNivel());
        if (comparacaoNivel != 0) {
            return comparacaoNivel;
        }
        return p1.getNome().compareTo(p2.getNome());
    }
}