package scuola;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class OrarioRow {
    private StringProperty[] giorni;

    public OrarioRow() {
        this.giorni = new StringProperty[6];
        for (int i = 0; i < 6; i++) {
            this.giorni[i] = new SimpleStringProperty();
        }
    }

    public StringProperty giornoProperty(int giorno) {
        return giorni[giorno];
    }

    public void setGiorno(int giorno, String sezione) {
        this.giorni[giorno].set(sezione);
    }
}
