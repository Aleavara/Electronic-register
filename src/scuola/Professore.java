package scuola;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Professore extends Persona{
	private String materia;

	
	public Professore(String nome, String cognome, String indirizzo, String codiceFiscale, LocalDate dataNascita,
			Classe classe) {
		super(nome, cognome, indirizzo, codiceFiscale, dataNascita, classe);
		
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




	
	
	
	
	
	
}
