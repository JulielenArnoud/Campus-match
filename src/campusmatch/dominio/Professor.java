package campusmatch.dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import campusmatch.enums.DiasDaSemana;
import campusmatch.enums.Disciplinas;
import campusmatch.enums.Periodo;

/**
 * The type Professor.
 */
public class Professor {
    private String nome;
    private String matricula;
    private List<Disciplinas> competencias;
    private Map<DiasDaSemana, List<Periodo> > disponibilidade;


    
    public Professor(String nome, String matricula, List<Disciplinas>competencias, Map<DiasDaSemana, List<Periodo>> disponibilidade) {
        this.nome = nome;
        this.matricula = matricula;
        this.competencias = competencias;
        this.disponibilidade = disponibilidade;
    }

    
    public String getNome() {
        return nome;
    }


    public List<Disciplinas> getCompetencias() {
        return competencias;
    }

   
    public Map<DiasDaSemana, List<Periodo>> getDisponibilidade() {
        return disponibilidade;
    }
}