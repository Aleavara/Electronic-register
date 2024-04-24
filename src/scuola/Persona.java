package scuola;

import java.time.LocalDate;

public class Persona {
private String nome;
private String cognome;
private String indirizzo;
private String codiceFiscale;
private LocalDate dataNascita;

public Persona(String nome,String cognome,String indirizzo,String codiceFiscale,LocalDate dataNascita) {
	this.nome=nome;
	this.cognome=cognome;
	this.indirizzo=indirizzo;
	this.codiceFiscale=codiceFiscale;
	this.dataNascita=dataNascita;
}
}
