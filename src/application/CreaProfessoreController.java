package application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import scuola.Classe;
import scuola.Professore;
import scuola.Segreteria;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Controller per la creazione di un nuovo professore.
 * 
 * @autore Alessio Avarapattù
 */
public class CreaProfessoreController implements Initializable {

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private TextField txtIndirizzo;

    @FXML
    private TextField txtCodiceFiscale;

    @FXML
    private DatePicker datepickerDataNascita;

    @FXML
    private TextField txtMateria;

    @FXML
    private VBox vboxClassi;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtPassword;

    private Segreteria segreteria;
    private Map<CheckBox, Classe> checkBoxClasseMap = new HashMap<>();

    /**
     * Metodo di inizializzazione chiamato automaticamente dopo il caricamento del file FXML.
     * 
     * @param location  La posizione utilizzata per risolvere il percorso relativo dell'oggetto radice, o null se il percorso non è noto.
     * @param resources Le risorse utilizzate per localizzare l'oggetto radice, o null se l'oggetto radice non è stato localizzato.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Metodo di inizializzazione vuoto, può essere utilizzato per configurazioni iniziali
    }

    /**
     * Popola la VBox con le CheckBox per le classi disponibili.
     */
    @FXML
    public void faccioIo() {
        List<Classe> listaClassi = segreteria.getListaClassi();
        for (Classe classe : listaClassi) {
            CheckBox checkBox = new CheckBox(classe.toString());
            vboxClassi.getChildren().add(checkBox);
            checkBoxClasseMap.put(checkBox, classe); // Associa la classe alla CheckBox
        }
    }

    /**
     * Imposta l'oggetto Segreteria per questo controller.
     *
     * @param segreteria L'istanza di Segreteria da impostare.
     */
    public void setSegreteria(Segreteria segreteria) {
        this.segreteria = segreteria;
    }

    /**
     * Gestisce il clic sul pulsante per creare un nuovo professore.
     */
    @FXML
    void creaProfessore() {
        String nome = txtNome.getText();
        String cognome = txtCognome.getText();
        String indirizzo = txtIndirizzo.getText();
        String codiceFiscale = txtCodiceFiscale.getText();
        LocalDate dataNascita = datepickerDataNascita.getValue();
        String materia = txtMateria.getText();
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        if (nome.isEmpty() || cognome.isEmpty() || indirizzo.isEmpty() || codiceFiscale.isEmpty() || dataNascita == null
                || materia.isEmpty() || username.isEmpty() || password.isEmpty()) {
            showAlert(AlertType.WARNING, "Campi Mancanti", "Per favore, compila tutti i campi.");
            return;
        }

        try {
            List<Classe> classiSelezionate = new ArrayList<>();
            for (Map.Entry<CheckBox, Classe> entry : checkBoxClasseMap.entrySet()) {
                CheckBox checkBox = entry.getKey();
                if (checkBox.isSelected()) {
                    classiSelezionate.add(entry.getValue());
                }
            }

            Professore p = new Professore(nome, cognome, indirizzo, codiceFiscale, dataNascita, materia,
                    classiSelezionate);
            segreteria.aggiungiProf(p, username, password);
            showAlert(AlertType.INFORMATION, "Successo", "Professore aggiunto con successo.");

        } catch (Exception e) {
            showAlert(AlertType.ERROR, "Errore", "Si è verificato un errore durante la creazione del professore.");
        }
    }

    /**
     * Mostra un messaggio di avviso.
     *
     * @param alertType Il tipo di avviso da mostrare.
     * @param title     Il titolo dell'avviso.
     * @param message   Il messaggio dell'avviso.
     */
    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
