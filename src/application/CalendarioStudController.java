package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import scuola.Studente;

import java.time.LocalDate;
import java.util.List;

/**
 * Controller per la gestione del calendario degli impegni di uno studente.
 * Autore: alessioavarappattu
 */
public class CalendarioStudController {

    @FXML
    private Label lblNomeMese;

    @FXML
    private GridPane gridCalendario;

    private Studente studente;

    /**
     * Imposta lo studente per il quale visualizzare e gestire il calendario degli impegni.
     *
     * @param studente Lo studente di cui visualizzare e gestire il calendario.
     */
    public void setStudente(Studente studente) {
        this.studente = studente;
        aggiornaCalendario();
    }

    /**
     * Aggiorna il calendario visualizzato nella GridPane con gli impegni dello studente.
     * Pulisce la GridPane e aggiunge i giorni del mese corrente con i rispettivi impegni.
     */
    private void aggiornaCalendario() {
        gridCalendario.getChildren().clear();
        LocalDate dataCorrente = LocalDate.now();
        int giornoInizioMese = 1;
        int giorniMese = dataCorrente.lengthOfMonth();

        lblNomeMese.setText(dataCorrente.getMonth().toString());

        for (int i = 0; i < giorniMese; i++) {
            LocalDate data = LocalDate.of(dataCorrente.getYear(), dataCorrente.getMonth(), giornoInizioMese);
            List<String> impegni = studente.getImpegni(data);
            Rectangle quadratoGiorno = new Rectangle(50, 50);
            quadratoGiorno.setFill(Color.LIGHTGRAY);
            gridCalendario.add(quadratoGiorno, i % 7, i / 7);

            StringBuilder testoImpegni = new StringBuilder();
            for (String impegno : impegni) {
                testoImpegni.append(impegno).append("\n");
            }
            Label lblImpegni = new Label(testoImpegni.toString());
            gridCalendario.add(lblImpegni, i % 7, i / 7);
            giornoInizioMese++;
        }
    }

    /**
     * Mostra il mese precedente nel calendario.
     * Imposta la data del calendario dello studente al mese precedente e aggiorna il calendario.
     */
    @FXML
    private void mostraMesePrecedente() {
        LocalDate dataCorrente = LocalDate.now().minusMonths(1);
        studente.setCalendario(dataCorrente);
        aggiornaCalendario();
    }

    /**
     * Mostra il mese successivo nel calendario.
     * Imposta la data del calendario dello studente al mese successivo e aggiorna il calendario.
     */
    @FXML
    private void mostraMeseSuccessivo() {
        LocalDate dataCorrente = LocalDate.now().plusMonths(1);
        studente.setCalendario(dataCorrente);
        aggiornaCalendario();
    }
}
