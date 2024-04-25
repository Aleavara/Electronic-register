package scuola;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Persona {
private String nome;
private String cognome;
private String indirizzo;
private String codiceFiscale;
private LocalDate dataNascita;
private Classe classe;
private static int numMatricola = 0;
private int nMatricola;
private Map<LocalDate, List<String>> calendario;
public Persona(String nome,String cognome,String indirizzo,String codiceFiscale,LocalDate dataNascita,Classe classe) {
	this.setNome(nome);
	this.setCognome(cognome);
	this.setIndirizzo(indirizzo);
	this.setCodiceFiscale(codiceFiscale);
	this.setDataNascita(dataNascita);
	this.setClasse(classe);
	this.setnMatricola(numMatricola);
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


public Classe getClasse() {
	return classe;
}


public void setClasse(Classe classe) {
	this.classe = classe;
}


public int getnMatricola() {
	return nMatricola;
}


public void setnMatricola(int nMatricola) {
	this.nMatricola = nMatricola;
}
}
