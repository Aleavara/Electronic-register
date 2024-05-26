package application;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import scuola.Studente;
import scuola.Voto;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

/**
 * Controller per la visualizzazione dei voti dello studente.
 */
public class VotiStudenteController {

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox vbox;

    private StudenteService studenteService;

    /**
     * Metodo chiamato durante l'inizializzazione del controller.
     */
    public void initialize() {
        // Assicura che lo studente non sia nullo prima di popolare i voti
        if (studenteService != null) {
            popolaVoti();
        }
    }

    /**
     * Imposta il servizio studente per questo controller.
     * @param studente Il servizio studente da impostare
     */
    public void setStudenteService(StudenteService studente) {
        this.studenteService = studente;
        // Se lo studente non è nullo, popola i voti
        if (studente != null) {
            popolaVoti();
        }
    }

    /**
     * Popola la visualizzazione dei voti dello studente.
     */
    private void popolaVoti() {
        Studente studente = studenteService.getStudente();
        Map<String, List<Voto>> voti = studente.getVoti();
        voti.forEach((materia, listaVoti) -> {
            listaVoti.forEach(voto -> {
           
                StackPane votoPane = new StackPane();
                votoPane.setPrefSize(300, 100);

               
                Rectangle riquadro = new Rectangle(300, 100);
                riquadro.setArcWidth(20);
                riquadro.setArcHeight(20);
                riquadro.setFill(Color.WHITE);

               
                Text votoText = new Text();
                votoText.setFont(new Font(14));
                votoText.setText("Inserito da: " + voto.getProfInserente().getNome() + " " + voto.getProfInserente().getCognome() + "\n" +
                        "Il: " + voto.getDataInserimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\n" +
                        "Voto: " + voto.getVoto());
                votoText.setStyle("-fx-font-weight: bold");

               
                votoPane.getChildren().addAll(riquadro, votoText);

            
                vbox.getChildren().add(votoPane);
            });
        });
    }
}
