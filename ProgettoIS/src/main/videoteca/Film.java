package main.videoteca;

import java.util.Objects;

public class Film {

    private String titolo, genere, regista;
    private int annoUscita, valutazione;
    private StatoVisione statoVisione;



    public Film(String titolo, String genere, String regista, int annoUscita, int valutazione, StatoVisione statoVisione) {
        if (titolo == null || genere == null || regista == null || statoVisione == null)
            throw new IllegalArgumentException("Gli attributi titolo, genere, regista e statoVisione non possono essere null.");
        if (titolo.isBlank() || genere.isBlank() || regista.isBlank())
            throw new IllegalArgumentException("I valori delle stringhe di testo di titolo, genere e regista non possono contenere solo spazi vuoti.");
        if (annoUscita < 1888 || valutazione < 1 || valutazione > 5)
            throw new IllegalArgumentException("I valori degli attributi annoUscita e valutazione non possono essere inconsistenti o fuori dai range prefissati.");
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

    public void setTitolo(String titolo) {
        if (titolo == null || titolo.isBlank())
            throw new IllegalArgumentException("Il valore dell'attributo titolo non può essere non definito.");
        this.titolo = titolo;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        if (genere == null || genere.isBlank())
            throw new IllegalArgumentException("Il valore dell'attributo genere non può essere non definito.");
        this.genere = genere;
    }

    public String getRegista() {
        return regista;
    }

    public void setRegista(String regista) {
        if (regista == null || regista.isBlank())
            throw new IllegalArgumentException("Il valore dell'attributo regista non può essere non definito.");
        this.regista = regista;
    }

    public int getAnnoUscita() {
        return annoUscita;
    }

    public void setAnnoUscita(int annoUscita) {
        if (annoUscita < 1888)
            throw new IllegalArgumentException("Il valore dell'attributo annoUscita non può essere inferiore all'anno di uscita del primi film della storia.");
        this.annoUscita = annoUscita;
    }

    public int getValutazione() {
        return valutazione;
    }

    public void setValutazione(int valutazione) {
        if (valutazione < 1 || valutazione > 5)
            throw new IllegalArgumentException("Il valore della valutazione deve necessariamente essere compreso tra 1 e 5 stelle.");
        this.valutazione = valutazione;
    }

    public StatoVisione getStatoVisione() {
        return statoVisione;
    }

    public void setStatoVisione(StatoVisione statoVisione) {
        if (statoVisione == null)
            throw new IllegalArgumentException("Il valore dell'attributo statoVisione non può essere non definito.");
        this.statoVisione = statoVisione;
    }


    @Override
    public String toString() {
        return "Film{" +
                "titolo='" + titolo + '\'' +
                ", genere='" + genere + '\'' +
                ", regista='" + regista + '\'' +
                ", annoUscita=" + annoUscita +
                ", valutazione=" + valutazione +
                ", statoVisione=" + statoVisione +
                '}';
    }



    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Film f))
            return false;
        if (o == this)
            return true;
        return titolo.equals(f.titolo) && genere.equals(f.genere) && regista.equals(f.regista) && annoUscita == f.annoUscita;
    }


    @Override
    public int hashCode() {
        return Objects.hash(titolo,genere,regista,annoUscita);
    }







}
