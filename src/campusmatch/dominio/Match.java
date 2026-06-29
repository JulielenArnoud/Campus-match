package campusmatch.dominio;

import java.util.List;
import java.util.stream.Collectors;

//imutabilidade: o stream não altera a lista original de corpoDocente, ele cria uma nova
//com somente os habilitados

public class Match{
    private List<Professor> professores = new ArrayList<>();
    private List<Disciplina> disciplinas;
    private List<DemandaPPC> demandasppc;
    

    public List<Professor> buscarCandidatos(List<Professor> corpoDocente, DemandaPPC demanda){
        return corpoDocente.stream()
                //Regra 1: o professor deve ter a competência exigida pela demanda
                .filter(professor -> professor.getCompetencias().contains(demanda.getCompetenciasExigidas()))
                //Regra 2: o professor está disponivel naquele dia e periodo
                .filter(professor -> verificarDisponibilidade(professor, demanda.getDiaDaSemana(), demanda.getPeriodo()))
                //coleta os aprovados em uma nova lista
                .collect(Collectors.toList());    
    }

    //metodo auxiliar para checar o mapa de agenda do professor(pode ser private)
    private boolean verificarDisponibilidade(Professor professor, DiasDaSemana dia, Periodo periodo){
        List<Periodo> periodosLivresNoDia = professor.getDisponibilidade().get(dia);
        return periodosLivresNoDia != null && periodosLivresNoDia.contains(periodo);
    }
}