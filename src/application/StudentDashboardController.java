package application;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import scuola.Classe;
import scuola.Studente;
import scuola.Voto;

public class StudentDashboardController {

    @FXML
    private ProgressBar fillBar;

    @FXML
    private TextArea compitiAssegnatiTextArea;

    @FXML
    private TextArea impegniTextArea;

    @FXML
    private TextArea bachecaTextArea;

    @FXML
    private Text mediaText;

    @FXML
    private LineChart<Number, Number> votiLineChart;

    @FXML
    private NumberAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    private Text studentNameText;

    @FXML
    private Text studentClassText;

    @FXML
    private Text studentIDText;

    private StudenteService studenteService;

    private void mostraProssimiImpegni() {
        Studente studente = studenteService.getStudente();
        if (studente != null) {
            LocalDate oggi = LocalDate.now();
            Map<LocalDate, List<String>> calendario = studente.getCalendario();
            impegniTextArea.clear();
            calendario.entrySet().stream()
                .filter(entry -> !entry.getKey().isBefore(oggi)) // Filtra solo gli impegni futuri
                .forEach(entry -> {
                    impegniTextArea.appendText("Data: " + entry.getKey() + "\n");
                    entry.getValue().forEach(impegno -> impegniTextArea.appendText("- " + impegno + "\n"));
                    impegniTextArea.appendText("\n");
                });
        }
    }

    public void initialize() {
        compitiAssegnatiTextArea.setEditable(false);
        bachecaTextArea.setEditable(false); // Add this line for the bulletin board TextArea
    }

    public void setStudenteService(StudenteService studenteService) {
        this.studenteService = studenteService;
        aggiornaRiempimentoBarra();
        mostraCompitiAssegnati();
        mostraProssimiImpegni();
        mostraBacheca();
        popolaLineChart();

        // Aggiungi questo codice per popolare le informazioni dello studente
        Studente studente = studenteService.getStudente();
        if (studente != null) {
            studentNameText.setText("\n" + studente.getNome() + " " + studente.getCognome());
            studentClassText.setText("\n" + studente.getClasse().toString());
            studentIDText.setText("\n" +String.valueOf(studente.getnMatricola()));
        }
    }

    public void aggiornaRiempimentoBarra() {
        Studente studente = studenteService.getStudente();
        
        if (studente != null && studente.getMediaGenerale() != null) {
            double media = studente.getMediaGenerale();
            
            fillBar.setProgress(media / 10.0);

            Color color;
            if (media < 6.0) {
                color = Color.RED;
            } else if (media < 8.0) {
                color = Color.ORANGE;
            } else {
                color = Color.GREEN;
            }
            fillBar.setStyle("-fx-accent: " + color.toString().replace("0x", "#"));
            mediaText.setText(String.format("%.2f", media));
        }
    }

    private void mostraCompitiAssegnati() {
        Studente studente = studenteService.getStudente();
        if (studente != null && studente.getClasse() != null) {
            Classe classe = studente.getClasse();
            Map<LocalDate, List<String>> compitiAssegnati = classe.getCompitiAssegnati();
            compitiAssegnati.forEach((data, compiti) -> {
                compitiAssegnatiTextArea.appendText("Data: " + data + "\n");
                compiti.forEach(compito -> compitiAssegnatiTextArea.appendText("- " + compito + "\n"));
                compitiAssegnatiTextArea.appendText("\n");
            });
        }
    }

    private void mostraBacheca() {
        Studente studente = studenteService.getStudente();
        if (studente != null && studente.getClasse() != null) {
            Classe classe = studente.getClasse();
            Map<LocalDate, List<String>> bacheca = classe.getBacheca();
            bacheca.forEach((data, note) -> {
                bachecaTextArea.appendText("Data: " + data + "\n");
                note.forEach(nota -> bachecaTextArea.appendText("- " + nota + "\n"));
                bachecaTextArea.appendText("\n");
            });
        }
    }

    private void popolaLineChart() {
        Studente studente = studenteService.getStudente();
        if (studente != null) {
            XYChart.Series<Number, Number> serieVoti = new XYChart.Series<>();
            XYChart.Series<Number, Number> serieMedia = new XYChart.Series<>();
            serieVoti.setName("Voti");
            serieMedia.setName("Media Generale");

            Map<String, List<Voto>> voti = studente.getVoti();
            if (voti.isEmpty()) {
                return;
            }

            LocalDate startDate = null;
            double mediaTotale = 0.0;
            int countVoti = 0;

            for (List<Voto> listaVoti : voti.values()) {
                for (Voto voto : listaVoti) {
                    LocalDate date = voto.getDataInserimento();
                    if (startDate == null || date.isBefore(startDate)) {
                        startDate = date;
                    }
                    long dateEpoch = date.atStartOfDay(ZoneId.systemDefault()).toEpochSecond();
                    long dateDifference = (dateEpoch - startDate.atStartOfDay(ZoneId.systemDefault()).toEpochSecond()) / (24 * 60 * 60);
                    serieVoti.getData().add(new XYChart.Data<>(dateDifference, voto.getVoto()));
                    mediaTotale += voto.getVoto();
                    countVoti++;

                    // Calcola la media per ogni voto aggiunto e aggiungila alla serie della media
                    if (countVoti > 0) {
                        double media = mediaTotale / countVoti;
                        serieMedia.getData().add(new XYChart.Data<>(dateDifference, media));
                    }
                }
            }

            votiLineChart.getData().addAll(serieVoti, serieMedia);

            // Nascondi i numeri sull'asse X
            votiLineChart.getXAxis().setTickLabelsVisible(false);
        }
    }

    @FXML
    private void handleButtonClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/calendarioAmm.fxml"));
            Parent root = loader.load();
            CalendarController controller = loader.getController();
      
        
            controller.setStudente(this.studenteService.getStudente());
            controller.drawCalendar();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleChartTitleClick(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/votiStudente.fxml"));
            Parent root = loader.load();
            VotiStudenteController controller = loader.getController();
            controller.setStudenteService(this.studenteService);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleCompitiAssegnatiClick(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/compitiAssegnati_dasboard.fxml"));
            Parent root = loader.load();
            CompitiAssegnatiController controller = loader.getController();
            controller.setClasse(this.studenteService.getStudente().getClasse());
            Scene scene = new Scene(root, 400, 700);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleBachecaClick(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/bacheca_dashboard.fxml"));
            Parent root = loader.load();
            BachecaController controller = loader.getController();
            controller.setClasse(this.studenteService.getStudente().getClasse());
            Scene scene = new Scene(root, 400, 700);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
