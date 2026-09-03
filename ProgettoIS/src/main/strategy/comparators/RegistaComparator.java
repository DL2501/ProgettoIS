package main.strategy.comparators;

import main.videoteca.Film;

public class RegistaComparator extends AbstractNullSafeComparator {


    @Override
    protected int compareSafe(Film f1, Film f2) {
        String regista1 = f1.getRegista();
        String regista2 = f2.getRegista();
        return regista1.compareToIgnoreCase(regista2);
    }





}