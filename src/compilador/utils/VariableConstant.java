package compilador.utils;

public class VariableConstant extends Token {
    private tipusDades tipus;

    public VariableConstant(String id, tipusDades tipus, String nivell, int fila, int columna) {
        super(id, nivell, fila, columna);
        this.tipus = tipus;
    }

    public tipusDades getTipus() {
        return tipus;
    }

    public void setTipus(tipusDades tipus) {
        this.tipus = tipus;
    }
}
