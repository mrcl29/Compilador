import java.io.*;

import compiler.lexic.Scanner;
import compiler.sintactic.Parser;

public class Executar {
    private static final Boolean compilar = true;

    private static final String JFLEX_PATH = "./lib/JFlex/jflex-full-1.8.2.jar";
    private static final String CUP_PATH = "./lib/JavaCUP/java-cup-11b.jar";
    private static final String UTILS_PATH = "./src/compiler/sintactic/Symbols/*.java";
    private static final String C3A_PATH = "./src/compiler/c3a/*.java";
    private static final String LEXIC_PATH = "./src/compiler/lexic/";
    private static final String SINTACTIC_PATH = "./src/compiler/sintactic/";
    private static final String LEXER_FILE = LEXIC_PATH + "Lexer.jflex";
    private static final String CUP_FILE = SINTACTIC_PATH + "Parser.cup";

    public static void main(String[] args) {
        try {
            if (compilar) {
                System.err.println("\n------------------------------------");
                System.err.println("------------------ Compilar Léxico ------------------");
                System.err.println("------------------------------------");
                compileLexer();
                System.err.println("------------------------------------");
                System.err.println("------------------ Compilar Sintáctico ------------------");
                System.err.println("------------------------------------");
                compileParser();
                System.err.println("\n------------------------------------");
                System.err.println("Compilando archivos generados...");
                System.err.println("------------------------------------");
                compileGeneratedFiles();
            }
            System.err.println("\n------------------------------------");
            System.err.println("Ejecutando análisis...");
            System.err.println("------------------------------------\n");
            executeAnalysis();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void compileLexer() throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder(
                "java", "-Xshare:off", "-jar", JFLEX_PATH, "-d", LEXIC_PATH, LEXER_FILE);
        processBuilder.inheritIO();
        Process process = processBuilder.start();
        process.waitFor();
    }

    private static void compileParser() throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder(
                "java", "-Xshare:off", "-jar", CUP_PATH, "-interface", "-parser", "Parser",
                "-destdir", SINTACTIC_PATH, CUP_FILE);
        processBuilder.inheritIO();
        Process process = processBuilder.start();
        process.waitFor();
    }

    private static void compileGeneratedFiles() throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder("javac", "--release",
                "20", "-cp", CUP_PATH, "-d", "./bin",
                LEXIC_PATH + "Scanner.java", SINTACTIC_PATH + "Parser.java", SINTACTIC_PATH +
                        "ParserSym.java",
                UTILS_PATH, C3A_PATH);
        processBuilder.inheritIO();
        Process process = processBuilder.start();
        process.waitFor();
    }

    private static void executeAnalysis() {
        try {
            FileReader sourceFile = new FileReader("src/programa.txt");
            Scanner lexer = new Scanner(sourceFile);
            Parser parser = new Parser(lexer);
            parser.parse();
            System.out.println("Parsing completed successfully!");
        } catch (Exception e) {
            System.err.println("Error during parsing:");
            e.printStackTrace();
        }
    }
}
