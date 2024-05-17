package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import scuola.Classe;
import scuola.Segreteria;
import java.io.IOException;

public class SegreteriaDashboardControllerIniziale {

    @FXML
    private ListView<Classe> classiListView;

    private Segreteria segreteria;

  public void setSegreteria(Segreteria seg) {
	  this.segreteria=seg;
  }

    public void initialize() {
   
    }
    
    @FXML
    private void aggiungiStudentiAlPoloClicked(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/segreteria1_dashboard.fxml"));
            // Carica il file FXML e ottieni il nodo radice
            Parent root = loader.load();

            // Ottieni il controller associato al file FXML caricato
            SegreteriaDashboardController controller = loader.getController();

            // Passa la segreteria al controller, se necessario
            controller.setSegreteria(this.segreteria);
            controller.faccioIo();
            // Crea una nuova finestra di dialogo (Stage)
            Stage stage = new Stage();
            // Imposta la scena
            stage.setScene(new Scene(root));
            // Mostra la finestra
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void aggiungiProfessoriClicked() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/creaProf.fxml"));
            Parent root = loader.load();
            CreaProfessoreController controller = loader.getController();
            controller.setSegreteria(segreteria);
            controller.faccioIo();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    
    public void updateLabels() {
        classiListView.getItems().addAll(segreteria.getListaClassi());
        classiListView.setOnMouseClicked(event -> {
            Classe classeSelezionata = classiListView.getSelectionModel().getSelectedItem();
            if (classeSelezionata != null) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/class_dashboard.fxml"));
                    Parent root = loader.load();
                    ClasseViewController controller = loader.getController();
                    controller.setClasse(classeSelezionata);
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
