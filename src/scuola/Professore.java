package scuola;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Professore extends Persona implements Serializable{
	private String materia;
	private List<Classe> classi;
    private Classe[][] orarioSettimanale; // Matrice 7x6 per gestire le classi durante la settimana
    private static final long serialVersionUID = 1;

	
    public Professore(String nome, String cognome, String indirizzo, String codiceFiscale, LocalDate dataNascita,
            String materia, List<Classe> classi) {
super(nome, cognome, indirizzo, codiceFiscale, dataNascita);
this.materia = materia;
this.classi = classi;
for(Classe c : classi) {
	c.aggiungiProfessore(this);
}
this.orarioSettimanale = new Classe[7][6];
orarioSettimanale[1][2]=new Classe("f");
orarioSettimanale[2][1]= new Classe("3b");
GestoreCredenziali.aggiungiProfessore(this);

}
    public Professore(Professore obi) {
    	super(obi.getNome(), obi.getCognome(), obi.getIndirizzo(), obi.getCodiceFiscale(), obi.getDataNascita());
        this.materia=obi.getMateria();
        this.classi=obi.getClassi();
        this.orarioSettimanale=obi.getOrarioSettimanale();
        GestoreCredenziali.aggiungiProfessore(this);
    }
	public void aggiungiClasse(int giorno, int ora, Classe classe) {
        if (giorno < 0 || giorno > 6 || ora < 0 || ora > 5) {
            System.out.println("Giorno o ora non validi.");
            return;
        }
        orarioSettimanale[giorno][ora] = classe;
    }

    public void rimuoviClasse(int giorno, int ora) {
        if (giorno < 0 || giorno > 6 || ora < 0 || ora > 5) {
            System.out.println("Giorno o ora non validi.");
            return;
        }
        orarioSettimanale[giorno][ora] = null;
    }

	public String getMateria() {
		return materia;
	}

	public void setMateria(String materia) {
		this.materia = materia;
	}
	
    
    public void aggiungiVoto(Studente studente, LocalDate data, Double voto) {

        Map<String, List<Voto>> voti = studente.getVoti();
        List<Voto> votiData = voti.getOrDefault(data.toString(), null);
        if (votiData != null) {
          
            for (Voto v : votiData) {
                if (v.getProfInserente().equals(this)) {
                    System.out.println("Il professore ha già inserito un voto per questa data.");
                    return;
                }
            }
        } else {
       
            votiData = new ArrayList<>();
            voti.put(data.toString(), votiData);
        }


        votiData.add(new Voto(data, voto, this));
        System.out.println("Voto aggiunto con successo per lo studente " + studente.getNome() + " " + studente.getCognome());
    }

	public List<Classe> getClassi() {
		return classi;
	}

	public void setClassi(List<Classe> classi) {
		this.classi = classi;
	}
	
	public Classe[][] getOrarioSettimanale() {
        return orarioSettimanale;
    }
	
	public String toString() {
		return "nome" + this.getNome();
	}




	
	
	
	
	
	
}
