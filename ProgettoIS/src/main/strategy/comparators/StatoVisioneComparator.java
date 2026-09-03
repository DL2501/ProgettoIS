package main.strategy.comparators;

import main.videoteca.Film;
import main.videoteca.StatoVisione;

public class StatoVisioneComparator extends AbstractNullSafeComparator {


    @Override
    protected int compareSafe(Film f1, Film f2) {
        StatoVisione statoVisione1 = f1.getStatoVisione();
        StatoVisione statoVisione2 = f2.getStatoVisione();
        return statoVisione1.compareTo(statoVisione2);
    }



}
