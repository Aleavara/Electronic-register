package scuola;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * La classe Persona rappresenta una persona generica.
 */
public class Persona implements Serializable {
    private String nome;
    private String cognome;
    private String indirizzo;
    private String codiceFiscale;
    private LocalDate dataNascita;
    private int nMatricola;
    private Map<LocalDate, List<String>> calendario;
    private static final long serialVersionUID = 1;

    /**
     * Costruttore vuoto della classe Persona.
     */
    public Persona() {}

    /**
     * Costruttore della classe Persona.
     * @param nome Il nome della persona.
     * @param cognome Il cognome della persona.
     * @param indirizzo L'indirizzo della persona.
     * @param codiceFiscale Il codice fiscale della persona.
     * @param dataNascita La data di nascita della persona.
     */
    public Persona(String nome, String cognome, String indirizzo, String codiceFiscale, LocalDate dataNascita) {
        this.setNome(nome);
        this.setCognome(cognome);
        this.setIndirizzo(indirizzo);
        this.setCodiceFiscale(codiceFiscale);
        this.setDataNascita(dataNascita);
        this.setnMatricola();
        this.calendario = new HashMap<>();
    }

    /**
     * Aggiunge un impegno al calendario per la data specificata.
     * @param data La data dell'impegno.
     * @param impegno L'impegno da aggiungere.
     */
    public void aggiungiImpegno(LocalDate data, String impegno) {
        if (calendario.containsKey(data)) {
            calendario.get(data).add(impegno);
        } else {
            List<String> impegni = new ArrayList<>();
            impegni.add(impegno);
            calendario.put(data, impegni);
        }
    }

    /**
     * Restituisce gli impegni per la data specificata.
     * @param data La data di cui si vogliono ottenere gli impegni.
     * @return La lista degli impegni per la data specificata.
     */
    public List<String> getImpegni(LocalDate data) {
        return calendario.getOrDefault(data, new ArrayList<>());
    }

    /**
     * Restituisce il nome della persona.
     * @return Il nome della persona.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Imposta il nome della persona.
     * @param nome Il nome da impostare.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Restituisce il cognome della persona.
     * @return Il cognome della persona.
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * Imposta il cognome della persona.
     * @param cognome Il cognome da impostare.
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     * Restituisce l'indirizzo della persona.
     * @return L'indirizzo della persona.
     */
    public String getIndirizzo() {
        return indirizzo;
    }

    /**
     * Imposta l'indirizzo della persona.
     * @param indirizzo L'indirizzo da impostare.
     */
    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    /**
     * Restituisce il codice fiscale della persona.
     * @return Il codice fiscale della persona.
     */
    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    /**
     * Imposta il codice fiscale della persona.
     * @param codiceFiscale Il codice fiscale da impostare.
     */
    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    /**
     * Restituisce la data di nascita della persona.
     * @return La data di nascita della persona.
     */
    public LocalDate getDataNascita() {
        return dataNascita;
    }

    /**
     * Imposta la data di nascita della persona.
     * @param dataNascita La data di nascita da impostare.
     */
    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    /**
     * Restituisce il numero di matricola della persona.
     * @return Il numero di matricola della persona.
     */
    public int getnMatricola() {
        return nMatricola;
    }

    /**
     * Imposta il numero di matricola della persona.
     * Genera casualmente un numero di matricola.
     */
    public void setnMatricola() {
        Random random = new Random();
        this.nMatricola = random.nextInt(Integer.MAX_VALUE);
    }

    /**
     * Aggiorna il calendario per il mese specificato.
     * @param data La data relativa al mese da aggiornare nel calendario.
     */
    public void setCalendario(LocalDate data) {
        // Ottieni il numero di giorni nel mese specificato dalla data
        int giorniMese = data.lengthOfMonth();

        // Popola il calendario con i giorni del mese e aggiorna gli impegni esistenti
        for (int giorno = 1; giorno <= giorniMese; giorno++) {
            LocalDate dataGiorno = LocalDate.of(data.getYear(), data.getMonth(), giorno);
            List<String> impegniGiorno = calendario.getOrDefault(dataGiorno, new ArrayList<>());
            calendario.put(dataGiorno, impegniGiorno);
        }
    }

    /**
     * Aggiunge un impegno per il giorno della settimana specificato.
     * @param giornoSettimana L'indice del giorno della settimana (1 = Lunedì, ..., 7 = Domenica).
     * @param impegno L'impegno da aggiungere.
     */
    public void aggiungiImpegno(int giornoSettimana, String impegno) {
        // Ottieni la data corrente
        LocalDate dataCorrente = LocalDate.now();

        // Trova il giorno della settimana corrispondente a quello specificato
        LocalDate prossimoGiorno = dataCorrente.with(TemporalAdjusters.nextOrSame(DayOfWeek.of(giornoSettimana)));

        // Aggiungi l'impegno per il prossimo giorno corrispondente
        aggiungiImpegno(prossimoGiorno, impegno);
    }

    public Map<LocalDate, List<String>> getCalendario() {
        return calendario;
    }
}
