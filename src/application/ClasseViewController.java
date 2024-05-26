package application;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import scuola.Classe;
import scuola.Professore;
import scuola.Studente;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Controller per la visualizzazione e gestione delle informazioni di una
 * Classe. Gestisce l'interfaccia utente per mostrare e modificare i dati di una
 * classe. Autore: alessioavarappattu
 */
public class ClasseViewController {

	@FXML
	private VBox bachecaVBox;

	@FXML
	private VBox compitiVBox;

	@FXML
	private VBox promemoriaVBox;

	@FXML
	private ChoiceBox<String> categoryChoiceBox;

	@FXML
	private DatePicker datePicker;

	@FXML
	private TextField inputTextField;

	@FXML
	private Label classeLabel;

	@FXML
	private VBox studentiVBox;

	@FXML
	private VBox professoriVBox;

	private Classe classe;

	/**
	 * Inizializza il controller, impostando le voci della ChoiceBox.
	 */
	public void initialize() {
		categoryChoiceBox.setItems(FXCollections.observableArrayList("bacheca", "compiti assegnati", "promemoria"));
	}

	/**
	 * Imposta la classe da visualizzare e aggiorna le informazioni mostrate.
	 * 
	 * @param classe La classe da visualizzare.
	 */
	public void setClasse(Classe classe) {
		this.classe = classe;
		updateLabels();
	}

	/**
	 * Aggiorna le etichette e le liste visualizzate nell'interfaccia utente.
	 */
	public void updateLabels() {
		if (this.classe != null) {
			classeLabel.setText("Classe: " + classe.getSezione());
			populateItemsWithDeleteButton(bachecaVBox, classe.getBacheca());
			populateItemsWithDeleteButton(compitiVBox, classe.getCompitiAssegnati());
			populateItemsWithDeleteButton(promemoriaVBox, classe.getPromemoria());
			populateProfessoriList();
			populateStudentiList();
		}
	}

	/**
	 * Popola un contenitore VBox con gli elementi di una mappa, aggiungendo un
	 * pulsante di eliminazione per ciascun elemento.
	 * 
	 * @param container Il contenitore VBox in cui aggiungere gli elementi.
	 * @param items     La mappa degli elementi da visualizzare.
	 */
	private void populateItemsWithDeleteButton(VBox container, Map<LocalDate, List<String>> items) {
		container.getChildren().clear();
		LocalDate today = LocalDate.now();
		int count = 0;
		for (Map.Entry<LocalDate, List<String>> entry : items.entrySet()) {
			if (!entry.getKey().isBefore(today) && count < 5) {
				VBox entryBox = new VBox(10);
				Label dateLabel = new Label(entry.getKey().toString());
				entryBox.getChildren().add(dateLabel);
				List<String> list = entry.getValue();
				for (String item : list) {
					if (count >= 5)
						break;
					HBox itemBox = new HBox(10);
					Label itemLabel = new Label(item);
					Button deleteButton = new Button("Elimina");
					deleteButton.setStyle("-fx-background-color: black; -fx-text-fill: white;");
					deleteButton.setOnAction(event -> {
						list.remove(item);
						if (list.isEmpty()) {
							container.getChildren().remove(entryBox);
						}
						updateLabels();
					});
					itemBox.getChildren().addAll(itemLabel, deleteButton);
					entryBox.getChildren().add(itemBox);
					count++;
				}
				container.getChildren().add(entryBox);
			}
		}
	}

	/**
	 * Popola la lista dei professori della classe, aggiungendo un pulsante di
	 * eliminazione per ciascun professore.
	 */
	@FXML
	private void populateProfessoriList() {
		professoriVBox.getChildren().clear();
		List<Professore> professori = classe.getProfessori();
		professori.forEach(professore -> {
			HBox professoreBox = new HBox(10);
			Label professoreLabel = new Label(professore.getNome() + " " + professore.getCognome());
			Button deleteButton = new Button("Elimina");
			deleteButton.setStyle("-fx-background-color: black; -fx-text-fill: white;");
			deleteButton.setOnAction(event -> {
				classe.rimuoviProfessore(professore); // Metodo che rimuove il professore dalla classe
				updateLabels();
			});
			professoreBox.getChildren().addAll(professoreLabel, deleteButton);

			// Aggiungi il gestore di eventi per il clic sul label del professore
			professoreLabel.setOnMouseClicked(event -> {
				mostraDettagliProfessore(professore);
			});

			professoriVBox.getChildren().add(professoreBox);
		});
	}

	/**
	 * Popola la lista degli studenti della classe, aggiungendo un pulsante di
	 * eliminazione per ciascuno studente.
	 */
	@FXML
	private void populateStudentiList() {
		studentiVBox.getChildren().clear();
		List<Studente> studenti = classe.getStudenti();
		studenti.forEach(studente -> {
			HBox studenteBox = new HBox(10);
			Label studenteLabel = new Label(studente.getNome() + " " + studente.getCognome());
			Button deleteButton = new Button("Elimina");
			deleteButton.setStyle("-fx-background-color: black; -fx-text-fill: white;");
			deleteButton.setOnAction(event -> {
				classe.rimuoviStudente(studente); // Metodo che rimuove lo studente dalla classe
				updateLabels();
			});
			studenteBox.getChildren().addAll(studenteLabel, deleteButton);

			// Aggiungi il gestore di eventi per il clic sul label dello studente
			studenteLabel.setOnMouseClicked(event -> {
				mostraDettagliStudente(studente);
			});

			studentiVBox.getChildren().add(studenteBox);
		});
	}

	/**
	 * Mostra i dettagli di un professore in una nuova finestra.
	 * 
	 * @param professore Il professore di cui mostrare i dettagli.
	 */
	private void mostraDettagliProfessore(Professore professore) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/professor_dashboard.fxml"));
			Parent root = loader.load();
			ProfessorDashboardController controller = loader.getController();
			controller.setProfessore(professore);
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Mostra i dettagli di uno studente in una nuova finestra.
	 * 
	 * @param studente Lo studente di cui mostrare i dettagli.
	 */
	private void mostraDettagliStudente(Studente studente) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DashboardInizialeStudente.fxml"));
			Parent root = loader.load();
			StudentDashboardController controller = loader.getController();
			StudenteService student = new StudenteService(studente);
			controller.setStudenteService(student);
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Aggiunge un nuovo elemento alla categoria selezionata (bacheca, compiti
	 * assegnati o promemoria) per la data selezionata.
	 * 
	 * @param event L'evento di aggiunta dell'elemento.
	 */
	@FXML
	private void aggiungiElemento(ActionEvent event) {
		LocalDate selectedDate = datePicker.getValue();
		String category = categoryChoiceBox.getValue();
		String element = inputTextField.getText();

		if (selectedDate != null && category != null && !element.isEmpty()) {
			switch (category) {
			case "bacheca":
				classe.aggiungiInBacheca(selectedDate, element);
				break;
			case "compiti assegnati":
				classe.aggiungiCompito(selectedDate, element);
				break;
			case "promemoria":
				classe.aggiungiPromemoria(selectedDate, element);
				break;
			}
			updateLabels();
		}
	}

	/**
	 * Mostra la bacheca della classe in una nuova finestra.
	 */
	@FXML
	private void mostraBacheca() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/bacheca_dashboard.fxml"));
			Parent root = loader.load();
			BachecaController controller = loader.getController();
			controller.setClasse(classe);
			Scene scene = new Scene(root, 400, 700);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Mostra i compiti assegnati della classe in una nuova finestra.
	 */
	@FXML
	private void mostraCompitiAssegnati() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/compitiAssegnati_dashboard.fxml"));
			Parent root = loader.load();
			CompitiAssegnatiController controller = loader.getController();
			controller.setClasse(classe);
			Scene scene = new Scene(root, 400, 700);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Mostra i promemoria della classe in una nuova finestra.
	 */
	@FXML
	private void mostraPromemoria() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/promemoria_dashboard.fxml"));
			Parent root = loader.load();
			PromemoriaController controller = loader.getController();
			controller.setClasse(classe); // Passaggio della classe al controller della nuova scena

			// Imposta le dimensioni fisse della scena
			Scene scene = new Scene(root, 400, 700);

			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setResizable(false); // Impedisce il ridimensionamento della finestra
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Elimina la classe corrente e chiude la finestra.
	 * 
	 * @param event L'evento di eliminazione della classe.
	 */
	@FXML
	private void eliminaClasse(ActionEvent event) {
		this.classe = null;
		Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
		stage.close();
	}
}
