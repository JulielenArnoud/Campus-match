package campusmatch.dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

/**
 * The type Grade.
 */
public class Grade {
    private List<Alocacao> alocacoes;

    /**
     * Instantiates a new Grade.
     */
    public Grade() {

        this.alocacoes = new ArrayList<>();
    }

    /**
     * Adiciona uma nova alocacao na grade.
     *
     * @param alocacao the alocacao
     */
    public void adicionarAlocacao (Alocacao alocacao) {
        if (alocacao == null) {
            throw new IllegalArgumentException("A alocação não pode ser nula.");
        }

        this.alocacoes.add(alocacao);
    }

    /**
     * Gets alocacoes.
     *
     * @return the alocacoes
     */
    public List<Alocacao> getAlocacoes() {
        return Collections.unmodifiableList(alocacoes);
    }
}