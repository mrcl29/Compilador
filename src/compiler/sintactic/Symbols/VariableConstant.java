package compiler.sintactic.Symbols;

public class VariableConstant extends Simbol {
    private boolean esConstant;
    private String ambit = "";

    public VariableConstant() {
        super("", TipusDades.NULL);
        this.esConstant = false;
    }

    public VariableConstant(Object id, TipusDades tipus, boolean esConstant) {
        super(id, tipus);
        this.esConstant = esConstant;
    }

    public VariableConstant(Object id, TipusDades tipus, boolean esConstant, String ambit) {
        super(id, tipus);
        this.esConstant = esConstant;
        this.ambit = ambit;
    }

    public VariableConstant(Object id, TipusDades tipus, boolean esConstant, int fila, int columna) {
        super(fila, columna, id, tipus);
        this.esConstant = esConstant;
    }

    @Override
    public String toString() {
        return value.toString() + " " + getTipus().toString() + " es "
                + (esConstant ? "constante" : "variable");
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
