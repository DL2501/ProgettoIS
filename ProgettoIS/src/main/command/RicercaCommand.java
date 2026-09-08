package main.command;

import javafx.scene.control.Dialog;
import main.gui.VideotecaFacade;
import main.builder.DialogDirector;
import main.builder.RicercaFilmDialogBuilder;
import main.support.FiltriRicerca;
import main.support.TabellaRisultati;
import main.videoteca.Film;

import java.util.List;

public class RicercaCommand implements Command {

    private VideotecaFacade receiver;
    private TabellaRisultati ricerca;



    public RicercaCommand(VideotecaFacade receiver, TabellaRisultati ricerca) {
        if (receiver == null || ricerca == null)
            throw new IllegalArgumentException("Un Command per la ricerca non può un receiver o dei comandi di visualizzazione dei risultati non definiti");
        this.receiver = receiver;
        this.ricerca = ricerca;
    }




    @Override
    public void esegui() {
        DialogDirector director = new DialogDirector(new RicercaFilmDialogBuilder());
        Dialog<?> dialog = director.buildDialog();
        dialog.showAndWait().ifPresent(risultato -> {
            FiltriRicerca filtri = (FiltriRicerca) risultato;
            List<Film> risultatiRicerca = receiver.ricercaAvanzata(filtri);
            ricerca.visualizzaRisultati(risultatiRicerca);
        });
    }















}
