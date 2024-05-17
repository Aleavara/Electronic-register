package scuola;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Persona implements Serializable{
private String nome;
private String cognome;
private String indirizzo;
private String codiceFiscale;
private LocalDate dataNascita;

private static int numMatricola = 0;
private int nMatricola;
private Map<LocalDate, List<String>> calendario;
private static final long serialVersionUID = 1;
public Persona() {

}
public Persona(String nome,String cognome,String indirizzo,String codiceFiscale,LocalDate dataNascita) {
	this.setNome(nome);
	this.setCognome(cognome);
	this.setIndirizzo(indirizzo);
	this.setCodiceFiscale(codiceFiscale);
	this.setDataNascita(dataNascita);
	this.setnMatricola();
	numMatricola++;
	this.calendario = new HashMap<>();
}



public void aggiungiImpegno(LocalDate data, String impegno) {

    if (calendario.containsKey(data)) {
        calendario.get(data).add(impegno);
    } else {
        List<String> impegni = new ArrayList<>();
        impegni.add(impegno);
        calendario.put(data, impegni);
    }
}

public List<String> getImpegni(LocalDate data) {
    return calendario.getOrDefault(data, new ArrayList<>());
}


public String getNome() {
	return nome;
}


public void setNome(String nome) {
	this.nome = nome;
}


public String getCognome() {
	return cognome;
}


public void setCognome(String cognome) {
	this.cognome = cognome;
}


public String getIndirizzo() {
	return indirizzo;
}


public void setIndirizzo(String indirizzo) {
	this.indirizzo = indirizzo;
}


public String getCodiceFiscale() {
	return codiceFiscale;
}


public void setCodiceFiscale(String codiceFiscale) {
	this.codiceFiscale = codiceFiscale;
}


public LocalDate getDataNascita() {
	return dataNascita;
}


public void setDataNascita(LocalDate dataNascita) {
	this.dataNascita = dataNascita;
}





public int getnMatricola() {
	return nMatricola;
}


public void setnMatricola() {
	this.nMatricola = nMatricola;
	nMatricola++;
}

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
