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
        caricaVideoteca();
    }


    private void caricaVideoteca() {
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




    public boolean aggiungiFilm(Film f) {
        boolean aggiunto = videotecaVirtuale.aggiungiFilm(f);
        if (aggiunto)
            fileImplementor.aggiungiFilm(f);
        return aggiunto;
    }



    public boolean rimuoviFilm(Integer filmId) {
        boolean filmRimosso = videotecaVirtuale.rimuoviFilm(filmId);
        if (filmRimosso)
            sovrascriviFile();
        return filmRimosso;
    }



    public Film getFilm(Integer filmId) {
        return videotecaVirtuale.getFilm(filmId);
    }



    public boolean modificaFilm(Integer filmId, Film nuovoFilm) {
        boolean filmModificato = videotecaVirtuale.modificaFilm(filmId,nuovoFilm);
        if (filmModificato)
            sovrascriviFile();
        return filmModificato;
    }



    public List<Film> getAllFilm() {
        return videotecaVirtuale.getAllFilm();
    }














}
