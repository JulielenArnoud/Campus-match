package campusmatch.dominio;

import campusmatch.enums.AreaCompetencia;
import java.util.HashSet;
import java.util.Set;

/**
 * Representa um professor: suas competências, sua disponibilidade de
 * horários e sua carga horária ainda disponível para novas alocações.
 */
public class Professor {
    private final String nome;
    private final String matricula;
    private final Set<AreaCompetencia> competencias;
    private final Set<Horario> disponibilidade;
    private int cargaHorariaDisponivel;

    public Professor(String nome, String matricula, Set<AreaCompetencia> competencias,
                     Set<Horario> disponibilidade, int cargaHorariaDisponivel) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do professor não pode ser vazio.");
        }
        if (competencias == null || competencias.isEmpty()) {
            throw new IllegalArgumentException("O professor deve ter pelo menos uma competência.");
        }
        if (disponibilidade == null) {
            throw new IllegalArgumentException("A disponibilidade do professor não pode ser nula.");
        }
        if (cargaHorariaDisponivel < 0) {
            throw new IllegalArgumentException("A carga horária disponível não pode ser negativa.");
        }

        this.nome = nome;
        this.matricula = matricula;
        this.competencias = Set.copyOf(competencias);
        this.disponibilidade = new HashSet<>(disponibilidade); // cópia mutável, para permitir remover horários
        this.cargaHorariaDisponivel = cargaHorariaDisponivel;
    }

    /**
     * Remove um horário da disponibilidade do professor (chamado após
     * ele ser efetivamente alocado naquele horário).
     */
    public void removerDisponibilidade(Horario horario) {
        this.disponibilidade.remove(horario);
    }

    public boolean isDisponivel(Horario horario) {
        return disponibilidade.contains(horario);
    }

    public void descontarCargaHoraria(int horas) {
        if (horas < 0) {
            throw new IllegalArgumentException("Não é possível descontar uma carga horária negativa.");
        }
        this.cargaHorariaDisponivel -= horas;
    }

    public String getNome() {
        return nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public Set<AreaCompetencia> getCompetencias() {
        return competencias;
    }

    public Set<Horario> getDisponibilidade() {
        return disponibilidade;
    }

    public int getCargaHorariaDisponivel() {
        return cargaHorariaDisponivel;
    }
}