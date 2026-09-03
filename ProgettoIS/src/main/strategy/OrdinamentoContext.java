package main.strategy;

import main.videoteca.Film;

import java.util.ArrayList;
import java.util.List;

public class OrdinamentoContext {

    private List<Film> listaFilm;
    private OrdinamentoStrategy strategy;


    public OrdinamentoContext(List<Film> listaFilm) {
        this.listaFilm = listaFilm;
    }


    public void setStrategy(OrdinamentoStrategy newStrategy) {
        strategy = newStrategy;
    }



    public List<Film> ordina() {
        if (listaFilm == null)
            return new ArrayList<>();
        if (strategy == null)
            return new ArrayList<>(listaFilm);
        return strategy.ordina(listaFilm);
    }




















}
