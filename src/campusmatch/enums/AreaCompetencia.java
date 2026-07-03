package campusmatch.enums;

/**
 * Representa o vocabulário oficial de tags de conhecimento/competências do sistema.
 * Tanto as Disciplinas (demandas) quanto os Professores (ofertas) devem usar 
 * essas mesmas tags para que o Motor de Match consiga cruzá-las.
 */
public enum AreaCompetencia {

    PROGRAMACAO_BASICA("Programação Básica e Lógica"),
    PROGRAMACAO_AVANCADA("Programação Avançada (Orientação a Objetos)"),
    BANCO_DE_DADOS("Banco de Dados e SQL"),
    ENGENHARIA_DE_SOFTWARE("Engenharia de Software e Projetos"),
    SISTEMAS_OPERACIONAIS("Sistemas Operacionais"),
    REDES_DE_COMPUTADORES("Redes e Infraestrutura"),
    MATEMATICA_DISCRETA("Matemática Discreta"),
    CALCULO("Cálculo e Matemática Avançada"),
    INTELIGENCIA_ARTIFICIAL("Inteligência Artificial e Machine Learning");

    // Nome amigável, usado na exibição em tela (Swing)
    private final String descricaoAmigavel;

    AreaCompetencia(String descricaoAmigavel) {
        this.descricaoAmigavel = descricaoAmigavel;
    }

    public String getDescricaoAmigavel() {
        return descricaoAmigavel;
    }
}