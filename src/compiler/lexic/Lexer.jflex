package compiler.lexic;

/**
  Per poder compilar aquest fitxer s'ha d'haver instal·lat JFlex
 **/

/**
 * Assignatura 21780 - Compiladors
 * Estudis: Grau en Enginyeria Informàtica
 *
 * Marc Llobera Villalonga
 */

/* Lexer per al llenguatge inventat */

import java.io.*;
import java.util.*;

import java_cup.runtime.*;
import java_cup.runtime.ComplexSymbolFactory.ComplexSymbol;
import java_cup.runtime.ComplexSymbolFactory.Location;
import compiler.sintactic.Symbols.*;

import compiler.sintactic.ParserSym;

%%
/** **
%standalone
 ** **/

/****
 Indicació de quin tipus d'analitzador sintàctic s'utilitzarà. En aquest cas
 es fa ús de Java CUP.
 ****/
%cup

%public              // Per indicar que la classe és pública
%class Scanner       // El nom de la classe

%line
%char
%column

%{
    private ArrayList<Simbol> tokens;
    private void escribirTokens() throws IOException{
        BufferedWriter buf = new BufferedWriter(new FileWriter("src/tokens.txt"));
        for(Simbol s : tokens){
            buf.write(s.toString() + "\n");
        }
        buf.close();
    }
%}

%init{
    tokens = new ArrayList<Simbol>();
%init}

%eofval{
    try{
        escribirTokens();
        return symbol(ParserSym.EOF);
    }catch(IOException e){
        System.err.println(e);
    }
%eofval}
%eofclose

/* Paraules clau */
fnct = "fnct"
endfnct = "endfnct"
rtrn = "rtrn"
val = "val"
con = "con"
if = "if"
else = "else"
endif = "endif"
while = "while"
endwhile = "endwhile"
for = "for"
to = "to"
endfor = "endfor"
in = "in"
out = "out"
tuple = "tuple"

/* Tipus de dades */
integer = "integer"
logical = "logical"

/* Operadors */
assign = "="
plus = "+"
minus = "-"
equal = "==="
not_equal = "/="
and = "&&"
or = "||"

/* Simbols */
lparen = "("
rparen = ")"
colon = ":"
double_colon = "::"
semicolon = ";"
comma = ","
lbrace = "{"
rbrace = "}"
lbracket = "["
rbracket = "]"

/* Literals */
integer_literal = [0-9]+
boolean_literal = "TRUE" | "FALSE" | "true" | "false"
id = [a-zA-Z][a-zA-Z0-9]*

/* Espais en blanc i comentaris */
whitespace = [ \t\n\r]+
comment = "///".*

// El següent codi es copiarà també, dins de la classe. És a dir, si es posa res
// ha de ser en el format adient: mètodes, atributs, etc.
%{
    /***
       Mecanismes de gestió de símbols basat en ComplexSymbol. Tot i que en
       aquest cas potser no és del tot necessari.
     ***/
    /**
     Construcció d'un symbol sense atribut associat.
     **/
    private ComplexSymbol symbol(int type) {
        // Sumar 1 per a que la primera línia i columna no sigui 0.
        Location esquerra = new Location(yyline+1, yycolumn+1);
        Location dreta = new Location(yyline+1, yycolumn+yytext().length()+1);

        return new ComplexSymbol(ParserSym.terminalNames[type], type, esquerra, dreta);
    }

    /**
     Construcció d'un symbol amb un atribut associat.
     **/
    private Symbol symbol(int type, Simbol value) {
        // Sumar 1 per a que la primera línia i columna no sigui 0.
        Location esquerra = new Location(yyline+1, yycolumn+1);
        Location dreta = new Location(yyline+1, yycolumn+yytext().length()+1);

        return new ComplexSymbol(ParserSym.terminalNames[type], type, esquerra, dreta, new Simbol((Integer) yyline+1, (Integer) yycolumn+1, value.getValue(), value.getTipus()));
    }
%}

/****************************************************************************/
%%

/* Paraules clau */
{fnct}          { tokens.add(new Simbol("fnct")); return symbol(ParserSym.fnct); }
{endfnct}       { tokens.add(new Simbol("endfnct")); return symbol(ParserSym.endfnct); }
{rtrn}          { tokens.add(new Simbol("rtrn")); return symbol(ParserSym.rtrn); }
{val}           { tokens.add(new Simbol("val")); return symbol(ParserSym.val); }
{con}           { tokens.add(new Simbol("con")); return symbol(ParserSym.con); }
{if}            { tokens.add(new Simbol("if")); return symbol(ParserSym.if_t, new Simbol(this.yytext())); }
{else}          { tokens.add(new Simbol("else")); return symbol(ParserSym.else_t); }
{endif}         { tokens.add(new Simbol("endif")); return symbol(ParserSym.endif); }
{while}         { tokens.add(new Simbol("while")); return symbol(ParserSym.while_t, new Simbol(this.yytext())); }
{endwhile}      { tokens.add(new Simbol("endwhile")); return symbol(ParserSym.endwhile); }
{for}           { tokens.add(new Simbol("for")); return symbol(ParserSym.for_t, new Simbol(this.yytext())); }
{to}            { tokens.add(new Simbol("to")); return symbol(ParserSym.to); }
{endfor}        { tokens.add(new Simbol("endfor")); return symbol(ParserSym.endfor); }
{in}            { tokens.add(new Simbol("in")); return symbol(ParserSym.in); }
{out}           { tokens.add(new Simbol("out")); return symbol(ParserSym.out); }
{tuple}         { tokens.add(new Simbol("tuple")); return symbol(ParserSym.tuple); }

/* Tipus de dades */
{integer}       { tokens.add(new Simbol("integer")); return symbol(ParserSym.integer); }
{logical}       { tokens.add(new Simbol("logical")); return symbol(ParserSym.logical); }

/* Operadors */
{assign}        { tokens.add(new Simbol("assign")); return symbol(ParserSym.assign); }
{plus}          { tokens.add(new Simbol("plus")); return symbol(ParserSym.plus); }
{minus}         { tokens.add(new Simbol("minus")); return symbol(ParserSym.minus); }
{equal}         { tokens.add(new Simbol("equal")); return symbol(ParserSym.equal); }
{not_equal}     { tokens.add(new Simbol("not_equal")); return symbol(ParserSym.not_equal); }
{and}           { tokens.add(new Simbol("and")); return symbol(ParserSym.and); }
{or}            { tokens.add(new Simbol("or")); return symbol(ParserSym.or); }

/* Simbols */
{lparen}        { tokens.add(new Simbol("lparen")); return symbol(ParserSym.lparen); }
{rparen}        { tokens.add(new Simbol("rparen")); return symbol(ParserSym.rparen); }
{colon}         { tokens.add(new Simbol("colon")); return symbol(ParserSym.colon); }
{double_colon}  { tokens.add(new Simbol("double_colon")); return symbol(ParserSym.double_colon); }
{semicolon}     { tokens.add(new Simbol("semicolon")); return symbol(ParserSym.semicolon); }
{comma}         { tokens.add(new Simbol("comma")); return symbol(ParserSym.comma); }
{lbrace}        { tokens.add(new Simbol("lbrace")); return symbol(ParserSym.lbrace); }
{rbrace}        { tokens.add(new Simbol("rbrace")); return symbol(ParserSym.rbrace); }
{lbracket}      { tokens.add(new Simbol("lbracket")); return symbol(ParserSym.lbracket); }
{rbracket}      { tokens.add(new Simbol("rbracket")); return symbol(ParserSym.rbracket); }

/* Literals */
{integer_literal}   { tokens.add(new Simbol("integer_literal")); return symbol(ParserSym.integer_literal, new Simbol(Integer.parseInt(this.yytext()), TipusDades.INTEGER)); }
{boolean_literal}   { tokens.add(new Simbol("boolean_literal")); return symbol(ParserSym.boolean_literal, new Simbol(this.yytext(), TipusDades.BOOLEAN)); }
{id}                { tokens.add(new Simbol("id")); return symbol(ParserSym.id, new Simbol(this.yytext())); }

/* Espais en blanc i comentaris */
{whitespace}        { /* Ignorar espais en blanc */ }
{comment}           { /* Ignorar comentaris */ }

[^]                 { return symbol(ParserSym.error);  }
