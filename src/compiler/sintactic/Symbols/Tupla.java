package compiler.sintactic.Symbols;

import compiler.sintactic.Symbols.Simbol.tipusDades;

public class Tupla extends Simbol {
    private tipusDades valor1;
    private tipusDades valor2;

    public Tupla(String id, tipusDades valor1, tipusDades valor2, int fila, int columna) {
        super(id, fila, columna);
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
