package compiler.sintactic.Symbols;

public class Tupla extends Simbol {
    private TipusDades valor1;
    private TipusDades valor2;
    private int val1;
    private int val2;
    private int ref;

    public Tupla() {
        super("", TipusDades.NULL);
        valor1 = TipusDades.NULL;
        valor2 = TipusDades.NULL;
    }

    public Tupla(Object id, TipusDades valor1, TipusDades valor2) {
        super(id, TipusDades.NULL);
        this.valor1 = valor1;
        this.valor2 = valor2;
    }

    public Tupla(Object id, String valor1, String valor2) {
        super(id, TipusDades.NULL);
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

    public TipusDades getTipusTupla(int i) {
        if (i == 0) {
            return valor1;
        } else if (i == 1) {
            return valor2;
        }
        return TipusDades.NULL;
    }

    public int getVal(int i) {
        if (i == 0) {
            return val1;
        } else {
            return val2;
        }

    }

    public void setVal1(int val1) {
        this.val1 = val1;
    }

    public void setVal2(int val2) {
        this.val2 = val2;
    }

    public int getRef() {
        return ref;
    }

    public void setRef(int ref) {
        this.ref = ref;
    }
}
