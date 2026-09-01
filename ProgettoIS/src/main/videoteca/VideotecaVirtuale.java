package main.videoteca;

import java.util.List;

public interface VideotecaVirtuale extends Iterable<Film> {

    void aggiungiFilm(Film f);

    void rimuoviFilm(Integer filmId);

    Film getFilm(Integer filmId);

    List<Film> getAllFilm();



}
