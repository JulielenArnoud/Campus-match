package campusmatch.dominio;

import campusmatch.enums.DiasDaSemana;

public class DemandaPPC{
   private String nomeDaDisciplina;
   private Disciplinas competenciasExigidas;
   private DiasDaSemana diaDaSemana;
   private Periodo periodo;

   public DemandaPPC(String nomeDaDisciplina, Disciplinas competenciasExigidas, DiasDaSemana diaDaSemana, Periodo periodo){
       this.nomeDaDisciplina = nomeDaDisciplina;
       this.competenciasExigidas = competenciasExigidas;
       this.diaDaSemana = diaDaSemana;
       this.periodo = periodo;

   }

   public Disciplinas getCompetenciasExigidas() {
       return competenciasExigidas;
   }
   public DiasDaSemana getDiaDaSemana(){
        return diaDaSemana;
   }
   public Periodo getPeriodo(){
        return periodo;
   }
    
}