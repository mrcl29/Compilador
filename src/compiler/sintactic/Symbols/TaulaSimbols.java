package compiler.sintactic.Symbols;

import java.util.ArrayList;

public class TaulaSimbols {
    private ArrayList<Simbol> taulaSimbols;

    public TaulaSimbols() {
        taulaSimbols = new ArrayList<Simbol>();
    }

    public boolean existeixSimbol(Simbol id) {
        Simbol simbol;
        for (int i = 0; i < taulaSimbols.size(); i++) {
            simbol = taulaSimbols.get(i);
            if (simbol.getValue() == id.getValue()) {
                return true;
            }
        }
        return false;
    }

    public boolean declararVariableConstant(VariableConstant varcon) {
        Simbol simbol;
        for (int i = 0; i < taulaSimbols.size(); i++) {
            simbol = taulaSimbols.get(i);
            if (simbol.getValue() == varcon.getValue() && simbol instanceof VariableConstant) {
                return false;
            }
        }
        taulaSimbols.add(varcon);
        return true;
    }

    public boolean declararTupla(Tupla tup) {
        Simbol simbol;
        for (int i = 0; i < taulaSimbols.size(); i++) {
            simbol = taulaSimbols.get(i);
            if (simbol.getValue() == tup.getValue() && simbol instanceof Tupla) {
                return false;
            }
        }
        taulaSimbols.add(tup);
        return true;
    }

    public boolean declararFuncio(Funcio fun) {
        Simbol simbol;
        for (int i = 0; i < taulaSimbols.size(); i++) {
            simbol = taulaSimbols.get(i);
            if (simbol.getValue() == fun.getValue() && simbol instanceof Funcio) {
                return false;
            }
        }
        taulaSimbols.add(fun);
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

    public boolean actualitzarVariableConstant(VariableConstant id) {
        Simbol simbol;
        for (int i = 0; i < taulaSimbols.size(); i++) {
            simbol = taulaSimbols.get(i);
            if (simbol instanceof VariableConstant && simbol.getValue().equals(id.getValue())) {
                taulaSimbols.set(i, id);
                return true;
            }
        }
        return false;
    }

    public boolean actualitzarTupla(Tupla id) {
        Simbol simbol;
        for (int i = 0; i < taulaSimbols.size(); i++) {
            simbol = taulaSimbols.get(i);
            if (simbol instanceof Tupla && simbol.getValue().equals(id.getValue())) {
                taulaSimbols.set(i, id);
                return true;
            }
        }
        return false;
    }

    public boolean actualitzarFuncio(Funcio id) {
        Simbol simbol;
        for (int i = 0; i < taulaSimbols.size(); i++) {
            simbol = taulaSimbols.get(i);
            if (simbol instanceof Funcio && simbol.getValue().equals(id.getValue())) {
                taulaSimbols.set(i, id);
                return true;
            }
        }
        return false;
    }
}
