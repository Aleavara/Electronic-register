package scuola;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Segreteria implements Serializable{

    private static GestoreCredenziali gestoreCredenziali=new GestoreCredenziali();
    private static List<Classe> listaClassi = new ArrayList<>();
    private static final long serialVersionUID = 1;

    public Segreteria() {
        listaClassi = new ArrayList<>(Segreteria.getListaClassi());
        GestoreCredenziali.aggiungiSegreteria(this);

	}
    public Segreteria(Segreteria segreteriaOriginale) {
        listaClassi = new ArrayList<>(Segreteria.getListaClassi());
    }

	public void creaStudente(String nome, String cognome, String indirizzo, String codiceFiscale, LocalDate dataNascita, Classe classe, String username, String password) {
        // Crea un nuovo studente
        Studente studente = new Studente(nome, cognome, indirizzo, codiceFiscale, dataNascita, classe);
        
        gestoreCredenziali.setCredenzialiStudente(studente, username, password);
    }

	public static List<Classe> getListaClassi() {
		return Segreteria.listaClassi;
	}

	public void setListaClassi(List<Classe> listaClassi) {
		Segreteria.listaClassi = listaClassi;
	}
	
	public static void aggiungiClasse(Classe c) {
		Segreteria.listaClassi.add(c);
	}
	
	public static void rimuoviClasse(Classe c) {
		Segreteria.listaClassi.remove(c);
	}
	
	public String toString() {
		return "ciao sono la segreteria";
	}
	
	
}
