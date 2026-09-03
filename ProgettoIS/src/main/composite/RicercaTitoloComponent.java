package main.composite;

import main.videoteca.Film;

public class RicercaTitoloComponent extends AbstractRicercaComponent {

    private String titolo;

    public RicercaTitoloComponent(String titolo) {
        if (titolo == null || titolo.isBlank())
            throw new IllegalArgumentException("Impossibile filtrare per un titolo non definito.");
        this.titolo = titolo.toLowerCase();
    }


    @Override
    protected boolean valido(Film f) {
        return f.getTitolo().toLowerCase().contains(titolo);
    }




}
