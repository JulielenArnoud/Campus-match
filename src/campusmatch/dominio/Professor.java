package campusmatch.dominio;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Professor.
 */
public class Professor {
    private String nome;
    private List<String> competencias;
    private List<Horario> disponibilidade;

    /**
     * Instantiates a new Professor.
     *
     * @param nome the nome
     */
    public Professor(String nome) {
        this.nome = nome;
        this.competencias = new ArrayList<>();
        this.disponibilidade = new ArrayList<>();
    }

    /**
     * Gets nome.
     *
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Gets competencias.
     *
     * @return the competencias
     */
    public List<String> getCompetencias() {
        return competencias;
    }

    /**
     * Gets disponibilidade.
     *
     * @return the disponibilidade
     */
    public List<Horario> getDisponibilidade() {
        return disponibilidade;
    }
}