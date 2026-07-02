package campusmatch.dominio;

import campusmatch.enums.AreaCompetencia;
import java.util.Objects;
import java.util.Set;

/**
 * Representa uma Disciplina na grade curricular.
 */
public class Disciplina {
    private final String codigo;
    private final String nome;
    private final int cargaHoraria;
    // Trocado Set<String> por Set<AreaCompetencia> para garantir o match exato sem erros de digitação
    private final Set<AreaCompetencia> competenciasExigidas;

    public Disciplina(String codigo, String nome, int cargaHoraria, Set<AreaCompetencia> competenciasExigidas) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("O código da disciplina não pode ser vazio.");
        }
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome da disciplina não pode ser vazio.");
        }
        if (cargaHoraria <= 0) {
            throw new IllegalArgumentException("A carga horária deve ser maior que zero.");
        }
        if (competenciasExigidas == null || competenciasExigidas.isEmpty()) {
            throw new IllegalArgumentException("A disciplina deve ter pelo menos uma competência/tag de conhecimento.");
        }

        this.codigo = codigo.trim();
        this.nome = nome.trim();
        this.cargaHoraria = cargaHoraria;
        
        // Set.copyOf já garante que a coleção original não seja alterada
        // e retorna um Set imutável (Java 10+)
        this.competenciasExigidas = Set.copyOf(competenciasExigidas);
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public Set<AreaCompetencia> getCompetenciasExigidas() {
        // Como this.competenciasExigidas já é um Set.copyOf, podemos retornar direto
        return competenciasExigidas;
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

    @Override
    public String toString() {
        return codigo + " - " + nome;
    }
}