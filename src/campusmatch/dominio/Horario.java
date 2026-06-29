package campusmatch.dominio;

import campusmatch.enums.DiasDaSemana;
import campusmatch.enums.Periodo;


public class Horario {
    private final DiasDaSemana dia;
    private final Periodo periodo;

    public Horario(DiasDaSemana dia, Periodo periodo) {
        this.dia = dia;
        this.periodo = periodo;
    }

    /**
     * Gets dia.
     *
     * @return the dia
     */
    public DiasDaSemana getDia() {
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