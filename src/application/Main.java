package application;

import java.time.LocalDate;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import scuola.Studente;

public class Main extends Application {
    
	@Override
	public void start(Stage stage) throws Exception {
	    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/sample.fxml"));
	    Parent root = loader.load();

	    
	    root.setStyle("-fx-background-color: #E0EAf0;");

	    Studente studente = new Studente("Mario", "Rossi", "Via Roma 123", "ABC123", LocalDate.of(2000, 5, 10));
	    LocalDate data = LocalDate.of(2024, 4, 5);
	    studente.aggiungiImpegno(data, "mangiare");
	    SampleController controller = loader.getController();
	    controller.setStudente(studente);

	    Scene scene = new Scene(root);
	    String css = this.getClass().getResource("application.css").toExternalForm();
	    scene.getStylesheets().add(css);
	    stage.setScene(scene);
	    stage.setTitle("Calendario Studente");
	    stage.show();
	}
    
    public static void main(String[] args) {
        launch(args);
    }
}
