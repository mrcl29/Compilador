package compiler.sintactic.Symbols;

import java.util.ArrayList;
import compiler.sintactic.Symbols.Simbol.tipusDades;

public class TaulaSimbols {
    private ArrayList<Simbol> taulaSimbols;

    public TaulaSimbols() {
        taulaSimbols = new ArrayList<Simbol>();
    }

    public boolean declararVariableConstant(Object id, String tipus, boolean constant) throws Exception {
        Simbol simbol;
        for (int i = 0; i < taulaSimbols.size(); i++) {
            simbol = taulaSimbols.get(i);
            if (simbol instanceof VariableConstant) {
                if (simbol.getValue().equals(id)) {
                    return false;
                }
            }
        }
        tipusDades aux = tipusDades.valueOf(tipus);
        taulaSimbols.add(new VariableConstant(id, aux, constant));
        return true;
    }

    public boolean declararTupla(Object id, String tipus1, String tipus2) throws Exception {
        Simbol simbol;
        for (int i = 0; i < taulaSimbols.size(); i++) {
            simbol = taulaSimbols.get(i);
            if (simbol instanceof Tupla) {
                if (simbol.getValue().equals(id)) {
                    return false;
                }
            }
        }
        tipusDades aux1 = tipusDades.valueOf(tipus1);
        tipusDades aux2 = tipusDades.valueOf(tipus2);
        taulaSimbols.add(new Tupla(id, aux1, aux2));
        return true;
    }

    public boolean declararFuncio(Object id, ArrayList<VariableConstant> parametres, String tipus) throws Exception {
        Simbol simbol;
        for (int i = 0; i < taulaSimbols.size(); i++) {
            simbol = taulaSimbols.get(i);
            if (simbol instanceof Funcio) {
                if (simbol.getValue().equals(id)) {
                    return false;
                }
            }
        }
        tipusDades aux = tipusDades.valueOf(tipus);
        taulaSimbols.add(new Funcio(id, parametres, aux));
        return true;
    }

    public void usarVariableConstant(Object id, boolean assignacio) throws Exception {
        VariableConstant simbol;
        for (int i = 0; i < taulaSimbols.size(); i++) {
            simbol = (VariableConstant) taulaSimbols.get(i);
            if (simbol.getValue().equals(id)) {
                if (assignacio && simbol.isEsConstant()) {
                    throw new Exception("No se puede assignar un valor nuevo a la constante '" + id + "'");
                }
                return;
            }
        }
        throw new Exception("La variable o constante con id '" + id + "' no ha sido declarada");
    }
}
