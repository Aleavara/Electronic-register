package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import scuola.Studente;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Controller per la gestione del calendario.
 * Autore: alessioavarappattu
 */
public class CalendarController implements Initializable {

    private ZonedDateTime dateFocus;
    private ZonedDateTime today;

    @FXML
    private Text year;

    @FXML
    private Text month;

    @FXML
    private FlowPane calendar;

    private Studente studente; 

    /**
     * Imposta lo studente per il quale visualizzare il calendario.
     *
     * @param studente Lo studente di cui visualizzare il calendario.
     */
    public void setStudente(Studente studente) {
        this.studente = studente;
    }

    /**
     * Inizializza il controller. Imposta la data di oggi e la data di focus.
     *
     * @param url L'URL utilizzato per risolvere il percorso del file FXML.
     * @param resourceBundle Il pacchetto di risorse utilizzato per localizzare l'applicazione.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dateFocus = ZonedDateTime.now();
        today = ZonedDateTime.now();
        // drawCalendar();
    }

    /**
     * Gestisce l'azione per andare indietro di un mese nel calendario.
     *
     * @param event L'evento di azione.
     */
    @FXML
    void backOneMonth(ActionEvent event) {
        dateFocus = dateFocus.minusMonths(1);
        calendar.getChildren().clear();
        drawCalendar();
    }

    /**
     * Gestisce l'azione per andare avanti di un mese nel calendario.
     *
     * @param event L'evento di azione.
     */
    @FXML
    void forwardOneMonth(ActionEvent event) {
        dateFocus = dateFocus.plusMonths(1);
        calendar.getChildren().clear();
        drawCalendar();
    }

    /**
     * Metodo per impostare lo studente e aggiornare il calendario.
     *
     * @param studente Lo studente di cui visualizzare il calendario.
     */
    void faccioIo(Studente studente) {
        this.studente = studente;
    }

    /**
     * Disegna il calendario per il mese corrente.
     */
    public void drawCalendar() {
        year.setText(String.valueOf(dateFocus.getYear()));
        month.setText(String.valueOf(dateFocus.getMonth()));

        double calendarWidth = calendar.getPrefWidth();
        double calendarHeight = calendar.getPrefHeight();
        double strokeWidth = 1;
        double spacingH = calendar.getHgap();
        double spacingV = calendar.getVgap();

        Map<LocalDate, List<String>> impegniStudente = studente.getCalendario();

        int monthMaxDate = dateFocus.getMonth().maxLength();
        int dateOffset = ZonedDateTime.of(dateFocus.getYear(), dateFocus.getMonthValue(), 1, 0, 0, 0, 0, dateFocus.getZone()).getDayOfWeek().getValue();

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                StackPane stackPane = new StackPane();

                Rectangle rectangle = new Rectangle();
                rectangle.setFill(null);
                rectangle.setStroke(Color.BLACK);
                rectangle.setStrokeWidth(strokeWidth);
                double rectangleWidth = (calendarWidth / 7) - strokeWidth - spacingH;
                rectangle.setWidth(rectangleWidth);
                double rectangleHeight = (calendarHeight / 6) - strokeWidth - spacingV;
                rectangle.setHeight(rectangleHeight);

                rectangle.setArcWidth(15.0);
                rectangle.setArcHeight(15.0);

                stackPane.getChildren().add(rectangle);

                int calculatedDate = (j + 1) + (7 * i);
                if (calculatedDate > dateOffset) {
                    int currentDate = calculatedDate - dateOffset;
                    if (currentDate <= monthMaxDate) {
                        Text date = new Text(String.valueOf(currentDate));
                        double textTranslationY = - (rectangleHeight / 2) * 0.75;
                        date.setTranslateY(textTranslationY);
                        stackPane.getChildren().add(date);

                        List<String> impegni = impegniStudente.get(LocalDate.of(dateFocus.getYear(), dateFocus.getMonth(), currentDate));
                        if (impegni != null && !impegni.isEmpty()) {
                        	for (String impegno : impegni) {
                        	    Text impegnoText = new Text(impegno);
                        	    double maxWidth = rectangleWidth - 10; // Larghezza massima del testo
                        	    impegnoText.setWrappingWidth(maxWidth);
                        	    impegnoText.setFont(Font.font("Arial", FontWeight.BOLD, 12)); // Regolazione del font e della dimensione del testo
                        	    
                        	    // Imposta il tooltip per visualizzare il testo completo quando si passa sopra con il mouse
                        	    Tooltip tooltip = new Tooltip(impegno);
                        	    Tooltip.install(impegnoText, tooltip);
                        	    
                        	    stackPane.getChildren().add(impegnoText);
                        	}
                        }
                    }
                    if (today.getYear() == dateFocus.getYear() && today.getMonth() == dateFocus.getMonth() && today.getDayOfMonth() == currentDate) {
                        rectangle.setStroke(Color.BLUE);
                    }
                }
                calendar.getChildren().add(stackPane);
            }
        }
    }


    /**
     * Gestisce l'azione per aggiungere impegni al calendario.
     *
     * @param event L'evento di azione.
     */
    @FXML
    void aggiungiImpegni(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/sample.fxml"));
            Parent root = loader.load();
          
            SampleController controller = loader.getController();
      
        
            controller.setStudenteService(studente);
            
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            
      
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
