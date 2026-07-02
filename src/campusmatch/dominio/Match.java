package campusmatch.dominio;

import campusmatch.dominio.excecoes.ConflitoDeHorarioException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Serviço responsável por orquestrar o match entre demandas do PPC e
 * professores disponíveis, em quatro etapas: filtragem, pontuação,
 * escolha do melhor candidato e validação final da grade.
 */
public class Match {
    private final List<Professor> professores;
    private final List<DemandaPPC> demandasppc;
    private final List<InteresseAlunos> interessesAlunos;
    private final CriterioDeMatch criterio;

    private final Grade gradeCurricular = new Grade();
    private final List<DemandaPPC> demandasNaoAtendidas = new ArrayList<>();

    public Match(List<Professor> professores, List<DemandaPPC> demandasppc,
                 List<InteresseAlunos> interessesAlunos, CriterioDeMatch criterio) {        this.professores = professores;
        this.demandasppc = demandasppc;
        this.interessesAlunos = interessesAlunos;
        this.criterio = criterio;
    }

    /**
     * Executa todo o fluxo do programa: para cada demanda, filtra candidatos,
     * escolhe o melhor e efetiva a alocação — ou registra a demanda como
     * não atendida, seja por falta de candidato, seja por conflito de horário.
     */
    public void executarMatch() {
        for (DemandaPPC demanda : demandasppc) {
            // ETAPA 1: filtragem
            List<Professor> candidatosValidos = buscarCandidatos(this.professores, demanda);

            if (candidatosValidos.isEmpty()) {
                demandasNaoAtendidas.add(demanda);
                continue; // pula para a próxima demanda
            }

            // ETAPA 2 e 3: pontuação e escolha
            Optional<Professor> melhorProfessor = escolherMelhorCandidato(candidatosValidos, demanda);

            if (melhorProfessor.isPresent()) {
                Professor escolhido = melhorProfessor.get();
                try {
                    efetivarAlocacao(escolhido, demanda);
                } catch (ConflitoDeHorarioException e) {
                    // Mesmo aprovado no filtro individual do professor, a grade como um
                    // todo pode recusar (ex: outro professor já ocupou aquele horário
                    // com outra disciplina). Nesse caso, a demanda também fica pendente.
                    System.out.println("[CONFLITO] " + e.getMessage());
                    demandasNaoAtendidas.add(demanda);
                }
            } else {
                demandasNaoAtendidas.add(demanda);
            }
        }
        // ETAPA 4: validação da grade curricular
        validarGrade();
    }

    /*
        ETAPA 1: Filtragem dos candidatos
    */
    public List<Professor> buscarCandidatos(List<Professor> corpoDocente, DemandaPPC demanda) {
        return corpoDocente.stream()
                // Regra 1: o professor precisa ter TODAS as competências exigidas pela disciplina
                .filter(professor -> professor.getCompetencias()
                        .containsAll(demanda.getDisciplina().getCompetenciasExigidas()))
                // Regra 2: o professor está disponível naquele dia/turno/período
                .filter(professor -> professor.isDisponivel(demanda.getHorario()))
                .collect(Collectors.toList());
    }

    /*
        ETAPA 2: Pontuação dos candidatos
    */
    private int calcularPontuacao(Professor professor, DemandaPPC demanda) {
        int score = 0;

        // Bônus: professor tem muita carga horária sobrando
        score += professor.getCargaHorariaDisponivel();
        // Bônus: a demanda tem prioridade alta no PPC
        score += demanda.getPrioridade().getPeso() * 10;
        // Bônus: interesse dos alunos
        Optional<InteresseAlunos> interesse = buscarInteresseDaDisciplina(demanda.getDisciplina());
        if (interesse.isPresent()) {
            InteresseAlunos dadosAlunos = interesse.get();
            score += dadosAlunos.getQuantidadeInteressados();
            score += dadosAlunos.getPrioridade().getPeso() * 10;
        }
        return score;
    }

    private Optional<InteresseAlunos> buscarInteresseDaDisciplina(Disciplina disciplina) {
        return interessesAlunos.stream()
                .filter(i -> i.getDisciplina().equals(disciplina))
                .findFirst();
    }

    /*
        ETAPA 3: Escolha do melhor candidato
    */
    private Optional<Professor> escolherMelhorCandidato(List<Professor> candidatos, DemandaPPC demanda) {
        return candidatos.stream()
                .max((prof1, prof2) -> Integer.compare(
                        calcularPontuacao(prof1, demanda),
                        calcularPontuacao(prof2, demanda)));
    }

    /*
        Efetivação da alocação na grade curricular
    */
    private void efetivarAlocacao(Professor escolhido, DemandaPPC demanda) throws ConflitoDeHorarioException {
        Alocacao alocacao = new Alocacao(demanda.getDisciplina(), escolhido, demanda.getHorario());
        gradeCurricular.adicionarAlocacao(alocacao);

        // Atualiza o estado do professor
        escolhido.removerDisponibilidade(demanda.getHorario());
        escolhido.descontarCargaHoraria(demanda.getDisciplina().getCargaHoraria());
    }

    /*
        ETAPA 4: Validação
    */
    public void validarGrade() {
        System.out.println("=== RELATÓRIO DE VALIDAÇÃO DA GRADE ===");

        if (demandasNaoAtendidas.isEmpty()) {
            System.out.println("[SUCESSO] Todas as demandas do PPC foram alocadas com sucesso!");
        } else {
            System.out.println("[ALERTA] As seguintes disciplinas ficaram sem professor:");
            for (DemandaPPC pendente : demandasNaoAtendidas) {
                System.out.println("- " + pendente.getDisciplina().getNome()
                        + " (" + pendente.getHorario().toString() + ")");
            }
        }
    }

    public Grade getGradeCurricular() {
        return gradeCurricular;
    }

    public List<DemandaPPC> getDemandasNaoAtendidas() {
        return demandasNaoAtendidas;
    }
}