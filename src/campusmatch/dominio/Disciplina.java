package campusmatch.dominio;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

/**
 * The type Disciplina.
 */
public class Disciplina {
    private final String codigo;
    private final String nome;
    private final int cargaHoraria;
    private final Set<String> tagsDeConhecimento;

    /**
     * Instantiates a new Disciplina.
     *
     * @param codigo             the codigo
     * @param nome               the nome
     * @param cargaHoraria       the carga horaria
     * @param tagsDeConhecimento the tags de conhecimento
     */
    public Disciplina(String codigo, String nome, int cargaHoraria, Set<String> tagsDeConhecimento) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("O código da disciplina não pode ser vazio.");
        }
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome da disciplina não pode ser vazio.");
        }
        if (cargaHoraria <= 0) {
            throw new IllegalArgumentException("A carga horária deve ser maior que zero.");
        }
        if (tagsDeConhecimento == null || tagsDeConhecimento.isEmpty()) {
            throw new IllegalArgumentException("A disciplina deve ter pelo menos uma tag de conhecimento.");
        }

        this.codigo = codigo;
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.tagsDeConhecimento = Set.copyOf(tagsDeConhecimento);
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

    /**
     * Gets carga horaria.
     *
     * @return the carga horaria
     */
    public int getCargaHoraria() {
        return cargaHoraria;
    }

    /**
     * Gets tags de conhecimento.
     *
     * @return the tags de conhecimento
     */
    public Set<String> getTagsDeConhecimento() {
        return Collections.unmodifiableSet(tagsDeConhecimento);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Disciplina that = (Disciplina) o;
        return Objects.equals(codigo, that.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
}