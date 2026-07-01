package campusmatch;

import campusmatch.dominio.Disciplina;
import campusmatch.dominio.Grade;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Disciplina disciplina = new Disciplina("CC100", "POO", 60, Set.of("Java"));
        Grade grade = new Grade();

        System.out.println(disciplina.getNome() + " carregada com sucesso.");
        System.out.println("Alocações na grade inicial: " + grade.getAlocacoes().size());

    }
}
