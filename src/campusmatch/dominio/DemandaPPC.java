package campusmatch.dominio;

import campusmatch.enums.DiaDaSemana;
import campusmatch.enums.Periodo;

public class DemandaPPC {
    

    private Disciplina disciplina;  
    private Horario horario;
    private Prioridade prioridade; 

    public DemandaPPC(Disciplina disciplina, Horario horario, prioridade prioridade) {
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
    //auxiliar para o motor de match, para facilitar a leitura do código
    public int calcularPeso(int multiplicador){
        return this.prioridade*multiplicador;

    }
    
    // Um método de conveniência opcional para facilitar a leitura no código
    public String getNomeDaDisciplina() {
        return this.disciplina.getNome();
    }
}