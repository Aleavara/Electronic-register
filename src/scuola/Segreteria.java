package scuola;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe Segreteria rappresenta l'entità che gestisce le attività
 * amministrative della scuola.
 */
public class Segreteria implements Serializable {

	private GestoreCredenziali gestoreCredenziali = new GestoreCredenziali();
	private List<Classe> listaClassi = new ArrayList<>();
	private final long serialVersionUID = 1;
	private Credenziali credenziali;

	/**
	 * Costruttore della classe Segreteria.
	 * 
	 * @param username Il nome utente per accedere alla segreteria.
	 * @param pw       La password per accedere alla segreteria.
	 */
	public Segreteria(String username, String pw) {
		this.credenziali = new Credenziali("xd" + username, pw);
		listaClassi = new ArrayList<>(getListaClassi());
	}

	/**
	 * Costruttore di copia della classe Segreteria.
	 * 
	 * @param segreteriaOriginale L'oggetto Segreteria da copiare.
	 */
	public Segreteria(Segreteria segreteriaOriginale) {
		listaClassi = new ArrayList<>(getListaClassi());
	}

	/**
	 * Crea uno studente e lo aggiunge alla gestione della segreteria.
	 * 
	 * @param nome          Il nome dello studente.
	 * @param cognome       Il cognome dello studente.
	 * @param indirizzo     L'indirizzo dello studente.
	 * @param codiceFiscale Il codice fiscale dello studente.
	 * @param dataNascita   La data di nascita dello studente.
	 * @param classe        La classe dello studente.
	 * @param username      Il nome utente dello studente.
	 * @param password      La password dello studente.
	 */
	public void creaStudente(String nome, String cognome, String indirizzo, String codiceFiscale, LocalDate dataNascita,
			Classe classe, String username, String password) {
		Studente studente = new Studente(nome, cognome, indirizzo, codiceFiscale, dataNascita, classe);
		gestoreCredenziali.aggiungiStudente(studente);
		gestoreCredenziali.setCredenzialiStudent(studente, username, password);

	}
	
	
    /**
     * Carica studenti da un file CSV e li aggiunge alla gestione della segreteria.
     * 
     * @param csvFilePath Il percorso del file CSV.
     * @throws Exception 
     */
    public void caricaStudentiDaCSV(String csvFilePath) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split("\t");
                if (values.length == 8) {
                    String nome = values[0].trim();
                    String cognome = values[1].trim();
                    String indirizzo = values[2].trim();
                    String codiceFiscale = values[3].trim();
                    LocalDate dataNascita = null;
                    try {
                        dataNascita = LocalDate.parse(values[4].trim());
                    } catch (DateTimeParseException e) {
                        System.err.println("Formato data non valido per: " + values[4].trim());
                        continue; // Skip this record and move to the next
                    }
                    String sezioneClasse = values[5].trim();
                    String username = values[6].trim();
                    String password = values[7].trim();

                    Classe classe = trovaClassePerSezione(sezioneClasse);
                    if (classe != null) {
                        creaStudente(nome, cognome, indirizzo, codiceFiscale, dataNascita, classe, username, password);
                    } else {
                        throw new Exception("classe non trovata");
                    }
                } else {
                    System.out.println("Riga malformata: " + line);
                }
            }
        }
    }

	/**
	 * Aggiunge uno studente alla gestione della segreteria.
	 * 
	 * @param stud     Lo studente da aggiungere.
	 * @param username Il nome utente dello studente.
	 * @param pw       La password dello studente.
	 */
	public void aggiungiStudente(Studente stud, String username, String pw) {
		this.gestoreCredenziali.aggiungiStudente(stud);
		this.gestoreCredenziali.setCredenzialiStudent(stud, username, pw);
	}

	/**
	 * Aggiunge un professore alla gestione della segreteria.
	 * 
	 * @param nome          Il nome del professore.
	 * @param cognome       Il cognome del professore.
	 * @param indirizzo     L'indirizzo del professore.
	 * @param codiceFiscale Il codice fiscale del professore.
	 * @param dataNascita   La data di nascita del professore.
	 * @param materia       La materia insegnata dal professore.
	 * @param classi        Le classi insegnate dal professore.
	 * @param username      Il nome utente del professore.
	 * @param password      La password del professore.
	 */
	public void aggiungiProfessore(String nome, String cognome, String indirizzo, String codiceFiscale,
			LocalDate dataNascita, String materia, List<Classe> classi, String username, String password) {
		Professore prof = new Professore(nome, cognome, indirizzo, codiceFiscale, dataNascita, materia, classi);
		gestoreCredenziali.aggiungiProfessore(prof);
		gestoreCredenziali.setCredenzialProf(prof, username, password);
	}

	/**
	 * Aggiunge un professore alla gestione della segreteria.
	 * 
	 * @param prof     Il professore da aggiungere.
	 * @param username Il nome utente del professore.
	 * @param pw       La password del professore.
	 */
	public void aggiungiProf(Professore prof, String username, String pw) {
		gestoreCredenziali.aggiungiProfessore(prof);
		gestoreCredenziali.setCredenzialProf(prof, username, pw);
	}

	/**
	 * Restituisce la lista delle classi gestite dalla segreteria.
	 * 
	 * @return La lista delle classi.
	 */
	public List<Classe> getListaClassi() {
		return listaClassi;
	}

	/**
	 * Imposta la lista delle classi gestite dalla segreteria.
	 * 
	 * @param listaClassi La lista delle classi da impostare.
	 */
	public void setListaClassi(List<Classe> listaClassi) {
		this.listaClassi = listaClassi;
	}

	/**
	 * Aggiunge una classe alla lista delle classi gestite dalla segreteria.
	 * 
	 * @param c La classe da aggiungere.
	 * @throws IllegalArgumentException Se la classe con la stessa sezione è già
	 *                                  presente nella lista.
	 */
	public void aggiungiClasse(Classe c) throws IllegalArgumentException {
		boolean sezioneGiaPresente = listaClassi.stream()
				.anyMatch(classe -> classe.getSezione().equals(c.getSezione()));
		if (sezioneGiaPresente) {
			throw new IllegalArgumentException("La classe con la stessa sezione è già presente nella lista.");
		} else {
			listaClassi.add(c);
		}
	}

	/**
	 * Rimuove una classe dalla lista delle classi gestite dalla segreteria.
	 * 
	 * @param c La classe da rimuovere.
	 */
	public void rimuoviClasse(Classe c) {
		listaClassi.remove(c);
	}

	/**
	 * Restituisce una rappresentazione in stringa della segreteria.
	 * 
	 * @return Una stringa rappresentante la segreteria.
	 */
	public String toString() {
		return "ciao sono la segreteria";
	}

	/**
	 * Restituisce le credenziali dello studente.
	 * 
	 * @param stud Lo studente di cui ottenere le credenziali.
	 * @return Le credenziali dello studente.
	 */
	public Credenziali getCredenzialiStud(Studente stud) {
		return gestoreCredenziali.getCredenzialStudente(stud);
	}
	
    /**
     * Trova una classe per la sezione specificata.
     * 
     * @param sezione La sezione della classe.
     * @return La classe corrispondente alla sezione, o null se non trovata.
     */
    private Classe trovaClassePerSezione(String sezione) {
        for (Classe classe : listaClassi) {
            if (classe.getSezione().equals(sezione)) {
                return classe;
            }
        }
        return null;
    }
    

	public Credenziali getCredenzialiProf(Professore prof) {
		return gestoreCredenziali.getCredenzialProfessore(prof);
	}

	public Professore validaCredenzialProf(String username, String password) {
		return this.gestoreCredenziali.validaCredenzialProf(username, password);
	}

	public boolean validaCredenzialiSegreteria(String username, String password) {
		return this.credenziali.getUsername().equals(username) && this.credenziali.getPassword().equals(password);
	}

	public Studente validaCredenzialiStudent(String username, String password) {
		return this.gestoreCredenziali.validaCredenzialiStudent(username, password);
	}
	
	public Credenziali getCredenziali() {
		return this.credenziali;
	}
}
