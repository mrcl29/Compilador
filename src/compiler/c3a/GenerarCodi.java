package compiler.c3a;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import compiler.sintactic.Symbols.*;

public class GenerarCodi {
    private static String fitxer = "codi3a.txt";
    BufferedWriter buf;
    int nv = -1;
    int np = -1;
    int ne = -1;
    ArrayList<Integer> instruccions1 = new ArrayList<Integer>();
    ArrayList<Integer> instruccions2 = new ArrayList<Integer>();
    ArrayList<Integer> instruccions3 = new ArrayList<Integer>();
    ArrayList<Integer> instruccions4 = new ArrayList<Integer>();
    ArrayList<Integer> tv = new ArrayList<Integer>();
    ArrayList<Funcio> tp = new ArrayList<Funcio>();
    ArrayList<Integer> te = new ArrayList<Integer>();

    public GenerarCodi() throws IOException {
        buf = new BufferedWriter(new FileWriter(fitxer));
    }

    private void escriure(String texte) throws IOException {
        buf.write(texte);
    }

    private void escriureLinia(String texte) throws IOException {
        buf.write(texte + "\n");
    }

    private void novaLinia(String texte) throws IOException {
        buf.write("\n");
    }

    private void tancar() throws IOException {
        buf.close();
    }

    private int novavar() throws IOException {
        nv = nv + 1;
        tv.add(nv);
        return nv;
    }

    private int nouproc(Funcio nouproc) throws IOException {
        np = np + 1;
        tp.add(nouproc);
        return np;
    }

    private int novaetiqueta() throws IOException {
        ne = ne + 1;
        te.add(ne);
        return ne;
    }

    private void genera(String d1, int d2, int d3, int d4) throws IOException {
        // instruccions1.add(d1);
        // instruccions2.add(d2);
        // instruccions3.add(d3);
        // instruccions4.add(d4);

        escriureLinia(d1 + " " + d2 + " " + d3 + " " + d4);
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
