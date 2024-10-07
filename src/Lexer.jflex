/* Lexer per al llenguatge inventat */

%%

%class Lexer
%unicode
%line
%column
%type Token

%{
  private Token token(String type) {
    return new Token(type, yytext(), yyline, yycolumn);
  }
%}

/* Paraules clau */
FNCT = "fnct"
ENDFNCT = "endfnct"
RTRN = "rtrn"
VAL = "val"
CON = "con"
IF = "if"
ELSE = "else"
ENDIF = "endif"
WHILE = "while"
ENDWHILE = "endwhile"
FOR = "for"
TO = "to"
ENDFOR = "endfor"
IN = "in"
OUT = "out"
TUPLE = "tuple"

/* Tipus de data */
INTEGER = "integer"
LOGICAL = "logical"

/* Operadors */
ASSIGN = "="
PLUS = "+"
MINUS = "-"
EQUAL = "==="
NOT_EQUAL = "/="
AND = "&&"
OR = "||"

/* Simbols */
LPAREN = "("
RPAREN = ")"
COLON = ":"
SEMICOLON = ";"
COMMA = ","
LBRACE = "{"
RBRACE = "}"

/* Literals */
INTEGER_LITERAL = [0-9]+
BOOLEAN_LITERAL = "TRUE" | "FALSE"
IDENTIFIER = [a-zA-Z][a-zA-Z0-9]*

/* Espais en blanc i comentaris */
WHITESPACE = [ \t\n\r]+
COMMENT = "///".*

%%

{FNCT} { return token("KEYWORD"); }
{ENDFNCT} { return token("KEYWORD"); }
{RTRN} { return token("KEYWORD"); }
{VAL} { return token("KEYWORD"); }
{CON} { return token("KEYWORD"); }
{IF} { return token("KEYWORD"); }
{ELSE} { return token("KEYWORD"); }
{ENDIF} { return token("KEYWORD"); }
{WHILE} { return token("KEYWORD"); }
{ENDWHILE} { return token("KEYWORD"); }
{FOR} { return token("KEYWORD"); }
{TO} { return token("KEYWORD"); }
{ENDFOR} { return token("KEYWORD"); }
{IN} { return token("KEYWORD"); }
{OUT} { return token("KEYWORD"); }
{TUPLE} { return token("KEYWORD"); }

{INTEGER} { return token("TYPE"); }
{LOGICAL} { return token("TYPE"); }

{ASSIGN} { return token("OPERATOR"); }
{PLUS} { return token("OPERATOR"); }
{MINUS} { return token("OPERATOR"); }
{EQUAL} { return token("OPERATOR"); }
{NOT_EQUAL} { return token("OPERATOR"); }
{AND} { return token("OPERATOR"); }
{OR} { return token("OPERATOR"); }

{LPAREN} { return token("SYMBOL"); }
{RPAREN} { return token("SYMBOL"); }
{COLON} { return token("SYMBOL"); }
{SEMICOLON} { return token("SYMBOL"); }
{COMMA} { return token("SYMBOL"); }
{LBRACE} { return token("SYMBOL"); }
{RBRACE} { return token("SYMBOL"); }

{INTEGER_LITERAL} { return token("INTEGER_LITERAL"); }
{BOOLEAN_LITERAL} { return token("BOOLEAN_LITERAL"); }
{IDENTIFIER} { return token("IDENTIFIER"); }

{WHITESPACE} { /* Ignorar espais en blanc */ }
{COMMENT} { /* Ignorar comentaris */ }

. { throw new RuntimeException("Caracter no reconegut: " + yytext()); }