package compiler.sintactic.Symbols;

public class Simbol {

    public Object value;
    private Integer fila;
    private Integer columna;
    private TipusDades tipus = TipusDades.NULL;
    private int r = -3;
    private int d = -3;
    private int valor;
    private String ambit;
    private int etiqueta = -1;
    private int etiquetafi = -1;

    public Simbol() {
        this.value = "";
    }

    public Simbol(Object value) {
        this.value = value;
    }

    public Simbol(Object value, int valor) {
        this.value = value;
        this.valor = valor;
    }

    public Simbol(Object value, TipusDades tipus) {
        this.tipus = tipus;
        this.value = value;
    }

    public Simbol(Object value, TipusDades tipus, int valor) {
        this.tipus = tipus;
        this.value = value;
        this.valor = valor;
    }

    public Simbol(Integer fila, Integer columna, Object value) {
        this.fila = fila;
        this.columna = columna;
        this.value = value;
    }

    public Simbol(Integer fila, Integer columna, Object value, TipusDades tipus) {
        this.fila = fila;
        this.columna = columna;
        this.value = value;
        this.tipus = tipus;
    }

    public Simbol(Integer fila, Integer columna, Object value, TipusDades tipus, int r) {
        this.fila = fila;
        this.columna = columna;
        this.value = value;
        this.tipus = tipus;
        this.r = r;
    }

    public Simbol(Integer fila, Integer columna, Object value, TipusDades tipus, int r, int valor) {
        this.fila = fila;
        this.columna = columna;
        this.value = value;
        this.valor = valor;
        this.tipus = tipus;
        this.r = r;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Integer getFila() {
        return fila;
    }

    public void setFila(Integer fila) {
        this.fila = fila;
    }

    public Integer getColumna() {
        return columna;
    }

    public void setColumna(Integer columna) {
        this.columna = columna;
    }

    public TipusDades getTipus() {
        return tipus;
    }

    public void setTipus(TipusDades tipus) {
        this.tipus = tipus;
    }

    @Override
    public String toString() {
        return getValue().toString();
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getAmbit() {
        return ambit;
    }

    public void setAmbit(String ambit) {
        this.ambit = ambit;
    }

    public int getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(int etiqueta) {
        this.etiqueta = etiqueta;
    }

    public int getEtiquetafi() {
        return etiquetafi;
    }

    public void setEtiquetafi(int etiquetafi) {
        this.etiquetafi = etiquetafi;
    }

}
