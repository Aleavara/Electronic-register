package application;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import scuola.Classe;
import scuola.Credenziali;
import scuola.GestoreCredenziali;
import scuola.Professore;
import scuola.Segreteria;
import scuola.Studente;
import scuola.Voto;

public class Main extends Application {
    
    private Stage primaryStage;
    private Scene loginScene, studentScene, professorScene, secretaryScene;
	Segreteria segreteria = new Segreteria();
    List<Professore> listaProfessori = new ArrayList<>();
    List<Studente> listaStudenti = new ArrayList<>();
    List<Credenziali> credenziali=new ArrayList<>();
    GestoreCredenziali gestoreCredenziali = new GestoreCredenziali();
    List<Classe> classi = new ArrayList<>();
    List<Voto> voti=new ArrayList<>();

    @Override
    public void start(Stage stage) throws Exception {
        

       
    	List<Object> data = loadData("data.dat");
        
    	
    	//for(Object dato : data)
    	//	System.out.println(dato);
    	
     for (Object obj : data) {
            switch (obj.getClass().getSimpleName()) {
                case "Professore":
                    listaProfessori.add((Professore) obj);
                    break;
                case "Studente":
                    listaStudenti.add((Studente) obj);
                   
                    break;
                case "Credenziali":
                    credenziali.add((Credenziali) obj);
                    break;
                case "GestoreCredenziali":
                    gestoreCredenziali = (GestoreCredenziali) obj;
                   
                    System.out.println("ciao "+ (GestoreCredenziali)obj);
                    break;
                case "Classe":
                    classi.add((Classe) obj);
                    break;
                case "Voto":
                    voti.add((Voto) obj);
                    break;         
                default:
                    
                    break;
            }
        }
     
  /*   List<Classe> listaClassi = new ArrayList<>();
     Classe classe1 = new Classe("1A" );
     Classe classe2 = new Classe("2B");
     Classe classe3 = new Classe("3C");
     listaClassi.add(classe1);
     listaClassi.add(classe2);
     listaClassi.add(classe3);
 	Professore professore = new Professore("Alessandro", "Baroni", "I", "Abroa1949j", LocalDate.now(),"Storia",listaClassi );
 	data.add(professore);
 	gestoreCredenziali.setCredenzialProf(professore, "dsd", "dsd");
 	data.add(gestoreCredenziali);*/
     
  /*	Classe classec=new Classe("5EF");

	Studente studente = new Studente("Nome", "Cognome", "Indirizzo", "CodiceFiscale", LocalDate.now(), classec);
    gestoreCredenziali.setCredenzialiStudent(studente, "aa", "aa");
    
    data.add(studente);
    data.add(gestoreCredenziali);*/
     
    /*    List<Object> studenti=new ArrayList<>();
       
    	Classe classec=new Classe("5EF");

    	Studente studente = new Studente("Nome", "Cognome", "Indirizzo", "CodiceFiscale", LocalDate.now(), classec);
    	studenti.add(studente);
    	studenti.add(classec);
    	
    	GestoreCredenziali.setCredenzialiStudente(studente, "giulio","giulio");
    	//System.out.println(gestoreCredenziali);
    	data.add(gestoreCredenziali);
    	data.add(studente);*/
    	
     /*   Classe classe1 = new Classe("1A" );
        Classe classe2 = new Classe("2B");
        Classe classe3 = new Classe("3C");

        // Aggiunta delle classi all'ArrayList
        List<Classe> listaClassi = new ArrayList<>();
        listaClassi.add(classe1);
        listaClassi.add(classe2);
        listaClassi.add(classe3);
    	Professore professore = new Professore("Alessandro", "Baroni", "I", "Abroa1949j", LocalDate.now(),"Storia",listaClassi );
    	GestoreCredenziali.setCredenzialiProfessore(professore, "giulio","giulio");
    	data.add(professore);
        Segreteria seg= new Segreteria();
        Classe classeg = new Classe("4c");
    	Studente student = new Studente("dgt", "Cognome", "Indirizzo", "CodiceFiscale", LocalDate.now(), classec);
    	GestoreCredenziali.setCredenzialiStudente(student, "dgb", "dgb");
    	data.add(student);
    	studenti.add(student);
        GestoreCredenziali.setCredenzialiSegreteria(seg, "giulio","giulio");
    	studenti.add(classeg);
    	studenti.add(listaClassi);
    ;*/

 
       
        primaryStage = stage;
       
        createLoginScene();

   
        primaryStage.setScene(loginScene);
        primaryStage.setTitle("Login");
        primaryStage.show();
        stage.setOnCloseRequest(event -> saveData(data, "data.dat"));
    }
    private void showProfessorDashboard(Professore professore) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/professor_dashboard.fxml"));
            Parent root = loader.load();

            ProfessorDashboardController controller = loader.getController();
       controller.setProfessore(professore);

            Scene professorScene = new Scene(root,400,300);
            primaryStage.setScene(professorScene);
            primaryStage.setTitle("Dashboard Professore");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void showSegreteriaDashboard(Segreteria segreteria) {
        try {
        	
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/segreteria_dashboardIniziale.fxml"));
           
            Parent root = loader.load();
        	
            //SegreteriaDashboardController controller = loader.getController();
        
            SegreteriaDashboardControllerIniziale controller = loader.getController();
        //   controller.setSegreteria(segreteria);
        //   controller.faccioIo();
    
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
    private void showClasseDashboard(Classe classe) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/class_dashboard.fxml"));
            Parent root = loader.load();

            ClasseViewController controller = loader.getController();
            controller.setClasse(classe); // Imposta la classe nel controller
          
            controller.updateLabels(); // Aggiorna le etichette con i dati della classe

            Scene classeScene = new Scene(root);
            primaryStage.setScene(classeScene);
            primaryStage.setTitle("Dashboard Classe");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }	
    }

    private void createLoginScene() {
        GridPane loginGrid = new GridPane();
        loginGrid.setHgap(10);
        loginGrid.setVgap(10);
        loginGrid.setStyle("-fx-background-color: linear-gradient(#e0cc55, #e0b055); -fx-padding: 20px;");
        
        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();

        Label usernameLabel = new Label("Username:");
        usernameLabel.setStyle("-fx-font-weight: bold;");

        Label passwordLabel = new Label("Password:");
        passwordLabel.setStyle("-fx-font-weight: bold;");
        Font comicSansFont = Font.font("Comic Sans MS", 12);
        usernameField.setFont(comicSansFont);
        passwordField.setFont(comicSansFont);
        usernameLabel.setFont(comicSansFont); // Correzione qui
        passwordLabel.setFont(comicSansFont); // Correzione qui

        Button loginButton = new Button("Login");
        loginButton.setStyle("-fx-background-color: #c28823; -fx-text-fill: white; -fx-font-weight: bold;");
        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            // Effettua l'autenticazione
            if (authenticate(username, password)) {
            	if(username.startsWith("s"))
            	showStudentDashboard(gestoreCredenziali.validaCredenzialiStudent(username,password));
            	if(username.startsWith("p"))
            		showProfessorDashboard(GestoreCredenziali.validaCredenzialiProfessore(username,password));
            	if(username.startsWith("xd"))
            		showSegreteriaDashboard(GestoreCredenziali.validaCredenzialiSegreteria(username,password));
            } else {
                showAlert(Alert.AlertType.ERROR, "Errore di autenticazione", "Credenziali non valide.");
            }
        });

        // Applica lo stile per arrotondare i bordi direttamente nel codice
        usernameField.setStyle("-fx-background-radius: 10px;");
        passwordField.setStyle("-fx-background-radius: 10px;");

        loginGrid.add(usernameLabel, 0, 0);
        loginGrid.add(usernameField, 1, 0);
        loginGrid.add(passwordLabel, 0, 1);
        loginGrid.add(passwordField, 1, 1);
        loginGrid.add(loginButton, 1, 2);
        
        // Centra il pulsante nel GridPane
        loginGrid.setHalignment(loginButton, HPos.CENTER);

        loginScene = new Scene(loginGrid, 300, 150);
        primaryStage.setResizable(false);
    }



    private boolean authenticate(String username, String password) {
      
       if(gestoreCredenziali.validaCredenzialiStudent(username,password)!=null)
    	   return true;

       if(GestoreCredenziali.validaCredenzialiProfessore(username,password)!=null)
    	   return true;

       if(GestoreCredenziali.validaCredenzialiSegreteria(username,password)!=null)
    	   return true;
        

        // Se nessuno ha le credenziali corrette, ritorna false
        return false;}
    
    
    

  /*  private void showSceneForUser(String username) {
        if (username.startsWith("S")) { // Studente
        	Classe classec=new Classe("5EF");
        	Studente studente = new Studente("Nome", "Cognome", "Indirizzo", "CodiceFiscale", LocalDate.now(), classec);
        	
        	classec.aggiungiCompito(LocalDate.now(), "dormi");
        	LocalDate data=  LocalDate.of(2024,05,07);
        	classec.aggiungiCompito(data, "uagliù");
        	studente.setMediaGenerale(7.5);
          
           showStudentDashboard(studente);
        } else if (username.startsWith("P")) { // Professore
            Classe classe1 = new Classe("1A" );
            Classe classe2 = new Classe("2B");
            Classe classe3 = new Classe("3C");

            // Aggiunta delle classi all'ArrayList
            List<Classe> listaClassi = new ArrayList<>();
            listaClassi.add(classe1);
            listaClassi.add(classe2);
            listaClassi.add(classe3);
        	Professore professore = new Professore("Alessandro", "Baroni", "I", "Abroa1949j", LocalDate.now(),"Storia",listaClassi );
            showProfessorDashboard(professore);
        } else if (username.startsWith("xdvr")) { // Segreteria
        
            Segreteria seg= new Segreteria();
            Classe classeg = new Classe("4c");
           
           showSegreteriaDashboard(seg);
        }
        else if(username.startsWith("ir")){
        	Classe classe = new Classe("4AIF");
        	LocalDate dta=  LocalDate.of(2024,05,07);
        	classe.aggiungiCompito(dta, "studiare mate");
        	classe.aggiungiInBacheca(dta, "soijdbg");
        	classe.aggiungiInBacheca(dta, "dhft");
        	
        	Classe classe1 = new Classe("1A" );
            Classe classe2 = new Classe("2B");
            Classe classe3 = new Classe("3C");

            // Aggiunta delle classi all'ArrayList
            List<Classe> listaClassi = new ArrayList<>();
            listaClassi.add(classe1);
            listaClassi.add(classe2);
            listaClassi.add(classe3);
        	Professore professore = new Professore("Nome", "Cognome", "Indirizzo", "CodiceFiscale", LocalDate.now(),"Storia",listaClassi );
            classe.aggiungiProfessore(professore);
            classe.aggiungiPromemoria(dta, "jrevod");

            // Imposta il professore alla prima ora del martedì
            classe.impostaOraProfessore(1, 0, professore);
            showClasseDashboard(classe);
        }
        		
        		else {
            showAlert(Alert.AlertType.ERROR, "Errore", "Tipo di utente non riconosciuto.");
        }
    }*/

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    
    private void saveData(List<Object> data, String filename) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filename))) {
            outputStream.writeObject(data);
            System.out.println("Dati salvati con successo.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Object> loadData(String filename) {
        List<Object> data = null;
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename))) {
            data = (List<Object>) inputStream.readObject();
            System.out.println("Dati caricati con successo.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void main(String[] args) {
        launch(args);
    }
}