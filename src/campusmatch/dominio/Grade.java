package campusmatch.dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Grade {
    private List<Alocacao> alocacoes;

    public Grade() {
        this.alocacoes = new ArrayList<>();
    }

    public void adicionarAlocacao (Alocacao alocacao) {
        if (alocacao == null) {
            throw new IllegalArgumentException("A alocação não pode ser nula.");
        }

        this.alocacoes.add(alocacao);
    }

    

    public List<Alocacao> getAlocacoes() {
        return Collections.unmodifiableList(alocacoes);
    }
}