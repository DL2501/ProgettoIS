package main.videoteca;

import java.util.*;

public class VideotecaVirtualeMap implements VideotecaVirtuale {


    private Map<Integer,Film> videotecaVirtuale = new HashMap<>();
    private int keyCounter = 1;



    @Override
    public void aggiungiFilm(Film f) {
        if (f != null) {
            if (!(videotecaVirtuale.containsValue(f))) {
                videotecaVirtuale.put(keyCounter,f);
                keyCounter++;
                System.out.println("Il film " + f + " è stato inserito con successo all'internodella Videoteca.");
            }
            else
                System.out.println("Il film " + f + " è già presente all'interno del della Videoteca.");
        }
    }


    @Override
    public void rimuoviFilm(Integer filmId) {
        if (filmId != null) {
            if (videotecaVirtuale.containsKey(filmId)) {
                videotecaVirtuale.remove(filmId);
                System.out.println("Il film con id " + filmId + " è stato rimosso con successo.");
            }
            else
                System.out.println("Il film con id " + filmId + " non è presente all'interno della videoteca.");
        }
    }


    @Override
    public Film getFilm(Integer filmId) {
        if (filmId != null) {
            if (videotecaVirtuale.containsKey(filmId))
                return videotecaVirtuale.get(filmId);
            else
                System.out.println("Il film con id " + filmId + " non è presente all'interno della videoteca.");
        }
        return null;
    }

    @Override
    public void modificaFilm(Integer filmId, Film nuovoFilm) {
        if (filmId != null && nuovoFilm != null && videotecaVirtuale.containsKey(filmId))
            videotecaVirtuale.put(filmId,nuovoFilm);
        else
            System.out.println("Modifica non valida: il film che si desidera modificare potrebbe non essere presente nella videoteca.");
    }



    @Override
    public List<Film> getAllFilm() {
        return new ArrayList<>(videotecaVirtuale.values());
    }



    @Override
    public Iterator<Film> iterator() {
        return videotecaVirtuale.values().iterator();
    }



}
