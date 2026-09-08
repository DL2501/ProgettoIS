package main.support;

import main.videoteca.Film;

public class RisultatoOperazione {

    private OperazioneVideoteca operazione;
    private Film filmCoinvolto;


    public RisultatoOperazione(OperazioneVideoteca operazione, Film filmCoinvolto) {
        if (operazione == null || (operazione == OperazioneVideoteca.MODIFICA && filmCoinvolto == null) || (operazione == OperazioneVideoteca.CANCELLAZIONE && filmCoinvolto != null))
            throw new IllegalArgumentException("La tipologia di operazione svolta sul film non può essere non definita e il valore del film modificato non può essere inconsistente con essa.");
        this.operazione = operazione;
        this.filmCoinvolto = filmCoinvolto;
    }


    public OperazioneVideoteca getOperazione() {
        return operazione;
    }

    public Film getFilmCoinvolto() {
        return filmCoinvolto;
    }






}



