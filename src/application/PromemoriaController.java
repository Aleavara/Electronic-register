/**
 * Controller per la visualizzazione e la gestione del promemoria nella GUI.
 * Questa classe gestisce l'aggiornamento e il popolamento del promemoria nella VBox specificata.
 * Autore: Alessio Avarappattu
 */
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

public class PromemoriaController {
    @FXML
    private VBox promemoriaVBox;

    private Classe classe;

    /**
     * Metodo di inizializzazione del controller.
     */
    public void initialize() {
       
    }

    /**
     * Imposta la classe per la quale visualizzare il promemoria.
     * @param classe La classe per la quale visualizzare il promemoria.
     */
    public void setClasse(Classe classe) {
        this.classe = classe;
        updatePromemoria();
    }

    /**
     * Aggiorna il promemoria visualizzato.
     */
    private void updatePromemoria() {
        if (classe != null) {
            populatePromemoria(classe.getPromemoria());
        }
    }

    /**
     * Popola la VBox con il promemoria specificato.
     * @param promemoria Il promemoria da visualizzare.
     */
    private void populatePromemoria(Map<LocalDate, List<String>> promemoria) {
        promemoriaVBox.getChildren().clear();
        promemoria.forEach((data, list) -> {
            VBox entryBox = new VBox(10);
            entryBox.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 10;" +
                "-fx-border-radius: 10;" +
                "-fx-border-color: #ccc;" +
                "-fx-border-width: 1;" +
                "-fx-padding: 10;" +
                "-fx-spacing: 5;"
            );

            // Aggiungi la riga "per il:" con la data
            Label dateLabel = new Label("Per il: " + data.toString());
            dateLabel.setStyle("-fx-font-weight: bold;");
            entryBox.getChildren().add(dateLabel);
            
            // Aggiungi i promemoria con il pulsante "Elimina"
            list.forEach(item -> {
                HBox itemBox = new HBox(10);
                Label itemLabel = new Label(item);

                // Crea uno spazio vuoto che cresce per spingere il pulsante a destra
                Region spacer = new Region();
                HBox.setHgrow(spacer, Priority.ALWAYS);

                Button deleteButton = new Button("Elimina");
                deleteButton.setStyle("-fx-background-color: black; -fx-text-fill: white;");
                deleteButton.setOnAction(event -> {
                    list.remove(item);
                    if (list.isEmpty()) {
                        classe.getPromemoria().remove(data);
                    }
                    updatePromemoria();
                });

                // Aggiungi margine superiore per posizionare il pulsante leggermente più in alto
                HBox.setMargin(deleteButton, new Insets(-28, 0, 0, -50)); // Spostamento più a sinistra e leggermente più in alto
                itemBox.getChildren().addAll(itemLabel, spacer, deleteButton);
                itemBox.setAlignment(Pos.CENTER_LEFT);
                entryBox.getChildren().add(itemBox);
            });

            promemoriaVBox.getChildren().add(entryBox);
        });
    }
}
