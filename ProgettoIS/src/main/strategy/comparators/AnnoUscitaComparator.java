package main.strategy.comparators;

import main.videoteca.Film;

public class AnnoUscitaComparator extends AbstractNullSafeComparator {


    @Override
    protected int compareSafe(Film f1, Film f2) {
        int annoUscita1 = f1.getAnnoUscita();
        int annoUscita2 = f2.getAnnoUscita();
        return Integer.compare(annoUscita1,annoUscita2);
    }





}
