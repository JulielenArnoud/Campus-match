package campusmatch.dominio;

import campusmatch.dominio.excecoes.ConflitoDeHorarioException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Grade {
    private final List<Alocacao> alocacoes;

    public Grade() {
        this.alocacoes = new ArrayList<>();
    }

    /**
     * Adiciona uma nova alocação na grade, validando conflitos antes.
     * Fail-fast: se houver conflito, a alocação NÃO entra na grade —
     * a exceção é lançada antes de qualquer alteração de estado.
     *
     * @throws ConflitoDeHorarioException se o professor ou a turma já tiverem outra alocação no mesmo horário
     *
     */
    public void adicionarAlocacao(Alocacao alocacao) throws ConflitoDeHorarioException {
        if (alocacao == null) {
            throw new IllegalArgumentException("A alocação não pode ser nula.");
        }

        boolean professorOcupado = alocacoes.stream()
                .anyMatch(a -> a.getProfessor().equals(alocacao.getProfessor())
                        && a.getHorario().equals(alocacao.getHorario()));

        if (professorOcupado) {
            throw new ConflitoDeHorarioException(
                    "o professor " + alocacao.getProfessor().getNome() + " já está alocado em outra disciplina",
                    alocacao.getHorario().getDia(),
                    alocacao.getHorario().getPeriodo()
            );
        }

        boolean turmaOcupada = alocacoes.stream()
                .anyMatch(a -> a.getHorario().equals(alocacao.getHorario()));

        if (turmaOcupada) {
            throw new ConflitoDeHorarioException(
                    "a turma já possui a disciplina " + buscarDisciplinaNoHorario(alocacao.getHorario()) + " alocada",
                    alocacao.getHorario().getDia(),
                    alocacao.getHorario().getPeriodo()
            );
        }

        this.alocacoes.add(alocacao);
    }

    private String buscarDisciplinaNoHorario(Horario horario) {
        return alocacoes.stream()
                .filter(a -> a.getHorario().equals(horario))
                .findFirst()
                .map(a -> a.getDisciplina().getNome())
                .orElse("outra disciplina");
    }

    public List<Alocacao> getAlocacoes() {
        return Collections.unmodifiableList(alocacoes);
    }
}