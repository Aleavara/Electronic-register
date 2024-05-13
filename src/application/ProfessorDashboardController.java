package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import scuola.Classe;
import scuola.OrarioRow;
import scuola.Professore;

public class ProfessorDashboardController {
    @FXML
    private Label welcomeLabel;

    @FXML
    private Label matricolaLabel;

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

    private Professore professore;

    public void setProfessore(Professore professore) {
        this.professore = professore;
        updateView();
    }

    private void updateView() {
        welcomeLabel.setText("Docente: " + professore.getNome() + professore.getCognome());
        matricolaLabel.setText("Numero Matricola: " + professore.getnMatricola());

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
    }
}