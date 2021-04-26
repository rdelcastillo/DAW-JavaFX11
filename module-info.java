module hellofx {
  requires transitive javafx.graphics;
  requires javafx.controls;
  requires javafx.fxml;
  
  opens org.iesgrancapitan.PROGR.openjfx to javafx.fxml;
  
  exports org.iesgrancapitan.PROGR.openjfx;
}
