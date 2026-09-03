package main.strategy;

import main.strategy.comparators.ValutazioneComparator;
import main.videoteca.Film;

import java.util.ArrayList;
import java.util.List;

public class OrdinamentoValutazione implements OrdinamentoStrategy {


    @Override
    public List<Film> ordina(List<Film> film) {
        if (film == null || film.isEmpty() || film.size() == 1)
            return film;
        List<Film> listaFilm = new ArrayList<>(film);
        ValutazioneComparator vc = new ValutazioneComparator();
        listaFilm.sort(vc);
        return listaFilm;
    }







}
