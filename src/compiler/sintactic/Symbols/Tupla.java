package compiler.sintactic.Symbols;

public class Tupla extends Simbol {
    private tipusDades valor1;
    private tipusDades valor2;

    public Tupla(Object id, tipusDades valor1, tipusDades valor2) {
        super(id);
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
