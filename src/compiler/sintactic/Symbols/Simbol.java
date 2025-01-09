package compiler.sintactic.Symbols;

public class Simbol {
    public static enum tipusDades {
        INTEGER, BOOLEAN
    }

    private Object value;
    private Integer fila;
    private Integer columna;

    public Simbol(Object value) {
        this.value = value;
    }

    public Simbol(int fila, int columna, Object value) {
        this.fila = fila;
        this.columna = columna;
        this.value = value;
    }

    public Simbol(Integer fila, Integer columna, Object value) {
        this.fila = fila;
        this.columna = columna;
        this.value = value;
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

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
