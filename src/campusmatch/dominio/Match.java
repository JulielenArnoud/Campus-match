package campusmatch.dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import campusmatch.enums.DiaDaSemana;
import campusmatch.enums.Periodo;
import campusmatch.enums.AreaCompetencia;

import campusmatch.dominio.Professor;
import campusmatch.dominio.Disciplina;
import campusmatch.dominio.DemandaPPC;

//imutabilidade: o stream não altera a lista original de corpoDocente, ele cria uma nova
//com somente os habilitados

public class Match{
    private List<Professor> professores = new ArrayList<>();
    //private List<Disciplina> disciplinas;
    private List<DemandaPPC> demandasppc = new ArrayList<>();
    
    private Grade gradeCurricular = new Grade();

    private List<DemandaPPC> demandasNaoAtendidas = new ArrayList<>();

    private List<InteresseAlunos> interessesAlunos = new ArrayList<>();

    public Match(List<Professor> professores, List<DemandaPPC> demandasppc, List<InteresseAlunos> interessesAlunos) {
        this.professores = professores;
        this.demandasppc = demandasppc;
        this.interessesAlunos = interessesAlunos;
    }

    /*
        Metodo que executa todo o fluxo completo do programa
    */
    public void executarMatch(){
        for(DemandaPPC demanda : demandasppc){
            // ETAPA 1: filtragem
            List<Professor> candidatosValidos = buscarCandidatos(this.professores, demanda);
            
            if(candidatosValidos.isEmpty()){  // guarda as demandas que não foram atendidas
                demandasNaoAtendidas.add(demanda);
                continue; //pula para a próxima demanda
            }

            //ETAPA 2 e 3: pontução e escolha
            Optional<Professor> melhorProfessor = escolherMelhorCandidato(candidatosValidos, demanda);
            
            if(melhorProfessor.isPresent()){
                Professor escolhido = melhorProfessor.get();
                efetivarAlocacao(escolhido, demanda);
            }
            else{
                demandasNaoAtendidas.add(demanda);
            }
        }
    // ETAPA 4: validação da grade curricular
        validarGradeCurricular();
    }


    /*
    ETAPA 1: Filtragem dos candidatos
    */
    public List<Professor> buscarCandidatos(List<Professor> corpoDocente, DemandaPPC demanda){
        return corpoDocente.stream()
                //Regra 1: o professor deve ter a competência exigida pela demanda
                .filter(professor -> professor.getCompetencias().contains(demanda.getDisciplina()))
                //Regra 2: o professor está disponivel naquele dia e periodo
                .filter(professor -> verificarDisponibilidade(professor, demanda.getHorario()))
                //coleta os aprovados em uma nova lista
                .collect(Collectors.toList());    
    }

    //metodo auxiliar para checar o mapa de agenda do professor(pode ser private)
    private boolean verificarDisponibilidade(Professor professor, Horario horario){
        return professor.isDisponivel(horario);
    }


    /*
        ETAPA 2: Pontuação dos candidatos
    */
    private int calcularPontuacao(Professor professor, DemandaPPC demanda){
        int score = 0;
        //Bonus: professor tem muita carga horaria sobrando
        score += professor.getCargaHorariaDisponivel();
        //Bonus: a demanda tem prioridade alta no ppc
        score += demanda.getPrioridade().getPeso()*10;
        //BOnus: intersse dos alunos
        Optional<InteresseAlunos> interesse = busacarInteresseDaDisciplina(demanda.getDisciplina());
        if(interesse.isPresent()){
            InteresseAlunos dadosAlunos = interesse.get();
            score += dadosAlunos.getQuantidadeInteressados();
            score += dadosAlunos.getPrioridade().getPeso() * 10;
        }
        return score;
    }   

    private Optional<InteresseAlunos> busacarInteresseDaDisciplina(Disciplina disciplina){
        return interesseDosAlunos.stream()
                .filter(i-> i.getDisciplina().equals(disciplina))            
                .findFirst();
    }

    /*
        ETAPA 3: Escolha do melhor candidato
    */
   // O Optional quer dizer que pode retornar uma valor ou não, ou seja, pode retornar NULL
    private Optional<Professor> escolherMelhorCandidato(List<Professor> candidatos, DemandaPPC demanda){    
        return candidatos.stream()
                .max((prof1, prof2) -> Integer.compare(calcularPontuacao(prof1, demanda), calcularPontuacao(prof2, demanda)));
    }

    /*
        efetivacao de alocacao na grade curricular
    */

    private void efetivarAlocacao(Professor escolhido, DemandaPPC demanda){
        Alocacao alocacao = new Alocacao(demanda.getDisciplina(), escolhido, demanda.getHorario());
        gradeCurricular.adicionarAlocacao(alocacao);
        
        //atualiza o estado do professor
        escolhido.removerDisponibilidade(demanda.getHorario());
        escolhido.descontarCargaHoraria(demanda.getDisciplina().getCargaHoraria());
    }

    /*
    ETAPA 4 : VALIDACAO 
    */

    public void validarGrade() {
        System.out.println("=== RELATÓRIO DE VALIDAÇÃO DA GRADE ===");
        
        if (demandasNaoAtendidas.isEmpty()) {
            System.out.println("[SUCESSO] Todas as demandas do PPC foram alocadas com sucesso!");
        } else {
            System.out.println("[ALERTA] As seguintes disciplinas ficaram sem professor:");
            for (DemandaPPC pendente : demandasNaoAtendidas) {
                System.out.println("- " + pendente.getDisciplina().getNome() + 
                                   " (" + pendente.getHorario().toString() + ")");
            }
        }
        
        // Aqui você pode adicionar outras validações, como:
        // - Checar se algum professor ficou com carga horária ociosa (recebendo sem dar aula)
        // - Checar se as disciplinas pré-requisito foram ofertadas antes das avançadas
    }


}




