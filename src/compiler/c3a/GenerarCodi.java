package compiler.c3a;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import compiler.sintactic.Symbols.*;

public class GenerarCodi {
    private final String fitxerC3a = "./src/compiler/c3a/codi3a";
    private final String fitxerEnsamblador = "./src/compiler/c3a/ensamblador";
    private final String opt = "Optimitzat.txt";
    private final String noOpt = "NoOptimitzat.txt";
    private final int nbytes = 4;
    private BufferedWriter bufw;
    private BufferedReader bufr;
    private static int nv = -1;
    private static int np = -1;
    private static int ne = -1;
    private static int nmsg = -1;
    ArrayList<String> ins1 = new ArrayList<String>();
    ArrayList<String> ins2 = new ArrayList<String>();
    ArrayList<String> ins3 = new ArrayList<String>();
    ArrayList<String> ins4 = new ArrayList<String>();
    ArrayList<Simbol> tv = new ArrayList<Simbol>();
    ArrayList<Funcio> tp = new ArrayList<Funcio>();
    ArrayList<Integer> te = new ArrayList<Integer>();

    public GenerarCodi() {
        try {
            bufw = new BufferedWriter(new FileWriter(fitxerC3a + noOpt));
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public void generarCodi() {
        crearEnsamblador(noOpt);
        optimitzarCodi();
        crearEnsamblador(opt);
    }

    public void optimitzarCodi() {
        try {
            bufw = new BufferedWriter(new FileWriter(fitxerC3a + opt));

            for (int i = 0; i < ins1.size(); i++) {
                switch (ins1.get(i)) {
                    case "copy":
                        bufw.write(asignacio(ins4.get(i), ins2.get(i)));
                        break;
                    case "add":
                        bufw.write(suma(ins4.get(i), ins2.get(i), ins3.get(i)));
                        break;
                    case "sub":
                        bufw.write(resta(ins4.get(i), ins2.get(i), ins3.get(i)));
                        break;
                    case "neg":
                        bufw.write(negacio(ins4.get(i), ins2.get(i)));
                        break;
                    case "prod":
                        bufw.write(producte(ins4.get(i), ins2.get(i), ins3.get(i)));
                        break;
                    case "ind_ass":
                        bufw.write(indexAssignat(ins4.get(i), ins3.get(i), ins2.get(i)));
                        break;
                    case "ind_val":
                        bufw.write(valorIndexat(ins4.get(i), ins2.get(i), ins3.get(i)));
                        break;
                    case "skip":
                        bufw.write(etiqueta(ins4.get(i)));
                        break;
                    case "goto":
                        bufw.write(botar(ins4.get(i)));
                        break;
                    case "if_LT":
                        try {
                            // Brancaments adjacents
                            if (ins1.get(i + 1) == "goto" && ins1.get(i + 2) == "skip"
                                    && ins4.get(i + 2) == ins4.get(i)) {
                                ins1.set(i, "if_GE"); // invertim condició
                                ins4.set(i, ins4.get(i + 1)); // bot a final

                                // eliminam goto
                                ins1.remove(i + 1);
                                ins2.remove(i + 1);
                                ins3.remove(i + 1);
                                ins4.remove(i + 1);
                                i--;
                                break;
                            }
                        } catch (IndexOutOfBoundsException e) {
                        }
                        bufw.write(siMenor(ins2.get(i), ins3.get(i), ins4.get(i)));
                        break;
                    case "if_EQ":
                        try {
                            // Brancaments adjacents
                            if (ins1.get(i + 1) == "goto" && ins1.get(i + 2) == "skip"
                                    && ins4.get(i + 2) == ins4.get(i)) {
                                ins1.set(i, "if_NE"); // invertim condició
                                ins4.set(i, ins4.get(i + 1)); // bot a final

                                // eliminam goto
                                ins1.remove(i + 1);
                                ins2.remove(i + 1);
                                ins3.remove(i + 1);
                                ins4.remove(i + 1);
                                i--;
                                break;
                            }
                        } catch (IndexOutOfBoundsException e) {
                        }
                        bufw.write(siIguals(ins2.get(i), ins3.get(i), ins4.get(i)));
                        break;
                    case "if_NE":
                        try {
                            // Brancaments adjacents
                            if (ins1.get(i + 1) == "goto" && ins1.get(i + 2) == "skip"
                                    && ins4.get(i + 2) == ins4.get(i)) {
                                ins1.set(i, "if_EQ"); // invertim condició
                                ins4.set(i, ins4.get(i + 1)); // bot a final

                                // eliminam goto
                                ins1.remove(i + 1);
                                ins2.remove(i + 1);
                                ins3.remove(i + 1);
                                ins4.remove(i + 1);
                                i--;
                                break;
                            }
                        } catch (IndexOutOfBoundsException e) {
                        }
                        bufw.write(siNoIguals(ins2.get(i), ins3.get(i), ins4.get(i)));
                        break;
                    case "if_GE":
                        try {
                            // Brancaments adjacents
                            if (ins1.get(i + 1) == "goto" && ins1.get(i + 2) == "skip"
                                    && ins4.get(i + 2) == ins4.get(i)) {
                                ins1.set(i, "if_LT"); // invertim condició
                                ins4.set(i, ins4.get(i + 1)); // bot a final

                                // eliminam goto
                                ins1.remove(i + 1);
                                ins2.remove(i + 1);
                                ins3.remove(i + 1);
                                ins4.remove(i + 1);
                                i--;
                                break;
                            }
                        } catch (IndexOutOfBoundsException e) {
                        }
                        bufw.write(siMajorIgual(ins2.get(i), ins3.get(i), ins4.get(i)));
                        break;
                    case "call":
                        bufw.write(crida(ins2.get(i), ins4.get(i)));
                        break;
                    case "rtn":
                        bufw.write(retorn(ins4.get(i)));
                        break;
                    case "in":
                        bufw.write(entrada(ins4.get(i), ins2.get(i)));
                        break;
                    case "out":
                        bufw.write(imprimir(ins3.get(i), ins2.get(i), ins4.get(i)));
                        break;
                    default:
                        break;
                }
            }
        } catch (IOException e) {
            System.err.println(e);
        } finally {
            try {
                bufw.close();
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }

    public void crearEnsamblador(String fitxer) {
        try {
            bufr = new BufferedReader(new FileReader(fitxerC3a + fitxer));
            bufw = new BufferedWriter(new FileWriter(fitxerEnsamblador + fitxer));

            bufw.write(instruccionsInicials());
            for (int i = 0; i < ins1.size(); i++) {
                switch (ins1.get(i)) {
                    case "copy":
                        bufw.write(asignacio(ins4.get(i), ins2.get(i)));
                        break;
                    case "add":
                        bufw.write(suma(ins4.get(i), ins2.get(i), ins3.get(i)));
                        break;
                    case "sub":
                        bufw.write(resta(ins4.get(i), ins2.get(i), ins3.get(i)));
                        break;
                    case "neg":
                        bufw.write(negacio(ins4.get(i), ins2.get(i)));
                        break;
                    case "prod":
                        bufw.write(producte(ins4.get(i), ins2.get(i), ins3.get(i)));
                        break;
                    case "ind_ass":
                        bufw.write(indexAssignat(ins4.get(i), ins3.get(i), ins2.get(i)));
                        break;
                    case "ind_val":
                        bufw.write(valorIndexat(ins4.get(i), ins2.get(i), ins3.get(i)));
                        break;
                    case "skip":
                        bufw.write(etiqueta(ins4.get(i)));
                        break;
                    case "goto":
                        bufw.write(botar(ins4.get(i)));
                        break;
                    case "if_EQ":
                        bufw.write(siIguals(ins2.get(i), ins3.get(i), ins4.get(i)));
                        break;
                    case "if_NE":
                        bufw.write(siNoIguals(ins2.get(i), ins3.get(i), ins4.get(i)));
                        break;
                    case "if_GE":
                        bufw.write(siMajorIgual(ins2.get(i), ins3.get(i), ins4.get(i)));
                        break;
                    case "call":
                        bufw.write(crida(ins2.get(i), ins4.get(i)));
                        break;
                    case "rtn":
                        bufw.write(retorn(ins4.get(i)));
                        break;
                    case "in":
                        bufw.write(entrada(ins4.get(i), ins2.get(i)));
                        break;
                    case "out":
                        bufw.write(imprimir(ins3.get(i), ins2.get(i), ins4.get(i)));
                        break;
                    default:
                        break;
                }
            }
            bufw.write(instruccionsFinals());
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
    private String asignacio(String a, String b) {
        String ins = "\n    ; " + a + " = " + b + "\n";
        a = pasaANumero(a);
        b = pasaANumero(b);

        ins += "    MOVE.L    " + b + ", D0 \n";
        ins += "    MOVE.L    D0, " + a + " \n";

        return ins;
    }

    // a = b + c
    private String suma(String a, String b, String c) {
        String ins = "\n    ; " + a + " = " + b + " + " + c + "\n";
        a = pasaANumero(a);
        b = pasaANumero(b);
        c = pasaANumero(c);

        ins += "    MOVE.L    " + b + ", D0 \n";
        ins += "    MOVE.L    " + c + ", D1 \n";
        ins += "    ADD.L     D1, D0 \n";
        ins += "    MOVE.L    D0, " + a + " \n";

        return ins;
    }

    // a = b - c
    private String resta(String a, String b, String c) {
        String ins = "\n    ; " + a + " = " + b + " - " + c + "\n";
        a = pasaANumero(a);
        b = pasaANumero(b);
        c = pasaANumero(c);

        ins += "    MOVE.L    " + b + ", D0 \n";
        ins += "    MOVE.L    " + c + ", D1 \n";
        ins += "    SUB.L     D1, D0 \n";
        ins += "    MOVE.L    D0, " + a + " \n";

        return ins;
    }

    // a = -b
    private String negacio(String a, String b) {
        String ins = "\n    ; " + a + " = -" + b + "\n";
        a = pasaANumero(a);
        b = pasaANumero(b);

        ins += "    MOVE.L    " + b + ", D0 \n";
        ins += "    NEG.L     D0 \n";
        ins += "    MOVE.L    D0, " + a + " \n";

        return ins;
    }

    // a = b * c
    private String producte(String a, String b, String c) {
        String ins = "\n    ; " + a + " = " + b + " * " + c + " \n";
        a = pasaANumero(a);
        b = pasaANumero(b);
        c = pasaANumero(c);

        ins += "    MOVE.L    " + b + ", D0 \n";
        ins += "    MOVE.L    " + c + ", D1 \n";
        ins += "    MULS      D1, D0 \n";
        ins += "    MOVE.L    D0, " + a + " \n";

        return ins;
    }

    // a[b] = c
    private String indexAssignat(String a, String b, String c) {
        String ins = "\n    ; " + a + "[" + b + "] = " + c + "\n";
        a = pasaANumero(a);
        b = pasaANumero(b);
        c = pasaANumero(c);

        ins += "    MOVE.L    " + b + ", D0 \n";
        ins += "    LEA       " + a + ", A0 \n";
        ins += "    ADD.L     D0, A0 \n";
        ins += "    MOVE.L    " + c + ", D1 \n";
        ins += "    MOVE.L    D1, (A0) \n";

        return ins;
    }

    // a = b[c]
    private String valorIndexat(String a, String b, String c) {
        String ins = "\n    ; " + a + " = " + b + "[" + c + "]\n";
        a = pasaANumero(a);
        b = pasaANumero(b);
        c = pasaANumero(c);

        ins += "    MOVE.L    " + c + ", D0 \n";
        ins += "    LEA       " + b + ", A0 \n";
        ins += "    ADD.L     D0, A0 \n";
        ins += "    MOVE.L    (A0), D1 \n";
        ins += "    MOVE.L    D1, " + a + " \n";

        return ins;
    }

    // e:
    private String etiqueta(String e) {
        String ins = "\n    ; " + e + "\n";
        e = pasaANumero(e);

        ins += "E" + e + ": \n";

        return ins;
    }

    // goto e
    private String botar(String e) {
        String ins = "\n    ; goto " + e + "\n";
        e = pasaANumero(e);

        ins += "    BRA       E" + e + " \n";

        return ins;
    }

    // if a < b then goto e
    private String siMenor(String a, String b, String e) {
        String ins = "\n    ; if " + a + " < " + b + " then goto " + e + " \n";
        a = pasaANumero(a);
        b = pasaANumero(b);
        e = pasaANumero(e);

        ins += "    MOVE.L    " + a + ", D0 \n";
        ins += "    MOVE.L    " + b + ", D1 \n";
        ins += "    CMP.L     D1, D0 \n";
        ins += "    BLT       E" + e + " \n";

        return ins;
    }

    // if a == b then goto e
    private String siIguals(String a, String b, String e) {
        String ins = "\n    ; if " + a + " == " + b + " then goto " + e + " \n";
        a = pasaANumero(a);
        b = pasaANumero(b);
        e = pasaANumero(e);

        ins += "    MOVE.L    " + a + ", D0 \n";
        ins += "    MOVE.L    " + b + ", D1 \n";
        ins += "    CMP.L     D1, D0 \n";
        ins += "    BEQ       E" + e + " \n";

        return ins;
    }

    // if a != b then goto e
    private String siNoIguals(String a, String b, String e) {
        String ins = "\n    ; if " + a + " != " + b + " then goto " + e + " \n";
        a = pasaANumero(a);
        b = pasaANumero(b);
        e = pasaANumero(e);

        ins += "    MOVE.L    " + a + ", D0 \n";
        ins += "    MOVE.L    " + b + ", D1 \n";
        ins += "    CMP.L     D1, D0 \n";
        ins += "    BNE       E" + e + " \n";

        return ins;
    }

    // if a >= b then goto e
    private String siMajorIgual(String a, String b, String e) {
        String ins = "\n    ; if " + a + " >= " + b + " then goto " + e + " \n";
        a = pasaANumero(a);
        b = pasaANumero(b);
        e = pasaANumero(e);

        ins += "    MOVE.L    " + a + ", D0 \n";
        ins += "    MOVE.L    " + b + ", D1 \n";
        ins += "    CMP.L     D1, D0 \n";
        ins += "    BGE       E" + e + " \n";

        return ins;
    }

    // a = e()
    private String crida(String a, String e) {
        String ins = "\n    ; " + a + " = " + e + "() \n";
        a = pasaANumero(a);
        e = pasaANumero(e);

        ins += "    JSR       E" + e + " \n";
        ins += "    MOVE.L    D0, " + a + " \n";

        return ins;
    }

    // rtrn a
    private String retorn(String a) {
        String ins = "\n    ; rtrn " + a + " \n";
        a = pasaANumero(a);

        ins += "    MOVE.L    " + a + ", D0\n";
        ins += "    RTS \n";

        return ins;
    }

    // a = in(b)
    private String entrada(String a, String b) {
        String ins = "\n    ; in(" + a + ") \n";
        a = pasaANumero(a);
        b = pasaANumero(b);

        ins += "    MOVE.L    " + b + ", D0 \n";
        ins += "    JSR       VALIDATE_INPUT \n";
        ins += "    MOVE.L    D1, " + a + " \n";

        return ins;
    }

    // out(a::b) cuando acaba salto a c
    private String imprimir(String a, String b, String c) {
        String ins = "\n    ; out(" + a + " :: " + b + ") \n";
        a = pasaANumero(a);
        b = pasaANumero(b);
        c = pasaANumero(c);

        String aux1 = pasaANumero(novaetiqueta());
        String aux2 = pasaANumero(novaetiqueta());

        ins += "    MOVE.L    " + b + ", D0 \n";
        ins += "    MOVE.L    " + a + ", D1 \n";

        ins += "    CMP.L     #1, D0 \n";
        ins += "    BNE       E" + aux1 + "   \n";
        ins += "    JSR       PRINT_INTEGER   \n";
        ins += "    BRA       E" + c + "   \n";
        ins += "E" + aux1 + ":   \n";

        ins += "    CMP.L     #2, D0 \n";
        ins += "    BNE       E" + aux2 + "   \n";
        ins += "    JSR       PRINT_BOOLEAN   \n";
        ins += "    BRA       E" + c + "   \n";
        ins += "E" + aux2 + ":   \n";

        ins += "    LEA       ERROR_MSG, A1 \n";
        ins += "    MOVE.W  #14, D0 \n";
        ins += "    TRAP    #15 \n";

        ins += "E" + c + ": \n";

        return ins;
    }

    private String pasaANumero(String x) {
        if (x.length() > 1) {
            if (x.charAt(0) == 't') {
                String aux = x.substring(1);
                if (aux == "0") {
                    return "(A7)";
                }
                aux = String.valueOf(Integer.parseInt(aux) * nbytes);
                return aux + "(A7)";
            } else if (x.charAt(0) == 'e') {
                String aux = x.substring(1);
                return aux;
            }
        }
        return "#" + x;
    }

    private String instruccionsInicials() {
        String ins = "";

        ins += "*----------------------------------------------------------- \n";
        ins += "* Title      : Codi Ensamblador 68K\n";
        ins += "* Written by : Marc Llobera Villalonga\n";
        ins += "* Description: Codi ensamblador generat per el compilador\n";
        ins += "*              de Marc Llobera per a la pràctica de \n";
        ins += "*              l'assignatura de Compiladors. \n";
        ins += "*----------------------------------------------------------- \n";
        ins += "    ORG    $1000 \n";
        ins += "START:                  ; first instruction of program \n";
        ins += "\n";

        return ins;
    }

    private String instruccionsFinals() {
        String ins = "\n; FINAL \n\n";

        ins += "END: \n";
        ins += "    SIMHALT \n";

        ////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////

        ins += "PRINT_INTEGER: \n";
        ins += "    MOVE.L  D1, D2 \n";
        ins += "    LEA     BUFFER, A0 \n";
        ins += "    JSR     INT_TO_STR \n";
        ins += "    BRA     PRINT_STRING \n";

        ins += "PRINT_BOOLEAN: \n";
        ins += "    CMP.L   #-1, D1 \n";
        ins += "    BEQ     PRINT_TRUE \n";

        ins += "    CMP.L   #0, D1 \n";
        ins += "    BEQ     PRINT_FALSE \n";

        ins += "    LEA     ERROR_MSG, A1 \n";
        ins += "    MOVE.W  #14, D0 \n";
        ins += "    TRAP    #15 \n";
        ins += "    RTS   \n";

        ins += "PRINT_TRUE: \n";
        ins += "    LEA     TRUE_MSG, A1 \n";
        ins += "    MOVE.W  #14, D0 \n";
        ins += "    TRAP    #15 \n";
        ins += "    RTS   \n";

        ins += "PRINT_FALSE: \n";
        ins += "    LEA     FALSE_MSG, A1 \n";
        ins += "    MOVE.W  #14, D0 \n";
        ins += "    TRAP    #15 \n";
        ins += "    RTS   \n";

        ins += "PRINT_STRING: \n";
        ins += "    MOVE.L  A0, A1 \n";
        ins += "    MOVE.W  #14, D0 \n";
        ins += "    TRAP    #15 \n";

        ins += "    LEA     NEWLINE, A1 \n";
        ins += "    MOVE.W  #14, D0 \n";
        ins += "    TRAP    #15 \n";

        ins += "    RTS \n";

        ins += "INT_TO_STR: \n";
        ins += "    LEA     BUFFER+10, A1 \n";
        ins += "    CLR.B   (A1) \n";
        ins += "    MOVE.L  D2, D3 \n";
        ins += "    TST.L   D3 \n";
        ins += "    BPL     POSITIVE \n";
        ins += "    NEG.L   D3 \n";
        ins += "    MOVE.B  #'-', D4 \n";

        ins += "POSITIVE: \n";
        ins += "    MOVE.B  #0, D5 \n";

        ins += "CONVERT_LOOP: \n";
        ins += "    DIVU    #10, D3 \n";
        ins += "    SWAP    D3 \n";
        ins += "    ADD.B   #'0', D3 \n";
        ins += "    MOVE.B  D3, -(A1) \n";
        ins += "    CLR.W   D3 \n";
        ins += "    SWAP    D3 \n";
        ins += "    ADDQ.B  #1, D5 \n";
        ins += "    TST.L   D3 \n";
        ins += "    BNE     CONVERT_LOOP \n";

        ins += "    TST.B   D4 \n";
        ins += "    BEQ     NO_SIGN \n";
        ins += "    MOVE.B  D4, -(A1) \n";

        ins += "NO_SIGN: \n";
        ins += "    MOVE.L  A1, A0 \n";
        ins += "    RTS \n";

        ////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////

        ins += "VALIDATE_INPUT: \n";
        ins += "    CMP.B   #1, D0 \n";
        ins += "    BEQ     INTEGER_INPUT \n";
        ins += "    CMP.B   #2, D0 \n";
        ins += "    BEQ     BOOLEAN_INPUT \n";
        ins += "    RTS \n";

        ins += "INTEGER_INPUT: \n";
        ins += "    LEA     PROMPT_INT, A0 \n";
        ins += "    JSR     PRINT_STRING \n";
        ins += "    JSR     READ_STRING \n";
        ins += "    JSR     CONVERT_TO_INT \n";
        ins += "    TST.B   D1 \n";
        ins += "    BEQ     VALID_INT \n";
        ins += "    LEA     ERROR_MSG, A0 \n";
        ins += "    JSR     PRINT_STRING \n";
        ins += "    MOVE.L  #0, D1 \n";
        ins += "    RTS \n";

        ins += "VALID_INT: \n";
        ins += "    MOVE.L  D2, D1 \n";
        ins += "    RTS \n";

        ins += "BOOLEAN_INPUT: \n";
        ins += "    LEA     PROMPT_BOOL, A0 \n";
        ins += "    JSR     PRINT_STRING \n";
        ins += "    JSR     READ_STRING \n";
        ins += "    JSR     CONVERT_TO_BOOL \n";
        ins += "    TST.B   D1 \n";
        ins += "    BEQ     VALID_BOOL \n";
        ins += "    LEA     ERROR_MSG, A0 \n";
        ins += "    JSR     PRINT_STRING \n";
        ins += "    MOVE.L  #0, D1 \n";
        ins += "    RTS \n";

        ins += "VALID_BOOL: \n";
        ins += "    MOVE.L  D2, D1  \n";
        ins += "    RTS \n";

        ins += "READ_STRING: \n";
        ins += "    LEA     INPUT_BUFFER, A1  \n";
        ins += "READ_LOOP: \n";
        ins += "    MOVE.W  #5, D0 \n";
        ins += "    TRAP    #15 \n";
        ins += "    CMP.B   #$0D, D1  \n";
        ins += "    BEQ     READ_DONE  \n";
        ins += "    MOVE.B  D1, (A1)+  \n";
        ins += "    BRA     READ_LOOP  \n";
        ins += "READ_DONE: \n";
        ins += "    CLR.B   (A1)  \n";
        ins += "    RTS  \n";

        ins += "CONVERT_TO_INT: \n";
        ins += "    CLR.L   D2  \n";
        ins += "    CLR.B   D3  \n";
        ins += "    LEA     INPUT_BUFFER, A1 \n";

        ins += "    MOVE.B  (A1), D1  \n";
        ins += "    CMP.B   #'-', D1   \n";
        ins += "    BNE     CONVERT_INT_LOOP  \n";
        ins += "    MOVE.B  #1, D3    \n";
        ins += "    ADDQ.L  #1, A1   \n";

        ins += "CONVERT_INT_LOOP: \n";
        ins += "    MOVE.B  (A1)+, D1  \n";
        ins += "    BEQ     CONVERT_INT_DONE  \n";
        ins += "    CMP.B   #'0', D1  \n";
        ins += "    BLT     INVALID_INPUT  \n";
        ins += "    CMP.B   #'9', D1    \n";
        ins += "    BGT     INVALID_INPUT \n";
        ins += "    SUB.B   #'0', D1  \n";
        ins += "    MULU.W  #10, D2   \n";
        ins += "    ADD.L   D1, D2  \n";
        ins += "    BRA     CONVERT_INT_LOOP  \n";

        ins += "CONVERT_INT_DONE: \n";
        ins += "    TST.B   D3  \n";
        ins += "    BEQ     POSITIVE_NUMBER  \n";
        ins += "    NEG.L   D2 \n";

        ins += "POSITIVE_NUMBER: \n";
        ins += "    CLR.B   D1   \n";
        ins += "    RTS  \n";

        ins += "INVALID_INPUT: \n";
        ins += "    MOVE.B  #1, D1   \n";
        ins += "    RTS    \n";

        ins += "CONVERT_TO_BOOL: \n";
        ins += "    LEA     INPUT_BUFFER, A1 \n";
        ins += "    MOVE.B  (A1)+, D1  \n";
        ins += "    CMP.B   #'T', D1   \n";
        ins += "    BEQ     CHECK_TRUE \n";
        ins += "    CMP.B   #'F', D1    \n";
        ins += "    BEQ     CHECK_FALSE   \n";
        ins += "    BRA     INVALID_INPUT  \n";

        ins += "CHECK_TRUE: \n";
        ins += "    MOVE.B  (A1)+, D1 \n";
        ins += "    CMP.B   #'R', D1 \n";
        ins += "    BNE     INVALID_INPUT \n";
        ins += "    MOVE.B  (A1)+, D1         \n";
        ins += "    CMP.B   #'U', D1  \n";
        ins += "    BNE     INVALID_INPUT   \n";
        ins += "    MOVE.B  (A1)+, D1    \n";
        ins += "    CMP.B   #'E', D1 \n";
        ins += "    BNE     INVALID_INPUT  \n";
        ins += "    MOVE.L  #-1, D2  \n";
        ins += "    CLR.B   D1  \n";
        ins += "    RTS \n";

        ins += "CHECK_FALSE: \n";
        ins += "    MOVE.B  (A1)+, D1 \n";
        ins += "    CMP.B   #'A', D1  \n";
        ins += "    BNE     INVALID_INPUT  \n";
        ins += "    MOVE.B  (A1)+, D1  \n";
        ins += "    CMP.B   #'L', D1   \n";
        ins += "    BNE     INVALID_INPUT  \n";
        ins += "    MOVE.B  (A1)+, D1  \n";
        ins += "    CMP.B   #'S', D1   \n";
        ins += "    BNE     INVALID_INPUT  \n";
        ins += "    MOVE.B  (A1)+, D1  \n";
        ins += "    CMP.B   #'E', D1  \n";
        ins += "    BNE     INVALID_INPUT   \n";
        ins += "    CLR.L   D2  \n";
        ins += "    CLR.B   D1   \n";
        ins += "    RTS \n";

        ////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////

        ins += "CR           EQU     $0D \n";
        ins += "LF           EQU     $0A \n";

        ins += "TRUE_MSG     DC.B    'TRUE',CR,LF, 0 \n";
        ins += "FALSE_MSG    DC.B    'FALSE',CR,LF, 0 \n";
        ins += "ERROR_MSG    DC.B    'ERROR',CR,LF, 0 \n";
        ins += "NEWLINE      DC.B    ' ',CR,LF, 0  \n";
        ins += "BUFFER       DS.B    20 \n";

        ins += "PROMPT_INT   DC.B    'Enter an integer: ', 0 \n";
        ins += "PROMPT_BOOL  DC.B    'Enter a boolean (TRUE/FALSE): ', 0 \n";
        ins += "INPUT_BUFFER DS.B    100 \n";

        ins += "    END    START \n";

        return ins;
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

    public String noumissatge() {
        nmsg = nmsg + 1;
        te.add(nmsg);
        return "msg" + nmsg;
    }

    public void genera(String d1, String d2, String d3, String d4) {
        ins1.add(d1);
        ins2.add(d2);
        ins3.add(d3);
        ins4.add(d4);

        System.out.println(d1 + " " + d2 + " " + d3 + " " + d4);
        escriureLinia(d1 + " " + d2 + " " + d3 + " " + d4);
    }

    public static int getNe() {
        return ne;
    }

}
