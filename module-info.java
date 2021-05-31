module hellofx {
  requires transitive javafx.graphics;
  requires javafx.controls;
  requires javafx.fxml;
  requires javafx.base;
  requires javafx.web;
  
  opens org.iesgrancapitan.PROGR.openjfx to javafx.fxml;
  opens org.iesgrancapitan.PROGR.openjfx.vistas to javafx.fxml;
  opens org.iesgrancapitan.PROGR.openjfx.ejercicios.vistas to javafx.fxml;
  opens org.iesgrancapitan.PROGR.openjfx.controles.view to javafx.fxml;
  opens org.iesgrancapitan.PROGR.openjfx.examenes.vistas to javafx.fxml;
  
  exports org.iesgrancapitan.PROGR.openjfx;
  exports org.iesgrancapitan.PROGR.openjfx.controles;
  exports org.iesgrancapitan.PROGR.openjfx.controles.tableview;
  exports org.iesgrancapitan.PROGR.openjfx.controles.view;
  exports org.iesgrancapitan.PROGR.openjfx.dialogos;
  exports org.iesgrancapitan.PROGR.openjfx.eventos;
  exports org.iesgrancapitan.PROGR.openjfx.ejercicios;
  exports org.iesgrancapitan.PROGR.openjfx.ejercicios.vistas;
  exports org.iesgrancapitan.PROGR.openjfx.examenes;
  exports org.iesgrancapitan.PROGR.openjfx.examenes.vistas;
}
