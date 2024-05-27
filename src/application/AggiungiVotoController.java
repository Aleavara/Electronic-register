package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import scuola.Classe;
import scuola.Professore;
import scuola.Studente;
import scuola.Voto;

import java.time.LocalDate;
import java.util.List;

/**
 * Controller per la finestra di aggiunta voto.
 * 
 * author Alessioavarappattu
 */
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

    private Professore professore;

    /**
     * Imposta il professore corrente.
     * 
     * @param professore Il professore corrente
     */
    public void setProfessore(Professore professore) {
        this.professore = professore;
        initialize();
    }

    /**
     * Metodo di inizializzazione del controller.
     */
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

    /**
     * Metodo per aggiungere un voto.
     */
    private void aggiungiVoto() {
        Classe classeSelezionata = classeChoiceBox.getValue();
        Studente studenteSelezionato = studenteChoiceBox.getValue();
        String votoText = votoTextField.getText();
        LocalDate dataVoto = dataVotoPicker.getValue();

        if (classeSelezionata == null || studenteSelezionato == null || votoText.isEmpty() || dataVoto == null) {
            System.out.println("Per favore, seleziona una classe, uno studente, inserisci un voto e seleziona una data.");
            return;
        }

        double voto;
        try {
            voto = Double.parseDouble(votoText);
        } catch (NumberFormatException e) {
            System.out.println("Il voto deve essere un numero.");
            return;
        }

        Voto nuovoVoto = new Voto(dataVoto,voto, this.professore);
       // studenteSelezionato.aggiungiVoto(this.professore.getMateria(), nuovoVoto);
        this.professore.aggiungiVoto(studenteSelezionato, dataVoto, voto);
        System.out.println("Voto aggiunto con successo.");
    }
}
