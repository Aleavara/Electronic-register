package application;

import scuola.Studente;

public class StudenteService {

    private static Studente studente;

    public StudenteService(Studente s) {
    	this.studente=s;
    }
    public static Studente getStudente() {
        return studente;
    }

    public static void setStudente(Studente nuovoStudente) {
        studente = nuovoStudente;
    }
}
