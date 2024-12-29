package compiler.sintactic.Symbols;

import java.util.ArrayList;
import compiler.sintactic.Symbols.Simbol.tipusDades;

public class TaulaSimbols {
    private ArrayList<Simbol> taulaSimbols;

    public TaulaSimbols() {
        taulaSimbols = new ArrayList<Simbol>();
    }

    public boolean declararVariable(String id, String tipus, int fila, int columna) throws Exception {
        Simbol simbol;
        for (int i = 0; i < taulaSimbols.size(); i++) {
            simbol = taulaSimbols.get(i);
            if (simbol instanceof VariableConstant) {
                if (simbol.getId() == id) {
                    return false;
                }
            }
        }
        tipusDades aux = tipusDades.valueOf(tipus);
        taulaSimbols.add(new VariableConstant(id, aux, false, fila, columna));
        return true;
    }

    public boolean declararConstant(String id, String tipus, int fila, int columna) throws Exception {
        Simbol simbol;
        for (int i = 0; i < taulaSimbols.size(); i++) {
            simbol = taulaSimbols.get(i);
            if (simbol instanceof VariableConstant) {
                if (simbol.getId() == id) {
                    return false;
                }
            }
        }
        tipusDades aux = tipusDades.valueOf(tipus);
        taulaSimbols.add(new VariableConstant(id, aux, true, fila, columna));
        return true;
    }

    public void usarVariableConstant(String id, boolean assignacio) throws Exception {
        VariableConstant simbol;
        for (int i = 0; i < taulaSimbols.size(); i++) {
            simbol = (VariableConstant) taulaSimbols.get(i);
            if (simbol.getId() == id) {
                if (assignacio && simbol.isEsConstant()) {
                    throw new Exception("No se puede assignar un valor nuevo a la constante '" + id + "'");
                }
                return;
            }
        }
        throw new Exception("La variable o constante con id '" + id + "' no ha sido declarada");
    }
}
