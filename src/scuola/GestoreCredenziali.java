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
    private static Map<Segreteria, Credenziali> credenzialiSegreteria;
    private  Map<Studente, Credenziali> credenzialiStudent=credenzialiStudenti;
    private  Map<Professore, Credenziali> credenzialProf=credenzialiProf;
    private static Map<Segreteria, Credenziali> credenzialSegreteria= credenzialiSegreteria;
    private static final long serialVersionUID = 1;

    public GestoreCredenziali() {
        credenzialiStudenti = new HashMap<>();
        credenzialiProf = new HashMap<>();
        credenzialiSegreteria = new HashMap<>();
    }
    
    public List<Credenziali> getCredenzialiStudent() {
        List<Credenziali> credenzialiStudentList = new ArrayList<>();
        for (Credenziali credenziali : credenzialiStudent.values()) {
            credenzialiStudentList.add(credenziali);
        }
        return credenzialiStudentList;
    }

    public static void setCredenzialiStudente(Studente studente, String username, String password) {
        boolean credenzialiDuplicati = false;
        for (Credenziali credenziali : credenzialiStudenti.values()) {
            if (credenziali.getUsername().equals("s" + username) && credenziali.getPassword().equals(password)) {
                credenzialiDuplicati = true;
                break;
            }
        }
        if (!credenzialiDuplicati) {
            credenzialiStudenti.put(studente, new Credenziali("s" + username, password));
        } else {
            System.out.println("Credenziali duplicate: username e password già utilizzati.");
        }
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

    public static void setCredenzialiSegreteria(Segreteria segreteria, String username, String password) {
        boolean credenzialiDuplicati = false;
        for (Credenziali credenziali : credenzialiSegreteria.values()) {
            if (credenziali.getUsername().equals("seg" + username) && credenziali.getPassword().equals(password)) {
                credenzialiDuplicati = true;
                break;
            }
        }
        if (!credenzialiDuplicati) {
            credenzialiSegreteria.put(segreteria, new Credenziali("xd" + username, password));
        } else {
            System.out.println("Credenziali duplicate: username e password già utilizzati.");
        }
    }

    public static Credenziali getCredenzialiSegreteria(Segreteria segreteria) {
        return credenzialiSegreteria.get(segreteria);
    }

    public static Segreteria validaCredenzialiSegreteria(String username, String password) {
        for (Map.Entry<Segreteria, Credenziali> entry : credenzialiSegreteria.entrySet()) {
            Segreteria segreteria = (Segreteria) entry.getKey();
            Credenziali credenziali = entry.getValue();
            if (credenziali.getUsername().equals(username) && credenziali.getPassword().equals(password)) {
                return segreteria;
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

    public static void aggiungiSegreteria(Segreteria segreteria) {
        if (credenzialiSegreteria != null) {
            credenzialiSegreteria.put(segreteria, new Credenziali("", "")); // Inizializza le credenziali con valori vuoti
        } else {
            System.err.println("La mappa credenzialiSegreteria non è stata inizializzata.");
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
        
        return stringBuilder.toString();
    }
}