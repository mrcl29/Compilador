package compiler.c3a;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import compiler.sintactic.Symbols.*;

public class GenerarCodi {
    private final String fitxerC3a = "./src/compiler/c3a/codi3a.txt";
    private final String fitxerEnsamblador = "./src/compiler/c3a/ensamblador.txt";
    private final int nbytes = 4;
    private BufferedWriter bufw;
    private BufferedReader bufr;
    private static int nv = -1;
    private static int np = -1;
    private static int ne = -1;
    ArrayList<String> instruccions1 = new ArrayList<String>();
    ArrayList<String> instruccions2 = new ArrayList<String>();
    ArrayList<String> instruccions3 = new ArrayList<String>();
    ArrayList<String> instruccions4 = new ArrayList<String>();
    ArrayList<Simbol> tv = new ArrayList<Simbol>();
    ArrayList<Funcio> tp = new ArrayList<Funcio>();
    ArrayList<Integer> te = new ArrayList<Integer>();

    public GenerarCodi() {
        try {
            this.bufw = new BufferedWriter(new FileWriter(fitxerC3a));
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public void crearEnsamblador() {
        try {
            bufr = new BufferedReader(new FileReader(fitxerC3a));
            bufw = new BufferedWriter(new FileWriter(fitxerEnsamblador));
            for (int i = 0; i < instruccions1.size(); i++) {
                switch (instruccions1.get(i)) {
                    case "copy":
                        bufw.write(crearAsignacio(instruccions4.get(i), instruccions2.get(i)));
                        break;
                    default:
                        break;
                }
            }
        } catch (IOException e) {
            System.err.println(e);
        } finally {
            try {
                bufr.close();
                bufw.close();
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }

    // a = b
    private String crearAsignacio(String a, String b) {
        String ai = pasaANumero(a);
        if (isToE(b)) {
            b = pasaANumero(b);
            return "    MOVE.L    " + b + "(A7), D0\n    MOVE.L    D0, " + ai + "(A7) \n";
        } else {
            return "    MOVE.L    # " + b + ", D0\n    MOVE.L    D0, " + ai + "(A7) \n";
        }
    }

    private String pasaANumero(String x) {
        if (x.length() > 1) {
            if (x.charAt(0) == 't' || x.charAt(0) == 'e') {
                String aux = x.substring(1);
                if (aux == "0") {
                    return "";
                }
                aux = String.valueOf(Integer.parseInt(aux) * nbytes);
                return aux;
            }
        }
        return x;
    }

    private boolean isToE(String x) {
        if (x.length() > 1) {
            if (x.charAt(0) == 't' || x.charAt(0) == 'e') {
                return true;
            }
        }
        return false;
    }

    public void escriureLinia(String texte) {
        try {
            bufw.write(texte + "\n");
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public void tancar() {
        try {
            bufw.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public String novavar(Simbol s) {
        nv = nv + 1;
        tv.add(s);
        return "t" + nv;
    }

    public String nouproc(Funcio nouproc) {
        np = np + 1;
        tp.add(nouproc);
        return "n" + np;
    }

    public String novaetiqueta() {
        ne = ne + 1;
        te.add(ne);
        return "e" + ne;
    }

    public void genera(String d1, String d2, String d3, String d4) {
        instruccions1.add(d1);
        instruccions2.add(d2);
        instruccions3.add(d3);
        instruccions4.add(d4);

        System.out.println(d1 + " " + d2 + " " + d3 + " " + d4);
        escriureLinia(d1 + " " + d2 + " " + d3 + " " + d4);
    }

    public static int getNe() {
        return ne;
    }

}
