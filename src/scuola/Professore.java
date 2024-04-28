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
	
    // Metodo per aggiungere un voto allo studente
    public void aggiungiVoto(Studente studente, LocalDate data, Double voto) {
        // Verifica se lo studente ha già voti per quella data
        Map<String, List<Voto>> voti = studente.getVoti();
        List<Voto> votiData = voti.getOrDefault(data.toString(), null);
        if (votiData != null) {
            // Verifica se il professore ha già inserito un voto per quella data
            for (Voto v : votiData) {
                if (v.getProfInserente().equals(this)) {
                    System.out.println("Il professore ha già inserito un voto per questa data.");
                    return;
                }
            }
        } else {
            // Se non ci sono voti per quella data, crea una nuova lista
            votiData = new ArrayList<>();
            voti.put(data.toString(), votiData);
        }

        // Aggiungi il nuovo voto
        votiData.add(new Voto(data, voto, this));
        System.out.println("Voto aggiunto con successo per lo studente " + studente.getNome() + " " + studente.getCognome());
    }




	
	
	
	
	
	
}
