package compiler.sintactic.Symbols;

import java.util.ArrayList;

import compiler.sintactic.Symbols.Simbol.tipusDades;

public class TaulaSimbols {
    private ArrayList<Simbol> taulaSimbols;

    public TaulaSimbols() {
        taulaSimbols = new ArrayList<Simbol>();
    }

    public void declararVariable(String id, tipusDades tipus, int fila, int columna) throws SemanticException {
        Simbol simbol;
        for (int i = 0; i < taulaSimbols.size(); i++) {
            simbol = taulaSimbols.get(i);
            if (simbol.getId() == id) {
                throw new SemanticException("Ya existe una variable o constante con el nombre '" + id + "'");
            }
        }
        taulaSimbols.add(new VariableConstant(id, tipus, false, fila, columna));
    }

    public void declararConstant(String id, tipusDades tipus, int fila, int columna) throws SemanticException {
        Simbol simbol;
        for (int i = 0; i < taulaSimbols.size(); i++) {
            simbol = taulaSimbols.get(i);
            if (simbol.getId() == id) {
                throw new SemanticException("Ya existe una variable o constante con el nombre '" + id + "'");
            }
        }
        taulaSimbols.add(new VariableConstant(id, tipus, true, fila, columna));
    }

    public void usarVariableConstant(String id, boolean assignacio) throws SemanticException {
        VariableConstant simbol;
        for (int i = 0; i < taulaSimbols.size(); i++) {
            simbol = (VariableConstant) taulaSimbols.get(i);
            if (simbol.getId() == id) {
                if (assignacio && simbol.isEsConstant()) {
                    throw new SemanticException("No se puede assignar un valor nuevo a la constante '" + id + "'");
                }
                return;
            }
        }
        throw new SemanticException("La variable o constante con id '" + id + "' no ha sido declarada");
    }

    public static class SemanticException extends Exception {
        public SemanticException(String message) {
            super(message);
        }
    }
}
