package scuola;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class GestoreCredenziali implements Serializable{

    private Credenziali credenzialiSeg;
    private  Map<Studente, Credenziali> credenzialiStudent;
    private  Map<Professore, Credenziali> credenzialProf;
    private static final long serialVersionUID = 1;

    public GestoreCredenziali() {
        credenzialiStudent = new HashMap<>();
        credenzialProf = new HashMap<>();
 
       
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



    //this
    public void setCredenzialiStudent(Studente studente, String username, String password) {
        boolean credenzialiDuplicati = false;
        for (Credenziali credenziali : credenzialiStudent.values()) {
            if (credenziali.getUsername().equals("s" + username) ) {
                credenzialiDuplicati = true;
                break;
            }
        }
        if (!credenzialiDuplicati) {
            credenzialiStudent.put(studente, new Credenziali("s" + username, password));
            if (credenzialiStudent.containsKey(studente)) {
              //  System.out.println("Lo studente è già presente. Aggiornamento delle credenziali.");
                credenzialiStudent.put(studente, new Credenziali("s" + username, password));
            } 
            
        } else {
            System.out.println("Credenziali duplicate: username e password già utilizzati.");
        }
    }
    
    //this
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
    
    
    public void setCredenzialiStudent(Studente studente, Credenziali cred) {
        boolean credenzialiDuplicati = false;
        for (Credenziali credenziali : credenzialiStudent.values()) {
            if (credenziali.equals(cred)) {
                credenzialiDuplicati = true;
                break;
            }
        }
        if (!credenzialiDuplicati) {
            credenzialiStudent.put(studente, cred);
        } else {
            System.out.println("Credenziali duplicate: username e password già utilizzati.");
        }
    }



    
   public Credenziali getCredenzialStudente(Studente studente) {
        return credenzialiStudent.get(studente);}
    
    
    public  Credenziali getCredenzialProfessore(Professore professore) {
        return credenzialProf.get(professore);
    }
    

    
    public void setCredenzialProf(Professore professore, Credenziali cred) {
        boolean credenzialiDuplicati = false;
        for (Credenziali credenziali : credenzialProf.values()) {
            if (credenziali.equals(cred) ) {
                credenzialiDuplicati = true;
                break;
            }
        }
        if (!credenzialiDuplicati) {
            credenzialProf.put(professore, cred);
        } else {
            System.out.println("Credenziali duplicate: username e password già utilizzati.");
        }
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




    







    public  void aggiungiStudente(Studente studente) {
        if (credenzialiStudent != null) {
            credenzialiStudent.put(studente, new Credenziali("", "")); // Inizializza le credenziali con valori vuoti
        } else {
            System.err.println("La mappa credenzialiStudenti non è stata inizializzata.");
        }
    }

    public  void aggiungiProfessore(Professore professore) {
        if (credenzialProf != null) {
            credenzialProf.put(professore, new Credenziali("", "")); // Inizializza le credenziali con valori vuoti
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
        
     //  stringBuilder.append("\nCredenziali segreteria:\n");
      //  stringBuilder.append("username: ").append(credenzialiSeg.getUsername()).append("\n password: ").append(credenzialiSeg.getPassword());        
  
        
        return stringBuilder.toString();
    }



	public Credenziali getCredenzialiSeg() {
		return credenzialiSeg;
	}



	public void setCredenzialiSeg(Credenziali credenzialiSeg) {
		this.credenzialiSeg = credenzialiSeg;
	}
	

	

}