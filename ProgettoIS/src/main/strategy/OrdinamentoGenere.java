package main.strategy;

import main.strategy.comparators.GenereComparator;
import main.videoteca.Film;

import java.util.ArrayList;
import java.util.List;

public class OrdinamentoGenere implements OrdinamentoStrategy {


    @Override
    public List<Film> ordina(List<Film> film) {
        if (film == null || film.isEmpty() || film.size() == 1)
            return film;
        List<Film> listaFilm = new ArrayList<>(film);
        GenereComparator gc = new GenereComparator();
        listaFilm.sort(gc);
        return listaFilm;
    }





}
