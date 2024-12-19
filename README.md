# Complilador

## Marc Llobera Villalonga

## Asignatura de Compiladores - UIB

## 2024 - 2025

Desarrollar un compilador para un lenguaje imperativo.

### Prerequisitos

Tener la librería JFlex instalada. Hay una librería ya instalada en el proyecto pero por si acaso puede seguir estos pasos:

1. Descargar JFlex: <https://github.com/jflex-de/jflex/releases/download/v1.9.1/jflex-1.9.1.tar.gz>

2. Descomprime el archivo en la carpeta deseada.

3. Ves a \jflex-1.9.1\bin\jflex.bat

4. Edita la variable JFLEX_HOME para que apunte al directorio \jflex-1.9.1

5. Incluye \jflex-1.9.1\bin al PATH en variables de entorno del sistema.

También se debe instalar el SDK de Java y añadirlo al PATH.

### Ejecución

1. Compilar Léxico: java -jar .\lib\JFlex\jflex-full-1.9.1.jar .\src\compilador\lexic\Lexer.jflex

2. Compilar Sintáctico: java -jar .\lib\JavaCUP\java-cup-11b.jar -parser Parser -symbols ParserSym .\src\compilador\sintactic\Parser.cup
