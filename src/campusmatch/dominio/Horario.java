package campusmatch.dominio;

import campusmatch.enums.DiaDaSemana;
import campusmatch.enums.Periodo;
import campusmatch.enums.Turno;
import java.util.Objects;

/**
 * Representa um horário: dia da semana, turno e período.
 * Classe imutável — dois horários com o mesmo dia, turno e período
 * são considerados o mesmo horário (equals/hashCode por valor).
 */
public class Horario {
    private final DiaDaSemana dia;
    private final Turno turno;
    private final Periodo periodo;

    public Horario(DiaDaSemana dia, Turno turno, Periodo periodo) {
        if (dia == null || turno == null || periodo == null) {
            throw new IllegalArgumentException("Dia, turno e período não podem ser nulos.");
        }
        this.dia = dia;
        this.turno = turno;
        this.periodo = periodo;
    }

    public DiaDaSemana getDia() {
        return dia;
    }

    public Turno getTurno() {
        return turno;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    // Compara dois horários pelo valor (dia + turno + período), não pela referência em memória
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Horario horario = (Horario) o;
        return dia == horario.dia && turno == horario.turno && periodo == horario.periodo;
    }

    // Gera um código numérico consistente com o equals: horários "iguais" têm o mesmo hashCode
    @Override
    public int hashCode() {
        return Objects.hash(dia, turno, periodo);
    }

    @Override
    public String toString() {
        return dia + "-" + turno.getDescricao() + "-" + periodo;
    }
}