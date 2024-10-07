import java.io.StringReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String input = "fnct suma(x::integer, y::integer):\nrtrn x + y;\nendfnct\n";
        Lexer lexer = new Lexer(new StringReader(input));
        Token token;
        try {
            while ((token = lexer.yylex()) != null) {
                System.out.println(token);
            }
        } catch (IOException e) {
            System.err.println("Error reading input: " + e.getMessage());
        }
    }
}