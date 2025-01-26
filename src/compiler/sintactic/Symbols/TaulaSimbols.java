package compiler.sintactic.Symbols;

import java.util.ArrayList;

public class TaulaSimbols {
    private ArrayList<Simbol> taulaSimbols = new ArrayList<Simbol>();

    public TaulaSimbols() {
        taulaSimbols = new ArrayList<Simbol>();
    }

    @Override
    public String toString() {
        String text = "";
        for (int i = 0; i < taulaSimbols.size(); i++) {
            Simbol s = taulaSimbols.get(i);
            text += s.getValue().toString();
            if (s instanceof VariableConstant) {
                VariableConstant v = (VariableConstant) s;
                if (v.isEsConstant()) {
                    text += " [Constant]";
                } else {
                    text += " [Variable]";
                }
            } else if (s instanceof Tupla) {
                text += " [Tupla]";
            } else if (s instanceof Funcio) {
                text += " [Funcio]";
            }
            text += "\n";
        }
        return text + "\n";
    }

    public boolean existeixSimbol(Simbol id, String ambit) {
        Simbol simbol;
        for (int i = 0; i < taulaSimbols.size(); i++) {
            simbol = taulaSimbols.get(i);
            if (simbol.getValue() == id.getValue() && (simbol.getAmbit() == "Global" || simbol.getAmbit() == ambit)) {
                return true;
            }
        }
        return false;
    }

    public boolean declararVariableConstant(VariableConstant varcon) {
        Simbol simbol;
        for (int i = 0; i < taulaSimbols.size(); i++) {
            simbol = taulaSimbols.get(i);
            if (simbol.getValue().equals(varcon.getValue()) && simbol instanceof VariableConstant
                    && (simbol.getAmbit() == "Global" || varcon.getAmbit() == "Global"
                            || simbol.getAmbit() == varcon.getAmbit())) {
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
            if (simbol.getValue().equals(tup.getValue()) && simbol instanceof Tupla
                    && (simbol.getAmbit() == "Global" || tup.getAmbit() == "Global"
                            || simbol.getAmbit() == tup.getAmbit())) {
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
            if (simbol.getValue().equals(fun.getValue()) && simbol instanceof Funcio) {
                return false;
            }
        }
        taulaSimbols.add(fun);
        return true;
    }

    public Simbol usarSimbol(Object id, String ambit) {
        Simbol simbol;
        for (int i = 0; i < taulaSimbols.size(); i++) {
            simbol = taulaSimbols.get(i);
            if (simbol.getValue().equals(id) && simbol.getAmbit() == ambit) {
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

    public VariableConstant usarVariableConstant(Object id, String ambit) {
        Simbol simbol;
        for (int i = 0; i < taulaSimbols.size(); i++) {
            simbol = taulaSimbols.get(i);
            if (simbol instanceof VariableConstant && simbol.getValue().equals(id)
                    && (simbol.getAmbit() == ambit || simbol.getAmbit() == "Global")) {
                VariableConstant aux = (VariableConstant) simbol;
                return aux;
            }
        }
        return new VariableConstant();
    }

    public Tupla usarTupla(Object id, String ambit) {
        Simbol simbol;
        for (int i = 0; i < taulaSimbols.size(); i++) {
            simbol = taulaSimbols.get(i);
            if (simbol instanceof Tupla && simbol.getValue().equals(id)
                    && (simbol.getAmbit() == ambit || simbol.getAmbit() == "Global")) {
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
