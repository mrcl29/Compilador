package compiler.sintactic.Symbols;

public class Simbol {
    public static enum tipusDades {
        INTEGER, BOOLEAN
    }

    private String id;
    private int fila;
    private int columna;

    public Simbol(String id, int fila, int columna) {
        this.id = id;
        this.fila = fila;
        this.columna = columna;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
