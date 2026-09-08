package main.videoteca;

import java.util.Objects;

public class Film {

    private Integer id;
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


    public Integer getId() {
        return id;
    }

    void setId(Integer nuovoId) {
        if (id != null)
            throw new IllegalStateException("Impossibile modificare l'ID del film una volta inizializzato.");
        id = nuovoId;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String nuovoTitolo) {
        if (nuovoTitolo == null || nuovoTitolo.isBlank())
            throw new IllegalArgumentException("Il valore dell'attributo titolo non può essere non definito.");
        titolo = nuovoTitolo;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String nuovoGenere) {
        if (nuovoGenere == null || nuovoGenere.isBlank())
            throw new IllegalArgumentException("Il valore dell'attributo genere non può essere non definito.");
        genere = nuovoGenere;
    }

    public String getRegista() {
        return regista;
    }

    public void setRegista(String nuovoRegista) {
        if (nuovoRegista == null || nuovoRegista.isBlank())
            throw new IllegalArgumentException("Il valore dell'attributo regista non può essere non definito.");
        regista = nuovoRegista;
    }

    public int getAnnoUscita() {
        return annoUscita;
    }

    public void setAnnoUscita(int nuovoAnnoUscita) {
        if (nuovoAnnoUscita < 1888)
            throw new IllegalArgumentException("Il valore dell'attributo annoUscita non può essere inferiore all'anno di uscita del primi film della storia.");
        annoUscita = nuovoAnnoUscita;
    }

    public int getValutazione() {
        return valutazione;
    }

    public void setValutazione(int nuovaValutazione) {
        if (nuovaValutazione < 1 || nuovaValutazione > 5)
            throw new IllegalArgumentException("Il valore della valutazione deve necessariamente essere compreso tra 1 e 5 stelle.");
        valutazione = nuovaValutazione;
    }

    public StatoVisione getStatoVisione() {
        return statoVisione;
    }

    public void setStatoVisione(StatoVisione nuovoStatoVisione) {
        if (nuovoStatoVisione == null)
            throw new IllegalArgumentException("Il valore dell'attributo statoVisione non può essere non definito.");
        statoVisione = nuovoStatoVisione;
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
        String titoloNorm1 = titolo.trim().replaceAll("\\s+", " ");
        String titoloNorm2 = f.titolo.trim().replaceAll("\\s+", " ");
        String registaNorm1 = regista.trim().replaceAll("\\s+", " ");
        String registaNorm2 = f.regista.trim().replaceAll("\\s+", " ");
        return titoloNorm1.equalsIgnoreCase(titoloNorm2) && registaNorm1.equalsIgnoreCase(registaNorm2) && annoUscita == f.annoUscita;
    }


    @Override
    public int hashCode() {
        String titoloNorm = titolo.trim().replaceAll("\\s+", " ");
        String registaNorm = regista.trim().replaceAll("\\s+", " ");
        return Objects.hash(titoloNorm,registaNorm,annoUscita);
    }







}
