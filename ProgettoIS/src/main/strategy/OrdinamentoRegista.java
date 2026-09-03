package main.strategy;

import main.strategy.comparators.RegistaComparator;
import main.videoteca.Film;

import java.util.ArrayList;
import java.util.List;

public class OrdinamentoRegista implements OrdinamentoStrategy {


    @Override
    public List<Film> ordina(List<Film> film) {
        if (film == null || film.isEmpty() || film.size() == 1)
            return film;
        List<Film> listaFilm = new ArrayList<>(film);
        RegistaComparator rc = new RegistaComparator();
        listaFilm.sort(rc);
        return listaFilm;
    }




}
