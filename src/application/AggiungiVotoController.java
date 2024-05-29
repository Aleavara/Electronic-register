package application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import scuola.Classe;
import scuola.Professore;
import scuola.Studente;
import scuola.Voto;

import java.time.LocalDate;
import java.util.List;

public class AggiungiVotoController {

    @FXML
    private ChoiceBox<Classe> classeChoiceBox;

    @FXML
    private ChoiceBox<Studente> studenteChoiceBox;

    @FXML
    private TextField votoTextField;

    @FXML
    private DatePicker dataVotoPicker;

    @FXML
    private Button aggiungiVotoButton;

    @FXML
    private Label statusLabel;

    private Professore professore;

    public void setProfessore(Professore professore) {
        this.professore = professore;
        initialize();
    }

    private void initialize() {
        List<Classe> classi = professore.getClassi();
        classeChoiceBox.getItems().addAll(classi);

        classeChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                List<Studente> studenti = newValue.getStudenti();
                studenteChoiceBox.getItems().clear();
                studenteChoiceBox.getItems().addAll(studenti);
            }
        });

        aggiungiVotoButton.setOnAction(event -> aggiungiVoto());
    }

    private void aggiungiVoto() {
        Classe classeSelezionata = classeChoiceBox.getValue();
        Studente studenteSelezionato = studenteChoiceBox.getValue();
        String votoText = votoTextField.getText();
        LocalDate dataVoto = dataVotoPicker.getValue();

        if (classeSelezionata == null || studenteSelezionato == null || votoText.isEmpty() || dataVoto == null) {
            statusLabel.setText("Per favore, seleziona una classe, uno studente, inserisci un voto e seleziona una data.");
            return;
        }

        double voto;
        try {
            voto = Double.parseDouble(votoText);
        } catch (NumberFormatException e) {
            statusLabel.setText("Il voto deve essere un numero.");
            return;
        }

        Voto nuovoVoto = new Voto(dataVoto, voto, this.professore);
        this.professore.aggiungiVoto(studenteSelezionato, dataVoto, voto);
        statusLabel.setText("Voto aggiunto con successo.");
    }
}
