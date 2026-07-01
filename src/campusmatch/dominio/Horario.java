package campusmatch.dominio;

import campusmatch.enums.DiaDaSemana;
import campusmatch.enums.Periodo;
import campusmatch.enums.Turno;


public class Horario {
    private final DiaDaSemana dia;
    private final Turno turno;
    private final Periodo periodo;

    public Horario(DiaDaSemana dia, Turno turno, Periodo periodo) {
        if(dia == null || turno == null || periodo == null){
            throw new IllegalArgumentException("Dia, turno e período não podem ser nulos.");
        }
        this.dia = dia;
        this.turno = turno;
        this.periodo = periodo;
    }

    /**
     * Gets dia.
     *
     * @return the dia
     */
    public DiaDaSemana getDia() {
        return dia;
    }

    /**
     * Gets turno.
     *
     * @return the turno
     */
    public Turno getTurno() {
        return turno;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    //isso compara os horarios, 
    @Override
    public boolean equals(Object o){
        if(this == o) return true; //se o objeto que chamou for igual ao objeto passado, retorna true
        if(o == null || getClass() != o.getClass()) return false;

        Horario horario = (Horario) o;
        return dia == horario.dia && turno == horario.turno && periodo == horario.periodo;
    }

    //ele faz o hash code do objeto, que é um número inteiro que representa o objeto.
    @Override
    public int hashCode(){
        return Objects.hash(dia, turno, periodo);
    }

    @Override 
    public String toString(){
        return dia + "-" + turno.getDescricao() + "-" + periodo;
    }





}   