package campusmatch.dominio.excecoes;

import campusmatch.enums.DiaDaSemana;
import campusmatch.enums.Periodo;

public class ConflitoDeHorarioException extends CampusMatchException {

    public ConflitoDeHorarioException(String descricaoConflito, DiaDaSemana dia, Periodo periodo) {
        super("Conflito de horário em " + dia.getDescricao() + ", período "
                + periodo.getOrdem() + ": " + descricaoConflito);
    }
}