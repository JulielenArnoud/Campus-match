package campusmatch.enums;

/**
 * The enum Periodo.
 */
public enum Periodo {
    PRIMEIRO(1),
    SEGUNDO(2),
    TERCEIRO(3);

    private final int ordem;

    Periodo(int ordem) {
        this.ordem = ordem;
    }

    public int getOrdem() {
        return ordem;
    }
}

