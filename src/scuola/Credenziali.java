package scuola;

import java.io.Serializable;

/**
 * La classe rappresenta le credenziali di accesso di un utente.
 */
public class Credenziali implements Serializable {
    private String username;
    private String password;
    private static final long serialVersionUID = 1;

    /**
     * Costruttore della classe Credenziali.
     * @param username Il nome utente.
     * @param password La password.
     */
    public Credenziali(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Restituisce il nome utente.
     * @return Il nome utente.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Imposta il nome utente.
     * @param username Il nome utente da impostare.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Restituisce la password.
     * @return La password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Imposta la password.
     * @param password La password da impostare.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Override del metodo toString per la classe Credenziali.
     * @return Una stringa contenente il nome utente e la password.
     */
    @Override
    public String toString() {
        return username + " " + password;
    }
}
