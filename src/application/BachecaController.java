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
 * Controller per la visualizzazione della bacheca della classe.
 */
public class BachecaController {
    @FXML
    private VBox bachecaVBox;

    private Classe classe;

    /**
     * Metodo di inizializzazione del controller.
     */
    public void initialize() {

    }

    /**
     * Imposta la classe corrente e aggiorna la bacheca.
     * 
     * @param classe La classe corrente
     */
    public void setClasse(Classe classe) {
        this.classe = classe;
        updateBacheca();
    }

    /**
     * Aggiorna la bacheca con i contenuti correnti.
     */
    private void updateBacheca() {
        if (classe != null) {
            populateBacheca(classe.getBacheca());
        }
    }

    /**
     * Popola la bacheca con i contenuti forniti.
     * 
     * @param bacheca Mappa contenente i contenuti della bacheca per data
     */
    private void populateBacheca(Map<LocalDate, List<String>> bacheca) {
        bachecaVBox.getChildren().clear();
        bacheca.forEach((data, list) -> {
            VBox entryBox = new VBox(10);
            entryBox.setStyle("-fx-background-color: white;" +
                    "-fx-background-radius: 10;" +
                    "-fx-border-radius: 10;" +
                    "-fx-border-color: #ccc;" +
                    "-fx-border-width: 1;" +
                    "-fx-padding: 10;" +
                    "-fx-spacing: 5;");
            entryBox.setMaxWidth(Double.MAX_VALUE);  // Permette al riquadro di espandersi

            Label dateLabel = new Label("Per il: " + data.toString());
            dateLabel.setStyle("-fx-font-weight: bold;");
            entryBox.getChildren().add(dateLabel);

            list.forEach(item -> {
                HBox itemBox = new HBox(10);
                itemBox.setMaxWidth(Double.MAX_VALUE);  // Permette all'HBox di espandersi

                Label itemLabel = new Label(item);
                itemLabel.setMaxWidth(Double.MAX_VALUE);  // Permette all'etichetta di espandersi
                itemLabel.setWrapText(true);

                Region spacer = new Region();
                HBox.setHgrow(spacer, Priority.ALWAYS);

                Button deleteButton = new Button("Elimina");
                deleteButton.setStyle("-fx-background-color: black; -fx-text-fill: white;");
                deleteButton.setOnAction(event -> {
                    list.remove(item);
                    if (list.isEmpty()) {
                        classe.getBacheca().remove(data);
                    }
                    updateBacheca();
                });

                HBox.setMargin(deleteButton, new Insets(-28, 0, 0, -50));
                itemBox.getChildren().addAll(itemLabel, spacer, deleteButton);
                itemBox.setAlignment(Pos.CENTER_LEFT);
                entryBox.getChildren().add(itemBox);
            });

            bachecaVBox.getChildren().add(entryBox);
        });
    }

}
