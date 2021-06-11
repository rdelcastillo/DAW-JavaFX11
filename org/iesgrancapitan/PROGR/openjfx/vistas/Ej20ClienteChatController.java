package org.iesgrancapitan.PROGR.openjfx.vistas;

/**
 * Cliente que maneja el servidor de chat...
 * 
 * Admite paso de argumentos.
 */

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.iesgrancapitan.PROGR.openjfx.Util;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class Ej20ClienteChatController {

  private static final String NICK_OK = "HELLO";
  private static final Font FUENTE_BIENVENIDA = Font.font("Gothic", FontPosture.REGULAR, 25);
  private static final Font FUENTE_SALUDO = Font.font("Arial", FontPosture.REGULAR, 25);
  private static final Font FUENTE_NORMAL = Font.font("System", FontPosture.REGULAR, 20);
  private static final Color COLOR_BIENVENIDA = Color.RED;
  private static final Color COLOR_SALUDO = Color.BLUE;
  private static final Color COLOR_NORMAL = Color.BLACK;
  private static final Color COLOR_USUARIO = Color.DARKRED;
  private static final Color COLOR_MI_NICK = Color.GREEN;

  @FXML
  private TextField nick;
  @FXML
  private TextField servidor;
  @FXML
  private TextField puerto;
  @FXML
  private Button conectarButton;
  @FXML
  private Button desconectarButton;
  @FXML
  private TextFlow visor;
  @FXML
  private TextField mensaje;
  @FXML
  private Button enviarButton;

  Socket cliente;
  BufferedReader in;
  PrintWriter out;
  ChatHandler chatHandler;
  Stage stage;

  // -----------------------------------------------------
  // Event Listener on Button[#conectarButton].onAction
  // -----------------------------------------------------
  @FXML
  public void conectar(ActionEvent event) {
    try {
      conectarConServidor();
      enviarNick();
      actualizarAccesibilidad(true);
      lanzarChatHandler();

    } catch (Ej20ClienteChatNickException e) {
      Util.mostrarError("Nick " + nick.getText() + " erróneo",
          "Introduzca un nick correcto y conéctese de nuevo");
      cerrarConexion();

    } catch (NumberFormatException | IOException e) {
      mostrarErrorConexion(e);
    }
  }

  private void conectarConServidor() throws NumberFormatException, IOException {
    cliente = new Socket(servidor.getText(), Integer.parseInt(puerto.getText()));
    out = new PrintWriter(cliente.getOutputStream(), true);
    in = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
    writeVisor(in.readLine() + "\n", FUENTE_BIENVENIDA, COLOR_BIENVENIDA);
  }

  private void enviarNick() throws IOException, Ej20ClienteChatNickException {
    out.println(nick.getText());
    String respuestaServidor = in.readLine();
    writeVisor(respuestaServidor + "\n", FUENTE_SALUDO, COLOR_SALUDO);
    if (!esNickOk(respuestaServidor)) {
      throw new Ej20ClienteChatNickException("Nick " + nick + " erróneo.");
    }
  }

  private boolean esNickOk(String respuestaServidor) {
    return respuestaServidor.toUpperCase().startsWith(NICK_OK);
  }

  private void writeVisor(String mensaje, Font fuente, Color color) {
    Text text = new Text(mensaje);
    text.setFill(color);
    text.setFont(fuente);
    visor.getChildren().add(text);
  }

  private void writeVisor(String mensaje) {
    writeVisor(mensaje + "\n", FUENTE_NORMAL, COLOR_NORMAL);
  }

  private void actualizarAccesibilidad(boolean conectado) {
    conectarButton.setDisable(conectado);
    desconectarButton.setDisable(!conectado);
    enviarButton.setDisable(!conectado);
    enviarButton.setDefaultButton(conectado);
    mensaje.setEditable(conectado);
    mensaje.requestFocus();
    nick.setEditable(!conectado);
    servidor.setEditable(!conectado);
    puerto.setEditable(!conectado);
  }

  private void lanzarChatHandler() {
    chatHandler = new ChatHandler();
    chatHandler.start();
  }

  private void mostrarErrorConexion(Exception e) {
    Util.mostrarError("Error al realizar la conexión",
        e.getClass() + "\n" + e.getMessage() + "\n\nMás información en la consola");
    e.printStackTrace();
  }

  private void cerrarConexion() {
    try {
      cliente.close();
      in.close();
      out.close();

    } catch (IOException e) {
      System.out.println("Error al cerrar la conexión.");
      e.printStackTrace();
    }
  }

  // -----------------------------------------------------
  // Event Listener on Button[#desconectarButton].onAction
  // -----------------------------------------------------
  @FXML
  public void desconectar(ActionEvent event) {
    out.println(); // mensaje vacío desconecta del servidor
  }

  // Event Listener on Button[#enviarButton].onAction
  @FXML
  public void enviar(ActionEvent event) {
    if (mensaje.getText().isBlank()) {
      Util.mostrarError("Mensaje vacío",
          "No puede mandar mensajes vacíos,\nsi quiere desconectar pulse 'Desconexión'");
      return;
    }
    out.println(mensaje.getText());
    mensaje.clear();
  }

  // Inyección desde la App

  public void setStage(Stage stage) {
    this.stage = stage;
    stage.setOnCloseRequest(e -> desconectar(null));
  }

  public void setNick(String n) {
    nick.setText(n);
  }

  public void setServidor(String s) {
    servidor.setText(s);
  }

  public void setPuerto(String p) {
    if (p.matches("^\\d*$")) {
      puerto.setText(p);
    } else {
      System.err.println("El parámetro " + p + " no es un puerto válido.");
    }
  }

  // Hilo chat cliente

  private class ChatHandler extends Thread {

    public void run() {
      try {
        String mensaje;
        while ((mensaje = in.readLine()) != null) {
          writeChat(mensaje);
        }

      } catch (IOException e) {
        mostrarErrorChat(e);

      } finally {
        cerrarChat();
      }
    }

    private void writeChat(String mensajeChat) {
      Platform.runLater(new Runnable() {
        @Override
        public void run() {
          String mensaje = mensajeChat;
          Matcher matcher = getMatcher(mensajeChat);
          if (matcher != null) {
            String usuario = matcher.group(1);
            Color color = usuario.equals(nick.getText()) ? COLOR_MI_NICK : COLOR_USUARIO;
            writeVisor("[" + usuario + "]", FUENTE_NORMAL, color);
            mensaje = matcher.group(2);
          }
          writeVisor(mensaje);
        }

        private Matcher getMatcher(String mensajeChat) {
          String expresionRegular = "^\\[(.*)\\](.*)";
          Pattern patron = Pattern.compile(expresionRegular);
          Matcher matcher = patron.matcher(mensajeChat);
          return matcher.find() ? matcher : null;
        }
      });
    }

    private void mostrarErrorChat(IOException e) {
      Platform.runLater(new Runnable() {
        @Override
        public void run() {
          mostrarErrorConexion(e);
        }
      });
    }

    private void cerrarChat() {
      cerrarConexion();
      Platform.runLater(new Runnable() {
        @Override
        public void run() {
          writeVisor("\nDESCONECTADO DEL CHAT.\n");
          actualizarAccesibilidad(false);
        }
      });
    }
  }

  @FXML
  public void initialize() {
    // Impedimos que se puedan meter caracteres que no sean dígitos en el puerto
    puerto.textProperty().addListener((observable, oldValue, newValue) -> {
      if (!newValue.matches("^\\d*$")) {
        puerto.setText(oldValue);
      }
    });
  }
}

