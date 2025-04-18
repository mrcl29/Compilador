package compiler.sintactic.Symbols;

import java.util.ArrayList;

public class Funcio extends Simbol {
    private ArrayList<VariableConstant> parametres;

    public Funcio() {
        super("", TipusDades.NULL);
        this.parametres = null;
    }

    public Funcio(Object id, ArrayList<VariableConstant> parametres, TipusDades tipus) {
        super(id, tipus);
        this.parametres = parametres;
    }

    public ArrayList<VariableConstant> getParametres() {
        return parametres;
    }

    public void setParametres(ArrayList<VariableConstant> parametres) {
        this.parametres = parametres;
    }

    public boolean mateixosParametres(ArrayList<Simbol> param) {
        int i = 0;
        int f = param.size();
        if (param != null && parametres != null) {
            if (f == parametres.size()) {
                for (int j = 0; j < parametres.size(); j++) {
                    if (i > f - 1) {
                        return false;
                    }
                    if (parametres.get(j).getTipus() != param.get(i).getTipus()) {
                        return false;
                    }
                    i++;
                }
                return true;
            }
        }
        return false;
    }

}
