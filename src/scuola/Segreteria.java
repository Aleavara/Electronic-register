package scuola;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Segreteria implements Serializable{

    private GestoreCredenziali gestoreCredenziali=new GestoreCredenziali();
    private List<Classe> listaClassi = new ArrayList<>();
    private  final long serialVersionUID = 1;

    public Segreteria() {
        listaClassi = new ArrayList<>(getListaClassi());
        

	}
    public Segreteria(Segreteria segreteriaOriginale) {
        listaClassi = new ArrayList<>(getListaClassi());
    }

	public void creaStudente(String nome, String cognome, String indirizzo, String codiceFiscale, LocalDate dataNascita, Classe classe, String username, String password) {
     
        Studente studente = new Studente(nome, cognome, indirizzo, codiceFiscale, dataNascita, classe);
        
        gestoreCredenziali.setCredenzialiStudent(studente, username, password);
    }


		public void aggiungiProfessore(String nome, String cognome, String indirizzo, String codiceFiscale, LocalDate dataNascita, String materia,List<Classe> classi, String username, String password) {
	      
	        Professore prof = new Professore(nome, cognome, indirizzo, codiceFiscale, dataNascita,materia, classi);
	        
	        gestoreCredenziali.setCredenzialProf(prof, username, password);
	        for(Classe c : classi) {
	        	System.out.println(c.getProfessori());
	        }
	    }
	

	public  List<Classe> getListaClassi() {
		return listaClassi;
	}

	public void setListaClassi(List<Classe> listaClassi) {
		listaClassi = listaClassi;
	}
	
	public  void aggiungiClasse(Classe c) {
		listaClassi.add(c);
	}
	
	public  void rimuoviClasse(Classe c) {
		listaClassi.remove(c);
	}
	
	public String toString() {
		return "ciao sono la segreteria";
	}
	
	
}
