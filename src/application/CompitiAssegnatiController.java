package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import scuola.Classe;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Controller per la gestione della visualizzazione dei compiti assegnati di una classe.
 * 
 * @autore Alessio Avarapattù
 */
public class CompitiAssegnatiController {

    @FXML
    private VBox compitiAssegnatiVBox;

    private Classe classe;

    /**
     * Metodo di inizializzazione chiamato automaticamente dopo il caricamento del file FXML.
     */
    public void initialize() {
        // Metodo di inizializzazione vuoto, può essere utilizzato per configurazioni iniziali
    }

    /**
     * Imposta la classe corrente e aggiorna la visualizzazione dei compiti assegnati.
     *
     * @param classe La classe da impostare.
     */
    public void setClasse(Classe classe) {
        this.classe = classe;
        updateCompitiAssegnati();
    }

    /**
     * Aggiorna la visualizzazione dei compiti assegnati per la classe corrente.
     */
    private void updateCompitiAssegnati() {
        if (classe != null) {
            populateCompitiAssegnati(classe.getCompitiAssegnati());
        }
    }

    /**
     * Popola la visualizzazione dei compiti assegnati con i dati forniti.
     *
     * @param compitiAssegnati Mappa contenente le date e i relativi compiti assegnati.
     */
    private void populateCompitiAssegnati(Map<LocalDate, List<String>> compitiAssegnati) {
        compitiAssegnatiVBox.getChildren().clear();
        compitiAssegnati.forEach((data, list) -> {
            VBox entryBox = new VBox(10);
            entryBox.setStyle("-fx-background-color: white;" + "-fx-background-radius: 10;" + "-fx-border-radius: 10;"
                    + "-fx-border-color: #ccc;" + "-fx-border-width: 1;" + "-fx-padding: 10;" + "-fx-spacing: 5;");

            Label dateLabel = new Label("Per il: " + data.toString());
            dateLabel.setStyle("-fx-font-weight: bold;");
            entryBox.getChildren().add(dateLabel);

            list.forEach(item -> {
                HBox itemBox = new HBox(10);
                Label itemLabel = new Label(item);

                Region spacer = new Region();
                HBox.setHgrow(spacer, Priority.ALWAYS);

                Button deleteButton = new Button("Elimina");
                deleteButton.setStyle("-fx-background-color: black; -fx-text-fill: white;");
                deleteButton.setOnAction(event -> {
                    list.remove(item);
                    if (list.isEmpty()) {
                        classe.getCompitiAssegnati().remove(data);
                    }
                    updateCompitiAssegnati();
                });

                HBox.setMargin(deleteButton, new Insets(-28, 0, 0, -50));
                itemBox.getChildren().addAll(itemLabel, spacer, deleteButton);
                itemBox.setAlignment(Pos.CENTER_LEFT);
                entryBox.getChildren().add(itemBox);
            });

            compitiAssegnatiVBox.getChildren().add(entryBox);
        });
    }
}
