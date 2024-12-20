package compilador.lexic;

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
import compilador.sintactic.sym;
import java_cup.runtime.*;
import java_cup.runtime.Symbol;

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

%init{
    yyline = 1;
    yychar = 1L; // Ensure yychar is initialized as long
%init}

%eofval{
  return new Symbol(sym.EOF);
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

/****************************************************************************/
%%

/* Paraules clau */
{fnct}          { return new Symbol(sym.fnct,yyline,(int)yychar, yytext()); }
{endfnct}       { return new Symbol(sym.endfnct,yyline,(int)yychar, yytext()); }
{rtrn}          { return new Symbol(sym.rtrn,yyline,(int)yychar, yytext()); }
{val}           { return new Symbol(sym.val,yyline,(int)yychar, yytext()); }
{con}           { return new Symbol(sym.con,yyline,(int)yychar, yytext()); }
{if}            { return new Symbol(sym.if_t,yyline,(int)yychar, yytext()); }
{else}          { return new Symbol(sym.else_t,yyline,(int)yychar, yytext()); }
{endif}         { return new Symbol(sym.endif,yyline,(int)yychar, yytext()); }
{while}         { return new Symbol(sym.while_t,yyline,(int)yychar, yytext()); }
{endwhile}      { return new Symbol(sym.endwhile,yyline,(int)yychar, yytext()); }
{for}           { return new Symbol(sym.for_t,yyline,(int)yychar, yytext()); }
{to}            { return new Symbol(sym.to,yyline,(int)yychar, yytext()); }
{endfor}        { return new Symbol(sym.endfor,yyline,(int)yychar, yytext()); }
{in}            { return new Symbol(sym.in,yyline,(int)yychar, yytext()); }
{out}           { return new Symbol(sym.out,yyline,(int)yychar, yytext()); }
{tuple}         { return new Symbol(sym.tuple,yyline,(int)yychar, yytext()); }

/* Tipus de dades */
{integer}       { return new Symbol(sym.integer,yyline,(int)yychar, yytext()); }
{logical}       { return new Symbol(sym.logical,yyline,(int)yychar, yytext()); }

/* Operadors */
{assign}        { return new Symbol(sym.assign,yyline,(int)yychar, yytext()); }
{plus}          { return new Symbol(sym.plus,yyline,(int)yychar, yytext()); }
{minus}         { return new Symbol(sym.minus,yyline,(int)yychar, yytext()); }
{equal}         { return new Symbol(sym.equal,yyline,(int)yychar, yytext()); }
{not_equal}     { return new Symbol(sym.not_equal,yyline,(int)yychar, yytext()); }
{and}           { return new Symbol(sym.and,yyline,(int)yychar, yytext()); }
{or}            { return new Symbol(sym.or,yyline,(int)yychar, yytext()); }

/* Simbols */
{lparen}        { return new Symbol(sym.lparen,yyline,(int)yychar, yytext()); }
{rparen}        { return new Symbol(sym.rparen,yyline,(int)yychar, yytext()); }
{colon}         { return new Symbol(sym.colon,yyline,(int)yychar, yytext()); }
{double_colon}  { return new Symbol(sym.double_colon,yyline,(int)yychar, yytext()); }
{semicolon}     { return new Symbol(sym.semicolon,yyline,(int)yychar, yytext()); }
{comma}         { return new Symbol(sym.comma,yyline,(int)yychar, yytext()); }
{lbrace}        { return new Symbol(sym.lbrace,yyline,(int)yychar, yytext()); }
{rbrace}        { return new Symbol(sym.rbrace,yyline,(int)yychar, yytext()); }
{lbracket}      { return new Symbol(sym.lbracket,yyline,(int)yychar, yytext()); }
{rbracket}      { return new Symbol(sym.rbracket,yyline,(int)yychar, yytext()); }

/* Literals */
{integer_literal}   { return new Symbol(sym.integer_literal,yyline,(int)yychar, yytext()); }
{boolean_literal}   { return new Symbol(sym.boolean_literal,yyline,(int)yychar, yytext()); }
{id}            { return new Symbol(sym.id,yyline,(int)yychar, yytext()); }

\n { yyline++; yychar = 1L; }

/* Espais en blanc i comentaris */
{whitespace}        { /* Ignorar espais en blanc */ }
{comment}           { /* Ignorar comentaris */ }

. {
    System.out.println("Este es un error lexico: " + yytext() +
    ", en la linea: " + yyline + ", en la columna: " + (int)yychar);
}
