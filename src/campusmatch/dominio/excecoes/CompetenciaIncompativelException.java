package campusmatch.dominio.excecoes;

public class CompetenciaIncompativelException extends CampusMatchException {

    public CompetenciaIncompativelException(String nomeProfessor, String nomeDisciplina) {
        super("O professor " + nomeProfessor + " não possui a(s) competência(s) "
                + "necessária(s) para lecionar " + nomeDisciplina + ".");
    }
}