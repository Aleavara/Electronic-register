package application;

import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import scuola.Classe;

/**
 * Rappresenta una cella personalizzata per visualizzare le informazioni di una Classe
 * in una ListView.
 * Autore: alessioavarappattu
 */
public class ClasseListCell extends ListCell<Classe> {

    private HBox hbox = new HBox();
    private Label nameLabel = new Label();
    private Label detailsLabel = new Label("                                                              dettagli");
    private Label infoLabel = new Label();

    /**
     * Costruttore della ClasseListCell.
     * Inizializza i componenti della cella e imposta gli stili.
     */
    public ClasseListCell() {
        super();

        nameLabel.setStyle("-fx-text-fill: black; -fx-font-weight: bold;");
        detailsLabel.setStyle("-fx-text-fill: black; -fx-font-weight: bold;");
        infoLabel.setStyle("-fx-text-fill: black; -fx-font-weight: lighter;");

        HBox.setHgrow(nameLabel, Priority.ALWAYS);
        hbox.getChildren().addAll(nameLabel, infoLabel, detailsLabel);
    }

    /**
     * Aggiorna il contenuto della cella con le informazioni della Classe.
     *
     * @param item  La Classe da visualizzare.
     * @param empty Se true, la cella deve essere svuotata.
     */
    @Override
    protected void updateItem(Classe item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            nameLabel.setText(item.toString());
            infoLabel.setText(String.format(" (%d professori e %d studenti)", item.getProfessori().size(),
                    item.getStudenti().size()));

            if (isSelected()) {
                nameLabel.setText(item.toString() + "*");
            } else {
                nameLabel.setText(item.toString());
            }

            setGraphic(hbox);
        }
    }
}
