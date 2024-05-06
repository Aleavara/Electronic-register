package application;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import scuola.Classe;
import scuola.Segreteria;

public class SegreteriaDashboardControllerIniziale {

    @FXML
    private ListView<Classe> classiListView;

    public void initialize() {

        classiListView.getItems().addAll(Segreteria.getListaClassi());
    }
}
