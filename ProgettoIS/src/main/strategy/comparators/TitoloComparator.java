package main.strategy.comparators;

import main.videoteca.Film;

public class TitoloComparator extends AbstractNullSafeComparator {


    @Override
    protected int compareSafe(Film f1, Film f2) {
        String titolo1 = f1.getTitolo();
        String titolo2 = f2.getTitolo();
        return titolo1.compareToIgnoreCase(titolo2);
    }


}
