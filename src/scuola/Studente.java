package scuola;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Studente extends Persona {
    
    
    private Map<String,List<Voto>> voti;
    private Double mediaGenerale;
  
 
    
    
    public Studente(String nome, String cognome, String indirizzo, String codiceFiscale, LocalDate dataNascita,Classe classe) {
        super(nome, cognome, indirizzo, codiceFiscale, dataNascita,classe);
        this.voti = new HashMap<>();
        classe.aggiungiStudente(this);
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
	 
	 

	
	
	






	



}
