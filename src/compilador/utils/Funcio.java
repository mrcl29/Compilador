package compilador.utils;

import java.util.ArrayList;

public class Funcio extends Token {
    private ArrayList<Object> parametres;
    private tipusDades tipus;

    public Funcio(String id, ArrayList<Object> parametres, tipusDades tipus, String nivell, int fila, int columna) {
        super(id, nivell, fila, columna);
        this.parametres = parametres;
        this.tipus = tipus;
    }

    public ArrayList<Object> getParametres() {
        return parametres;
    }

    public void setParametres(ArrayList<Object> parametres) {
        this.parametres = parametres;
    }

    public tipusDades getTipus() {
        return tipus;
    }

    public void setTipus(tipusDades tipus) {
        this.tipus = tipus;
    }

}
