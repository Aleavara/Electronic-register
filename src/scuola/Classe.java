package scuola;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Classe implements Serializable{
    private String sezione;
    private Map<LocalDate, List<String>> bacheca;
    private Map<LocalDate, List<String>> compitiAssegnati;
    private Map<LocalDate, List<String>> promemoria;
    private List<Studente> studenti;
    private List<Professore> professori;
    private static final long serialVersionUID = 1;
    private String[][] orarioMaterie;
    private Professore[][] orarioProf;

    public Classe(String nome) {
        this.setSezione(nome);

        this.bacheca = new HashMap<>();
        this.compitiAssegnati = new HashMap<>();
        this.promemoria = new HashMap<>();

        this.orarioMaterie = new String[6][10];
        this.orarioProf = new Professore[6][10];
        this.setStudenti(new ArrayList<>());
        this.setProfessori(new ArrayList<>());
        Segreteria.aggiungiClasse(this);
    }

    public void aggiungiStudente(Studente studente) {
        studenti.add(studente);
    }

    public void rimuoviStudente(Studente studente) {
        studenti.remove(studente);
    }
    
    public void aggiungiProfessore(Professore professore) {
        professori.add(professore);
    }

    public void rimuoviProfessore(Professore professore) {
        professori.remove(professore);
    }
    public void impostaOra(int giorno, int ora, String materia, Professore professore) {
        if (giorno >= 0 && giorno < 6 && ora >= 0 && ora < 10) {
            this.orarioMaterie[giorno][ora] = materia;
            this.orarioProf[giorno][ora] = professore;
        } else {
            System.out.println("Giorno o ora non validi.");
        }
    }

    // Metodo per aggiungere un promemoria
    public void aggiungiPromemoria(LocalDate data, String promemoria) {
        if (!this.promemoria.containsKey(data)) {
            this.promemoria.put(data, new ArrayList<>());
        }
        this.promemoria.get(data).add(promemoria);
    }

    // Metodo per aggiungere una cosa nella bacheca
    public void aggiungiInBacheca(LocalDate data, String nota) {
        if (!this.bacheca.containsKey(data)) {
            this.bacheca.put(data, new ArrayList<>());
        }
        this.bacheca.get(data).add(nota);
    }

    // Metodo per aggiungere un compito assegnato
    public void aggiungiCompito(LocalDate data, String compito) {
        if (!this.compitiAssegnati.containsKey(data)) {
            this.compitiAssegnati.put(data, new ArrayList<>());
        }
        this.compitiAssegnati.get(data).add(compito);
    }

    // Metodi getter per ottenere le mappe dei promemoria, della bacheca e dei compiti assegnati
    public Map<LocalDate, List<String>> getPromemoria() {
        return promemoria;
    }

    public Map<LocalDate, List<String>> getBacheca() {
        return bacheca;
    }

    public Map<LocalDate, List<String>> getCompitiAssegnati() {
        return compitiAssegnati;
    }
    
    public Professore[][] getOrarioProf() {
        return this.orarioProf;
    }

	public String getSezione() {
		return sezione;
	}

	public void setSezione(String sezione) {
		this.sezione = sezione;
	}

	public List<Studente> getStudenti() {
		return studenti;
	}

	public void setStudenti(List<Studente> studenti) {
		this.studenti = studenti;
	}

	public List<Professore> getProfessori() {
		return professori;
	}

	public void setProfessori(List<Professore> professori) {
		this.professori = professori;
	}
	
	public String toString() {
		return this.getSezione();
	}
	
	public void impostaOraProfessore(int giorno, int ora, Professore professore) {
	    if (giorno >= 0 && giorno < 6 && ora >= 0 && ora < 10) {
	        this.orarioProf[giorno][ora] = professore;
	    } else {
	        System.out.println("Giorno o ora non validi.");
	    }
	}
	
    public String[][] getOrarioMaterie() {
        return orarioMaterie;
    }

    public void setOrarioMaterie(String[][] orarioMaterie) {
        this.orarioMaterie = orarioMaterie;
    }

 
}