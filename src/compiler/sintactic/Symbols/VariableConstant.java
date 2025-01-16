package compiler.sintactic.Symbols;

public class VariableConstant extends Simbol {
    private boolean esConstant;

    public VariableConstant() {
        super("", TipusDades.NULL);
        this.esConstant = false;
    }

    public VariableConstant(Object id, TipusDades tipus, boolean esConstant) {
        super(id, tipus);
        this.esConstant = esConstant;
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
}
