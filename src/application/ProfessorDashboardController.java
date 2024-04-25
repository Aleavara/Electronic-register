package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import scuola.Professore;

public class ProfessorDashboardController {
    
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
    private Label materiaLabel;
    
    @FXML
    private Label usernameLabel;

    private Professore professore;
    
    public ProfessorDashboardController() {
        // Costruttore vuoto richiesto da FXMLLoader
    }

    //public void setProfessore(Professore professore) {
     //   this.professore = professore;
        // Mostra i dati del professore nella dashboard
     //   nameLabel.setText(professore.getNome());
      //  surnameLabel.setText(professore.getCognome());
      //  addressLabel.setText(professore.getIndirizzo());
      //  codiceFiscaleLabel.setText(professore.getCodiceFiscale());
      //  birthDateLabel.setText(professore.getDataNascita().toString());
     //   materiaLabel.setText(professore.getMateria());
     //   usernameLabel.setText(professore.getUsername());
   // }
}

