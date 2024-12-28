package compiler.sintactic.Symbols;

import compiler.sintactic.Symbols.Simbol.tipusDades;

public class VariableConstant extends Simbol {
    private tipusDades tipus;
    private boolean esConstant;

    public VariableConstant(String id, tipusDades tipus, boolean esConstant, int fila, int columna) {
        super(id, fila, columna);
        this.tipus = tipus;
        this.esConstant = esConstant;
    }

    public tipusDades getTipus() {
        return tipus;
    }

    public void setTipus(tipusDades tipus) {
        this.tipus = tipus;
    }

    public boolean isEsConstant() {
        return esConstant;
    }

    public void setEsConstant(boolean esConstant) {
        this.esConstant = esConstant;
    }
}
