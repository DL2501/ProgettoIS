package main.composite;

import main.videoteca.Film;

public class RicercaRegistaComponent extends AbstractRicercaComponent {

    private String regista;


    public RicercaRegistaComponent(String regista) {
        if (regista == null || regista.isBlank())
            throw new IllegalArgumentException("Impossibile filtrare per un regista non definito.");
        this.regista = regista.toLowerCase();
    }


    @Override
    protected boolean valido(Film f) {
        return f.getRegista().toLowerCase().contains(regista);
    }



}
