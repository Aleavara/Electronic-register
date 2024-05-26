package scuola;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * La classe Studente rappresenta uno studente.
 */
public class Studente extends Persona implements Serializable {
    
    private Map<String,List<Voto>> voti; // Mappa dei voti per materia
    private Double mediaGenerale; // Media generale degli voti dello studente
    private Classe classe; // Classe frequentata dallo studente
    private static final long serialVersionUID = 1;

    /**
     * Costruttore vuoto della classe Studente.
     */
    public Studente() {
        this.setnMatricola();
        this.voti = new HashMap<>();
    }

    /**
     * Costruttore della classe Studente.
     * @param nome Il nome dello studente.
     * @param cognome Il cognome dello studente.
     * @param indirizzo L'indirizzo dello studente.
     * @param codiceFiscale Il codice fiscale dello studente.
     * @param dataNascita La data di nascita dello studente.
     * @param classe La classe frequentata dallo studente.
     */
    public Studente(String nome, String cognome, String indirizzo, String codiceFiscale, LocalDate dataNascita, Classe classe) {
        super(nome, cognome, indirizzo, codiceFiscale, dataNascita);
        this.classe = classe;
        this.voti = new HashMap<>();
        classe.aggiungiStudente(this);
    }

    /**
     * Restituisce la classe frequentata dallo studente.
     * @return La classe frequentata.
     */
    public Classe getClasse() {
        return classe;
    }

    /**
     * Imposta la classe frequentata dallo studente.
     * @param classe La classe da impostare.
     */
    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    /**
     * Restituisce la media generale degli voti dello studente.
     * @return La media generale degli voti.
     */
    public Double getMediaGenerale() {
        return mediaGenerale;
    }

    /**
     * Imposta la media generale degli voti dello studente.
     * @param mediaGenerale La media generale da impostare.
     */
    public void setMediaGenerale(Double mediaGenerale) {
        this.mediaGenerale = mediaGenerale;
    }

    /**
     * Restituisce i voti dello studente per materia.
     * @return La mappa dei voti per materia.
     */
    public Map<String,List<Voto>> getVoti() {
        return voti;
    }

    /**
     * Imposta i voti dello studente per materia.
     * @param voti La mappa dei voti per materia da impostare.
     */
    public void setVoti(Map<String,List<Voto>> voti) {
        this.voti = voti;
        calcolaMediaGenerale(); // Ricalcola la media quando i voti vengono impostati
    }

    /**
     * Aggiunge un voto per lo studente e aggiorna la media generale.
     * @param materia La materia relativa al voto.
     * @param voto Il voto da aggiungere.
     */
    public void aggiungiVoto(String materia, Voto voto) {
        if (!voti.containsKey(materia)) {
            voti.put(materia, new ArrayList<>());
        }
        voti.get(materia).add(voto);
        calcolaMediaGenerale();
    }

    /**
     * Calcola la media generale degli voti dello studente.
     */
    private void calcolaMediaGenerale() {
        int numeroVoti = 0;
        double sommaVoti = 0.0;

        for (List<Voto> listaVoti : voti.values()) {
            for (Voto voto : listaVoti) {
                sommaVoti += voto.getVoto();
                numeroVoti++;
            }
        }

        if (numeroVoti > 0) {
            mediaGenerale = sommaVoti / numeroVoti;
        } else {
            mediaGenerale = 0.0;
        }
    }

    /**
     * Restituisce una rappresentazione in stringa dello studente.
     * @return Una stringa rappresentante lo studente.
     */
    public String toString() {
        return this.getNome()+ " " +this.getCognome();
    }
}
