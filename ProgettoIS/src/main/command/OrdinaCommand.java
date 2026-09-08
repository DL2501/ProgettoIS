package main.command;

import main.gui.CriterioOrdinamento;
import main.gui.VideotecaFacade;
import main.support.TabellaRisultati;
import main.videoteca.Film;

import java.util.List;

public class OrdinaCommand implements Command {

    private VideotecaFacade receiver;
    private TabellaRisultati ordinamento;
    private CriterioOrdinamento criterio;




    public OrdinaCommand(VideotecaFacade receiver, TabellaRisultati ordinamento, CriterioOrdinamento criterio) {
        if (receiver == null || ordinamento == null)
            throw new IllegalArgumentException("In un Command di ordinamento il receiver o il comando per la visualizzazione dei risultati non possono essere non definiti");
        this.receiver = receiver;
        this.ordinamento = ordinamento;
        this.criterio = criterio;
    }





    @Override
    public void esegui() {
        List<Film> filmOrdinati = receiver.ordinaVideoteca(criterio);
        ordinamento.visualizzaRisultati(filmOrdinati);
    }









}
