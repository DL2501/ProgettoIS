package main.videoteca;

import java.util.List;

public interface VideotecaVirtuale extends Iterable<Film> {

    boolean aggiungiFilm(Film f);

    boolean rimuoviFilm(Integer filmId);

    Film getFilm(Integer filmId);

    boolean modificaFilm(Integer filmId, Film nuovoFilm);

    List<Film> getAllFilm();



}
