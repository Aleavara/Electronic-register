package scuola;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * La classe rappresenta una classe scolastica.
 */
public class Classe implements Serializable {
    private String sezione;
    private Map<LocalDate, List<String>> bacheca;
    private Map<LocalDate, List<String>> compitiAssegnati;
    private Map<LocalDate, List<String>> promemoria;
    private List<Studente> studenti;
    private List<Professore> professori;
    private static final long serialVersionUID = 1;

    /**
     * Costruttore della classe Classe.
     * @param nome Il nome della sezione della classe.
     */
    public Classe(String nome) {
        this.setSezione(nome);

        this.bacheca = new HashMap<>();
        this.compitiAssegnati = new HashMap<>();
        this.promemoria = new HashMap<>();

        this.setStudenti(new ArrayList<>());
        this.setProfessori(new ArrayList<>());
    }

    /**
     * Aggiunge uno studente alla classe.
     * @param studente Lo studente da aggiungere.
     */
    public void aggiungiStudente(Studente studente) {
        studenti.add(studente);
    }

    /**
     * Rimuove uno studente dalla classe.
     * @param studente Lo studente da rimuovere.
     */
    public void rimuoviStudente(Studente studente) {
        studenti.remove(studente);
    }

    /**
     * Aggiunge un professore alla classe.
     * @param professore Il professore da aggiungere.
     */
    public void aggiungiProfessore(Professore professore) {
        professori.add(professore);
    }

    /**
     * Rimuove un professore dalla classe.
     * @param professore Il professore da rimuovere.
     */
    public void rimuoviProfessore(Professore professore) {
        professori.remove(professore);
    }

    /**
     * Aggiunge un promemoria alla classe.
     * @param data La data del promemoria.
     * @param promemoria Il promemoria da aggiungere.
     */
    public void aggiungiPromemoria(LocalDate data, String promemoria) {
        if (!this.promemoria.containsKey(data)) {
            this.promemoria.put(data, new ArrayList<>());
        }
        this.promemoria.get(data).add(promemoria);
    }

    /**
     * Aggiunge una nota alla bacheca della classe.
     * @param data La data della nota.
     * @param nota La nota da aggiungere.
     */
    public void aggiungiInBacheca(LocalDate data, String nota) {
        if (!this.bacheca.containsKey(data)) {
            this.bacheca.put(data, new ArrayList<>());
        }
        this.bacheca.get(data).add(nota);
    }

    /**
     * Aggiunge un compito assegnato alla classe.
     * @param data La data del compito.
     * @param compito Il compito da aggiungere.
     */
    public void aggiungiCompito(LocalDate data, String compito) {
        if (!this.compitiAssegnati.containsKey(data)) {
            this.compitiAssegnati.put(data, new ArrayList<>());
        }
        this.compitiAssegnati.get(data).add(compito);
    }

    /**
     * Restituisce i promemoria della classe.
     * @return I promemoria della classe.
     */
    public Map<LocalDate, List<String>> getPromemoria() {
        return promemoria;
    }

    /**
     * Restituisce la bacheca della classe.
     * @return La bacheca della classe.
     */
    public Map<LocalDate, List<String>> getBacheca() {
        return bacheca;
    }

    /**
     * Restituisce i compiti assegnati della classe.
     * @return I compiti assegnati della classe.
     */
    public Map<LocalDate, List<String>> getCompitiAssegnati() {
        return compitiAssegnati;
    }

    /**
     * Restituisce la sezione della classe.
     * @return La sezione della classe.
     */
    public String getSezione() {
        return sezione;
    }

    /**
     * Imposta la sezione della classe.
     * @param sezione La sezione della classe da impostare.
     */
    public void setSezione(String sezione) {
        this.sezione = sezione;
    }

    /**
     * Restituisce la lista degli studenti della classe.
     * @return La lista degli studenti della classe.
     */
    public List<Studente> getStudenti() {
        return studenti;
    }

    /**
     * Imposta la lista degli studenti della classe.
     * @param studenti La lista degli studenti da impostare.
     */
    public void setStudenti(List<Studente> studenti) {
        this.studenti = studenti;
    }

    /**
     * Restituisce la lista dei professori della classe.
     * @return La lista dei professori della classe.
     */
    public List<Professore> getProfessori() {
        return professori;
    }

    /**
     * Imposta la lista dei professori della classe.
     * @param professori La lista dei professori da impostare.
     */
    public void setProfessori(List<Professore> professori) {
        this.professori = professori;
    }

    /**
     * Override del metodo toString per la classe Classe.
     * @return Una stringa rappresentante la sezione della classe.
     */
    @Override
    public String toString() {
        return this.getSezione();
    }
}
