package main.composite;

import main.videoteca.Film;

import java.util.ArrayList;
import java.util.List;

public class RicercaComposite implements RicercaComponent {

    private List<RicercaComponent> filtriFigli = new ArrayList<>();


    public RicercaComposite() {}

    public RicercaComposite(List<RicercaComponent> filtriFigli) {
        if (filtriFigli != null)
            this.filtriFigli = filtriFigli;
    }


    public void aggiungiFiltro(RicercaComponent filtro) {
        if (filtro != null)
            filtriFigli.add(filtro);
    }


    public void rimuoviFiltro(RicercaComponent filtro) {
        if (filtro != null)
            filtriFigli.remove(filtro);
    }


    public RicercaComponent getFiltro(int i) {
        if (i >= 0 && i < filtriFigli.size())
            return filtriFigli.get(i);
        return null;
    }


    public List<RicercaComponent> getFiltriFigli() {
        return filtriFigli;
    }


    public void rimuoviFiltriFigli() {
        filtriFigli.clear();
    }



    @Override
    public List<Film> filtra(List<Film> listaFilm) {
        List<Film> listaFiltrata = new ArrayList<>(listaFilm);
        for (RicercaComponent filtroFiglio : filtriFigli)
            listaFiltrata = filtroFiglio.filtra(listaFiltrata);
        return listaFiltrata;
    }



}
