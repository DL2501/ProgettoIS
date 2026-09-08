package main.command;

import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import main.gui.VideotecaFacade;
import main.builder.AggiungiFilmDialogBuilder;
import main.builder.DialogDirector;
import main.videoteca.Film;

public class AggiungiFilmCommand implements Command {

    private VideotecaFacade receiver;
    private Runnable ricaricaTabella;


    public AggiungiFilmCommand(VideotecaFacade receiver, Runnable aggiornaTabella) {
        if (receiver == null || aggiornaTabella == null)
            throw new IllegalArgumentException("Un Command non può avere un receiver o un altro comando da eseguire non definiti");
        this.receiver = receiver;
        this.ricaricaTabella = aggiornaTabella;
    }


    @Override
    public void esegui() {
        DialogDirector director = new DialogDirector(new AggiungiFilmDialogBuilder());
        Dialog<?> dialog = director.buildDialog();
        dialog.showAndWait().ifPresent(risultato -> {
            Film f = (Film) risultato;
            boolean aggiunto = receiver.aggiungiFilm(f);
            if (aggiunto) {
                ricaricaTabella.run();
                System.out.println("Il film è stato aggiunto con successo.");
            }
            else {
                System.out.println("Errore: il film non è stato aggiunto alla Videoteca.");
                mostraMessaggioDiErroreInserimento();
            }
        });
    }



    private void mostraMessaggioDiErroreInserimento() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errore di inserimento");
        alert.setHeaderText("Impossibile aggiungere il film");
        alert.setContentText("Il film potrebbe essere già presente nella videoteca");
        alert.showAndWait();
    }






}
