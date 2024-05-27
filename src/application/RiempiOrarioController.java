package application;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import scuola.Classe;
import scuola.Professore;

import java.util.List;

public class RiempiOrarioController {

    @FXML
    private ChoiceBox<Classe> lunediOra1, martediOra1, mercolediOra1, giovediOra1, venerdiOra1, sabatoOra1;
    @FXML
    private ChoiceBox<Classe> lunediOra2, martediOra2, mercolediOra2, giovediOra2, venerdiOra2, sabatoOra2;
    @FXML
    private ChoiceBox<Classe> lunediOra3, martediOra3, mercolediOra3, giovediOra3, venerdiOra3, sabatoOra3;
    @FXML
    private ChoiceBox<Classe> lunediOra4, martediOra4, mercolediOra4, giovediOra4, venerdiOra4, sabatoOra4;
    @FXML
    private ChoiceBox<Classe> lunediOra5, martediOra5, mercolediOra5, giovediOra5, venerdiOra5, sabatoOra5;
    @FXML
    private ChoiceBox<Classe> lunediOra6, martediOra6, mercolediOra6, giovediOra6, venerdiOra6, sabatoOra6;

    private Professore professore;

    /**
     * Imposta il professore corrente.
     * 
     * @param professore Il professore corrente
     */
    public void setProfessore(Professore professore) {
        this.professore = professore;
        populateChoiceBoxes();
    }

    /**
     * Popola i ChoiceBox con le classi del professore.
     */
    private void populateChoiceBoxes() {
        List<Classe> classi = professore.getClassi();
        ChoiceBox[] choiceBoxes = {
            lunediOra1, martediOra1, mercolediOra1, giovediOra1, venerdiOra1, sabatoOra1,
            lunediOra2, martediOra2, mercolediOra2, giovediOra2, venerdiOra2, sabatoOra2,
            lunediOra3, martediOra3, mercolediOra3, giovediOra3, venerdiOra3, sabatoOra3,
            lunediOra4, martediOra4, mercolediOra4, giovediOra4, venerdiOra4, sabatoOra4,
            lunediOra5, martediOra5, mercolediOra5, giovediOra5, venerdiOra5, sabatoOra5,
            lunediOra6, martediOra6, mercolediOra6, giovediOra6, venerdiOra6, sabatoOra6
        };
        
        for (ChoiceBox<Classe> choiceBox : choiceBoxes) {
            choiceBox.getItems().addAll(classi);
            choiceBox.getItems().add(null); // Per le ore libere
        }
    }

    /**
     * Salva l'orario settimanale inserito dal professore.
     */
    @FXML
    private void salvaOrario() {
        ChoiceBox[] choiceBoxes = {
            lunediOra1, martediOra1, mercolediOra1, giovediOra1, venerdiOra1, sabatoOra1,
            lunediOra2, martediOra2, mercolediOra2, giovediOra2, venerdiOra2, sabatoOra2,
            lunediOra3, martediOra3, mercolediOra3, giovediOra3, venerdiOra3, sabatoOra3,
            lunediOra4, martediOra4, mercolediOra4, giovediOra4, venerdiOra4, sabatoOra4,
            lunediOra5, martediOra5, mercolediOra5, giovediOra5, venerdiOra5, sabatoOra5,
            lunediOra6, martediOra6, mercolediOra6, giovediOra6, venerdiOra6, sabatoOra6
        };

        for (int ora = 0; ora < 6; ora++) {
            for (int giorno = 0; giorno < 6; giorno++) {
                professore.aggiungiClasse(giorno, ora, (Classe) choiceBoxes[ora * 6 + giorno].getValue());
            }
        }

        System.out.println("Orario salvato con successo!");
    }
}
