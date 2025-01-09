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

import java_cup.runtime.*;
import java_cup.runtime.ComplexSymbolFactory.ComplexSymbol;
import java_cup.runtime.ComplexSymbolFactory.Location;
import compiler.sintactic.Symbols.Simbol;

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

%eofval{
  return symbol(ParserSym.EOF);
%eofval}

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
boolean_literal = "TRUE" | "FALSE"
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

        return new ComplexSymbol(ParserSym.terminalNames[type], type, esquerra, dreta, new Simbol((Integer) yyline+1, (Integer) yycolumn+1, value.getValue()));
    }
%}

/****************************************************************************/
%%

/* Paraules clau */
{fnct}          { return symbol(ParserSym.fnct); }
{endfnct}       { return symbol(ParserSym.endfnct); }
{rtrn}          { return symbol(ParserSym.rtrn); }
{val}           { return symbol(ParserSym.val); }
{con}           { return symbol(ParserSym.con); }
{if}            { return symbol(ParserSym.if_t); }
{else}          { return symbol(ParserSym.else_t); }
{endif}         { return symbol(ParserSym.endif); }
{while}         { return symbol(ParserSym.while_t); }
{endwhile}      { return symbol(ParserSym.endwhile); }
{for}           { return symbol(ParserSym.for_t); }
{to}            { return symbol(ParserSym.to); }
{endfor}        { return symbol(ParserSym.endfor); }
{in}            { return symbol(ParserSym.in); }
{out}           { return symbol(ParserSym.out); }
{tuple}         { return symbol(ParserSym.tuple); }

/* Tipus de dades */
{integer}       { return symbol(ParserSym.integer); }
{logical}       { return symbol(ParserSym.logical); }

/* Operadors */
{assign}        { return symbol(ParserSym.assign); }
{plus}          { return symbol(ParserSym.plus); }
{minus}         { return symbol(ParserSym.minus); }
{equal}         { return symbol(ParserSym.equal); }
{not_equal}     { return symbol(ParserSym.not_equal); }
{and}           { return symbol(ParserSym.and); }
{or}            { return symbol(ParserSym.or); }

/* Simbols */
{lparen}        { return symbol(ParserSym.lparen); }
{rparen}        { return symbol(ParserSym.rparen); }
{colon}         { return symbol(ParserSym.colon); }
{double_colon}  { return symbol(ParserSym.double_colon); }
{semicolon}     { return symbol(ParserSym.semicolon); }
{comma}         { return symbol(ParserSym.comma); }
{lbrace}        { return symbol(ParserSym.lbrace); }
{rbrace}        { return symbol(ParserSym.rbrace); }
{lbracket}      { return symbol(ParserSym.lbracket); }
{rbracket}      { return symbol(ParserSym.rbracket); }

/* Literals */
{integer_literal}   { return symbol(ParserSym.integer_literal, new Simbol(Integer.parseInt(this.yytext()))); }
{boolean_literal}   { return symbol(ParserSym.boolean_literal, new Simbol(this.yytext())); }
{id}                { return symbol(ParserSym.id, new Simbol(this.yytext())); }

/* Espais en blanc i comentaris */
{whitespace}        { /* Ignorar espais en blanc */ }
{comment}           { /* Ignorar comentaris */ }

[^]                 { return symbol(ParserSym.error);  }
