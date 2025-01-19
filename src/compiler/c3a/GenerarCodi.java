package compiler.c3a;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import compiler.sintactic.Symbols.*;

public class GenerarCodi {
    private static String fitxer = "codi3a.txt";
    private static BufferedWriter buf;
    private static int nv = -1;
    private static int np = -1;
    private static int ne = -1;
    private static ArrayList<String> instruccions1 = new ArrayList<String>();
    ArrayList<Integer> instruccions2 = new ArrayList<Integer>();
    ArrayList<Integer> instruccions3 = new ArrayList<Integer>();
    ArrayList<Integer> instruccions4 = new ArrayList<Integer>();
    ArrayList<Simbol> tv = new ArrayList<Simbol>();
    ArrayList<Funcio> tp = new ArrayList<Funcio>();
    ArrayList<Integer> te = new ArrayList<Integer>();

    public GenerarCodi() {
        try {
            buf = new BufferedWriter(new FileWriter(fitxer));
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public void escriure(String texte) {
        try {
            buf.write(texte);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public void escriureLinia(String texte) {
        try {
            buf.write(texte + "\n");
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public void novaLinia(String texte) {
        try {
            buf.write("\n");
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public void tancar() {
        try {
            buf.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public int novavar(Simbol s) {
        nv = nv + 1;
        tv.add(s);
        return nv;
    }

    public int nouproc(Funcio nouproc) {
        np = np + 1;
        tp.add(nouproc);
        return np;
    }

    public int novaetiqueta() {
        ne = ne + 1;
        te.add(ne);
        return ne;
    }

    public void genera(String d1, int d2, int d3, int d4) {
        instruccions1.add(d1);
        instruccions2.add(d2);
        instruccions3.add(d3);
        instruccions4.add(d4);

        escriureLinia(d1 + " " + d2 + " " + d3 + " " + d4);
    }

    public static int getNe() {
        return ne;
    }

    // private void operacioAritmetica(int t, int E1, int E2, String operacio)
    // throws IOException {
    // instruccions1.add(operacio);
    // instruccions2.add(E1);
    // instruccions3.add(E2);
    // instruccions4.add(t);
    // }

    // private void operacioNegacio(int t, int E1) throws IOException {
    // instruccions1.add("neg");
    // instruccions2.add(E1);
    // instruccions3.add(-2);
    // instruccions4.add(t);
    // }

    // private void operacioLiteral(int t, int lit) throws IOException {
    // instruccions1.add("copy");
    // instruccions2.add(lit);
    // instruccions3.add(-2);
    // instruccions4.add(t);
    // }

    // private void operacioIndireccio(int t, int Rr, int Rd) throws IOException {
    // instruccions1.add("indx_val");
    // instruccions2.add(Rr);
    // instruccions3.add(Rd);
    // instruccions4.add(t);
    // }

    // private void operacioId(int t, int dvalor) throws IOException {
    // instruccions1.add("indx_val");
    // instruccions2.add(dvalor);
    // instruccions3.add(-2);
    // instruccions4.add(t);
    // }

    // private void expresioRelacional(int t, int dvalor) throws IOException {
    // instruccions1.add("indx_val");
    // instruccions2.add(dvalor);
    // instruccions3.add(-2);
    // instruccions4.add(t);
    // }

}
