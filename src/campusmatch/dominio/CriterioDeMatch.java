package campusmatch.dominio;

import java.util.List;

public interface CriterioDeMatch {
    int calcularPontuacao(Professor professor, DemandaPPC demanda, List<InteresseAlunos> interesses);
}