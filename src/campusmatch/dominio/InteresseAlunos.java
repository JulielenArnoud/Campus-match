package campusmatch.dominio;

import campusmatch.enums.Prioridade;

/**
 * Representa a demanda quantitativa e qualitativa dos alunos por uma disciplina.
 */
public class InteresseAlunos {
    private final Disciplina disciplina;
    private final int quantidadeInteressados;
    private final Prioridade prioridade; // Ex: ALTA (se tiver muitos formandos precisando)

    public InteresseAlunos(Disciplina disciplina, int quantidadeInteressados, Prioridade prioridade) {
        if (disciplina == null || prioridade == null) {
            throw new IllegalArgumentException("Disciplina e Prioridade não podem ser nulos.");
        }
        if (quantidadeInteressados < 0) {
            throw new IllegalArgumentException("A quantidade de interessados não pode ser negativa.");
        }
        
        this.disciplina = disciplina;
        this.quantidadeInteressados = quantidadeInteressados;
        this.prioridade = prioridade;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public int getQuantidadeInteressados() {
        return quantidadeInteressados;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }
}