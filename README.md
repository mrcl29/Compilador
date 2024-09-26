# Complilador

## Marc Llobera Villalonga

## Asignatura de Compiladores - UIB

## 2024 - 2025

Desarrollar un compilador para un lenguaje imperativo.

### Funcionalidad del Procesador

El compilador a desarrollar debe contemplar las siguientes funcionalidades:

- Deberá ser capaz de procesar el código fuente suministrado en un archivo de texto, proporcionado por el programador.
- Deberá generar una serie de archivos como resultado de su ejecución:

#### Front-end

- **Archivo de tókenos**: todos los tókenos generados según la secuencia de entrada.
- **Tabla de símbolos**: toda la información de los datos introducidos en la tabla de símbolos una vez que se haya procesado completamente el código fuente.

#### Back-end

- **Tablas de variables y de procedimientos**: para poder comprobar la corrección del código de tres direcciones.
- **Archivo de código intermedio**: el código intermedio correspondiente al programa en código fuente introducido.
- **Archivo con código ensamblador, sin optimizar**: para cada instrucción de tres direcciones se mostrará un comentario con la instrucción y, a continuación, la traducción correspondiente.
- **Archivo con código ensamblador, optimizado**: la idea es que el ejecutable obtenido con el código optimizado y el código sin optimizar haga lo mismo, pero se pueda ver la diferencia en el rendimiento.

#### Errores

- Si se detectan errores, se generará un documento con los errores detectados. En cada error se indicará la línea donde se ha detectado, el tipo de error (léxico, sintáctico, semántico) y un mensaje explicativo.
