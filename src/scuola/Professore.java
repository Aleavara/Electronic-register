package scuola;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * La classe Professore rappresenta un docente.
 */
public class Professore extends Persona implements Serializable {
	private String materia;
	private List<Classe> classi;
	private Classe[][] orarioSettimanale; // Matrice 7x6 per gestire le classi durante la settimana
	private static final long serialVersionUID = 1;

	/**
	 * Costruttore della classe Professore.
	 * 
	 * @param nome          Il nome del professore.
	 * @param cognome       Il cognome del professore.
	 * @param indirizzo     L'indirizzo del professore.
	 * @param codiceFiscale Il codice fiscale del professore.
	 * @param dataNascita   La data di nascita del professore.
	 * @param materia       La materia insegnata dal professore.
	 * @param classi        La lista delle classi assegnate al professore.
	 */
	public Professore(String nome, String cognome, String indirizzo, String codiceFiscale, LocalDate dataNascita,
			String materia, List<Classe> classi) {
		super(nome, cognome, indirizzo, codiceFiscale, dataNascita);
		this.materia = materia;
		this.classi = classi;
		for (Classe c : classi) {
			c.aggiungiProfessore(this);
		}

		this.orarioSettimanale = new Classe[7][6];
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 6; j++) {
				this.orarioSettimanale[i][j] = null;
			}
		}
	}

	/**
	 * Costruttore di copia della classe Professore.
	 * 
	 * @param obi Oggetto Professore da copiare.
	 */
	public Professore(Professore obi) {
		super(obi.getNome(), obi.getCognome(), obi.getIndirizzo(), obi.getCodiceFiscale(), obi.getDataNascita());
		this.materia = obi.getMateria();
		this.classi = obi.getClassi();
	}

	/**
	 * Aggiunge una classe all'orario settimanale del professore.
	 * 
	 * @param giorno Indice del giorno della settimana (0 = Lunedì, ..., 6 =
	 *               Domenica).
	 * @param ora    Indice dell'ora (0 = Prima ora, ..., 5 = Sesta ora).
	 * @param classe Classe da aggiungere.
	 */
	public void aggiungiClasse(int giorno, int ora, Classe classe) {
		if (giorno < 0 || giorno > 6 || ora < 0 || ora > 5) {
			System.out.println("Giorno o ora non validi.");
			return;
		}
		orarioSettimanale[giorno][ora] = classe;
	}

	/**
	 * Rimuove una classe dall'orario settimanale del professore.
	 * 
	 * @param giorno Indice del giorno della settimana (0 = Lunedì, ..., 6 =
	 *               Domenica).
	 * @param ora    Indice dell'ora (0 = Prima ora, ..., 5 = Sesta ora).
	 */
	public void rimuoviClasse(int giorno, int ora) {
		if (giorno < 0 || giorno > 6 || ora < 0 || ora > 5) {
			System.out.println("Giorno o ora non validi.");
			return;
		}
		orarioSettimanale[giorno][ora] = null;
	}

	/**
	 * Restituisce la materia insegnata dal professore.
	 * 
	 * @return La materia insegnata.
	 */
	public String getMateria() {
		return materia;
	}

	/**
	 * Imposta la materia insegnata dal professore.
	 * 
	 * @param materia La materia da impostare.
	 */
	public void setMateria(String materia) {
		this.materia = materia;
	}

	/**
	 * Aggiunge un voto a uno studente.
	 * 
	 * @param studente Lo studente a cui aggiungere il voto.
	 * @param data     La data del voto.
	 * @param voto     Il voto da assegnare.
	 */
	public void aggiungiVoto(Studente studente, LocalDate data, Double voto) {
		Map<String, List<Voto>> voti = studente.getVoti();
		List<Voto> votiData = voti.getOrDefault(data.toString(), null);
		if (votiData != null) {
			for (Voto v : votiData) {
				if (v.getProfInserente().equals(this)&&v.getDataInserimento().equals(data)&&v.getVoto().equals(voto)) {
					System.out.println("Il professore ha già inserito un voto per questa data.");
					return;
				}
			}
		} else {
			votiData = new ArrayList<>();
			voti.put(data.toString(), votiData);
		}
		votiData.add(new Voto(data, voto, this));
		studente.calcolaMediaGenerale();
		System.out.println(
				"Voto aggiunto con successo per lo studente " + studente.getNome() + " " + studente.getCognome());
	}

	/**
	 * Restituisce la lista delle classi assegnate al professore.
	 * 
	 * @return La lista delle classi.
	 */
	public List<Classe> getClassi() {
		return classi;
	}

	/**
	 * Imposta la lista delle classi assegnate al professore.
	 * 
	 * @param classi La lista delle classi da impostare.
	 */
	public void setClassi(List<Classe> classi) {
		this.classi = classi;
	}

	public Classe[][] getOrarioSettimanale() {
		return orarioSettimanale;
	}

	public String toString() {
		return "nome" + this.getNome();
	}
	
	public void aggiungiClassee(int giorno, int ora, Classe classe) {
	    if (giorno < 0 || giorno > 6 || ora < 0 || ora > 5) {
	        System.out.println("Giorno o ora non validi.");
	        return;
	    }
	    orarioSettimanale[giorno][ora] = classe;
	}

}
