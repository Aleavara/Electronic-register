package application;

import java.time.LocalDate;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import scuola.Professore;
import scuola.Segreteria;
import scuola.Studente;

/**
 * Controller per la finestra di esempio.
 * Questa classe gestisce l'interazione dell'utente con la finestra e le azioni associate ai controlli.
 */
public class SampleController {
    
    @FXML
    private GridPane gridPane; 
    
    @FXML
    private DatePicker dataPicker;
    
    @FXML
    private Label outputLabel;
    
    @FXML
    private TextField impegnoTextField;
    
    private Studente studente;

    /**
     * Imposta il servizio per il recupero delle informazioni sugli studenti.
     * @param studenteService Il servizio per il recupero delle informazioni sugli studenti.
     */
    public void setStudenteService(Studente studenteService) {
        this.studente = studenteService;
    }

    /**
     * Metodo di inizializzazione del controller.
     */
    @FXML
    private void initialize() {
        outputLabel.setText("");

        if (gridPane != null) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setMinHeight(100);
            rowConstraints.setVgrow(Priority.ALWAYS);
            gridPane.getRowConstraints().add(rowConstraints);
        }
    }
    
    /**
     * Visualizza gli impegni dello studente per la data selezionata.
     */
    @FXML
    private void visualizzaImpegni() {
        
        LocalDate data = dataPicker.getValue();
        if (data != null) {
            List<String> impegni = studente.getImpegni(data);
            if (!impegni.isEmpty()) {
                StringBuilder sb = new StringBuilder();
                sb.append("Impegni per il ").append(data).append(": \n");
                for (String impegno : impegni) {
                    sb.append("- ").append(impegno).append("\n");
                }
                outputLabel.setText(sb.toString());
            } else {
                outputLabel.setText("Nessun impegno per questa data.");
            }
        } else {
            outputLabel.setText("Seleziona una data.");
        }
    }
    
    /**
     * Aggiunge un nuovo impegno per lo studente per la data selezionata.
     */
    @FXML
    private void aggiungiImpegno() {
    
        LocalDate data = dataPicker.getValue();
        String impegno = impegnoTextField.getText();
        if (data != null && !impegno.isEmpty()) {
            studente.aggiungiImpegno(data, impegno);
            outputLabel.setText("Impegno aggiunto con successo per il " + data);
        } else {
            outputLabel.setText("Seleziona una data e inserisci un impegno.");
        }
    }
}
