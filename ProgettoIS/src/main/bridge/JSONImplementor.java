package main.bridge;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import main.AppPaths;
import main.videoteca.Film;
import main.videoteca.StatoVisione;

import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;


public class JSONImplementor implements FileImplementor {

    private JsonReader jr;



    public JSONImplementor() {
        inizializzaFileEDirectory();
    }




    private void inizializzaFileEDirectory() {
        try {
            Path path = Paths.get(AppPaths.JSON_FILE);
            Path cartellaPadre = path.getParent();
            if (cartellaPadre != null && !(Files.exists(cartellaPadre))) {
                Files.createDirectories(cartellaPadre);
                System.out.println("Cartella padre creata con successo.");
            }
            if (!(Files.exists(path))) {
                Files.writeString(path,"[]");
                System.out.println("File creato con successo.");
            }
        } catch (IOException | InvalidPathException e) {
            throw new RuntimeException("Inizializzazione di file e directory fallita.", e);
        }
    }




    @Override
    public void apriFile() {
        if (jr == null) {
            try {
                jr = new JsonReader(new FileReader(AppPaths.JSON_FILE));
                jr.beginArray();
            } catch (IOException e) {
                throw new RuntimeException("Errore nell'apertura del file JSON.", e);
            }
        }
    }



    @Override
    public void chiudiFile() {
        if (jr != null) {
            try {
                jr.close();
            } catch (IOException e) {
                throw new RuntimeException("Errore nella chiusura del file JSON.", e);
            }
        }
    }


    @Override
    public String leggiRiga() {
        if (jr == null)
            throw new IllegalStateException("Impossibile leggere da un file chiuso.");
        try {
            if (jr.hasNext())
                return JsonParser.parseReader(jr).toString();
        } catch (IOException e) {
            throw new RuntimeException("Errore nella lettura della riga del file JSON.", e);
        }
        return null;
    }



    @Override
    public Film ottieniFilm(String riga) {
        if (riga == null || riga.isBlank())
            return null;
        try {
            JsonObject filmJson = JsonParser.parseString(riga).getAsJsonObject();
            String titolo = filmJson.get("titolo").getAsString();
            String genere = filmJson.get("genere").getAsString();
            String regista = filmJson.get("regista").getAsString();
            int annoUscita = filmJson.get("annoUscita").getAsInt();
            int valutazione = filmJson.get("valutazione").getAsInt();
            StatoVisione statoVisione = StatoVisione.valueOf(filmJson.get("statoVisione").getAsString());
            return new Film(titolo,genere,regista,annoUscita,valutazione,statoVisione);
        } catch (Exception e) {
            System.out.println("Errore nella ricostruzione del film.");
            return null;
        }
    }




    @Override
    public void aggiungiFilm(Film f) {
        if (f != null) {
            try {
                JsonObject filmJson = new JsonObject();
                filmJson.addProperty("titolo",f.getTitolo());
                filmJson.addProperty("genere",f.getGenere());
                filmJson.addProperty("regista",f.getRegista());
                filmJson.addProperty("annoUscita",f.getAnnoUscita());
                filmJson.addProperty("valutazione",f.getValutazione());
                filmJson.addProperty("statoVisione",f.getStatoVisione().name());
                String filmJsonString = filmJson.toString();
                try (RandomAccessFile raf = new RandomAccessFile(AppPaths.JSON_FILE,"rw")) {
                    long lunghezzaFile = raf.length();
                    if (lunghezzaFile <= 2) {
                        raf.seek(0);
                        raf.writeBytes("[" + filmJsonString + "]");
                    }
                    else {
                        raf.seek(lunghezzaFile - 1);
                        raf.writeBytes("," + filmJsonString + "]");
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException("Errore nell'aggiunta del film all'interno del file JSON.", e);
            }
        }
        else
            System.out.println("Errore nell'aggiunta del film all'interno del file CSV: film non definito.");
    }


    @Override
    public void svuotaFile() {
        try {
            Files.writeString(Paths.get(AppPaths.JSON_FILE),"[]");
        }
        catch (IOException e) {
            throw new RuntimeException("Errore nello svuotamento del file JSON.", e);
        }
    }



}
