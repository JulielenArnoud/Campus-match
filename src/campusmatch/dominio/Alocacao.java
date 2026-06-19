package campusmatch.dominio;

/**
 * The type Alocacao.
 */
public class Alocacao {
    private Disciplina disciplina;
    private Professor professor;
    private Horario horario;

    /**
     * Instantiates a new Alocacao.
     *
     * @param disciplina the disciplina
     * @param professor  the professor
     * @param horario    the horario
     */
    public Alocacao(Disciplina disciplina, Professor professor, Horario horario) {
        this.disciplina = disciplina;
        this.professor = professor;
        this.horario = horario;
    }

    /**
     * Gets disciplina.
     *
     * @return the disciplina
     */
    public Disciplina getDisciplina() {
        return disciplina;
    }

    /**
     * Gets professor.
     *
     * @return the professor
     */
    public Professor getProfessor() {
        return professor;
    }

    /**
     * Gets horario.
     *
     * @return the horario
     */
    public Horario getHorario() {
        return horario;
    }
}