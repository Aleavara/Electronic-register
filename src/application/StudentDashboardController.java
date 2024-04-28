package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import scuola.Studente;

public class StudentDashboardController {
    
    @FXML
    private Label nameLabel;
    
    @FXML
    private Label surnameLabel;
    
    @FXML
    private Label addressLabel;
    
    @FXML
    private Label codiceFiscaleLabel;
    
    @FXML
    private Label birthDateLabel;
    
    @FXML
    private Label classLabel;

    private Studente studente;
    
    public void setStudente(Studente studente) {
        this.studente = studente;
        
        
        nameLabel.setText(studente.getNome());
        surnameLabel.setText(studente.getCognome());
        addressLabel.setText(studente.getIndirizzo());
        codiceFiscaleLabel.setText(studente.getCodiceFiscale());
        birthDateLabel.setText(studente.getDataNascita().toString());
       
    }
}