package campusmatch.dominio;

import campusmatch.enums.DiaDaSemana;
import campusmatch.enums.Periodo;

public class DemandaPPC{
   private String nomeDaDisciplina;
   private Disciplina competenciasExigidas;
   private DiaDaSemana diaDaSemana;
   private Periodo periodo;

   public DemandaPPC(String nomeDaDisciplina, Disciplina competenciasExigidas, DiaDaSemana diaDaSemana, Periodo periodo){
       this.nomeDaDisciplina = nomeDaDisciplina;
       this.competenciasExigidas = competenciasExigidas;
       this.diaDaSemana = diaDaSemana;
       this.periodo = periodo;

   }

   public Disciplina getCompetenciasExigidas() {
       return competenciasExigidas;
   }
   public DiaDaSemana getDiaDaSemana(){
        return diaDaSemana;
   }
   public Periodo getPeriodo(){
        return periodo;
   }
    
}