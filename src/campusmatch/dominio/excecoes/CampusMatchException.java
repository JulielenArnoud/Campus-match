package campusmatch.dominio.excecoes;

public abstract class CampusMatchException extends Exception {
    protected CampusMatchException(String mensagem) {
        super(mensagem);
    }
}