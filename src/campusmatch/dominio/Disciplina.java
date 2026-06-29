package campusmatch.dominio;

/**
 * The type Disciplina.
 */
public class Disciplina {
    private String codigo;
    private String nome;
    private int cargaHoraria;
    private String tagsDeConhecimento;
    

    /**
     * Instantiates a new Disciplina.
     *
     * @param codigo       the codigo
     * @param nome         the nome
     * @param cargaHoraria the carga horaria
     */
    public Disciplina(String codigo, String nome, int cargaHoraria) {
        this.codigo = codigo;
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
    }

    /**
     * Gets codigo.
     *
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Gets nome.
     *
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    
    public int getCargaHoraria() {
        return cargaHoraria;
    }
}