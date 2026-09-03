package main.composite;

import main.videoteca.Film;

import java.util.ArrayList;
import java.util.List;

public class RicercaComposite implements RicercaComponent {

    private List<RicercaComponent> filtriFigli = new ArrayList<>();


    public RicercaComposite(RicercaComponent... filtri) {
        for (RicercaComponent filtro : filtri) {
            if (filtro != null)
                filtriFigli.add(filtro);
        }
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



    @Override
    public List<Film> filtra(List<Film> listaFilm) {
        List<Film> listaFiltrata = new ArrayList<>(listaFilm);
        for (RicercaComponent filtroFiglio : filtriFigli)
            listaFiltrata = filtroFiglio.filtra(listaFiltrata);
        return listaFiltrata;
    }



}
