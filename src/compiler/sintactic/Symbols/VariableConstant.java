package compiler.sintactic.Symbols;

public class VariableConstant extends Simbol {
    private tipusDades tipus;
    private boolean esConstant;

    public VariableConstant(Object id, tipusDades tipus, boolean esConstant) {
        super(id);
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
