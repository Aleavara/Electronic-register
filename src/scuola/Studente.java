package scuola;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Studente extends Persona {
    
    private static int numMatricola = 0;
    private int nMatricola;
    private Map<LocalDate, List<String>> calendario;

    public Studente(String nome, String cognome, String indirizzo, String codiceFiscale, LocalDate dataNascita) {
        super(nome, cognome, indirizzo, codiceFiscale, dataNascita);
        this.nMatricola = numMatricola;
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


}
