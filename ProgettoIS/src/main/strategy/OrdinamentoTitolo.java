package main.strategy;

import main.strategy.comparators.TitoloComparator;
import main.videoteca.Film;

import java.util.ArrayList;
import java.util.List;

public class OrdinamentoTitolo implements OrdinamentoStrategy {


    @Override
    public List<Film> ordina(List<Film> film) {
        if (film == null || film.isEmpty() || film.size() == 1)
            return film;
        List<Film> listaFilm = new ArrayList<>(film);
        TitoloComparator tc = new TitoloComparator();
        listaFilm.sort(tc);
        return listaFilm;
    }



}
