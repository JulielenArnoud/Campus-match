package campusmatch.enums;

/**
 * Representa o vocabulário oficial de tags de conhecimento/competências do sistema.
 * Tanto as Disciplinas (demandas) quanto os Professores (ofertas) devem usar 
 * essas mesmas tags para que o Motor de Match consiga cruzá-las.
 */
public enum AreaCompetencia {
    
    // Lista de tags padronizadas. 
    // Você pode adicionar quantas forem necessárias para os cursos da instituição.
    PROGRAMACAO_BASICA("Programação Básica e Lógica"),
    PROGRAMACAO_AVANCADA("Programação Avançada (Orientação a Objetos)"),
    BANCO_DE_DADOS("Banco de Dados e SQL"),
    ENGENHARIA_DE_SOFTWARE("Engenharia de Software e Projetos"),
    SISTEMAS_OPERACIONAIS("Sistemas Operacionais"),
    REDES_DE_COMPUTADORES("Redes e Infraestrutura"),
    MATEMATICA_DISCRETA("Matemática Discreta"),
    CALCULO("Cálculo e Matemática Avançada"),
    INTELIGENCIA_ARTIFICIAL("Inteligência Artificial e Machine Learning");

    // Atributo opcional: Um nome "amigável" para mostrar na Interface Gráfica
    private final String descricaoAmigavel;

    // Construtor do Enum
    AreaCompetencia(String descricaoAmigavel) {
        this.descricaoAmigavel = descricaoAmigavel;
    }

    // Getter para quando você for exibir essa tag na tela (JavaFX/Swing/Web)
    public String getDescricaoAmigavel() {
        return descricaoAmigavel;
    }
}