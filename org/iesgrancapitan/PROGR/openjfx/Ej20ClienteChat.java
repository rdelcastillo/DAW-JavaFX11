package org.iesgrancapitan.PROGR.openjfx;

import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;
import org.iesgrancapitan.PROGR.openjfx.vistas.Ej20ClienteChatController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Ej20ClienteChat extends Application {

  @Override
  public void start(Stage primaryStage) {
    try {
      var fxml = new FXMLLoader(getClass().getResource("vistas/Ej20ClienteChat.fxml"));
      var scene = new Scene(fxml.<Pane>load());
      Ej20ClienteChatController controller = fxml.getController();
      controller.setStage(primaryStage);
      inyectaArgumentos(controller);

      primaryStage.setScene(scene);
      primaryStage.setTitle(getClass().getSimpleName());
      primaryStage.show();

    } catch (IOException e) {
      System.out.println("Error al cargar el fxml.");
      e.printStackTrace();
    }
  }

  private void inyectaArgumentos(Ej20ClienteChatController controller) {
    // Accedo a los argumentos con los que hemos llamado a la aplicación
    List<String> params = getParameters().getRaw();
    
    // Creo una lista de lambdas para inyectar los datos
    List<Consumer<String>> lambdaSet = List.of(n -> controller.setNick(n),
        s -> controller.setServidor(s), p -> controller.setPuerto(p));
    
    // Inyecto los datos al controlador
    for (int i = 0; i < params.size(); i++) {
      lambdaSet.get(i).accept(params.get(i));
    }
  }

  public static void main(String[] args) {
    launch(args);
  }
}
