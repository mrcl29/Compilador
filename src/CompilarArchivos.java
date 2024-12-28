import java.io.*;

public class CompilarArchivos {

    private static final String JFLEX_PATH = "./lib/JFlex/jflex-full-1.8.2.jar";
    private static final String CUP_PATH = "./lib/JavaCUP/java-cup-11b.jar";
    private static final String BIN_PATH = "./bin/";
    private static final String UTILS_PATH = "./src/compiler/sintactic/Symbols/*.java";
    private static final String LEXIC_PATH = "./src/compiler/lexic/";
    private static final String SINTACTIC_PATH = "./src/compiler/sintactic/";
    private static final String LEXER_FILE = LEXIC_PATH + "Lexer.jflex";
    private static final String CUP_FILE = SINTACTIC_PATH + "Parser.cup";

    private static final boolean SOLO_EJECUTAR_MAIN = false;

    public static void main(String[] args) {
        try {
            if (!SOLO_EJECUTAR_MAIN) {
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
            }
            System.err.println("------------------------------------");
            System.err.println("------------------ 5. Compilar Main ------------------");
            System.err.println("------------------------------------");
            compileMain();
            System.err.println("------------------------------------");
            System.err.println("------------------ 6. Ejecutar Main ------------------");
            System.err.println("------------------------------------");
            executeMain();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void cleanGeneratedFiles() throws IOException {
        String[] filesToDelete = {
                LEXIC_PATH + "Scanner.java",
                SINTACTIC_PATH + "Parser.java",
                SINTACTIC_PATH + "sym.java"
        };
        for (String filePath : filesToDelete) {
            File file = new File(filePath);
            if (file.exists() && file.delete()) {
                System.out.println("Archivo eliminado: " + filePath);
            }
        }
        // Eliminar el directorio bin y su contenido
        File binDirectory = new File(BIN_PATH);
        if (binDirectory.exists()) {
            deleteDirectoryRecursively(binDirectory);
            System.out.println("Directorio eliminado: " + BIN_PATH);
        } else {
            System.out.println("El directorio " + BIN_PATH + " no existe.");
        }
    }

    private static void compileLexer() throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder(
                "java", "-jar", JFLEX_PATH, "-d", LEXIC_PATH, LEXER_FILE);
        processBuilder.inheritIO();
        Process process = processBuilder.start();
        process.waitFor();
    }

    private static void compileParser() throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder(
                "java", "-jar", CUP_PATH, "-interface", "-parser", "Parser",
                "-destdir", SINTACTIC_PATH, CUP_FILE);
        processBuilder.inheritIO();
        Process process = processBuilder.start();
        process.waitFor();
    }

    private static void compileGeneratedFiles() throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder("javac", "--release",
                "20", "-cp", CUP_PATH, "-d", "./bin",
                LEXIC_PATH + "Scanner.java", SINTACTIC_PATH + "Parser.java", SINTACTIC_PATH +
                        "sym.java",
                UTILS_PATH);
        processBuilder.inheritIO();
        Process process = processBuilder.start();
        process.waitFor();
    }

    private static void compileMain() throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder("javac", "-cp", ".;bin;lib/JavaCUP/*;lib/JFlex/*", "-d",
                "bin", "src/programa/Main.java");
        processBuilder.inheritIO();
        Process process = processBuilder.start();
        process.waitFor();
    }

    private static void executeMain() throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder("java", "-cp", ".;bin;lib/JavaCUP/*;lib/JFlex/*",
                "programa.Main", "src/programa/programa.txt");
        processBuilder.inheritIO();
        Process process = processBuilder.start();
        process.waitFor();
    }

    // javac -cp .;bin;lib/JavaCUP/*;lib/JFlex/* -d bin src/programa/Main.java
    // COMPILAR MAIN

    // java -cp .;bin;lib/JavaCUP/*;lib/JFlex/* programa.Main
    // src/programa/programa.txt
    // EJECUTAR MAIN

    /**
     * Elimina de forma recursiva todos los archivos y directorios dentro del
     * directorio proporcionado.
     *
     * @param directory Directorio a eliminar
     */
    private static void deleteDirectoryRecursively(File directory) {
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    deleteDirectoryRecursively(file);
                }
            }
        }

        if (directory.delete()) {
            System.out.println("Eliminado: " + directory.getPath());
        } else {
            System.err.println("No se pudo eliminar: " + directory.getPath());
        }
    }
}
