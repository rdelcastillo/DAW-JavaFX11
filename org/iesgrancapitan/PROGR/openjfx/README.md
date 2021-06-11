# Ejemplos de programas usando JavaFX

## Ej01HolaMundo.java

Primer Interfaz Gráfico usando JavaFX. Creamos un Stage y probamos los métodos init(), start() y stop().

## Ej02HolaMundo.java

Mostramos una ventana con título y contenido en su interior.

## Ej03Ventanas.java 

Creamos diferentes ventanas y probamos sus diferentes comportamientos.

## Ej04VentanasDecoradas.java

Creamos ventanas con diferentes decoraciones.

## Ej05HolaMundo.java

Mostramos una ventana con título a pantalla completa.

## Ej06HolaMundo.java

Mostramos una ventana con título maximizada.

## Ej07VentanasConEventos.java

Manejamos eventos emitidos desde un escenario.

## Ej08CursorRaton.java

Cambiamos el cursor gráfico del ratón desde una escena.

## Ej09EscenasConEventos.java

Manejamos eventos emitidos desde una escena.

## Ej10Nodos.java

Accedemos a las propiedades de los nodos de un layout.

## Ej11Factorial.java

Ejemplo de uso de JavaFX con FXML y controlador para calcular el factorial de un número.

## Ej12FXML.java

Ejemplo de uso de FXML.

## Ej13FactorialConCSS.java

Nueva implementación de *Ej11Factorial.java* usando una hoja de estilos llamada desde el objeto Scene.

## Ej14FactorialConCSS.java

Nueva implementación de *Ej11Factorial.java* usando una hoja de estilos llamada desde el fichero FXML y controlando que no se puedan meter dígitos en el campo de texto del número.

## Ej15BindindigYEventosConProperty.java

Ejemplo de binding y uso de eventos basados en propiedades.

## Ej16ConversionDinero.java

A veces tenemos que acceder a datos de la aplicación principal desde un controlador. Este ejemplo ilustra como hacerlo, básicamente hay que inyectar a cada controlador el dato, o los datos, que necesita.

## Ej17ControlSpinner.JAVA

Ejemplo de control del valor de un spinner editable de enteros. Impedimos cambiar el valor del spinner desde el editor si ponemos uno que no sea numérico. 

## Ej18ConversionDinero.java

Este ejemplo se basa en el *Ej16ConversionDinero* pero usa un Stage inicial para pedir los valores de cambio de monedas.

## Ej19ConversionDinero.java

Este ejemplo se diferencia del anterior en que el Stage que pide los valores de cambio se mantiene abierto y al aplicar se inyectan los datos en el controlador del Stage principal.

## Ej20ClienteChat.java

Implementación de un cliente de chat para el servidor [ChatServer](https://github.com/rdelcastillo/DAW-Java/blob/master/org/iesgrancapitan/PROGR/ejemplos/sockets/ChatServer.java) que usa un hilo para leer las notificaciones del servidor y reflejarlas en visor que usa un contenedor TextFlow. 