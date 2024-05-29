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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import scuola.Classe;
import scuola.Credenziali;
import scuola.GestoreCredenziali;
import scuola.Professore;
import scuola.Segreteria;
import scuola.Studente;
import scuola.Voto;

public class Main extends Application {
	private final long serialVersionUID = 1;
	private Stage primaryStage;
	private Scene loginScene, studentScene, professorScene, secretaryScene;
	Segreteria segreteria;
	List<Professore> listaProfessori = new ArrayList<>();
	List<Studente> listaStudenti = new ArrayList<>();
	List<Credenziali> credenziali = new ArrayList<>();
	GestoreCredenziali gestoreCredenziali = new GestoreCredenziali();
	List<Classe> classi = new ArrayList<>();
	List<Voto> voti = new ArrayList<>();

	@Override
	public void start(Stage stage) throws Exception {

		List<Object> data =loadData("data.dat");
				
			//	
		//data.clear();

		// new ArrayList<Object>();

		for (Object obj : data) {
			switch (obj.getClass().getSimpleName()) {

			case "Segreteria":
			//	System.out.println("sono la segreteria");
				segreteria = ((Segreteria) obj);
				for (Classe c : segreteria.getListaClassi()) {
					if (!classi.contains(c))
						classi.add(c);
					for (Studente s : c.getStudenti()) {
						if (!listaStudenti.contains(s)) {
							listaStudenti.add(s);
							gestoreCredenziali.aggiungiStudente(s);
							gestoreCredenziali.setCredenzialiStudent(s, segreteria.getCredenzialiStud(s));
							System.out.println("studente " + s.getNome() + " " + segreteria.getCredenzialiStud(s));
						}

					}
					for (Professore p : c.getProfessori()) {
						if (!listaProfessori.contains(p)) {
							listaProfessori.add(p);
							gestoreCredenziali.aggiungiProfessore(p);
							gestoreCredenziali.setCredenzialProf(p, segreteria.getCredenzialiProf(p));
							System.out.println("Professore:" +segreteria.getCredenzialiProf(p));
						}
					}
				}

				break;
			default:

				break;
			}
		}
		
		/* Classe classea = new Classe("4CIF"); Classe classeb= new Classe("5BIF");
		  List<Classe> listaClassi = new ArrayList<>(); listaClassi.add(classeb);
		  listaClassi.add(classea); Studente studente = new Studente("marco",
		  "pollini", "Indirizzo", "CodiceFiscale", LocalDate.now(), classea);
		  Professore professore = new Professore("Alessandro", "Baroni", "I",
		  "Abroa1949j", LocalDate.now(),"Storia",listaClassi ); Segreteria seg = new
		  Segreteria("se","se"); seg.aggiungiProf(professore, "alessandro.baroni",
		  "alessandro"); seg.aggiungiStudente(studente, "marco.pollini", "marco");
		  seg.aggiungiClasse(classeb); seg.aggiungiClasse(classea);
		  
		  data.add(seg);*/
		
	System.out.println("credenziali segreteria: "+ segreteria.getCredenziali());


	primaryStage = stage;

		createLoginScene();

		primaryStage.setScene(loginScene);
		primaryStage.setTitle("Login");
		primaryStage.show();

	    
		primaryStage.setOnCloseRequest(event -> saveData(data, "data.dat"));
	}

	private void showProfessorDashboard(Professore professore) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/professor_dashboard.fxml"));
			Parent root = loader.load();
			ProfessorDashboardController controller = loader.getController();
			controller.setProfessore(professore);
			controller.updateView();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
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

	        SegreteriaDashboardControllerIniziale controller = loader.getController();
	        controller.setSegreteria(segreteria);
	        controller.updateLabels();

	        Scene segreteriaScene = new Scene(root, 800, 400); // Set scene size to 600x600
	        primaryStage.setScene(segreteriaScene);
	        primaryStage.setResizable(true);
	        primaryStage.setTitle("Dashboard Segreteria");
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
	    BorderPane borderPane = new BorderPane();
	    borderPane.setStyle(" -fx-background-size: cover;");

	    TextField usernameField = new TextField();
	    usernameField.setFont(Font.font("Sans Serif", FontWeight.BOLD, 50));
	    usernameField.setPromptText("Username");
	    usernameField.setStyle("-fx-background-radius: 10px; -fx-padding: 10px; -fx-border-color: white; -fx-border-width: 1px;");

	    PasswordField passwordField = new PasswordField();
	    passwordField.setStyle("-fx-background-radius: 10px; -fx-padding: 10px; -fx-border-color: white; -fx-border-width: 1px;");
	    passwordField.setFont(Font.font("Sans Serif", FontWeight.BOLD, 14));
	    passwordField.setPromptText("Password");

	    TextField passwordTextField = new TextField();
	    passwordTextField.setManaged(false);
	    passwordTextField.setVisible(false);
	    passwordTextField.setStyle("-fx-background-radius: 10px; -fx-padding: 10px; -fx-border-color: white; -fx-border-width: 1px;");

	    CheckBox showPassword = new CheckBox("Show Password");
	    showPassword.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");
	    borderPane.setStyle("-fx-border-style: none;");

	    passwordField.textProperty().bindBidirectional(passwordTextField.textProperty());

	    showPassword.setOnAction(e -> {
	        if (showPassword.isSelected()) {
	            passwordTextField.setManaged(true);
	            passwordTextField.setVisible(true);
	            passwordField.setManaged(false);
	            passwordField.setVisible(false);
	        } else {
	            passwordField.setManaged(true);
	            passwordField.setVisible(true);
	            passwordTextField.setManaged(false);
	            passwordTextField.setVisible(false);
	        }
	    });

	    Button loginButton = new Button("Accedi ");
	    loginButton.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-font-weight: bold; -fx-background-radius: 10px;");
	    loginButton.setOnAction(e -> {
	        String username = usernameField.getText();
	        String password = passwordField.getText();

	        if (authenticate(username, password)) {
	            if (username.startsWith("s")) {
	                showStudentDashboard(segreteria.validaCredenzialiStudent(username, password));
	            } else if (username.startsWith("p")) {
	                showProfessorDashboard(segreteria.validaCredenzialProf(username, password));
	            } else if (username.startsWith("xd")) {
	                showSegreteriaDashboard(segreteria);
	            }
	        } else {
	            showAlert(Alert.AlertType.ERROR, "Authentication Error", "Invalid credentials.");
	        }
	    });

	    GridPane loginGrid = new GridPane();
	    loginGrid.setHgap(10);
	    loginGrid.setVgap(10);
	    loginGrid.setPadding(new Insets(20));
	    loginGrid.setAlignment(Pos.CENTER);

	    Label loginLabel = new Label("Log-in");
	    loginLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 18px; -fx-text-fill: white;");
	    loginGrid.add(loginLabel, 0, 0, 2, 1); 
	    GridPane.setHalignment(loginLabel, HPos.CENTER); 

	    loginGrid.add(usernameField, 0, 1, 2, 1);
	    loginGrid.add(passwordField, 0, 2, 2, 1);
	    loginGrid.add(passwordTextField, 0, 2, 2, 1);
	    loginGrid.add(showPassword, 0, 3, 2, 1);
	    loginGrid.add(loginButton, 0, 4, 2, 1);
	    loginGrid.setHalignment(loginButton, HPos.CENTER);

	    VBox topBox = new VBox(10);

	    Label titleLabel = new Label("Registro Scolastico");
	    titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 24px; -fx-text-fill: white;");
	    topBox.getChildren().add(titleLabel);

	    topBox.setAlignment(Pos.CENTER);
	    topBox.setPadding(new Insets(10, 20, 20, 20)); // Imposta l'Insets con meno spazio sopra e più spazio sotto

	    borderPane.setTop(topBox);
	    borderPane.setCenter(loginGrid);

	    loginScene = new Scene(borderPane, 400, 300);
	    primaryStage.setResizable(false);
	    loginScene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

	    loginScene.setOnKeyPressed(e -> {
	        if (e.getCode() == KeyCode.ENTER) {
	            String username = usernameField.getText();
	            String password = passwordField.getText();

	            if (authenticate(username, password)) {
	                if (username.startsWith("s")) {
	                    showStudentDashboard(segreteria.validaCredenzialiStudent(username, password));
	                } else if (username.startsWith("p")) {
	                    showProfessorDashboard(segreteria.validaCredenzialProf(username, password));
	                } else if (username.startsWith("xd")) {
	                    showSegreteriaDashboard(segreteria);
	                }
	            } else {
	                showAlert(Alert.AlertType.ERROR, "Authentication Error", "Invalid credentials.");
	            }
	        }
	    });
	}





	private boolean authenticate(String username, String password) {

		if (segreteria.validaCredenzialiStudent(username, password) != null)
			return true;

		if (segreteria.validaCredenzialProf(username, password) != null)
			return true;

		if (segreteria.validaCredenzialiSegreteria(username, password) != false)
			return true;

		
		return false;
	}

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