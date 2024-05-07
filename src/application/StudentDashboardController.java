package application;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import scuola.Classe;
import scuola.Studente;

public class StudentDashboardController {

    @FXML
    private ProgressBar fillBar;

    @FXML
    private TextArea compitiAssegnatiTextArea;

   
    private StudenteService studenteService;
    
    @FXML
    private TextArea impegniTextArea;
    
    @FXML
    private Text mediaText;

    // Metodo per mostrare i prossimi impegni
    private void mostraProssimiImpegni() {
    	Studente studente = studenteService.getStudente();
        if (studente != null) {
            // Ottenere la data corrente
            LocalDate oggi = LocalDate.now();
            
            // Ottenere i prossimi impegni dal calendario dello studente
            List<String> prossimiImpegni = studente.getImpegni(oggi);
            
            // Aggiungere i prossimi impegni al TextArea
            impegniTextArea.clear();
            prossimiImpegni.forEach(impegno -> {
                impegniTextArea.appendText(impegno + "\n");
            });
        }
    }
    


    // Metodo chiamato durante l'inizializzazione del controller
    public void initialize() {
        // Disabilita la modifica del TextArea
        compitiAssegnatiTextArea.setEditable(false);
    }

    public void setStudenteService(StudenteService studenteService) {
        this.studenteService = studenteService;
        aggiornaRiempimentoBarra();
        mostraCompitiAssegnati();
        mostraProssimiImpegni();
    }

    // Metodo per aggiornare il riempimento della barra in base alla media generale dello studente
    private void aggiornaRiempimentoBarra() {
        Studente studente = studenteService.getStudente();
        if (studente != null && studente.getMediaGenerale() != null) {
            double media = studente.getMediaGenerale();
            fillBar.setProgress(media / 10.0);
            
            // Imposta il colore della barra di riempimento in base alla media dello studente
            Color color;
            if (media < 6.0) {
                color = Color.RED; // Voto basso, colore rosso
            } else if (media < 8.0) {
                color = Color.ORANGE; // Voto medio, colore arancione
            } else {
                color = Color.GREEN; // Voto alto, colore verde
            }
            // Applica il colore alla barra di riempimento
            fillBar.setStyle("-fx-accent: " + color.toString().replace("0x", "#"));
            
            // Aggiorna il testo della media dello studente
            mediaText.setText(String.format("%.2f", media));
        }
    }

    // Metodo per mostrare i prossimi compiti assegnati
    private void mostraCompitiAssegnati() {
    	Studente studente = studenteService.getStudente();
        if (studente != null && studente.getClasse() != null) {
            Classe classe = studente.getClasse();
            // Ottenere i prossimi compiti assegnati dalla classe dello studente
            // Supponiamo che il formato dei compiti assegnati sia una mappa con LocalDate come chiave e lista di stringhe come valore
            Map<LocalDate, List<String>> compitiAssegnati = classe.getCompitiAssegnati();
            // Aggiungere i compiti assegnati al TextArea
            compitiAssegnati.forEach((data, compiti) -> {
                compitiAssegnatiTextArea.appendText("Data: " + data + "\n");
                compiti.forEach(compito -> {
                    compitiAssegnatiTextArea.appendText("- " + compito + "\n");
                });
                compitiAssegnatiTextArea.appendText("\n");
            });
        }
    }
    
    @FXML
    private void handleButtonClick(ActionEvent event) {
        try {
            // Carica la nuova scena dal file FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/calendarioAmm.fxml"));
            Parent root = loader.load();
            
            // Ottieni il controller della nuova scena
            CalendarController controller = loader.getController();
            LocalDate date = LocalDate.of(2024, 5, 10);
            this.studenteService.getStudente().aggiungiImpegno(date, "uagliò");
            // Passa lo studente al controller
            controller.setStudente(studenteService.getStudente());
            controller.drawCalendar();
            
            // Crea una nuova finestra per la nuova scena
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}