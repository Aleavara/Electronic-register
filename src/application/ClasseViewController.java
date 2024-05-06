package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import scuola.Classe;

public class ClasseViewController {
    @FXML
    private VBox bachecaVBox;

    @FXML
    private VBox compitiVBox;

    @FXML
    private VBox promemoriaVBox;

    @FXML
    private Label classeLabel;

    @FXML
    private Label bachecaLabel;

    @FXML
    private Label compitiLabel;

    @FXML
    private Label promemoriaLabel;

   // @FXML
   // private Label orarioLabel;

    private Classe classe;

    public void initialize() {
        // Vuoto
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public void faccioIo(Classe classe) {
        if (this.classe == null) {
            System.out.println("era vuota");
            this.classe = classe;
        }
        System.out.println(this.classe);
    }

    public void updateLabels() {
        if (this.classe != null)
            System.out.println("non è nulla");
        // Visualizza il nome della classe
        classeLabel.setText("Classe: " + classe.getSezione());

        // Visualizza la bacheca
        StringBuilder bachecaText = new StringBuilder("Bacheca:\n");
        classe.getBacheca().forEach((data, note) -> {
            bachecaText.append(data.toString()).append(":\n");
            note.forEach(nota -> bachecaText.append("- ").append(nota).append("\n"));
        });
        bachecaLabel.setText(bachecaText.toString());

        // Visualizza i compiti assegnati
        StringBuilder compitiText = new StringBuilder("Compiti Assegnati:\n");
        classe.getCompitiAssegnati().forEach((data, compiti) -> {
            compitiText.append(data.toString()).append(":\n");
            compiti.forEach(compito -> compitiText.append("- ").append(compito).append("\n"));
        });
        compitiLabel.setText(compitiText.toString());

        // Visualizza i promemoria
        StringBuilder promemoriaText = new StringBuilder("Promemoria:\n");
        classe.getPromemoria().forEach((data, promemorie) -> {
            promemoriaText.append(data.toString()).append(":\n");
            promemorie.forEach(promemoria -> promemoriaText.append("- ").append(promemoria).append("\n"));
        });
        promemoriaLabel.setText(promemoriaText.toString());

        // Visualizza l'orario della classe
        // Nota: Assumo che l'orario della classe sia memorizzato in una struttura di dati adeguata, ad esempio una matrice
        // di stringhe o un'altra classe dedicata.
        // In questo esempio, supponiamo che l'orario sia memorizzato nella variabile orarioMaterie della classe Classe.
        StringBuilder orarioText = new StringBuilder("Orario:\n");
        for (int giorno = 0; giorno < 6; giorno++) {
            for (int ora = 0; ora < 10; ora++) {
                if (classe.getOrarioMaterie()[giorno][ora] != null) {
                    orarioText.append("Giorno ").append(giorno).append(", Ora ").append(ora).append(": ")
                            .append(classe.getOrarioMaterie()[giorno][ora]).append("\n");
                }
            }
        }
      //  orarioLabel.setText(orarioText.toString());
    }
}