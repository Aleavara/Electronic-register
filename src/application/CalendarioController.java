package application;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import scuola.Studente;

import java.time.LocalDate;

public class CalendarioController {

    @FXML
    private DatePicker datePicker;

    @FXML
    private ListView<String> listView;

    @FXML
    private TextField textField;

    private Studente studente;

    public void setStudente(Studente studente) {
        this.studente = studente;
    }

    @FXML
    public void aggiungiImpegno() {
        LocalDate data = datePicker.getValue();
        String impegno = textField.getText();
        studente.aggiungiImpegno(data, impegno);
        aggiornaImpegni();
    }

    @FXML
    public void dataSelezionata() {
        aggiornaImpegni();
    }

    private void aggiornaImpegni() {
        listView.getItems().clear();
        LocalDate data = datePicker.getValue();
        if (data != null) {
            listView.getItems().addAll(studente.getImpegni(data));
        }
    }
}
