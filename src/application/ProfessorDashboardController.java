package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import scuola.Classe;
import scuola.OrarioRow;
import scuola.Professore;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller per la dashboard del professore.
 * 
 * @autore Alessio Avarapattù
 */
public class ProfessorDashboardController {

    @FXML
    private Label welcomeLabel;

    @FXML
    private Label matricolaLabel;

    @FXML
    private Label classesLabel;

    @FXML
    private TableView<OrarioRow> orarioTable;

    @FXML
    private TableColumn<OrarioRow, String> lunediColumn;

    @FXML
    private TableColumn<OrarioRow, String> martediColumn;

    @FXML
    private TableColumn<OrarioRow, String> mercolediColumn;

    @FXML
    private TableColumn<OrarioRow, String> giovediColumn;

    @FXML
    private TableColumn<OrarioRow, String> venerdiColumn;

    @FXML
    private TableColumn<OrarioRow, String> sabatoColumn;

    @FXML
    private VBox bachecaVBox;

    @FXML
    private VBox compitiVBox;

    @FXML
    private VBox promemoriaVBox;

    @FXML
    private DatePicker datePicker;

    @FXML
    private ChoiceBox<String> categoryChoiceBox;

    @FXML
    private TextField inputTextField;

    @FXML
    private VBox classCheckBoxVBox;

    private Professore professore;
    private Map<Classe, CheckBox> classCheckBoxMap;

    /**
     * Imposta il professore e aggiorna la vista.
     * 
     * @param professore Il professore da impostare.
     */
    public void setProfessore(Professore professore) {
        this.professore = professore;
        populateClassCheckBoxes();
        updateView();
    }

    /**
     * Aggiorna la vista della dashboard del professore.
     */
    public void updateView() {
        welcomeLabel.setText(professore.getNome() + " " + professore.getCognome());
        matricolaLabel.setText("Matricola: " + professore.getnMatricola());

        StringBuilder classi = new StringBuilder("");
        for (Classe classe : professore.getClassi()) {
            classi.append(classe.toString()).append(", ");
        }
        if (classi.length() > 2) {
            classi.setLength(classi.length() - 2);
        }
        classesLabel.setText(classi.toString());

        Classe[][] orarioSettimanale = professore.getOrarioSettimanale();
        ObservableList<OrarioRow> data = FXCollections.observableArrayList();

        for (int ora = 0; ora < 6; ora++) {
            OrarioRow row = new OrarioRow();
            for (int giorno = 0; giorno < 7; giorno++) {
                Classe classe = orarioSettimanale[giorno][ora];
                if (classe != null) {
                    row.setGiorno(giorno, classe.getSezione());
                }
            }
            data.add(row);
        }

        lunediColumn.setCellValueFactory(cellData -> cellData.getValue().giornoProperty(0));
        martediColumn.setCellValueFactory(cellData -> cellData.getValue().giornoProperty(1));
        mercolediColumn.setCellValueFactory(cellData -> cellData.getValue().giornoProperty(2));
        giovediColumn.setCellValueFactory(cellData -> cellData.getValue().giornoProperty(3));
        venerdiColumn.setCellValueFactory(cellData -> cellData.getValue().giornoProperty(4));
        sabatoColumn.setCellValueFactory(cellData -> cellData.getValue().giornoProperty(5));

        orarioTable.setItems(data);

        populateSection(bachecaVBox, professore, "Bacheca", Classe::getBacheca);
        populateSection(compitiVBox, professore, "Compiti Assegnati", Classe::getCompitiAssegnati);
        populateSection(promemoriaVBox, professore, "Promemoria", Classe::getPromemoria);
    }

    /**
     * Popola la sezione specificata con gli elementi appropriati.
     * 
     * @param sectionVBox      Il VBox della sezione da popolare.
     * @param professore       Il professore.
     * @param sectionTitle     Il titolo della sezione.
     * @param getItemsFunction La funzione per ottenere gli elementi della sezione.
     */
    private void populateSection(VBox sectionVBox, Professore professore, String sectionTitle,
                                 java.util.function.Function<Classe, Map<LocalDate, List<String>>> getItemsFunction) {
        sectionVBox.getChildren().clear();
        for (Classe classe : professore.getClassi()) {
            ListView<String> listView = new ListView<>();
            ObservableList<String> items = FXCollections.observableArrayList();

            Label sectionLabel = new Label(sectionTitle + " " + classe.toString() + ":");
            sectionVBox.getChildren().add(sectionLabel);

            for (Map.Entry<LocalDate, List<String>> entry : getItemsFunction.apply(classe).entrySet()) {
                for (String item : entry.getValue()) {
                    items.add(item + " (" + entry.getKey() + ")");
                }
            }

            listView.setItems(items);
            sectionVBox.getChildren().add(listView);
        }
    }

    /**
     * Popola la VBox con le CheckBox per le classi del professore.
     */
    private void populateClassCheckBoxes() {
        classCheckBoxMap = new HashMap<>();
        classCheckBoxVBox.getChildren().clear();

        for (Classe classe : professore.getClassi()) {
            CheckBox checkBox = new CheckBox(classe.toString());
            checkBox.setStyle("-fx-text-fill: white;");
            classCheckBoxVBox.getChildren().add(checkBox);
            classCheckBoxMap.put(classe, checkBox);
        }
    }

    /**
     * Aggiunge un nuovo elemento alla bacheca, ai compiti assegnati o ai promemoria.
     */
    @FXML
    private void aggiungiElemento() {
        LocalDate selectedDate = datePicker.getValue();
        String category = categoryChoiceBox.getValue();
        String newItem = inputTextField.getText();

        if (selectedDate == null || category == null || newItem.isEmpty()) {
            return;
        }

        for (Map.Entry<Classe, CheckBox> entry : classCheckBoxMap.entrySet()) {
            if (entry.getValue().isSelected()) {
                Classe classe = entry.getKey();
                switch (category) {
                    case "bacheca":
                        classe.aggiungiInBacheca(selectedDate, newItem);
                        break;
                    case "compiti assegnati":
                        classe.aggiungiCompito(selectedDate, newItem);
                        break;
                    case "promemoria":
                        classe.aggiungiPromemoria(selectedDate, newItem);
                        break;
                }
            }
        }

        updateView();
    }

    /**
     * Apre la finestra per aggiungere un voto.
     * 
     * @throws IOException Se si verifica un errore durante il caricamento del file FXML.
     */
    @FXML
    private void aggiungiVoto() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/aggiungiVoto.fxml"));
        Parent root = loader.load();
        AggiungiVotoController controller = loader.getController();
        controller.setProfessore(professore);
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 300, 230));
        stage.show();
    }
}
