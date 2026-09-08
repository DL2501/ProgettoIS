package main.support;

import main.videoteca.StatoVisione;

public class FiltriRicerca {

    private String titolo, genere, regista;
    private Integer annoUscita, valutazione;
    private StatoVisione statoVisione;


    public FiltriRicerca(String titolo, String genere, String regista, Integer annoUscita, Integer valutazione, StatoVisione statoVisione) {
        this.titolo = titolo;
        this.genere = genere;
        this.regista = regista;
        this.annoUscita = annoUscita;
        this.valutazione = valutazione;
        this.statoVisione = statoVisione;
    }


    public String getTitolo() {
        return titolo;
    }

    public String getGenere() {
        return genere;
    }

    public String getRegista() {
        return regista;
    }

    public Integer getAnnoUscita() {
        return annoUscita;
    }

    public Integer getValutazione() {
        return valutazione;
    }

    public StatoVisione getStatoVisione() {
        return statoVisione;
    }
}
