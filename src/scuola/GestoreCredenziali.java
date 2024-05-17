package scuola;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class GestoreCredenziali implements Serializable{
    private static Map<Studente, Credenziali> credenzialiStudenti;
    private static Map<Professore, Credenziali> credenzialiProf;
    private Credenziali credenzialiSeg;
    private  Map<Studente, Credenziali> credenzialiStudent=credenzialiStudenti;
    private  Map<Professore, Credenziali> credenzialProf=credenzialiProf;
 
    private static final long serialVersionUID = 1;

    public GestoreCredenziali() {
        credenzialiStudenti = new HashMap<>();
        credenzialiProf = new HashMap<>();
 
       
    }
    public void setCredenzialiSegreteria(String username, String password) {
        if (this.credenzialiSeg == null) {
            this.credenzialiSeg = new Credenziali("xd" + username, password);
        } else {
            this.credenzialiSeg.setUsername("xd" +username);
            this.credenzialiSeg.setPassword(password);
        }
    }


    public Credenziali getCredenzialiSegreteria() {
        return credenzialiSeg;
    }

    public boolean validaCredenzialiSegreteria(String username,String pw) {
    	if(this.credenzialiSeg.getUsername().equals(username)&&this.credenzialiSeg.getPassword().equals(pw))
    		return true;
    				return false;
    }
    
    


    
    public List<Credenziali> getCredenzialiStudent() {
        List<Credenziali> credenzialiStudentList = new ArrayList<>();
        for (Credenziali credenziali : credenzialiStudent.values()) {
            credenzialiStudentList.add(credenziali);
        }
        return credenzialiStudentList;
    }
    
    public Professore validaCredenzialProf(String username, String password) {
        for (Entry<Professore, Credenziali> entry : credenzialProf.entrySet()) {
            Professore professore = entry.getKey();
            Credenziali credenziali = entry.getValue();
            if (credenziali.getUsername().equals(username) && credenziali.getPassword().equals(password)) {
                return professore;
            }
        }
        return null; 
    }



    
    public void setCredenzialiStudent(Studente studente, String username, String password) {
        boolean credenzialiDuplicati = false;
        for (Credenziali credenziali : credenzialiStudent.values()) {
            if (credenziali.getUsername().equals("s" + username) && credenziali.getPassword().equals(password)) {
                credenzialiDuplicati = true;
                break;
            }
        }
        if (!credenzialiDuplicati) {
            credenzialiStudent.put(studente, new Credenziali("s" + username, password));
        } else {
            System.out.println("Credenziali duplicate: username e password già utilizzati.");
        }
    }


    public static Credenziali getCredenzialiStudente(Studente studente) {
        return credenzialiStudenti.get(studente);
    }
    
    public void setCredenzialProf(Professore professore, String username, String password) {
        boolean credenzialiDuplicati = false;
        for (Credenziali credenziali : credenzialProf.values()) {
            if (credenziali.getUsername().equals("p" + username) && credenziali.getPassword().equals(password)) {
                credenzialiDuplicati = true;
                break;
            }
        }
        if (!credenzialiDuplicati) {
            credenzialProf.put(professore, new Credenziali("p" + username, password));
        } else {
            System.out.println("Credenziali duplicate: username e password già utilizzati.");
        }
    }

    public static Studente validaCredenzialiStudente(String username, String password) {
        for (Map.Entry<Studente, Credenziali> entry : credenzialiStudenti.entrySet()) {
            Studente studente = entry.getKey();
            Credenziali credenziali = entry.getValue();
            if (credenziali.getUsername().equals(username) && credenziali.getPassword().equals(password)) {
                return studente;
            }
        }
        return null; 
    }
    
    public Studente validaCredenzialiStudent(String username, String password) {
        for (Map.Entry<Studente, Credenziali> entry : credenzialiStudent.entrySet()) {
            Studente studente = entry.getKey();
            Credenziali credenziali = entry.getValue();
            if (credenziali.getUsername().equals(username) && credenziali.getPassword().equals(password)) {
                return studente;
            }
        }
        return null; 
    }


    public static void setCredenzialiProfessore(Professore professore, String username, String password) {
        boolean credenzialiDuplicati = false;
        for (Credenziali credenziali : credenzialiProf.values()) {
            if (credenziali.getUsername().equals("p" + username) && credenziali.getPassword().equals(password)) {
                credenzialiDuplicati = true;
                break;
            }
        }
        if (!credenzialiDuplicati) {
            credenzialiProf.put(professore, new Credenziali("p" + username, password));
        } else {
            System.out.println("Credenziali duplicate: username e password già utilizzati.");
        }
    }



    public static Credenziali getCredenzialiProfessore(Professore professore) {
        return credenzialiProf.get(professore);
    }

    public static Professore validaCredenzialiProfessore(String username, String password) {
        for (Entry<Professore, Credenziali> entry : credenzialiProf.entrySet()) {
            Professore professore = (Professore) entry.getKey();
            Credenziali credenziali = entry.getValue();
            if (credenziali.getUsername().equals(username) && credenziali.getPassword().equals(password)) {
                return professore;
            }
        }
        return null; 
    }





    public static void aggiungiStudente(Studente studente) {
        if (credenzialiStudenti != null) {
            credenzialiStudenti.put(studente, new Credenziali("", "")); // Inizializza le credenziali con valori vuoti
        } else {
            System.err.println("La mappa credenzialiStudenti non è stata inizializzata.");
        }
    }

    public static void aggiungiProfessore(Professore professore) {
        if (credenzialiProf != null) {
            credenzialiProf.put(professore, new Credenziali("", "")); // Inizializza le credenziali con valori vuoti
        } else {
            System.err.println("La mappa credenzialiProf non è stata inizializzata.");
        }
    }


    
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        
        // Credenziali degli studenti
        stringBuilder.append("Credenziali degli studenti:\n");
        for (Map.Entry<Studente, Credenziali> entry : credenzialiStudent.entrySet()) {
            Studente studente = entry.getKey();
            Credenziali credenziali = entry.getValue();
            stringBuilder.append("Studente: ").append(studente.getNome()).append(" ").append(studente.getCognome())
                         .append(" - Username: ").append(credenziali.getUsername()).append(", Password: ").append(credenziali.getPassword())
                         .append("\n");
        }
        
        // Credenziali dei professori
        stringBuilder.append("\nCredenziali dei professori:\n");
        for (Map.Entry<Professore, Credenziali> entry : credenzialProf.entrySet()) {
            Professore professore = entry.getKey();
            Credenziali credenziali = entry.getValue();
            stringBuilder.append("Professore: ").append(professore.getNome()).append(" ").append(professore.getCognome())
                         .append(" - Username: ").append(credenziali.getUsername()).append(", Password: ").append(credenziali.getPassword())
                         .append("\n");
        }
        
       stringBuilder.append("\nCredenziali segreteria:\n");
        stringBuilder.append("username: ").append(credenzialiSeg.getUsername()).append("\n password: ").append(credenzialiSeg.getPassword());        
  
        
        return stringBuilder.toString();
    }



	public Credenziali getCredenzialiSeg() {
		return credenzialiSeg;
	}



	public void setCredenzialiSeg(Credenziali credenzialiSeg) {
		this.credenzialiSeg = credenzialiSeg;
	}

}