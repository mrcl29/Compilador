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

    public boolean declararVariableConstant(VariableConstant varcon) {
        if (existeixSimbol(varcon.getValue())) {
            return false;
        }
        taulaSimbols.add(varcon);
        return true;
    }

    public boolean declararVariableConstant(VariableConstant varcon, String ambit) {
        if (existeixSimbol(varcon.getValue())) {
            return false;
        }
        taulaSimbols.add(new VariableConstant(varcon.getValue(), varcon.getTipus(), varcon.isEsConstant(), ambit));
        return true;
    }

    public boolean declararTupla(Tupla tup) {
        if (existeixSimbol(tup.getValue())) {
            return false;
        }
        taulaSimbols.add(tup);
        return true;
    }

    public boolean declararFuncio(Object id, ArrayList<VariableConstant> parametres, String tipus) {
        if (existeixSimbol(id)) {
            return false;
        }
        TipusDades aux = TipusDades.valueOf(tipus);
        taulaSimbols.add(new Funcio(id, parametres, aux));
        return true;
    }

    public Simbol usarSimbol(Object id) {
        Simbol simbol;
        for (int i = 0; i < taulaSimbols.size(); i++) {
            simbol = taulaSimbols.get(i);
            if (simbol.getValue().equals(id)) {
                if (simbol instanceof VariableConstant) {
                    VariableConstant aux = (VariableConstant) simbol;
                    return aux;
                }
                if (simbol instanceof Tupla) {
                    Tupla aux = (Tupla) simbol;
                    return aux;
                }
                if (simbol instanceof Funcio) {
                    Funcio aux = (Funcio) simbol;
                    return aux;
                }
                return new Simbol();
            }
        }
        return new Simbol();
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
        return new VariableConstant();
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
        return new Tupla();
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
        return new Funcio();
    }
}
