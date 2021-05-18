package org.iesgrancapitan.PROGR.openjfx;

/**
 * Ejemplo de control del valor de un spinner editable de enteros.
 * 
 * Impedimos cambiar el valor del spinner desde el editor si ponemos uno que no sea numérico. 
 */

import java.util.regex.Pattern;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Ej17ControlSpinner extends Application {

  private static final int STEP = 5;
  private static final int MIN_VALUE = 1;
  private static final int INIT_VALUE = 25;
  private static final int MAX_VALUE = 50;

  @Override
  public void start(Stage primaryStage) {

    // Layout

    GridPane root = new GridPane();
    root.setHgap(10);
    root.setVgap(10);

    int row = 0;

    root.add(new Label("Nivel: "), 0, row);
    
    // Spinner con valores numéricos

    Spinner<Integer> spinner = new Spinner<Integer>(); 

    SpinnerValueFactory<Integer> valueFactory = // Value factory
        new SpinnerValueFactory.IntegerSpinnerValueFactory(MIN_VALUE, MAX_VALUE, INIT_VALUE);

    spinner.setValueFactory(valueFactory);
    spinner.setEditable(true);
    
    // Accedemos al editor del spinner y controlamos que no se metan valores no enteros 
    
    spinner.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
      if (! esNumerico(newValue)) {
        spinner.getEditor().setText(oldValue);
        System.out.println("Valor incorrecto: " + newValue);
      }
    });
    
    // Podemos acceder a las teclas pulsadas dentro del spinner
    spinner.getEditor().setOnKeyPressed( e -> {
      System.out.println("Código tecla: " + e.getCode());
    });

    root.add(spinner, 1, row++, 2, 1);

    // Botones

    Button button1 = new Button("-" + STEP);
    // accedo a variable donde está SpinnerValueFactory
    button1.setOnAction(value -> valueFactory.setValue(spinner.getValue() - STEP));
    root.add(button1, 1, row);
    
    Button button2 = new Button("+" + STEP);
    // accedo a atributo donde está SpinnerValueFactory
    button2.setOnAction(value -> spinner.getValueFactory().setValue(spinner.getValue() + STEP));
    root.add(button2, 2, row);

    // Scene y stage

    Scene scene = new Scene(root);
    scene.getStylesheets().add(getClass()
        .getResource("vistas/estilos.css").toExternalForm());

    primaryStage.setTitle("Spinner Experiments");
    primaryStage.setScene(scene);
    primaryStage.show();
  }
  
  private static boolean esNumerico(String cadena) {
    return Pattern.matches("^[1-9][0-9]*$", cadena);
  }

  public static void main(String[] args) {
    launch(args);
  }
}
