# Campus Match - Sistema de Apoio à Criação de Grades de Horário

## Sobre o Projeto
Ferramenta desenvolvida em Java, com interface gráfica em Swing, para apoiar o *match* entre disciplinas, professores, demandas da coordenação (PPC) e interesses dos alunos. O sistema cruza competências e disponibilidade dos professores com as necessidades da coordenação e o interesse dos alunos, sugere uma alocação de horários e sinaliza conflitos de forma compreensível na própria tela.

## Equipe
* Antonio Augusto
* Artur Antunes
* Julielen Dorneles

## Status do desenvolvimento
- [x] Modelagem de domínio (`Professor`, `Disciplina`, `Horario`, `Alocacao`, `Grade`, `DemandaPPC`, `InteresseAlunos`)
- [x] Enums (`DiaDaSemana`, `Periodo`, `Turno`, `AreaCompetencia`, `Prioridade`)
- [x] Motor de match em quatro etapas — filtragem, pontuação, escolha e validação (`Match`)
- [x] Exceções de domínio (pacote `dominio.excecoes`)
- [x] Detecção de conflito de horário (professor e turma) na `Grade`
- [x] Strategy para o critério de pontuação (`CriterioDeMatch` + `PontuacaoPadraoStrategy`), já integrada ao `Match`
- [x] Interface gráfica em Swing (`GradeGUI`), com cadastro de professores e disciplinas

## Como executar

### Pré-requisitos
- JDK 17 ou superior
- Git

### Passo a passo
```bash
git clone https://github.com/JulielenArnoud/Campus-match.git
cd Campus-match
javac -d out $(find src -name "*.java")
java -cp out campusmatch.Main
```

Ao rodar, a aplicação já abre com dados de exemplo (3 professores, 4 disciplinas, 4 demandas do PPC e 4 registros de interesse dos alunos) carregados em memória.

## Como usar a interface
1. **Aba "Professores"** — consulte os professores já cadastrados. No formulário abaixo da tabela, preencha nome, matrícula, carga horária, marque as competências e os horários disponíveis, e clique em **"Adicionar Professor"**.
2. **Aba "Disciplinas"** — mesma lógica: código, nome, carga horária, competências exigidas, e **"Adicionar Disciplina"**.
3. **Aba "Gerar Grade"** — clique em **"Gerar Sugestão de Grade"** para rodar o match.
4. A tabela superior mostra a **grade gerada** (disciplina, professor e horário). A tabela inferior, em destaque, mostra as **disciplinas não alocadas** — por falta de competência, disponibilidade, ou conflito de horário.
5. Um aviso ao final informa se todas as demandas foram atendidas ou se restaram pendências.
6. Campos inválidos (ex: nome vazio, nenhuma competência selecionada) geram uma mensagem de erro específica, sem quebrar a aplicação.

## Estrutura do projeto
```
src/campusmatch/
├── Main.java                      → ponto de entrada; monta os dados de exemplo e abre a GUI
├── dominio/
│   ├── Professor.java             → nome, competências, disponibilidade e carga horária
│   ├── Disciplina.java            → código, nome, carga horária e competências exigidas
│   ├── Horario.java               → dia, turno e período (classe imutável)
│   ├── Alocacao.java              → vincula disciplina + professor + horário (classe imutável)
│   ├── Grade.java                 → conjunto de alocações; valida conflito de horário
│   ├── DemandaPPC.java            → demanda da coordenação (disciplina, horário e prioridade)
│   ├── InteresseAlunos.java       → interesse dos alunos por disciplina
│   ├── CriterioDeMatch.java       → interface do critério de pontuação (Strategy)
│   ├── PontuacaoPadraoStrategy.java → implementação padrão do critério de pontuação
│   ├── Match.java                 → motor de match: filtragem, pontuação, escolha e validação
│   └── excecoes/
│       ├── CampusMatchException.java            → exceção-mãe abstrata do domínio
│       ├── ConflitoDeHorarioException.java       → choque de horário de professor ou turma
│       └── CompetenciaIncompativelException.java → professor sem a competência exigida
├── enums/
│   ├── DiaDaSemana.java
│   ├── Periodo.java
│   ├── Turno.java
│   ├── AreaCompetencia.java
│   └── Prioridade.java
└── gui/
    └── GradeGUI.java              → janela principal (Swing): abas de professores, disciplinas e geração de grade
```

## Classes principais e responsabilidades
* **`Professor`** — nome, matrícula, competências (`Set<AreaCompetencia>`), disponibilidade (`Set<Horario>`) e carga horária disponível; valida seus dados no construtor.
* **`Disciplina`** — código, nome, carga horária e competências exigidas; classe imutável, valida no construtor (fail-fast).
* **`Horario`** — dia, turno e período; classe imutável, com `equals`/`hashCode` por valor.
* **`Alocacao`** — associação imutável entre disciplina, professor e horário.
* **`Grade`** — mantém as alocações e impede, via `ConflitoDeHorarioException`, que um mesmo professor ou a mesma turma fiquem em dois lugares no mesmo horário.
* **`DemandaPPC`** — disciplina, horário e prioridade definidos pela coordenação.
* **`InteresseAlunos`** — quantidade de interessados e prioridade qualitativa por disciplina.
* **`CriterioDeMatch` / `PontuacaoPadraoStrategy`** — ponto de variação (Strategy) do critério de pontuação usado na escolha do professor, isolado do fluxo do `Match`.
* **`Match`** — orquestra o match em quatro etapas: filtra candidatos aptos, pontua via `CriterioDeMatch`, escolhe o melhor candidato e valida a grade final, reportando demandas não atendidas.
* **Exceções de domínio** — representam violações de regra de negócio, tratadas explicitamente (sem `catch(Exception)` genérico).
* **`GradeGUI`** — interface Swing com abas de cadastro de professores/disciplinas e geração da grade; não contém nenhuma regra de negócio, apenas chama o `Match` já pronto e exibe o resultado.
