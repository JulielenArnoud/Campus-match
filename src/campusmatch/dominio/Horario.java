package campusmatch.dominio;

import campusmatch.enums.DiaDaSemana;
import campusmatch.enums.Periodo;


public class Horario {
    private final DiaDaSemana dia;
    private final Periodo periodo;

    public Horario(DiaDaSemana dia, Periodo periodo) {
        this.dia = dia;
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
     * Gets periodo.
     *
     * @return the periodo
     */
    public Periodo getPeriodo() {
        return periodo;
    }
}