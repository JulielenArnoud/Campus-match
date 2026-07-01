package campusmatch.dominio;

import campusmatch.enums.DiaDaSemana;
import campusmatch.enums.Periodo;

public class DemandaPPC {
    
    // 1. Mudança principal: Em vez de guardar o nome e a competência soltos, 
    // guardamos o objeto Disciplina inteiro.
    private Disciplina disciplina;
    
    private DiaDaSemana diaDaSemana;
    private Periodo periodo;

    public DemandaPPC(Disciplina disciplina, DiaDaSemana diaDaSemana, Periodo periodo) {
        this.disciplina = disciplina;
        this.diaDaSemana = diaDaSemana;
        this.periodo = periodo;
    }

    // Getters para o Motor de Match utilizar
    public Disciplina getDisciplina() { 
        return disciplina; 
    }
    
    public DiaDaSemana getDiaDaSemana() { 
        return diaDaSemana; 
    }
    
    public Periodo getPeriodo() { 
        return periodo; 
    }
    
    // Um método de conveniência opcional para facilitar a leitura no código
    public String getNomeDaDisciplina() {
        return this.disciplina.getNome();
    }
}