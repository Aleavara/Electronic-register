package application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import scuola.Classe;
import scuola.Professore;
import scuola.Studente;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class ClasseViewController {
    @FXML
    private VBox bachecaVBox;

    @FXML
    private VBox compitiVBox;

    @FXML
    private VBox promemoriaVBox;

    @FXML
    private ChoiceBox<String> categoryChoiceBox;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField inputTextField;

    @FXML
    private Label classeLabel;
    @FXML
    private VBox studentiVBox;

    @FXML
    private VBox professoriVBox;

    private Classe classe;

    public void initialize() {
        // Imposta le opzioni per la scelta della categoria
        categoryChoiceBox.setItems(FXCollections.observableArrayList("bacheca", "compiti assegnati", "promemoria"));
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
        updateLabels();
    }

    public void updateLabels() {
        if (this.classe != null) {
            // Visualizza il nome della classe
            classeLabel.setText("Classe: " + classe.getSezione());

            // Popola la bacheca
            populateItemsWithDeleteButton(bachecaVBox, classe.getBacheca());

            // Popola i compiti assegnati
            populateItemsWithDeleteButton(compitiVBox, classe.getCompitiAssegnati());

            // Popola i promemoria
            populateItemsWithDeleteButton(promemoriaVBox, classe.getPromemoria());

            // Popola la lista dei professori
            populateProfessoriList();

            // Popola la lista degli studenti
            populateStudentiList();
        }
    }

    private void populateItemsWithDeleteButton(VBox container, Map<LocalDate, List<String>> items) {
        container.getChildren().clear();
        items.forEach((data, list) -> {
            VBox entryBox = new VBox();
            Label dateLabel = new Label(data.toString());
            entryBox.getChildren().add(dateLabel);
            list.forEach(item -> {
                HBox itemBox = new HBox();
                Label itemLabel = new Label(item);
                Button deleteButton = new Button("Elimina");
                deleteButton.setOnAction(event -> {
                    // Rimuovi l'elemento dalla mappa
                    list.remove(item);
                    // Rimuovi la data se la lista è vuota
                    if (list.isEmpty()) {
                        container.getChildren().remove(entryBox);
                    }
                    // Aggiorna l'interfaccia utente
                    updateLabels();
                });
                itemBox.getChildren().addAll(itemLabel, deleteButton);
                entryBox.getChildren().add(itemBox);
            });
            container.getChildren().add(entryBox);
        });
    }

    @FXML
    private void populateProfessoriList() {
        professoriVBox.getChildren().clear();
        List<Professore> professori = classe.getProfessori();
        professori.forEach(professore -> {
            Label professoreLabel = new Label(professore.getNome() + " " + professore.getCognome());
            professoriVBox.getChildren().add(professoreLabel);
        });
    }
    @FXML
    private void populateStudentiList() {
        studentiVBox.getChildren().clear();
        List<Studente> studenti = classe.getStudenti();
        studenti.forEach(studente -> {
            Label studenteLabel = new Label(studente.getNome() + " " + studente.getCognome());
            studentiVBox.getChildren().add(studenteLabel);
        });
    }

    @FXML
    private void aggiungiElemento(ActionEvent event) {
        LocalDate selectedDate = datePicker.getValue();
        String category = categoryChoiceBox.getValue();
        String element = inputTextField.getText();
        
        if (selectedDate != null && category != null && !element.isEmpty()) {
            switch (category) {
                case "bacheca":
                    classe.aggiungiInBacheca(selectedDate, element);
                    break;
                case "compiti assegnati":
                    classe.aggiungiCompito(selectedDate, element);
                    break;
                case "promemoria":
                    classe.aggiungiPromemoria(selectedDate, element);
                    break;
            }
            updateLabels(); // Aggiorna l'interfaccia utente dopo l'aggiunta
        }
    }
}

