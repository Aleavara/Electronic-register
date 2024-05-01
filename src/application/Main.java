package application;

import java.io.IOException;
import java.time.LocalDate;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import scuola.Classe;
import scuola.GestoreCredenziali;
import scuola.Professore;
import scuola.Segreteria;
import scuola.Studente;

public class Main extends Application {
    
    private Stage primaryStage;
    private Scene loginScene, studentScene, professorScene, secretaryScene;
    private Segreteria segreteria;

    @Override
    public void start(Stage stage) throws Exception {
       primaryStage = stage;


      

       
        createLoginScene();

   
        primaryStage.setScene(loginScene);
        primaryStage.setTitle("Login");
        primaryStage.show();
    }
    private void showProfessorDashboard(Professore professore) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/professor_dashboard.fxml"));
            Parent root = loader.load();

            ProfessorDashboardController controller = loader.getController();
           // controller.setProfessore(professore);

            Scene professorScene = new Scene(root);
            primaryStage.setScene(professorScene);
            primaryStage.setTitle("Dashboard Professore");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void showSegreteriaDashboard(Segreteria segreteria) {
        try {
        	
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/segreteria1_dashboard.fxml"));
           
            Parent root = loader.load();
        	
            SegreteriaDashboardController controller = loader.getController();
        
           controller.setSegreteria(segreteria);
           controller.faccioIo();
    
            Scene SegreteriaScene = new Scene(root);
            primaryStage.setScene(SegreteriaScene);
            primaryStage.setTitle("Dashboard Segrete");
            primaryStage.show();
         
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void showStudentDashboard(Studente studente) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Studen.fxml"));
            Parent root = loader.load();

            StudentDashboardController controller = loader.getController();
            StudenteService student = new StudenteService(studente);
          controller.setStudenteService(student);

            Scene studentScene = new Scene(root);
            primaryStage.setScene(studentScene);
            primaryStage.setTitle("Dashboard Studente");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createLoginScene() {
        GridPane loginGrid = new GridPane();
        loginGrid.setHgap(10);
        loginGrid.setVgap(10);

        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();

        Label usernameLabel = new Label("Username:");
        Label passwordLabel = new Label("Password:");

        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            // Effettua l'autenticazione
            if (authenticate(username, password)) {
               
                showSceneForUser(username);
            } else {
                showAlert(Alert.AlertType.ERROR, "Errore di autenticazione", "Credenziali non valide.");
            }
        });

        loginGrid.add(usernameLabel, 0, 0);
        loginGrid.add(usernameField, 1, 0);
        loginGrid.add(passwordLabel, 0, 1);
        loginGrid.add(passwordField, 1, 1);
        loginGrid.add(loginButton, 1, 2);

        loginScene = new Scene(loginGrid, 300, 200);
    }

    private boolean authenticate(String username, String password) {

        // Per ora restituisce true se l'username è non vuoto e la password è "password"
        return !username.isEmpty() && password.equals("password");
    }

    private void showSceneForUser(String username) {
        if (username.startsWith("S")) { // Studente
        	Classe classec=new Classe("5EF");
        	Studente studente = new Studente("Nome", "Cognome", "Indirizzo", "CodiceFiscale", LocalDate.now(), classec);
        	
        	classec.aggiungiCompito(LocalDate.now(), "dormi");
        	LocalDate data=  LocalDate.of(2024,05,07);
        	classec.aggiungiCompito(data, "uagliù");
        	studente.setMediaGenerale(7.5);
          
           showStudentDashboard(studente);
        } else if (username.startsWith("P")) { // Professore
        	Professore professore = new Professore("Nome", "Cognome", "Indirizzo", "CodiceFiscale", LocalDate.now(), new Classe("Classe"));
            showProfessorDashboard(professore);
        } else if (username.startsWith("xdvr")) { // Segreteria
        
            Segreteria seg= new Segreteria();
            Classe classeg = new Classe("4c");
           
           showSegreteriaDashboard(seg);
        } else {
            showAlert(Alert.AlertType.ERROR, "Errore", "Tipo di utente non riconosciuto.");
        }
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}