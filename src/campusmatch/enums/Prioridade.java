package campusmatch.enums;

/**
 * Define o nível de urgência/importância de uma demanda da coordenação.
 */
public enum Prioridade {
    // Associamos um valor numérico a cada nível para o Motor de Match usar nos cálculos
    ALTA(3),
    MEDIA(2),
    BAIXA(1);

    private final int peso;

    // Construtor do Enum
    Prioridade(int peso) {
        this.peso = peso;
    }

    public int getPeso() {
        return peso;
    }
}