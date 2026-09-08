package main.command;

import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import main.gui.VideotecaFacade;
import main.builder.DialogDirector;
import main.builder.ModificaFilmDialogBuilder;
import main.support.OperazioneVideoteca;
import main.support.RisultatoOperazione;
import main.videoteca.Film;

public class ModificaFilmCommand implements Command {

    private VideotecaFacade receiver;
    private Runnable ricaricaTabella;
    private Runnable aggiornaTabella;
    private Film filmDaModificare;




    public ModificaFilmCommand(VideotecaFacade receiver, Runnable ricaricaTabella, Runnable aggiornaTabella, Film filmDaModificare) {
        if (receiver == null || ricaricaTabella == null || aggiornaTabella == null || filmDaModificare == null)
            throw new IllegalArgumentException("Un Command di modifica non può avere receiver o comandi aggiuntivi o film da modificare non definiti");
        this.receiver = receiver;
        this.ricaricaTabella = ricaricaTabella;
        this.aggiornaTabella = aggiornaTabella;
        this.filmDaModificare = filmDaModificare;
    }




    @Override
    public void esegui() {
        Integer filmId = filmDaModificare.getId();
        DialogDirector director = new DialogDirector(new ModificaFilmDialogBuilder(filmDaModificare));
        Dialog<?> dialog = director.buildDialog();
        dialog.showAndWait().ifPresent(risultato -> {
            RisultatoOperazione rm = (RisultatoOperazione) risultato;
            OperazioneVideoteca operazione = rm.getOperazione();
            if (operazione == OperazioneVideoteca.CANCELLAZIONE) {
                boolean rimosso = receiver.rimuoviFilm(filmId);
                if (rimosso) {
                    ricaricaTabella.run();
                    System.out.println("Il film è stato rimosso con successo.");
                }
                else {
                    System.out.println("Errore: il film non è stato rimosso alla Videoteca.");
                    mostraMessaggioDiErroreRimozione();
                }
            }
            else {
                Film filmModificato = rm.getFilmCoinvolto();
                boolean modificato = receiver.modificaFilm(filmId,filmModificato);
                if (modificato) {
                    aggiornaTabella.run();
                    System.out.println("Il film è stato aggiornato con successo.");
                }
                else {
                    System.out.println("Errore: il film non è stato aggiornato all'interno della videoteca.");
                    mostraMessaggioDiErroreModifica();
                }
            }
        });
    }





    private void mostraMessaggioDiErroreRimozione() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errore di rimozione");
        alert.setHeaderText("Impossoibile rimuovere il film");
        alert.setContentText("Il film potrebbe essere già stato rimosso dalla videoteca");
        alert.showAndWait();
    }




    private void mostraMessaggioDiErroreModifica() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errore di Modifica");
        alert.setHeaderText("Impossoibile modificare il film");
        alert.setContentText("Il film potrebbe non essere all'interno della videoteca.");
        alert.showAndWait();
    }










}
