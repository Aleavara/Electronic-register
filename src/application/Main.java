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

		List<Object> data = loadData("data.dat");
		// data.clear();

		// new ArrayList<Object>();

		// for(Object dato : data)
		// System.out.println(dato);

		for (Object obj : data) {
			switch (obj.getClass().getSimpleName()) {

			case "Segreteria":
				System.out.println("sono la segreteria");
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
							System.out.println(segreteria.getCredenzialiProf(p));
						}
					}
				}

				break;
			default:

				break;
			}
		}

		/*
		 * Classe classea = new Classe("4CIF"); Classe classeb= new Classe("5BIF");
		 * List<Classe> listaClassi = new ArrayList<>(); listaClassi.add(classeb);
		 * listaClassi.add(classea); Studente studente = new Studente("marco",
		 * "pollini", "Indirizzo", "CodiceFiscale", LocalDate.now(), classea);
		 * Professore professore = new Professore("Alessandro", "Baroni", "I",
		 * "Abroa1949j", LocalDate.now(),"Storia",listaClassi ); Segreteria seg = new
		 * Segreteria("se","se"); seg.aggiungiProf(professore, "alessandro.baroni",
		 * "alessandro"); seg.aggiungiStudente(studente, "marco.pollini", "marco");
		 * seg.aggiungiClasse(classeb); seg.aggiungiClasse(classea);
		 * 
		 * data.add(seg);
		 */

		/*
		 * Segreteria seg = new Segreteria(); Classe classe1 = new Classe("1A" ); Classe
		 * classe2 = new Classe("2B"); Classe classe3 = new Classe("3C");
		 * seg.aggiungiClasse(classe3);
		 * gestoreCredenziali.setCredenzialiSegreteria("se", "se"); data.add(seg);
		 * data.add(gestoreCredenziali); List<Classe> listaClassi = new ArrayList<>();
		 * Classe classe4 = new Classe("1A" ); Classe classe5 = new Classe("2B"); Classe
		 * classe6 = new Classe("3C"); listaClassi.add(classe4);
		 * listaClassi.add(classe5); listaClassi.add(classe6); Professore professore =
		 * new Professore("Alessandro", "Baroni", "I", "Abroa1949j",
		 * LocalDate.now(),"Storia",listaClassi ); data.add(professore);
		 * gestoreCredenziali.setCredenzialProf(professore, "dsd", "dsd");
		 * data.add(gestoreCredenziali);
		 */
		/*
		 * Classe classec=new Classe("5EF");
		 * 
		 * Studente studente = new Studente("Nome", "Cognome", "Indirizzo",
		 * "CodiceFiscale", LocalDate.now(), classec);
		 * gestoreCredenziali.setCredenzialiStudent(studente, "aa", "aa");
		 * 
		 * data.add(studente); data.add(gestoreCredenziali);
		 */

		/*
		 * List<Object> studenti=new ArrayList<>();
		 * 
		 * Classe classec=new Classe("5EF");
		 * 
		 * Studente studente = new Studente("Nome", "Cognome", "Indirizzo",
		 * "CodiceFiscale", LocalDate.now(), classec); studenti.add(studente);
		 * studenti.add(classec);
		 * 
		 * GestoreCredenziali.setCredenzialiStudente(studente, "giulio","giulio");
		 * //System.out.println(gestoreCredenziali); data.add(gestoreCredenziali);
		 * data.add(studente);
		 */

		/*
		 * Classe classe1 = new Classe("1A" ); Classe classe2 = new Classe("2B"); Classe
		 * classe3 = new Classe("3C");
		 * 
		 * // Aggiunta delle classi all'ArrayList List<Classe> listaClassi = new
		 * ArrayList<>(); listaClassi.add(classe1); listaClassi.add(classe2);
		 * listaClassi.add(classe3); Professore professore = new
		 * Professore("Alessandro", "Baroni", "I", "Abroa1949j",
		 * LocalDate.now(),"Storia",listaClassi );
		 * GestoreCredenziali.setCredenzialiProfessore(professore, "giulio","giulio");
		 * data.add(professore); Segreteria seg= new Segreteria(); Classe classeg = new
		 * Classe("4c"); Studente student = new Studente("dgt", "Cognome", "Indirizzo",
		 * "CodiceFiscale", LocalDate.now(), classec);
		 * GestoreCredenziali.setCredenzialiStudente(student, "dgb", "dgb");
		 * data.add(student); studenti.add(student);
		 * GestoreCredenziali.setCredenzialiSegreteria(seg, "giulio","giulio");
		 * studenti.add(classeg); studenti.add(listaClassi); ;
		 */

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

			Scene SegreteriaScene = new Scene(root);
			primaryStage.setScene(SegreteriaScene);
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
		borderPane.setStyle("-fx-background-color: #000000");

		TextField usernameField = new TextField();
		PasswordField passwordField = new PasswordField();
		TextField passwordTextField = new TextField();
		passwordTextField.setManaged(false);
		passwordTextField.setVisible(false);

		CheckBox showPassword = new CheckBox("mostra");
		showPassword.setStyle("-fx-text-fill: white;");

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

		Label usernameLabel = new Label("Username:");
		usernameLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: white; -fx-font-size: 14px;");

		Label passwordLabel = new Label("Password:");
		passwordLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: white; -fx-font-size: 14px;");

		Button loginButton = new Button("Login");
		loginButton.setStyle("-fx-background-color: #ffffff; -fx-text-fill: black; -fx-font-weight: bold;");
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
				showAlert(Alert.AlertType.ERROR, "Errore di autenticazione", "Credenziali non valide.");
			}
		});

		usernameField.setStyle("-fx-background-radius: 10px;");
		passwordField.setStyle("-fx-background-radius: 10px;");
		passwordTextField.setStyle("-fx-background-radius: 10px;");

		GridPane loginGrid = new GridPane();
		loginGrid.setHgap(10);
		loginGrid.setVgap(10);
		loginGrid.setAlignment(Pos.CENTER);

		loginGrid.add(usernameLabel, 0, 0);
		loginGrid.add(usernameField, 1, 0);
		loginGrid.add(passwordLabel, 0, 1);
		loginGrid.add(passwordField, 1, 1);
		loginGrid.add(passwordTextField, 1, 1);

		loginGrid.add(showPassword, 1, 2); // Aggiunge la casella di controllo nella colonna 1, riga 2

		ColumnConstraints colConstraints = new ColumnConstraints();
		colConstraints.setPercentWidth(40); // Imposta la larghezza della colonna al 50% della larghezza totale
		loginGrid.getColumnConstraints().addAll(new ColumnConstraints(), colConstraints); // Aggiungi la colonna
																							// constraints

		// Aggiungi un'ulteriore riga per centrare il pulsante di login
		loginGrid.add(new Label(""), 0, 3); // Riempi una colonna vuota
		loginGrid.add(loginButton, 1, 3); // Aggiungi il pulsante nella colonna 1, riga 4
		loginGrid.setColumnSpan(loginButton, 2); // Occupa due colonne per centrarlo
		loginGrid.setHalignment(loginButton, HPos.CENTER); // Centra il pulsante orizzontalmente

		VBox topBox = new VBox(); // Aggiunge meno spazio tra il titolo "Login" e i campi di input
		topBox.setStyle("-fx-background-color: white;"); // Aggiunge meno spazio tra il titolo "Login" e i campi di
															// input
		Label loginLabel = new Label("Log-In   ");
		loginLabel.setStyle(
				"-fx-font-weight: bold; -fx-font-size: 18px; -fx-text-fill: black; -fx-background-color: white;");
		topBox.getChildren().add(loginLabel);
		topBox.setAlignment(Pos.CENTER);

		borderPane.setTop(topBox);
		borderPane.setCenter(loginGrid);

		loginScene = new Scene(borderPane, 300, 200);
		primaryStage.setResizable(false);

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
					showAlert(Alert.AlertType.ERROR, "Errore di autenticazione", "Credenziali non valide.");
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

		// Se nessuno ha le credenziali corrette, ritorna false
		return false;
	}

	/*
	 * private void showSceneForUser(String username) { if
	 * (username.startsWith("S")) { // Studente Classe classec=new Classe("5EF");
	 * Studente studente = new Studente("Nome", "Cognome", "Indirizzo",
	 * "CodiceFiscale", LocalDate.now(), classec);
	 * 
	 * classec.aggiungiCompito(LocalDate.now(), "dormi"); LocalDate data=
	 * LocalDate.of(2024,05,07); classec.aggiungiCompito(data, "uagliù");
	 * studente.setMediaGenerale(7.5);
	 * 
	 * showStudentDashboard(studente); } else if (username.startsWith("P")) { //
	 * Professore Classe classe1 = new Classe("1A" ); Classe classe2 = new
	 * Classe("2B"); Classe classe3 = new Classe("3C");
	 * 
	 * // Aggiunta delle classi all'ArrayList List<Classe> listaClassi = new
	 * ArrayList<>(); listaClassi.add(classe1); listaClassi.add(classe2);
	 * listaClassi.add(classe3); Professore professore = new
	 * Professore("Alessandro", "Baroni", "I", "Abroa1949j",
	 * LocalDate.now(),"Storia",listaClassi ); showProfessorDashboard(professore); }
	 * else if (username.startsWith("xdvr")) { // Segreteria
	 * 
	 * Segreteria seg= new Segreteria(); Classe classeg = new Classe("4c");
	 * 
	 * showSegreteriaDashboard(seg); } else if(username.startsWith("ir")){ Classe
	 * classe = new Classe("4AIF"); LocalDate dta= LocalDate.of(2024,05,07);
	 * classe.aggiungiCompito(dta, "studiare mate"); classe.aggiungiInBacheca(dta,
	 * "soijdbg"); classe.aggiungiInBacheca(dta, "dhft");
	 * 
	 * Classe classe1 = new Classe("1A" ); Classe classe2 = new Classe("2B"); Classe
	 * classe3 = new Classe("3C");
	 * 
	 * // Aggiunta delle classi all'ArrayList List<Classe> listaClassi = new
	 * ArrayList<>(); listaClassi.add(classe1); listaClassi.add(classe2);
	 * listaClassi.add(classe3); Professore professore = new Professore("Nome",
	 * "Cognome", "Indirizzo", "CodiceFiscale", LocalDate.now(),"Storia",listaClassi
	 * ); classe.aggiungiProfessore(professore); classe.aggiungiPromemoria(dta,
	 * "jrevod");
	 * 
	 * // Imposta il professore alla prima ora del martedì
	 * classe.impostaOraProfessore(1, 0, professore); showClasseDashboard(classe); }
	 * 
	 * else { showAlert(Alert.AlertType.ERROR, "Errore",
	 * "Tipo di utente non riconosciuto."); } }
	 */

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