package scuola;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Studente extends Persona implements Serializable {
    
    
    private Map<String,List<Voto>> voti;
    private Double mediaGenerale;
    private Classe classe;
    private static final long serialVersionUID = 1;
  
 
    
    public Studente() {
        this.setnMatricola();
    }
    public Studente(String nome, String cognome, String indirizzo, String codiceFiscale, LocalDate dataNascita,Classe classe) {
        super(nome, cognome, indirizzo, codiceFiscale, dataNascita);
        this.classe=classe;
        this.voti = new HashMap<>();
        classe.aggiungiStudente(this);
        GestoreCredenziali.aggiungiStudente(this);
    } 

    public Classe getClasse() {
    	return classe;
    }


    public void setClasse(Classe classe) {
    	this.classe = classe;
    }

	public Double getMediaGenerale() {
		return mediaGenerale;
	}


	public void setMediaGenerale(Double mediaGenerale) {
		this.mediaGenerale = mediaGenerale;
	}


	public Map<String,List<Voto>> getVoti() {
		return voti;
	}


	public void setVoti(Map<String,List<Voto>> voti) {
		this.voti = voti;
	}
	
	 public Professore prossimoProfessore(Classe classe) {
	        LocalDateTime now = LocalDateTime.now();
	        int giorno = now.getDayOfWeek().getValue() - 1; 
	        int ora = now.getHour();
	        
	      
	        if (ora < 8) { 
	            ora = 8; 
	        }
	        
	        return classe.getOrarioProf()[giorno][ora - 8]; 
	    }
	 
	 public String toString() {
		 return "il mio nome è" + this.getNome();
	 }
	 
	 

	
	
	






	



}
