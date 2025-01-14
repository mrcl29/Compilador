package compiler.sintactic.Symbols;

public class Tupla extends Simbol {
    private TipusDades valor1;
    private TipusDades valor2;

    public Tupla(Object id, TipusDades valor1, TipusDades valor2) {
        super(id);
        this.valor1 = valor1;
        this.valor2 = valor2;
    }

    public Tupla(Object id, String valor1, String valor2) {
        super(id);
        this.valor1 = TipusDades.valueOf(valor1);
        this.valor2 = TipusDades.valueOf(valor2);
    }

    public TipusDades getValor1() {
        return valor1;
    }

    public void setValor1(TipusDades valor1) {
        this.valor1 = valor1;
    }

    public TipusDades getValor2() {
        return valor2;
    }

    public void setValor2(TipusDades valor2) {
        this.valor2 = valor2;
    }

    public TipusDades getTipus(int i) {
        if (i == 1) {
            return valor1;
        } else if (i == 2) {
            return valor2;
        }
        return null;
    }
}
