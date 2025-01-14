package compiler.sintactic.Symbols;

import java.util.ArrayList;

public class TaulaSimbols {
    private ArrayList<Simbol> taulaSimbols;

    public TaulaSimbols() {
        taulaSimbols = new ArrayList<Simbol>();
    }

    public boolean existeixSimbol(Object id) {
        Simbol simbol;
        for (int i = 0; i < taulaSimbols.size(); i++) {
            simbol = taulaSimbols.get(i);
            if (simbol.getValue().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public boolean declararVariableConstant(Object id, String tipus, boolean constant) throws Exception {
        if (existeixSimbol(id)) {
            return false;
        }
        TipusDades aux = TipusDades.valueOf(tipus);
        taulaSimbols.add(new VariableConstant(id, aux, constant));
        return true;
    }

    public boolean declararTupla(Object id, String tipus1, String tipus2) throws Exception {
        if (existeixSimbol(id)) {
            return false;
        }
        TipusDades aux1 = TipusDades.valueOf(tipus1);
        TipusDades aux2 = TipusDades.valueOf(tipus2);
        taulaSimbols.add(new Tupla(id, aux1, aux2));
        return true;
    }

    public boolean declararFuncio(Object id, ArrayList<Simbol> parametres, String tipus) throws Exception {
        if (existeixSimbol(id)) {
            return false;
        }
        TipusDades aux = TipusDades.valueOf(tipus);
        taulaSimbols.add(new Funcio(id, parametres, aux));
        return true;
    }

    public TipusDades usarSimbol(Object id) {
        Simbol simbol;
        for (int i = 0; i < taulaSimbols.size(); i++) {
            simbol = taulaSimbols.get(i);
            if (simbol.getValue().equals(id)) {
                if (simbol instanceof VariableConstant) {
                    VariableConstant aux = (VariableConstant) simbol;
                    return aux.getTipus();
                }
                if (simbol instanceof Tupla) {
                    Tupla aux = (Tupla) simbol;
                    return aux.getTipus(1);
                }
                if (simbol instanceof Funcio) {
                    Funcio aux = (Funcio) simbol;
                    return aux.getTipus();
                }
                return null;
            }
        }
        return null;
    }

    public VariableConstant usarVariableConstant(Object id) {
        Simbol simbol;
        for (int i = 0; i < taulaSimbols.size(); i++) {
            simbol = taulaSimbols.get(i);
            if (simbol instanceof VariableConstant && simbol.getValue().equals(id)) {
                VariableConstant aux = (VariableConstant) simbol;
                return aux;
            }
        }
        return null;
    }

    public Tupla usarTupla(Object id) {
        Simbol simbol;
        for (int i = 0; i < taulaSimbols.size(); i++) {
            simbol = taulaSimbols.get(i);
            if (simbol instanceof Tupla && simbol.getValue().equals(id)) {
                Tupla aux = (Tupla) simbol;
                return aux;
            }
        }
        return null;
    }

    public Funcio usarFuncio(Object id) {
        Simbol simbol;
        for (int i = 0; i < taulaSimbols.size(); i++) {
            simbol = taulaSimbols.get(i);
            if (simbol instanceof Funcio && simbol.getValue().equals(id)) {
                Funcio aux = (Funcio) simbol;
                return aux;
            }
        }
        return null;
    }

}
