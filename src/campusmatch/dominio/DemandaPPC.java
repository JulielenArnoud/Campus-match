package campusmatch.dominio;

import campusmatch.enums.Prioridade;

/**
 * Representa a demanda da coordenação (PPC): qual disciplina precisa
 * ser ofertada, em qual horário, e com qual prioridade.
 */
public class DemandaPPC {

    private final Disciplina disciplina;
    private final Horario horario;
    private final Prioridade prioridade;

    public DemandaPPC(Disciplina disciplina, Horario horario, Prioridade prioridade) {
        if (disciplina == null) {
            throw new IllegalArgumentException("A disciplina da demanda não pode ser nula.");
        }
        if (horario == null) {
            throw new IllegalArgumentException("O horário da demanda não pode ser nulo.");
        }
        if (prioridade == null) {
            throw new IllegalArgumentException("A prioridade da demanda não pode ser nula.");
        }

        this.disciplina = disciplina;
        this.horario = horario;
        this.prioridade = prioridade;
    }

    // Getters para o Motor de Match utilizar
    public Disciplina getDisciplina() {
        return disciplina;
    }

    public Horario getHorario() {
        return horario;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    // Auxiliar para o motor de match, para facilitar a leitura do código
    public int calcularPeso(int multiplicador) {
        return this.prioridade.getPeso() * multiplicador;
    }

    // Método de conveniência para facilitar a leitura no código
    public String getNomeDaDisciplina() {
        return this.disciplina.getNome();
    }
}