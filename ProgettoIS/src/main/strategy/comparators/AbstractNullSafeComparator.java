package main.strategy.comparators;

import main.videoteca.Film;

import java.util.Comparator;

public abstract class AbstractNullSafeComparator implements Comparator<Film> {


    @Override
    public int compare(Film f1, Film f2) {
        if (f1 == null && f2 == null)
            return 0;
        if (f1 == null)
            return 1;
        if (f2 == null)
            return -1;
        return compareSafe(f1,f2);
    }



    protected abstract int compareSafe(Film f1, Film f2);




}
