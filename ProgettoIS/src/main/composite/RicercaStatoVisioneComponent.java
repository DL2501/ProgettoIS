package main.composite;

import main.videoteca.Film;
import main.videoteca.StatoVisione;

public class RicercaStatoVisioneComponent extends AbstractRicercaComponent {

    private StatoVisione statoVisione;


    public RicercaStatoVisioneComponent(StatoVisione statoVisione) {
        if (statoVisione == null)
            throw new IllegalArgumentException("Il valore dello stato di visione non può essere un valore non definito.");
        this.statoVisione = statoVisione;
    }


    @Override
    protected boolean valido(Film f) {
        return f.getStatoVisione() == statoVisione;
    }


}
