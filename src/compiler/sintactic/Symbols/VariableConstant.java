package compiler.sintactic.Symbols;

public class VariableConstant extends Simbol {
    private TipusDades tipus;
    private boolean esConstant;
    private String ambit = "";

    public VariableConstant(Object id, TipusDades tipus, boolean esConstant) {
        super(id);
        this.tipus = tipus;
        this.esConstant = esConstant;
    }

    public VariableConstant(Object id, TipusDades tipus, boolean esConstant, int fila, int columna) {
        super(fila, columna, id);
        this.tipus = tipus;
        this.esConstant = esConstant;
    }

    public VariableConstant(Object id, String tipus, boolean esConstant) {
        super(id);
        this.tipus = TipusDades.valueOf(tipus);
        this.esConstant = esConstant;
    }

    @Override
    public String toString() {
        return value.toString() + " " + tipus.toString() + " es "
                + (esConstant ? "constante" : "variable");
    }

    public TipusDades getTipus() {
        return tipus;
    }

    public void setTipus(TipusDades tipus) {
        this.tipus = tipus;
    }

    public boolean isEsConstant() {
        return esConstant;
    }

    public void setEsConstant(boolean esConstant) {
        this.esConstant = esConstant;
    }

    public String isAmbit() {
        return ambit;
    }

    public void setAmbit(String ambit) {
        this.ambit = ambit;
    }
}
