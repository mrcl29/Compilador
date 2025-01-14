package compiler.sintactic.Symbols;

import java.util.ArrayList;

public class Funcio extends Simbol {
    private ArrayList<Simbol> parametres;
    private TipusDades tipus;

    public Funcio(Object id, ArrayList<Simbol> parametres, TipusDades tipus) {
        super(id);
        this.parametres = parametres;
        this.tipus = tipus;
    }

    public ArrayList<Simbol> getParametres() {
        return parametres;
    }

    public void setParametres(ArrayList<Simbol> parametres) {
        this.parametres = parametres;
    }

    public TipusDades getTipus() {
        return tipus;
    }

    public void setTipus(TipusDades tipus) {
        this.tipus = tipus;
    }

}
