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

/**
 * Controller per la dashboard della segreteria.
 * Gestisce l'interazione dell'utente con la finestra e le azioni associate ai controlli.
 */
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

    /**
     * Metodo costruttore della classe.
     */
    public SegreteriaDashboardController() {
    
    }
    
    /**
     * Imposta l'istanza della Segreteria.
     * @param segreteria L'istanza della segreteria da impostare.
     */
    public void setSegreteria(Segreteria segreteria) {
        this.segreteria = segreteria;
    }

    /**
     * Metodo di inizializzazione del controller.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    
    }
    
    /**
     * Metodo per popolare la ComboBox delle classi.
     */
    public void faccioIo() {
        if (segreteria != null) {
            classeComboBox.setItems(FXCollections.observableArrayList(segreteria.getListaClassi()));
        } else {
            showAlert(Alert.AlertType.ERROR, "Errore", "Impossibile caricare la lista delle classi.");
        }
    }

    /**
     * Gestisce l'evento del clic sul pulsante "Crea Studente".
     */
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
            
            // Pulisce i campi del form dopo la creazione dello studente
            clearFields();
        }
    }

    /**
     * Mostra un alert con il tipo specificato, il titolo e il contenuto forniti.
     * @param type Il tipo di alert.
     * @param title Il titolo dell'alert.
     * @param content Il contenuto dell'alert.
     */
    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /**
     * Pulisce tutti i campi del form.
     */
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
