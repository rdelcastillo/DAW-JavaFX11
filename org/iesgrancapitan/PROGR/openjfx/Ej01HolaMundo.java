package org.iesgrancapitan.PROGR.openjfx;

import java.util.Scanner;

/**
 * Ejemplo de GUI usando JavaFX.
 * 
 * Mostramos una ventana con título.
 * 
 */

import javafx.application.Application;
import javafx.stage.Stage;

public class Ej01HolaMundo extends Application {

  @Override
  public void start(Stage primaryStage) {
    System.out.println("Soy el método start(), conmigo empieza la interfaz gráfica.\n");
    
    // el objeto stage (ventana) que se recibe es creado por Application
    primaryStage.setTitle("Mi primera aplicación JavaFX");
    primaryStage.show();
  }
  
  public void init() {
    muestraMensajeInit();
  }
  
  public void stop() {
    System.out.println("Soy el método stop(), no soy obligatorio y conmigo terminamos.");
  }

  private void muestraMensajeInit() {
    Scanner s = new Scanner(System.in);
    System.out.println("Soy el método init() y si aparezco (no soy obligatorio)");
    System.out.println("soy llamado el primero y antes de que la interfaz gráfica se inicie.");
    System.out.println("Normalmente soy útil cuando es necesario inicializar rutinas");
    System.out.println("antes del lanzamiento de la GUI.\n");
    System.out.println("Pulsa Intro para continuar...");
    s.nextLine();
  }

  public static void main(String[] args) {
    launch(args);
  }
}