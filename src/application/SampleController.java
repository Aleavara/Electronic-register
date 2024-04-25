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

    
    public void setStudente(Studente studente) {
        this.studente = studente;
    }
    
    @FXML
    private void initialize() {
    	outputLabel.setText(""); 
        
    
        if (gridPane != null) {
         
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setMinWidth(100);
            columnConstraints.setHgrow(Priority.ALWAYS);
            gridPane.getColumnConstraints().add(columnConstraints);
            
         
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setMinHeight(100);
            rowConstraints.setVgrow(Priority.ALWAYS);
            gridPane.getRowConstraints().add(rowConstraints);
        }
    }
    
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