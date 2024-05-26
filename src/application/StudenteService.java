package application;

import scuola.Studente;

/**
 * Servizio per gestire le informazioni dello studente.
 */
public class StudenteService {

    private Studente studente;

    /**
     * Costruttore per inizializzare il servizio con uno studente.
     * @param s Lo studente da impostare
     */
    public StudenteService(Studente s) {
        this.studente = s;
    }

    /**
     * Restituisce lo studente attualmente impostato nel servizio.
     * @return Lo studente attualmente impostato
     */
    public Studente getStudente() {
        return studente;
    }

    /**
     * Imposta lo studente nel servizio.
     * @param nuovoStudente Il nuovo studente da impostare
     */
    public void setStudente(Studente nuovoStudente) {
        studente = nuovoStudente;
    }
}
