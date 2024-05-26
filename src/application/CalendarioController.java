package application;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import scuola.Studente;

import java.time.LocalDate;

/**
 * Controller per la gestione del calendario degli impegni di uno studente.
 * Autore: alessioavarappattu
 */
public class CalendarioController {

    @FXML
    private DatePicker datePicker;

    @FXML
    private ListView<String> listView;

    @FXML
    private TextField textField;

    private Studente studente;

    /**
     * Imposta lo studente per il quale visualizzare e gestire il calendario degli impegni.
     *
     * @param studente Lo studente di cui visualizzare e gestire il calendario.
     */
    public void setStudente(Studente studente) {
        this.studente = studente;
    }

    /**
     * Aggiunge un nuovo impegno per la data selezionata nel DatePicker.
     * Legge la data dal DatePicker e l'impegno dal TextField, poi aggiorna la lista degli impegni.
     */
    @FXML
    public void aggiungiImpegno() {
        LocalDate data = datePicker.getValue();
        String impegno = textField.getText();
        studente.aggiungiImpegno(data, impegno);
        aggiornaImpegni();
    }

    /**
     * Aggiorna la lista degli impegni quando una nuova data viene selezionata nel DatePicker.
     */
    @FXML
    public void dataSelezionata() {
        aggiornaImpegni();
    }

    /**
     * Aggiorna la ListView con gli impegni per la data selezionata nel DatePicker.
     * Pulisce la ListView e aggiunge gli impegni dello studente per la data corrente.
     */
    private void aggiornaImpegni() {
        listView.getItems().clear();
        LocalDate data = datePicker.getValue();
        if (data != null) {
            listView.getItems().addAll(studente.getImpegni(data));
        }
    }
}
