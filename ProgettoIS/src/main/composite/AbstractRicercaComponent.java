package main.composite;

import main.videoteca.Film;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractRicercaComponent implements RicercaComponent {


    @Override
    public List<Film> filtra(List<Film> listaFilm) {
        List<Film> listaFiltrata = new ArrayList<>();
        if (listaFilm != null && !(listaFilm.isEmpty())) {
            for (Film f : listaFilm) {
                if (f != null && valido(f))
                    listaFiltrata.add(f);
            }
        }
        return listaFiltrata;
    }



    protected abstract boolean valido(Film f);

























}
