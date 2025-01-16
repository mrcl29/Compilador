import java.io.*;
import java.util.concurrent.TimeUnit;

import compiler.lexic.Scanner;
import compiler.sintactic.Parser;

public class Ejecutar {

    private static final String JFLEX_PATH = "./lib/JFlex/jflex-full-1.8.2.jar";
    private static final String CUP_PATH = "./lib/JavaCUP/java-cup-11b.jar";
    private static final String UTILS_PATH = "./src/compiler/sintactic/Symbols/*.java";
    private static final String LEXIC_PATH = "./src/compiler/lexic/";
    private static final String SINTACTIC_PATH = "./src/compiler/sintactic/";
    private static final String LEXER_FILE = LEXIC_PATH + "Lexer.jflex";
    private static final String CUP_FILE = SINTACTIC_PATH + "Parser.cup";
    private static final Boolean noCompilar = false;

    public static void main(String[] args) {
        try {
            if (!noCompilar) {
                System.err.println("------------------------------------");
                System.err.println("------------------ 1. Limpieza ------------------");
                System.err.println("------------------------------------");
                cleanGeneratedFiles();
                System.err.println("------------------------------------");
                System.err.println("------------------ 2. Compilar Léxico ------------------");
                System.err.println("------------------------------------");
                compileLexer();
                System.err.println("------------------------------------");
                System.err.println("------------------ 3. Compilar Sintáctico ------------------");
                System.err.println("------------------------------------");
                compileParser();
                System.err.println("------------------------------------");
                System.err.println("------------------ 4. Compilar archivos generados ------------------");
                System.err.println("------------------------------------");
                compileGeneratedFiles();
                TimeUnit.SECONDS.sleep(1);
            }
            System.err.println("------------------------------------");
            System.err.println("------------------ 5. Ejecutar Análisis ------------------");
            System.err.println("------------------------------------");
            executeAnalysis();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void cleanGeneratedFiles() throws IOException {
        String[] filesToDelete = {
                LEXIC_PATH + "Scanner.java",
                LEXIC_PATH + "Scanner~.java",
                SINTACTIC_PATH + "Parser.java",
                SINTACTIC_PATH + "ParserSym.java"
        };
        for (String filePath : filesToDelete) {
            File file = new File(filePath);
            if (file.exists() && file.delete()) {
                System.out.println("Archivo eliminado: " + filePath);
            }
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
                UTILS_PATH);
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
