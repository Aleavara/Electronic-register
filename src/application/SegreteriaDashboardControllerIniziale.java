package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import scuola.Classe;
import scuola.Segreteria;
import java.io.IOException;

public class SegreteriaDashboardControllerIniziale {

    @FXML
    private ListView<Classe> classiListView;

    public void initialize() {
        classiListView.getItems().addAll(Segreteria.getListaClassi());
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
