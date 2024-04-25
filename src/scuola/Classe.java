package scuola;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Classe {
private String sezione;
private Map<LocalDate, List<String>> bacheca;
private Map<LocalDate,List<String>> compitiAssegnati;
private Map<LocalDate,List <String>> promemoria;

private String[][] orarioMaterie;
private Professore[][] orarioProf;

public Classe(String nome) {
    this.sezione = nome;
    // Inizializza le matrici per l'orario delle materie e dei professori
    this.orarioMaterie = new String[6][10];
    this.orarioProf = new Professore[6][10];
}

// Metodo per impostare un'ora nell'orario scolastico
public void impostaOra(int giorno, int ora, String materia, Professore professore) {
    // Verifica che il giorno e l'ora siano validi
    if (giorno >= 0 && giorno < 6 && ora >= 0 && ora < 10) {
        this.orarioMaterie[giorno][ora] = materia;
        this.orarioProf[giorno][ora] = professore;
    } else {
        System.out.println("Giorno o ora non validi.");
    }
}

// Metodo per ottenere l'orario delle materie
public String[][] getOrarioMaterie() {
    return this.orarioMaterie;
}

// Metodo per ottenere l'orario dei professori
public Professore[][] getOrarioProf() {
    return this.orarioProf;
}
}
