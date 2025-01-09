package compiler.sintactic.Symbols;

import java.util.ArrayList;

public class Funcio extends Simbol {
    private ArrayList<VariableConstant> parametres;
    private tipusDades tipus;

    public Funcio(Object id, ArrayList<VariableConstant> parametres, tipusDades tipus) {
        super(id);
        this.parametres = parametres;
        this.tipus = tipus;
    }

    public ArrayList<VariableConstant> getParametres() {
        return parametres;
    }

    public void setParametres(ArrayList<VariableConstant> parametres) {
        this.parametres = parametres;
    }

    public tipusDades getTipus() {
        return tipus;
    }

    public void setTipus(tipusDades tipus) {
        this.tipus = tipus;
    }

}
