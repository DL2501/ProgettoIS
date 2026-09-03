package main.composite;

import main.videoteca.Film;

public class RicercaGenereComponent extends AbstractRicercaComponent {

    private String genere;


    public RicercaGenereComponent(String genere) {
        if (genere == null || genere.isBlank())
            throw new IllegalArgumentException("Impossibile filtrare per un genere non definito.");
        this.genere = genere.toLowerCase();
    }


    @Override
    protected boolean valido(Film f) {
        return f.getGenere().toLowerCase().equals(genere);
    }



}
