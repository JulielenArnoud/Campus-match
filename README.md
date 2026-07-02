# Sistema de Apoio à Criação de Grades de Horário

## Sobre o Projeto
Este projeto é uma ferramenta desenvolvida em Java para apoiar o match entre disciplinas, professores, demandas da coordenação e interesses dos alunos. 

Nesta fase de ponto de verificação, o sistema foca na modelagem de domínio, rodando de forma executável com dados de exemplo criados em memória e simulando a geração da grade via console, ainda sem a interface gráfica definitiva.

## Equipe
* Antonio Augusto
* Artur Antunes
* Julielen Dorneles

 Como funciona o Motor de Match?

A lógica central do sistema (Match.java) é dividida em 4 etapas rigorosas, utilizando programação funcional (Java Streams API):

 Filtragem (Hard Constraints): Elimina candidatos que não possuem a Tag de Competência exigida pela disciplina ou que não tenham o horário livre na agenda.

 Pontuação (Soft Constraints): Calcula um Score para os professores aprovados na etapa anterior. O algoritmo soma bônus baseados na folga de carga horária do professor, no nível de prioridade do PPC e no volume de interesse dos alunos.

 Escolha e Efetivação: Seleciona o professor com a maior pontuação (abordagem Greedy). Realiza a alocação, consome o horário na agenda do professor e desconta sua carga horária disponível.

 Validação: Analisa o resultado gerando um relatório que aponta o sucesso da grade ou emite alertas de "Conflito de Horário" para as disciplinas que ficaram sem professor.

Próximos Passos

[ ] Interface Gráfica (GUI): Desenvolver painéis de controle e sliders de peso utilizando JavaFX ou Swing (Padrão MVC).

[ ] Banco de Dados: Conectar a aplicação a um SGBD relacional (ex: PostgreSQL) via JDBC ou JPA/Hibernate.

[ ] Exportação de Grade: Gerar a grade final em formato PDF ou Excel.
