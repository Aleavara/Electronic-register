package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import scuola.Classe;
import scuola.Segreteria;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller per la dashboard iniziale della segreteria.
 * Gestisce l'interazione dell'utente con la finestra e le azioni associate ai controlli.
 */
public class SegreteriaDashboardControllerIniziale {

    @FXML
    private ListView<Classe> classiListView;

    private Segreteria segreteria;

    /**
     * Imposta l'istanza della segreteria.
     * @param seg L'istanza della segreteria da impostare.
     */
    public void setSegreteria(Segreteria seg) {
        this.segreteria = seg;
    }

    /**
     * Metodo di inizializzazione del controller.
     */
    public void initialize() {
        // Configura la factory delle celle personalizzata per la ListView
        classiListView.setCellFactory(new Callback<ListView<Classe>, ListCell<Classe>>() {
            @Override
            public ListCell<Classe> call(ListView<Classe> param) {
                return new ClasseListCell();
            }
        });

        // Abilita la selezione multipla nella ListView
        classiListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // Aggiungi un gestore di eventi per catturare il click destro sull'elemento della lista
        classiListView.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.SECONDARY) { // Verifica se è stato cliccato il tasto destro
                // Ottieni l'elemento selezionato
                Classe classeSelezionata = classiListView.getSelectionModel().getSelectedItem();
                if (classeSelezionata != null) {
                    // Esegui le azioni desiderate quando viene richiesto il menu contestuale sulla classe selezionata
                }
            }
        });
    }

    /**
     * Gestisce l'evento del clic sul pulsante "Aggiungi Studenti al Polo".
     */
    @FXML
    private void aggiungiStudentiAlPoloClicked(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/segreteria1_dashboard.fxml"));
            Parent root = loader.load();
            SegreteriaDashboardController controller = loader.getController();
            controller.setSegreteria(this.segreteria);
            controller.faccioIo();
            Stage stage = new Stage();
            Scene scene = new Scene(root, 400, 450);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Gestisce l'evento del clic sul pulsante "Elimina Classe".
     */
    @FXML
    private void eliminaClasseClicked() {
        // Ottieni tutte le classi selezionate
        List<Classe> classiSelezionate = new ArrayList<>(classiListView.getSelectionModel().getSelectedItems());
        
        // Rimuovi le classi selezionate dalla lista della segreteria
        segreteria.getListaClassi().removeAll(classiSelezionate);
        
        // Pulisci la ListView per riflettere i cambiamenti
        classiListView.getItems().clear();
        
        // Aggiorna la ListView con la nuova lista di classi
        classiListView.getItems().addAll(segreteria.getListaClassi());
    }

    /**
     * Gestisce l'evento del clic sul pulsante "Aggiungi Professori".
     */
    @FXML
    private void aggiungiProfessoriClicked() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/creaProf.fxml"));
            Parent root = loader.load();
            CreaProfessoreController controller = loader.getController();
            controller.setSegreteria(segreteria);
            controller.faccioIo();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gestisce l'evento del clic sul pulsante "Crea Classe".
     */
    @FXML
    private void creaClasseClicked() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/creaClasse.fxml"));
            Parent root = loader.load();
            CreaClasseController controller = loader.getController();
            controller.setSegreteria(segreteria);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Aggiorna le etichette per visualizzare la lista delle classi.
     */
    public void updateLabels() {
        classiListView.getItems().addAll(segreteria.getListaClassi());
        classiListView.setOnMouseClicked(event -> {
            if (event.getButton() != MouseButton.SECONDARY) { // Verifica se è stato cliccato il tasto destro
                Classe classeSelezionata = classiListView.getSelectionModel().getSelectedItem();
                if (classeSelezionata != null) {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/class_dashboard.fxml"));
                        Parent root = loader.load();
                        ClasseViewController controller = loader.getController();
                        controller.setClasse(classeSelezionata);
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
