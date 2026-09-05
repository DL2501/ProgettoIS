package main.bridge;

import main.videoteca.Film;
import main.videoteca.VideotecaVirtuale;

import java.util.List;

public class VideotecaAbstraction {

    protected FileImplementor fileImplementor;
    protected VideotecaVirtuale videotecaVirtuale;


    public VideotecaAbstraction(FileImplementor fileImplementor, VideotecaVirtuale videotecaVirtuale) {
        if (fileImplementor == null || videotecaVirtuale == null)
            throw new IllegalArgumentException("Non è possibile assegnare al fileImplementor o alla videotecaVirtuale dei valori non definiti");
        this.fileImplementor = fileImplementor;
        this.videotecaVirtuale = videotecaVirtuale;
    }


    public void caricaVideoteca() {
        boolean letturaInCorso = true;
        fileImplementor.apriFile();
        while (letturaInCorso) {
            String riga = fileImplementor.leggiRiga();
            if (riga == null)
                letturaInCorso = false;
            Film f = fileImplementor.ottieniFilm(riga);
            if (f != null)
                videotecaVirtuale.aggiungiFilm(f);
        }
        fileImplementor.chiudiFile();
    }



    public void sovrascriviFile() {
        fileImplementor.svuotaFile();
        List<Film> listaFilmVideoteca = videotecaVirtuale.getAllFilm();
        for (Film f : listaFilmVideoteca)
            fileImplementor.aggiungiFilm(f);
        System.out.println("Aggiornamento file terminato con successo.");
    }






}
