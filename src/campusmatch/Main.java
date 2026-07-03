package campusmatch;

import campusmatch.dominio.*;
import campusmatch.enums.*;
import campusmatch.gui.GradeGUI;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        Horario segundaManha1 = new Horario(DiaDaSemana.SEGUNDA, Turno.MANHA, Periodo.PRIMEIRO);
        Horario segundaManha2 = new Horario(DiaDaSemana.SEGUNDA, Turno.MANHA, Periodo.SEGUNDO);
        Horario segundaManha3 = new Horario(DiaDaSemana.SEGUNDA, Turno.MANHA, Periodo.TERCEIRO);
        Horario tercaManha1 = new Horario(DiaDaSemana.TERCA, Turno.MANHA, Periodo.PRIMEIRO);
        Horario tercaManha2 = new Horario(DiaDaSemana.TERCA, Turno.MANHA, Periodo.SEGUNDO);
        Horario tercaManha3 = new Horario(DiaDaSemana.TERCA, Turno.MANHA, Periodo.TERCEIRO);

        Disciplina poo = new Disciplina("CC100", "Programação Orientada a Objetos", 60,
                Set.of(AreaCompetencia.PROGRAMACAO_AVANCADA));
        Disciplina bancoDeDados = new Disciplina("CC200", "Banco de Dados", 60,
                Set.of(AreaCompetencia.BANCO_DE_DADOS));
        Disciplina redes = new Disciplina("CC300", "Redes de Computadores", 40,
                Set.of(AreaCompetencia.REDES_DE_COMPUTADORES));
        Disciplina engenhariaSoftware = new Disciplina("CC400", "Engenharia de Software", 60,
                Set.of(AreaCompetencia.ENGENHARIA_DE_SOFTWARE));

        List<Disciplina> disciplinas = new ArrayList<>(List.of(poo, bancoDeDados, redes, engenhariaSoftware));

        Professor ana = new Professor("Ana Silva", "P001",
                Set.of(AreaCompetencia.PROGRAMACAO_AVANCADA, AreaCompetencia.ENGENHARIA_DE_SOFTWARE),
                new HashSet<>(Set.of(segundaManha1, segundaManha2, tercaManha1)), 12);

        Professor bruno = new Professor("Bruno Costa", "P002",
                Set.of(AreaCompetencia.BANCO_DE_DADOS),
                new HashSet<>(Set.of(segundaManha3, tercaManha2, tercaManha3)), 8);

        Professor carla = new Professor("Carla Souza", "P003",
                Set.of(AreaCompetencia.REDES_DE_COMPUTADORES, AreaCompetencia.ENGENHARIA_DE_SOFTWARE),
                new HashSet<>(Set.of(tercaManha1, tercaManha2)), 10);

        List<Professor> professores = new ArrayList<>(List.of(ana, bruno, carla));

        DemandaPPC demandaPoo = new DemandaPPC(poo, segundaManha1, Prioridade.ALTA);
        DemandaPPC demandaBanco = new DemandaPPC(bancoDeDados, segundaManha3, Prioridade.MEDIA);
        DemandaPPC demandaRedes = new DemandaPPC(redes, tercaManha1, Prioridade.MEDIA);
        DemandaPPC demandaEngenharia = new DemandaPPC(engenhariaSoftware, tercaManha2, Prioridade.BAIXA);

        List<DemandaPPC> demandas = List.of(demandaPoo, demandaBanco, demandaRedes, demandaEngenharia);

        List<InteresseAlunos> interesses = List.of(
                new InteresseAlunos(poo, 40, Prioridade.ALTA),
                new InteresseAlunos(bancoDeDados, 25, Prioridade.MEDIA),
                new InteresseAlunos(redes, 15, Prioridade.BAIXA),
                new InteresseAlunos(engenhariaSoftware, 30, Prioridade.MEDIA)
        );

        SwingUtilities.invokeLater(() -> {
            GradeGUI tela = new GradeGUI(professores, disciplinas, demandas, interesses);
            tela.setVisible(true);
        });
    }
}