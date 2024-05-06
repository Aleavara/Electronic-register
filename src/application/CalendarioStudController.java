package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import scuola.Studente;

import java.time.LocalDate;
import java.util.List;

public class CalendarioStudController {

    @FXML
    private Label lblNomeMese;

    @FXML
    private GridPane gridCalendario;

    private Studente studente;

    public void setStudente(Studente studente) {
        this.studente = studente;
        aggiornaCalendario();
    }

    private void aggiornaCalendario() {
        gridCalendario.getChildren().clear();
        LocalDate dataCorrente = LocalDate.now();
        int giornoInizioMese = 1;
        int giorniMese = dataCorrente.lengthOfMonth();

        // Imposta il nome del mese
        lblNomeMese.setText(dataCorrente.getMonth().toString());

        // Popola il calendario con i giorni del mese e gli impegni dello studente
        for (int i = 0; i < giorniMese; i++) {
            LocalDate data = LocalDate.of(dataCorrente.getYear(), dataCorrente.getMonth(), giornoInizioMese);
            List<String> impegni = studente.getImpegni(data);
            Rectangle quadratoGiorno = new Rectangle(50, 50);
            quadratoGiorno.setFill(Color.LIGHTGRAY);
            gridCalendario.add(quadratoGiorno, i % 7, i / 7);

            // Aggiungi impegni al quadrato del giorno
            StringBuilder testoImpegni = new StringBuilder();
            for (String impegno : impegni) {
                testoImpegni.append(impegno).append("\n");
            }
            Label lblImpegni = new Label(testoImpegni.toString());
            gridCalendario.add(lblImpegni, i % 7, i / 7);
            giornoInizioMese++;
        }
    }
    @FXML
    private void mostraMesePrecedente() {
        LocalDate dataCorrente = LocalDate.now().minusMonths(1);
        studente.setCalendario(dataCorrente);
        aggiornaCalendario();
    }

    @FXML
    private void mostraMeseSuccessivo() {
        LocalDate dataCorrente = LocalDate.now().plusMonths(1);
        studente.setCalendario(dataCorrente);
        aggiornaCalendario();
    }
}