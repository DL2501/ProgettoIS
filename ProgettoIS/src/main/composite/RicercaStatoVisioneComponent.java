package main.composite;

import main.videoteca.Film;
import main.videoteca.StatoVisione;

public class RicercaStatoVisioneComponent extends AbstractRicercaComponent {

    private StatoVisione statoVisione;


    public RicercaStatoVisioneComponent(StatoVisione statoVisione) {
        this.statoVisione = statoVisione;
    }


    @Override
    protected boolean valido(Film f) {
        return f.getStatoVisione() == statoVisione;
    }


}
