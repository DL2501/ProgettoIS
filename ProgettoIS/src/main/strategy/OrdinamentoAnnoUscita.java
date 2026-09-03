package main.strategy;

import main.strategy.comparators.AnnoUscitaComparator;
import main.videoteca.Film;

import java.util.ArrayList;
import java.util.List;

public class OrdinamentoAnnoUscita implements OrdinamentoStrategy {


    @Override
    public List<Film> ordina(List<Film> film) {
        if (film == null || film.isEmpty() || film.size() == 1)
            return film;
        List<Film> listaFilm = new ArrayList<>(film);
        AnnoUscitaComparator auc = new AnnoUscitaComparator();
        listaFilm.sort(auc);
        return listaFilm;
    }





}
