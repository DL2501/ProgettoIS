package main.strategy.comparators;

import main.videoteca.Film;

public class ValutazioneComparator extends AbstractNullSafeComparator {


    @Override
    protected int compareSafe(Film f1, Film f2) {
        int valutazione1 = f1.getValutazione();
        int valutazione2 = f2.getValutazione();
        return Integer.compare(valutazione1,valutazione2);
    }





}
