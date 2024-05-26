package scuola;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * La classe OrarioRow rappresenta una riga dell'orario delle lezioni.
 */
public class OrarioRow {
    private StringProperty[] giorni;

    /**
     * Costruttore della classe OrarioRow.
     */
    public OrarioRow() {
        this.giorni = new StringProperty[6];
        for (int i = 0; i < 6; i++) {
            this.giorni[i] = new SimpleStringProperty();
        }
    }

    /**
     * Restituisce la proprietà stringa relativa al giorno specificato.
     * @param giorno L'indice del giorno.
     * @return La proprietà stringa relativa al giorno specificato.
     */
    public StringProperty giornoProperty(int giorno) {
        return giorni[giorno];
    }

    /**
     * Imposta la sezione per il giorno specificato.
     * @param giorno L'indice del giorno.
     * @param sezione La sezione da impostare.
     */
    public void setGiorno(int giorno, String sezione) {
        this.giorni[giorno].set(sezione);
    }
}
