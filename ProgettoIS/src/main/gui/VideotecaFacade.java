package main.gui;

import main.bridge.FileImplementor;
import main.bridge.VideotecaAbstraction;
import main.composite.*;
import main.strategy.*;
import main.support.FiltriRicerca;
import main.videoteca.Film;
import main.videoteca.StatoVisione;
import main.videoteca.VideotecaVirtuale;

import java.util.ArrayList;
import java.util.List;

public class VideotecaFacade {

    private VideotecaAbstraction videotecaAbs;
    private RicercaComponent ricerca;
    private OrdinamentoContext context;


    public VideotecaFacade(VideotecaVirtuale videotecaVirtuale, FileImplementor fileImplementor) {
        if (videotecaVirtuale == null || fileImplementor == null)
            throw new IllegalArgumentException("Videoteca virtuale e file implementor non possono essere valori non definiti.");
        videotecaAbs = new VideotecaAbstraction(fileImplementor,videotecaVirtuale);
    }


    public List<Film> ricercaAvanzata(FiltriRicerca filtri) {
        List<RicercaComponent> listaFiltriRicerca = new ArrayList<>();
        String titolo = filtri.getTitolo();
        if (titolo != null && !(titolo.isBlank())) {
            RicercaTitoloComponent rt = new RicercaTitoloComponent(titolo);
            listaFiltriRicerca.add(rt);
        }
        String genere = filtri.getGenere();
        if (genere != null && !(genere.isBlank())) {
            RicercaGenereComponent rg = new RicercaGenereComponent(genere);
            listaFiltriRicerca.add(rg);
        }
        String regista = filtri.getRegista();
        if (regista != null && !(regista.isBlank())) {
            RicercaRegistaComponent rr = new RicercaRegistaComponent(regista);
            listaFiltriRicerca.add(rr);
        }
        Integer annoUscita = filtri.getAnnoUscita();
        if (annoUscita != null) {
            RicercaAnnoUscitaComponent rau = new RicercaAnnoUscitaComponent(annoUscita);
            listaFiltriRicerca.add(rau);
        }
        Integer valuatzione = filtri.getValutazione();
        if (valuatzione != null) {
            RicercaValutazioneComponent rv = new RicercaValutazioneComponent(valuatzione);
            listaFiltriRicerca.add(rv);
        }
        StatoVisione statoVisione = filtri.getStatoVisione();
        if (statoVisione != null) {
            RicercaStatoVisioneComponent rsv = new RicercaStatoVisioneComponent(statoVisione);
            listaFiltriRicerca.add(rsv);
        }
        ricerca = new RicercaComposite(listaFiltriRicerca);
        return ricerca.filtra(videotecaAbs.getAllFilm());
    }


    public List<Film> ordinaVideoteca(CriterioOrdinamento criterio) {
        context = new OrdinamentoContext(videotecaAbs.getAllFilm());
        if (criterio != null && criterio != CriterioOrdinamento.NESSUNO) {
            switch (criterio) {
                case TITOLO:
                    OrdinamentoTitolo ot = new OrdinamentoTitolo();
                    context.setStrategy(ot);
                    break;
                case GENERE:
                    OrdinamentoGenere og = new OrdinamentoGenere();
                    context.setStrategy(og);
                    break;
                case REGISTA:
                    OrdinamentoRegista or = new OrdinamentoRegista();
                    context.setStrategy(or);
                    break;
                case ANNO_USCITA:
                    OrdinamentoAnnoUscita oau = new OrdinamentoAnnoUscita();
                    context.setStrategy(oau);
                    break;
                case VALUTAZIONE:
                    OrdinamentoValutazione ov = new OrdinamentoValutazione();
                    context.setStrategy(ov);
                    break;
                case STATO_VISIONE:
                    OrdinamentoStatoVisione osv = new OrdinamentoStatoVisione();
                    context.setStrategy(osv);
                    break;
            }
        }
        return context.ordina();
    }


    public boolean aggiungiFilm(Film f) {
        return videotecaAbs.aggiungiFilm(f);
    }

    public boolean rimuoviFilm(Integer filmId) {
        return videotecaAbs.rimuoviFilm(filmId);
    }

    public boolean modificaFilm(Integer filmId, Film nuovoFilm) {
        return videotecaAbs.modificaFilm(filmId,nuovoFilm);
    }

    public Film getFilm(Integer filmId) {
        return videotecaAbs.getFilm(filmId);
    }

    public List<Film> getAllFilm() {
        return videotecaAbs.getAllFilm();
    }








}
