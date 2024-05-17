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
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    
    @FXML
    public void faccioIo() {
        List<Classe> listaClassi = segreteria.getListaClassi();
        for (Classe classe : listaClassi) {
            CheckBox checkBox = new CheckBox(classe.toString());
            vboxClassi.getChildren().add(checkBox);
            checkBoxClasseMap.put(checkBox, classe); // Associa la classe alla CheckBox
        }
    }
    
    public void setSegreteria(Segreteria segreteria) {
    	this.segreteria=segreteria;
    }

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

        // Verifica che tutti i campi obbligatori siano compilati
        if (nome.isEmpty() || cognome.isEmpty() || indirizzo.isEmpty() || codiceFiscale.isEmpty() ||
            dataNascita == null || materia.isEmpty() || username.isEmpty() || password.isEmpty()) {
            showAlert(AlertType.WARNING, "Campi Mancanti", "Per favore, compila tutti i campi.");
            return;
        }

        try {
            // Creazione della lista di classi selezionate
            List<Classe> classiSelezionate = new ArrayList<>();
            for (Map.Entry<CheckBox, Classe> entry : checkBoxClasseMap.entrySet()) {
                CheckBox checkBox = entry.getKey();
                if (checkBox.isSelected()) {
                    classiSelezionate.add(entry.getValue()); // Aggiungi la classe esistente
                }
            }

            // Creazione del professore
            segreteria.aggiungiProfessore(nome, cognome, indirizzo, codiceFiscale, dataNascita, materia, classiSelezionate, username, password);
            showAlert(AlertType.INFORMATION, "Successo", "Professore aggiunto con successo.");

        } catch (Exception e) {
            showAlert(AlertType.ERROR, "Errore", "Si è verificato un errore durante la creazione del professore.");
        }
    }
    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
