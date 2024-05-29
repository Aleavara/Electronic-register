package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import scuola.GestoreCredenziali;
import scuola.Professore;
import scuola.Segreteria;
import scuola.Studente;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerLogin implements Initializable {
    private double x = 0, y = 0;

    @FXML
    private AnchorPane sideBar;

    private Stage stage;

    private Segreteria segreteria;
    private List<Professore> listaProfessori = new ArrayList<>();
    private List<Studente> listaStudenti = new ArrayList<>();;
    private GestoreCredenziali gestoreCredenziali;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sideBar.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });

        sideBar.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX() - x);
            stage.setY(mouseEvent.getScreenY() - y);
        });
        
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setSegreteria(Segreteria segreteria) {
        this.segreteria = segreteria;
    }

    public void setListaProfessori(List<Professore> listaProfessori) {
        this.listaProfessori = listaProfessori;
    }

    public void setListaStudenti(List<Studente> listaStudenti) {
        this.listaStudenti = listaStudenti;
    }

    public void setGestoreCredenziali(GestoreCredenziali gestoreCredenziali) {
        this.gestoreCredenziali = gestoreCredenziali;
    }

    @FXML
    void closeProgram(ActionEvent event) {
        stage.close();
    }
}
