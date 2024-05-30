package scuola;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeneraStudentiProf {

    private static final String[] CLASSI = {"5BIF", "4CIF", "3BET", "2AET", "1ASP", "2ODA", "3ATM", "5AET"};
    private static final String[] MATERIE = {"Matematica", "Geografia", "Storia", "Inglese", "Fisica", "Chimica", "Biologia"};
    private static final int NUM_STUDENTS = 150;
    private static final int NUM_TEACHERS = 50A;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static final String[] NOMI = {"Mario", "Luigi", "Paolo", "Giovanni", "Roberto", "Francesco", "Alessandro", "Antonio", "Marco", "Davide"};
    private static final String[] COGNOMI = {"Rossi", "Verdi", "Bianchi", "Ferrari", "Russo", "Esposito", "Bianco", "Romano", "Colombo", "Ricci"};

    public static void main(String[] args) {
        List<String[]> data = new ArrayList<>();
        data.add(new String[]{"nome", "cognome", "indirizzo", "codice fiscale", "data nascita", "classe", "username", "password", "materia"});

        // Generate students
        for (int i = 0; i < NUM_STUDENTS; i++) {
            String nome = getRandomElement(NOMI);
            String cognome = getRandomElement(COGNOMI);
            String indirizzo = generateRandomAddress();
            String codiceFiscale = generateRandomCode();
            LocalDate dataNascita = generateRandomDate(14, 19);
            String classe = getRandomElement(CLASSI);
            String username = nome.toLowerCase() + "." + cognome.toLowerCase();
            String password = nome.toLowerCase();

            data.add(new String[]{nome, cognome, indirizzo, codiceFiscale, dataNascita.format(DATE_FORMATTER), classe, username, password, ""});
        }

        // Generate teachers
        for (int i = 0; i < NUM_TEACHERS; i++) {
            String nome = getRandomElement(NOMI);
            String cognome = getRandomElement(COGNOMI);
            String indirizzo = generateRandomAddress();
            String codiceFiscale = generateRandomCode();
            LocalDate dataNascita = generateRandomDate(30, 65);
            String classe = getRandomElement(CLASSI) + "," + getRandomElement(CLASSI);
            String username = nome.toLowerCase() + "." + cognome.toLowerCase();
            String password = nome.toLowerCase();
            String materia = getRandomElement(MATERIE);

            data.add(new String[]{nome, cognome, indirizzo, codiceFiscale, dataNascita.format(DATE_FORMATTER), classe, username, password, materia});
        }

        // Write to CSV
        try (FileWriter csvWriter = new FileWriter("studenti_professori.csv")) {
            for (String[] rowData : data) {
                csvWriter.append(String.join("\t", rowData));
                csvWriter.append("\n");
            }
            System.out.println("CSV file generated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getRandomElement(String[] array) {
        Random random = new Random();
        return array[random.nextInt(array.length)];
    }

    private static String generateRandomAddress() {
        Random random = new Random();
        return random.nextInt(1000) + " " + getRandomElement(NOMI) + " Street";
    }

    private static String generateRandomCode() {
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            code.append(random.nextInt(10));
        }
        return code.toString();
    }

    private static LocalDate generateRandomDate(int minAge, int maxAge) {
        Random random = new Random();
        int days = random.nextInt((maxAge - minAge + 1) * 365);
        return LocalDate.now().minusDays(days);
    }
}
