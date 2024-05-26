package application;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import scuola.Classe;
import scuola.Segreteria;

/**
 * Controller per la creazione di una nuova classe.
 * 
 * @autore Alessio Avarapattù
 */
public class CreaClasseController {

    @FXML
    private TextField nomeClasseField;

    private Segreteria segreteria;

    /**
     * Imposta l'oggetto Segreteria per questo controller.
     *
     * @param segreteria L'istanza di Segreteria da impostare.
     */
    public void setSegreteria(Segreteria segreteria) {
        this.segreteria = segreteria;
    }

    /**
     * Metodo di inizializzazione chiamato automaticamente dopo il caricamento del file FXML.
     */
    @FXML
    private void initialize() {
        // Metodo di inizializzazione vuoto, può essere utilizzato per configurazioni iniziali
    }

    /**
     * Gestisce il clic sul pulsante per creare una nuova classe.
     */
    @FXML
    private void creaClasseClicked() {
        String nomeClasse = nomeClasseField.getText();
        if (!nomeClasse.isEmpty()) {
            Classe nuovaClasse = new Classe(nomeClasse);
            try {
                segreteria.aggiungiClasse(nuovaClasse);
                mostraMessaggioConferma(nuovaClasse);
            } catch (IllegalArgumentException e) {
                mostraMessaggioErrore("Classe già presente",
                        "Una classe con la stessa sezione è già presente nella lista.");
            }
        }
    }

    /**
     * Mostra un messaggio di conferma dopo la creazione di una nuova classe.
     *
     * @param classe La classe appena creata.
     */
    private void mostraMessaggioConferma(Classe classe) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Creazione Classe");
        alert.setHeaderText(null);
        alert.setContentText("La classe è stata creata con successo!");
        alert.showAndWait();
    }

    /**
     * Mostra un messaggio di errore.
     *
     * @param titolo    Il titolo dell'errore.
     * @param contenuto Il contenuto del messaggio di errore.
     */
    private void mostraMessaggioErrore(String titolo, String contenuto) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(titolo);
        alert.setHeaderText(null);
        alert.setContentText(contenuto);
        alert.showAndWait();
    }
}
