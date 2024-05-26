package application;

import java.time.ZonedDateTime;

/**
 * Classe che rappresenta un'attività nel calendario.
 * Autore: alessioavarappattu
 */
public class CalendarActivity {
    private ZonedDateTime date;
    private String clientName;
    private Integer serviceNo;

    /**
     * Costruttore della classe CalendarActivity.
     *
     * @param date       La data e l'ora dell'attività.
     * @param clientName Il nome del cliente associato all'attività.
     * @param serviceNo  Il numero del servizio associato all'attività.
     */
    public CalendarActivity(ZonedDateTime date, String clientName, Integer serviceNo) {
        this.date = date;
        this.clientName = clientName;
        this.serviceNo = serviceNo;
    }

    /**
     * Restituisce la data e l'ora dell'attività.
     *
     * @return La data e l'ora dell'attività.
     */
    public ZonedDateTime getDate() {
        return date;
    }

    /**
     * Imposta la data e l'ora dell'attività.
     *
     * @param date La data e l'ora dell'attività.
     */
    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    /**
     * Restituisce il nome del cliente associato all'attività.
     *
     * @return Il nome del cliente associato all'attività.
     */
    public String getClientName() {
        return clientName;
    }

    /**
     * Imposta il nome del cliente associato all'attività.
     *
     * @param clientName Il nome del cliente associato all'attività.
     */
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    /**
     * Restituisce il numero del servizio associato all'attività.
     *
     * @return Il numero del servizio associato all'attività.
     */
    public Integer getServiceNo() {
        return serviceNo;
    }

    /**
     * Imposta il numero del servizio associato all'attività.
     *
     * @param serviceNo Il numero del servizio associato all'attività.
     */
    public void setServiceNo(Integer serviceNo) {
        this.serviceNo = serviceNo;
    }

    /**
     * Restituisce una stringa che rappresenta l'oggetto CalendarActivity.
     *
     * @return Una stringa che rappresenta l'oggetto CalendarActivity.
     */
    @Override
    public String toString() {
        return "CalendarActivity";
               
    }
}
