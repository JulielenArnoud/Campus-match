# Sistema de Apoio à Criação de Grades de Horário

## Sobre o Projeto
Este projeto é uma ferramenta desenvolvida em Java para apoiar o match entre disciplinas, professores, demandas da coordenação e interesses dos alunos. 

Nesta fase de ponto de verificação, o sistema foca na modelagem de domínio, rodando de forma executável com dados de exemplo criados em memória e simulando a geração da grade via console, ainda sem a interface gráfica definitiva.

## Equipe
* Antonio Augusto
* Artur Antunes
* Julielen Dorneles

## Classes Principais e Responsabilidades
Abaixo estão as classes centrais do domínio sugeridas para este ciclo, desenhadas para garantir encapsulamento e proteção de estado:

* **`Professor`**: Responsável por armazenar o nome, as competências cadastradas e a disponibilidade de cada docente.
* **`Disciplina`**: Mantém as informações da matéria, contendo código, nome e carga horária simplificada.
* **`Horario`**: Representa os blocos de tempo (dia e período) e foi projetada para ser uma classe imutável.
* **`Alocacao`**: Classe que vincula uma disciplina específica a um professor em um determinado horário.
* **`Grade`**: Gerencia o conjunto de alocações geradas.

## Estrutura Inicial
A arquitetura atual prioriza a separação de responsabilidades:
* `app.dominio`: Contém as classes de modelo descritas acima.
* `app.Main`: Ponto de entrada do sistema que carrega as coleções de dados em memória e demonstra a lógica básica.
