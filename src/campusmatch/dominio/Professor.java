package campusmatch.dominio;

import java.util.List;
import java.util.Map;
import java.util.HashSet;


import campusmatch.enums.DiaDaSemana;
import campusmatch.enums.Periodo;

/**
 * The type Professor.
 */
public class Professor {
    private String nome;
    private String matricula;
    private List<Disciplina> competencias;
    private Set<Horario> disponibilidade;
    //private Map<DiaDaSemana, List<Periodo> > disponibilidade;
    private int cargaHorariaDisponivel; 

    
    public Professor(String nome, String matricula, List<Disciplina>competencias, Set<Horario> disponibilidade, int cargaHorariaDisponivel) {
        this.nome = nome;
        this.matricula = matricula;
        this.competencias = competencias;
        this.disponibilidade = new HashSet<>(disponibilidade); // Cria uma cópia mutavel para que possamos remover 
        this.cargaHorariaDisponivel = cargaHorariaDisponivel;
    }
    // recebe todos periodos do dia, se existe remove o perido, se nao existe periodos remove dia
    public void removerDisponibilidade(Horario horario) {
        this.disponibilidade.remove(horario);
    }

    public boolean isDisponivel(Horario horario){
        return disponibilidade.contains(horario);
    }

    public void descontarCargaHoraria(int horas){
        this.cargaHorariaDisponivel -= horas;
    }
    
    

    public void descontarCargaHoraria(){

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