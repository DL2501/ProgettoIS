package main.videoteca;

import java.util.*;

public final class VideotecaVirtualeSingleton implements VideotecaVirtuale {

    private static VideotecaVirtualeSingleton INSTANCE = null;

    private Map<Integer,Film> videotecaVirtuale;
    private int keyCounter;


    private VideotecaVirtualeSingleton() {
        videotecaVirtuale = new HashMap<>();
        keyCounter = 1;
    }


    public static synchronized VideotecaVirtualeSingleton getInstance() {
        if (INSTANCE == null)
            INSTANCE = new VideotecaVirtualeSingleton();
        return INSTANCE;
    }


    @Override
    public synchronized void aggiungiFilm(Film f) {
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
    public synchronized void rimuoviFilm(Integer filmId) {
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
    public synchronized Film getFilm(Integer filmId) {
        if (filmId != null) {
            if (videotecaVirtuale.containsKey(filmId))
                return videotecaVirtuale.get(filmId);
            else
                System.out.println("Il film con id " + filmId + " non è presente all'interno della videoteca.");
        }
        return null;
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
