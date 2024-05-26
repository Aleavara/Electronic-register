package scuola;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * La classe Voto rappresenta un voto inserito per uno studente.
 */
public class Voto implements Serializable{
    private LocalDate dataInserimento; // Data in cui è stato inserito il voto
    private Double voto; // Valore del voto
    private Professore profInserente; // Professore che ha inserito il voto
    private static final long serialVersionUID = 1;

    /**
     * Costruttore della classe Voto.
     * @param data La data di inserimento del voto.
     * @param v Il valore del voto.
     * @param prof Il professore che ha inserito il voto.
     */
    public Voto(LocalDate data,Double v,Professore prof) {
        this.setDataInserimento(data);
        this.setVoto(v);
        this.setProfInserente(prof);
    }

    /**
     * Restituisce la data di inserimento del voto.
     * @return La data di inserimento.
     */
    public LocalDate getDataInserimento() {
        return dataInserimento;
    }

    /**
     * Imposta la data di inserimento del voto.
     * @param dataInserimento La data di inserimento da impostare.
     */
    public void setDataInserimento(LocalDate dataInserimento) {
        this.dataInserimento = dataInserimento;
    }

    /**
     * Restituisce il valore del voto.
     * @return Il valore del voto.
     */
    public Double getVoto() {
        return voto;
    }

    /**
     * Imposta il valore del voto.
     * @param voto Il valore del voto da impostare.
     */
    public void setVoto(Double voto) {
        this.voto = voto;
    }

    /**
     * Restituisce il professore che ha inserito il voto.
     * @return Il professore inserente.
     */
    public Professore getProfInserente() {
        return profInserente;
    }

    /**
     * Imposta il professore che ha inserito il voto.
     * @param profInserente Il professore inserente da impostare.
     */
    public void setProfInserente(Professore profInserente) {
        this.profInserente = profInserente;
    }

    /**
     * Verifica se due voti sono uguali confrontandone data di inserimento e valore.
     * @param v Il voto da confrontare.
     * @return True se i voti sono uguali, false altrimenti.
     */
    public boolean equals(Voto v) {
        return this.voto.equals(v.getVoto()) && this.dataInserimento.equals(v.getDataInserimento());
    }
}
