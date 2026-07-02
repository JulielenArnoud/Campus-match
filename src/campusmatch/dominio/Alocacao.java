package campusmatch.dominio;

import java.util.Objects;

public class Alocacao {
    private final Disciplina disciplina;
    private final Professor professor;
    private final Horario horario;

    public Alocacao(Disciplina disciplina, Professor professor, Horario horario) {
        if (disciplina == null) {
            throw new IllegalArgumentException("A disciplina da alocação não pode ser nula.");
        }
        if (professor == null) {
            throw new IllegalArgumentException("O professor da alocação não pode ser nulo.");
        }
        if (horario == null) {
            throw new IllegalArgumentException("O horário da alocação não pode ser nulo.");
        }

        this.disciplina = disciplina;
        this.professor = professor;
        this.horario = horario;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public Professor getProfessor() {
        return professor;
    }

    public Horario getHorario() {
        return horario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alocacao alocacao = (Alocacao) o;
        return disciplina.equals(alocacao.disciplina)
                && professor.equals(alocacao.professor)
                && horario.equals(alocacao.horario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(disciplina, professor, horario);
    }

    @Override
    public String toString() {
        return disciplina.getNome() + " — " + professor.getNome() + " — " + horario;
    }
}