package main.strategy;

import main.strategy.comparators.StatoVisioneComparator;
import main.videoteca.Film;

import java.util.ArrayList;
import java.util.List;

public class OrdinamentoStatoVisione implements OrdinamentoStrategy {


    @Override
    public List<Film> ordina(List<Film> film) {
        if (film == null || film.isEmpty() || film.size() == 1)
            return film;
        List<Film> listaFilm = new ArrayList<>(film);
        StatoVisioneComparator svc = new StatoVisioneComparator();
        listaFilm.sort(svc);
        return listaFilm;
    }




}
