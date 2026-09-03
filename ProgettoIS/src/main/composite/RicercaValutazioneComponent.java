package main.composite;

import main.videoteca.Film;

public class RicercaValutazioneComponent extends AbstractRicercaComponent {

    private int valutazione;


    public RicercaValutazioneComponent(int valutazione) {
        if (valutazione < 1 || valutazione > 5)
            throw new IllegalArgumentException("Non è possibile filtrare per valori di valutazioni che non esistono in questa applicazione.");
        this.valutazione = valutazione;
    }


    @Override
    protected boolean valido(Film f) {
        return f.getValutazione() == valutazione;
    }



}
