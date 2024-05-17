package application;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import scuola.Classe;
import scuola.Segreteria;
import scuola.Studente;

public class SegreteriaDashboardController implements Initializable {
    
    @FXML
    private TextField nomeField;

    @FXML
    private TextField cognomeField;

    @FXML
    private TextField indirizzoField;

    @FXML
    private TextField codiceFiscaleField;

    @FXML
    private DatePicker dataNascitaPicker;

    @FXML
    private ComboBox<Classe> classeComboBox;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    private Segreteria segreteria;

    public SegreteriaDashboardController() {
    
    }
    
    public void setSegreteria(Segreteria segreteria) {
        this.segreteria = segreteria;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    
    }
    
    
    public void faccioIo() {
        if (segreteria != null) {
            classeComboBox.setItems(FXCollections.observableArrayList(segreteria.getListaClassi()));
        } else {
            showAlert(Alert.AlertType.ERROR, "Errore", "Impossibile caricare la lista delle classi.");
        }
    }

    @FXML
    private void creaStudenteButtonClicked() {
        String nome = nomeField.getText();
        String cognome = cognomeField.getText();
        String indirizzo = indirizzoField.getText();
        String codiceFiscale = codiceFiscaleField.getText();
        LocalDate dataNascita = dataNascitaPicker.getValue();
        Classe selectedClasse = classeComboBox.getValue();
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Controlla che tutti i campi siano stati compilati
        if (nome.isEmpty() || cognome.isEmpty() || indirizzo.isEmpty() || codiceFiscale.isEmpty() || dataNascita == null || selectedClasse == null || username.isEmpty() || password.isEmpty()) {
            // Mostra un messaggio di errore
            showAlert(Alert.AlertType.ERROR, "Errore", "Per favore, completa tutti i campi.");
        } else {
            // Crea lo studente utilizzando la segreteria
            segreteria.creaStudente(nome, cognome, indirizzo, codiceFiscale, dataNascita, selectedClasse, username, password);
            // Mostra un messaggio di successo
            showAlert(Alert.AlertType.INFORMATION, "Successo", "Studente creato con successo.");
            System.out.println(selectedClasse.getSezione());
            // Pulisce i campi del form dopo la creazione dello studente
            clearFields();
        }
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void clearFields() {
        nomeField.clear();
        cognomeField.clear();
        indirizzoField.clear();
        codiceFiscaleField.clear();
        dataNascitaPicker.setValue(null);
        classeComboBox.setValue(null);
        usernameField.clear();
        passwordField.clear();
    }
}
