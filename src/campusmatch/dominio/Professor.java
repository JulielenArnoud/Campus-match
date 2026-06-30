package campusmatch.dominio;

import java.util.List;
import java.util.Map;

import campusmatch.enums.DiaDaSemana;
import campusmatch.enums.Periodo;

/**
 * The type Professor.
 */
public class Professor {
    private String nome;
    private String matricula;
    private List<Disciplina> competencias;
    private Map<DiaDaSemana, List<Periodo> > disponibilidade;


    
    public Professor(String nome, String matricula, List<Disciplina>competencias, Map<DiaDaSemana, List<Periodo>> disponibilidade) {
        this.nome = nome;
        this.matricula = matricula;
        this.competencias = competencias;
        this.disponibilidade = disponibilidade;
    }

    
    public String getNome() {
        return nome;
    }


    public List<Disciplina> getCompetencias() {
        return competencias;
    }

   
    public Map<DiaDaSemana, List<Periodo>> getDisponibilidade() {
        return disponibilidade;
    }
}