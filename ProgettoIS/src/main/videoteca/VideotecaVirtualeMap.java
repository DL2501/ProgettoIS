package main.videoteca;

import java.util.*;

public class VideotecaVirtualeMap implements VideotecaVirtuale {


    private Map<Integer,Film> videotecaVirtuale = new HashMap<>();
    private int keyCounter = 1;



    @Override
    public boolean aggiungiFilm(Film f) {
        if (f != null && !(videotecaVirtuale.containsValue(f)) && f.getId() == null) {
            f.setId(keyCounter);
            Film risultatoInserimento = videotecaVirtuale.put(keyCounter,f);
            if (risultatoInserimento == null) {
                keyCounter++;
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean rimuoviFilm(Integer filmId) {
        if (filmId != null) {
            Film filmRimosso = videotecaVirtuale.remove(filmId);
            if (filmRimosso != null)
                return true;
        }
        return false;
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
    public boolean modificaFilm(Integer filmId, Film nuovoFilm) {
        if (filmId != null && nuovoFilm != null) {
            Film filmNonAggiornato = videotecaVirtuale.replace(filmId,nuovoFilm);
            if (filmNonAggiornato != null)
                return true;
        }
        return false;
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
