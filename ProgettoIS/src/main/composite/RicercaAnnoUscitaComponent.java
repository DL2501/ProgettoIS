package main.composite;

import main.videoteca.Film;

public class RicercaAnnoUscitaComponent extends AbstractRicercaComponent {

    private int annoUscita;


    public RicercaAnnoUscitaComponent(int annoUscita) {
        if (annoUscita < 1888)
            throw new IllegalArgumentException("Non è possibile filtrare i film per un anno di uscita in cui i film ancora non esistevano.");
        this.annoUscita = annoUscita;
    }


    @Override
    protected boolean valido(Film f) {
        return f.getAnnoUscita() == annoUscita;
    }




}
