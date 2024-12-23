package compilador.utils;

public class Tupla extends Token {
    private tipusDades valor1;
    private tipusDades valor2;

    public Tupla(String id, tipusDades valor1, tipusDades valor2, String nivell, int fila, int columna) {
        super(id, nivell, fila, columna);
        this.valor1 = valor1;
        this.valor2 = valor2;
    }

    public tipusDades getValor1() {
        return valor1;
    }

    public void setValor1(tipusDades valor1) {
        this.valor1 = valor1;
    }

    public tipusDades getValor2() {
        return valor2;
    }

    public void setValor2(tipusDades valor2) {
        this.valor2 = valor2;
    }
}
