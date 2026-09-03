package main.strategy.comparators;

import main.videoteca.Film;

public class GenereComparator extends AbstractNullSafeComparator {


    @Override
    protected int compareSafe(Film f1, Film f2) {
        String genere1 = f1.getGenere();
        String genere2 = f2.getGenere();
        return genere1.compareToIgnoreCase(genere2);
    }




}
