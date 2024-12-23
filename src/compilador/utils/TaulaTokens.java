package compilador.utils;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Stack;

import java_cup.reduce_action;

public class TaulaTokens {
    private static ArrayList<Token> taulaTokens;
    // Pila per guardar el tipus de les dades que es van definint
    private Stack<Token.tipusDades> tipusVariables;
    // Pila per guardar el conjunt de variables, constants y tuples
    public ArrayList<Object> identificadors;
    // Guarda els parámetres d'una funció
    private ArrayList<Object> parametres;
    private boolean parCorrectes;
    // Guarda les variables, constants y tuples usades en el cos d'una funció
    private ArrayList<Object> identificadorsUsats;
    // Guarda els parámetres que criden a una funció
    private ArrayList<Object> parametresCridada;

    public TaulaTokens() {
        taulaTokens = new ArrayList<Token>();
        tipusVariables = new Stack<Token.tipusDades>();
        identificadors = new ArrayList<Object>();
        parametres = new ArrayList<Object>();
        parCorrectes = true;
        identificadorsUsats = new ArrayList<Object>();
        parametresCridada = new ArrayList<Object>();
    }

    public void instertarToken(Token token) {
        taulaTokens.add(token);
    }

    public boolean existeixTokenTaulaTokens(Token token) {
        Token tokenTaula;
        for (int i = 0; i < taulaTokens.size(); i++) {
            tokenTaula = taulaTokens.get(i);

            if (token.getClass().isInstance(tokenTaula)) {
                // Dos tokens son iguals si coincideixen en id y en nivell
                if (tokenTaula.getId() == token.getId() && tokenTaula.getNivell() == token.getNivell()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean existeixIdentificadorTaulaTokens(Token token) {
        Token tokenTaula;
        for (int i = 0; i < taulaTokens.size(); i++) {
            tokenTaula = taulaTokens.get(i);
            if (tokenTaula instanceof VariableConstant || tokenTaula instanceof Tupla) {
                // Si tenen el mateix id també refusam si el token de la taula es de nivell
                // Global
                if (tokenTaula.getId() == token.getId()
                        && (tokenTaula.getNivell() == token.getNivell() || tokenTaula.getNivell() == "Global")) {
                    return true;
                }
            }
        }
        return false;
    }
}
