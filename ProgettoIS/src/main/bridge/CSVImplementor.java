package main.bridge;

import main.AppPaths;
import main.videoteca.Film;
import main.videoteca.StatoVisione;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.StringTokenizer;

public class CSVImplementor implements FileImplementor {

    private BufferedReader br;


    public CSVImplementor() {
        inizializzaFileEDirectory();
    }



    private void inizializzaFileEDirectory() {
        try {
            Path path = Paths.get(AppPaths.CSV_FILE);
            Path cartellaPadre = path.getParent();
            if (cartellaPadre != null && !(Files.exists(cartellaPadre))) {
                Files.createDirectories(cartellaPadre);
                System.out.println("Cartella padre creata con successo.");
            }
            if (!(Files.exists(path))) {
                Files.createFile(path);
                System.out.println("File creato con successo.");
            }
        } catch (IOException | InvalidPathException e) {
            throw new RuntimeException("Inizializzazione di file e directory fallita.", e);
        }
    }




    @Override
    public void apriFile() {
        if (br == null) {
            try {
                br = new BufferedReader(new FileReader(AppPaths.CSV_FILE));
            } catch (IOException e) {
                throw new RuntimeException("Errore nell'apertura del file CSV", e);
            }
        }
    }


    @Override
    public void chiudiFile() {
        if (br != null) {
            try {
                br.close();
            } catch (IOException e) {
                throw new RuntimeException("Errore nella chiusura del file CSV", e);
            }
        }
    }


    @Override
    public String leggiRiga() {
        if (br == null)
            throw new IllegalStateException("Impossibile leggere da un file chiuso.");
        try {
            return br.readLine();
        } catch (IOException e) {
            throw new RuntimeException("Errore nella lettura della riga del file CSV.", e);
        }
    }


    @Override
    public Film ottieniFilm(String riga) {
        if (riga == null || riga.isBlank())
            return null;
        try {
            StringTokenizer st = new StringTokenizer(riga,";");
            String titolo = st.nextToken();
            String genere = st.nextToken();
            String regista = st.nextToken();
            int annoUscita = Integer.parseInt(st.nextToken());
            int valutazione = Integer.parseInt(st.nextToken());
            StatoVisione statoVisione = StatoVisione.valueOf(st.nextToken());
            return new Film(titolo,genere,regista,annoUscita,valutazione,statoVisione);
        } catch (Exception e) {
            System.out.println("Errore nella ricostruzione del film.");
            return null;
        }
    }


    @Override
    public void aggiungiFilm(Film f) {
        if (f != null) {
            try (PrintWriter pw = new PrintWriter(new FileWriter(AppPaths.CSV_FILE,true),true)) {
                pw.println(f.getTitolo() + ";" +
                           f.getGenere() + ";" +
                           f.getRegista() + ";" +
                           f.getAnnoUscita() + ";" +
                           f.getValutazione() + ";" +
                           f.getStatoVisione());
            }
            catch (IOException e) {
                throw new RuntimeException("Errore nell'aggiunta del film all'interno del file CSV.", e);
            }
        }
        else
            System.out.println("Errore nell'aggiunta del film all'interno del file CSV: film non definito.");
    }



    @Override
    public void svuotaFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(AppPaths.CSV_FILE,false))) {
            pw.print("");
        }
        catch (IOException e) {
            throw new RuntimeException("Errore durante lo svuotamento del file CSV.", e);
        }
    }





}
