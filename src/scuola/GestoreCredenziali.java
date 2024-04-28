package scuola;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class GestoreCredenziali {
    private static Map<Studente, Credenziali> credenzialiStudenti;
    private static Map<Professore, Credenziali> credenzialiProf;
    private static Map<Segreteria, Credenziali> credenzialiSegreteria;

    public GestoreCredenziali() {
        credenzialiStudenti = new HashMap<>();
        credenzialiProf = new HashMap<>();
        credenzialiSegreteria = new HashMap<>();
    }

    public void setCredenzialiStudente(Studente studente, String username, String password) {
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

    public Credenziali getCredenzialiStudente(Studente studente) {
        return credenzialiStudenti.get(studente);
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

    public void setCredenzialiProfessore(Professore professore, String username, String password) {
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

    public Credenziali getCredenzialiProfessore(Professore professore) {
        return credenzialiProf.get(professore);
    }

    public Professore validaCredenzialiProfessore(String username, String password) {
        for (Entry<Professore, Credenziali> entry : credenzialiProf.entrySet()) {
            Professore professore = (Professore) entry.getKey();
            Credenziali credenziali = entry.getValue();
            if (credenziali.getUsername().equals(username) && credenziali.getPassword().equals(password)) {
                return professore;
            }
        }
        return null; 
    }

    public void setCredenzialiSegreteria(Segreteria segreteria, String username, String password) {
        boolean credenzialiDuplicati = false;
        for (Credenziali credenziali : credenzialiSegreteria.values()) {
            if (credenziali.getUsername().equals("seg" + username) && credenziali.getPassword().equals(password)) {
                credenzialiDuplicati = true;
                break;
            }
        }
        if (!credenzialiDuplicati) {
            credenzialiSegreteria.put(segreteria, new Credenziali("seg" + username, password));
        } else {
            System.out.println("Credenziali duplicate: username e password già utilizzati.");
        }
    }

    public Credenziali getCredenzialiSegreteria(Segreteria segreteria) {
        return credenzialiSegreteria.get(segreteria);
    }

    public Segreteria validaCredenzialiSegreteria(String username, String password) {
        for (Map.Entry<Segreteria, Credenziali> entry : credenzialiSegreteria.entrySet()) {
            Segreteria segreteria = (Segreteria) entry.getKey();
            Credenziali credenziali = entry.getValue();
            if (credenziali.getUsername().equals(username) && credenziali.getPassword().equals(password)) {
                return segreteria;
            }
        }
        return null; 
    }
}