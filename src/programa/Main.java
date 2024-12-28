package programa;

import java.io.FileReader;

import compiler.lexic.Scanner;
import compiler.sintactic.Parser;
import java_cup.runtime.Symbol;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Main <source_file>");
            return;
        }

        try {
            // Open the input file
            FileReader sourceFile = new FileReader(args[0]);

            // Initialize the lexer and parser
            Scanner lexer = new Scanner(sourceFile);
            Parser parser = new Parser(lexer);

            // Parse the input file
            Symbol result = parser.parse();

            // Output success
            System.out.println("Parsing completed successfully!");

        } catch (Exception e) {
            // Handle errors
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
