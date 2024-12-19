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
/****
La línia anterior és una alternativa a la indicació element a element:

%implements java_cup.runtime.Scanner
%function next_token
%type java_cup.runtime.Symbol

****/

%public              // Per indicar que la classe és pública
%class Scanner       // El nom de la classe

%char
%line
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
lparen = "\\("
rparen = "\\)"
colon = ":"
double_colon = "::"
semicolon = ";"
comma = ","
lbrace = "\\{"
rbrace = "\\}"
lbracket = "\\["
rbracket = "\\]"

/* Literals */
zero = [0]
one = [1]
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
       Mecanismes de gestió de símbols basat en ComplexSymbol. tot i que en
       aquest cas potser no és del tot necessari.
     ***/
    /**
     construcció d'un symbol sense atribut associat.
     **/
    private ComplexSymbol symbol(int type) {
        return new ComplexSymbol(ParserSym.terminalNames[type], type);
    }

    /**
     construcció d'un symbol amb un atribut associat.
     **/
    private Symbol symbol(int type, Object value) {
        return new ComplexSymbol(ParserSym.terminalNames[type], type, value);
    }
%}

/****************************************************************************/
%%

/* Paraules clau */
{fnct}          { return symbol("fnct"); }
{endfnct}       { return symbol("endfnct"); }
{rtrn}          { return symbol("rtrn"); }
{val}           { return symbol("val"); }
{con}           { return symbol("con"); }
{if}            { return symbol("if"); }
{else}          { return symbol("else"); }
{endif}         { return symbol("endif"); }
{while}         { return symbol("while"); }
{endwhile}      { return symbol("endwhile"); }
{for}           { return symbol("for"); }
{to}            { return symbol("to"); }
{endfor}        { return symbol("endfor"); }
{in}            { return symbol("in"); }
{out}           { return symbol("out"); }
{tuple}         { return symbol("tuple"); }

/* Tipus de dades */
{integer}       { return symbol("integer"); }
{logical}       { return symbol("logical"); }

/* Operadors */
{assign}        { return symbol("assign"); }
{plus}          { return symbol("plus"); }
{minus}         { return symbol("minus"); }
{equal}         { return symbol("equal"); }
{not_equal}     { return symbol("not_equal"); }
{and}           { return symbol("and"); }
{or}            { return symbol("or"); }

/* Simbols */
{lparen}        { return symbol("lparen"); }
{rparen}        { return symbol("rparen"); }
{colon}         { return symbol("colon"); }
{double_colon}  { return symbol("double_colon"); }
{semicolon}     { return symbol("semicolon"); }
{comma}         { return symbol("comma"); }
{lbrace}        { return symbol("lbrace"); }
{rbrace}        { return symbol("rbrace"); }
{lbracket}      { return symbol("lbracket"); }
{rbracket}      { return symbol("rbracket"); }

/* Literals */
{zero}        { return symbol("zero"); }
{one}         { return symbol("one"); }
{integer_literal}   { return symbol("integer_literal"); }
{boolean_literal}   { return symbol("boolean_literal"); }
{id}          { return symbol("id"); }

/* Espais en blanc i comentaris */
{whitespace}        { /* Ignorar espais en blanc */ }
{comment}           { /* Ignorar comentaris */ }

[^]                 { return symbol(ParserSym.error);  }
