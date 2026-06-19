package campusmatch.dominio;

import java.util.ArrayList;
import java.util.List;

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
     * Gets alocacoes.
     *
     * @return the alocacoes
     */
    public List<Alocacao> getAlocacoes() {
        return alocacoes;
    }
}