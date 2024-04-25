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
    private Classe classe;
    private String password;
    private String username;
    
    public Studente(String nome, String cognome, String indirizzo, String codiceFiscale, LocalDate dataNascita,Classe classe) {
        super(nome, cognome, indirizzo, codiceFiscale, dataNascita,classe);
       
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
	        int giorno = now.getDayOfWeek().getValue() - 1; // Lunedì = 1, ... Domenica = 7
	        int ora = now.getHour();
	        
	        // Verifica se è già iniziata l'ora corrente
	        if (ora < 8) { // Supponendo che la scuola inizi alle 8:00
	            ora = 8; // Se l'ora corrente è prima delle 8:00, considera l'inizio della prima ora
	        }
	        
	        return classe.getOrarioProf()[giorno][ora - 8]; // Sottrai 8 per adattare all'indice dell'orario
	    }


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}
	
	
	






	



}
