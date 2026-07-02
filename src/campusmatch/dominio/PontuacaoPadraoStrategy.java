package campusmatch.dominio;

import java.util.List;
import java.util.Optional;

public class PontuacaoPadraoStrategy implements CriterioDeMatch {

    @Override
    public int calcularPontuacao(Professor professor, DemandaPPC demanda, List<InteresseAlunos> interesses) {
        int pontuacao = 0;

        pontuacao += professor.getCargaHorariaDisponivel();
        pontuacao += demanda.getPrioridade().getPeso() * 10;

        Optional<InteresseAlunos> interesse = buscarInteresseDaDisciplina(interesses, demanda.getDisciplina());
        if (interesse.isPresent()) {
            pontuacao += interesse.get().getQuantidadeInteressados();
            pontuacao += interesse.get().getPrioridade().getPeso() * 10;
        }

        return pontuacao;
    }

    private Optional<InteresseAlunos> buscarInteresseDaDisciplina(List<InteresseAlunos> interesses, Disciplina disciplina) {
        return interesses.stream()
                .filter(i -> i.getDisciplina().equals(disciplina))
                .findFirst();
    }
}