package compilador.utils;

public class Token {
    public static enum tipusDades {
        INTEGER, BOOLEAN
    }

    private String id;
    private String nivell;
    private int fila;
    private int columna;

    public Token(String id, String nivell, int fila, int columna) {
        this.id = id;
        this.nivell = nivell;
        this.fila = fila;
        this.columna = columna;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNivell() {
        return nivell;
    }

    public void setNivel(String nivell) {
        this.nivell = nivell;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

}
