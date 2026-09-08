package main.builder;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import main.support.OperazioneVideoteca;
import main.support.RisultatoOperazione;
import main.videoteca.Film;
import main.videoteca.StatoVisione;

public class ModificaFilmDialogBuilder implements DialogBuilder {


    private Dialog<RisultatoOperazione> dialog;
    private TextField campoTitolo;
    private TextField campoGenere;
    private TextField campoRegista;
    private TextField campoAnnoUscita;
    private TextField campoValutazione;
    private ComboBox<StatoVisione> statoVisioneBox;
    private Film filmDaModificare;
    private ButtonType bottoneApplicaModifica;
    private ButtonType bottoneRimuovi;
    private ButtonType bottoneIndietro;


    public ModificaFilmDialogBuilder(Film filmDaModificare) {
        if (filmDaModificare == null)
            throw new IllegalArgumentException("Impossibile modificare un film non definito.");
        dialog = new Dialog<>();
        this.filmDaModificare = filmDaModificare;
    }


    @Override
    public void buildTitolo() {
        dialog.setTitle("Modifica o Rimuovi Film");
        dialog.setHeaderText("Modifica i 6 attributi del film o rimuovilo dalla videoteca");
    }


    @Override
    public void buildCampiInsrimento() {
        VBox layout = new VBox(10);
        campoTitolo = new TextField();
        campoTitolo.setText(filmDaModificare.getTitolo());
        campoGenere = new TextField();
        campoGenere.setText(filmDaModificare.getGenere());
        campoRegista = new TextField();
        campoRegista.setText(filmDaModificare.getRegista());
        campoAnnoUscita = new TextField();
        campoAnnoUscita.setText(String.valueOf(filmDaModificare.getAnnoUscita()));
        campoValutazione = new TextField();
        campoValutazione.setText(String.valueOf(filmDaModificare.getValutazione()));
        statoVisioneBox = new ComboBox<>();
        statoVisioneBox.getItems().addAll(StatoVisione.values());
        statoVisioneBox.setValue(filmDaModificare.getStatoVisione());
        layout.getChildren().addAll(campoTitolo,campoGenere,campoRegista,campoAnnoUscita,campoValutazione,statoVisioneBox);
        dialog.getDialogPane().setContent(layout);
    }



    @Override
    public void buildBottoni() {
        bottoneApplicaModifica = new ButtonType("Applica Modifica", ButtonBar.ButtonData.OK_DONE);
        bottoneRimuovi = new ButtonType("Rimuovi", ButtonBar.ButtonData.OTHER);
        bottoneIndietro = new ButtonType("Indietro", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(bottoneApplicaModifica,bottoneRimuovi,bottoneIndietro);
    }



    @Override
    public void buildConvertitore() {
        dialog.setResultConverter(bottone -> {
            if (bottone == bottoneRimuovi)
                return new RisultatoOperazione(OperazioneVideoteca.CANCELLAZIONE,null);
            if (bottone == bottoneApplicaModifica) {
                try {
                    filmDaModificare.setTitolo(campoTitolo.getText().trim());
                    filmDaModificare.setGenere(campoGenere.getText().trim());
                    filmDaModificare.setRegista(campoRegista.getText().trim());
                    filmDaModificare.setAnnoUscita(Integer.parseInt(campoAnnoUscita.getText()));
                    filmDaModificare.setValutazione(Integer.parseInt(campoValutazione.getText()));
                    filmDaModificare.setStatoVisione(statoVisioneBox.getValue());
                    return new RisultatoOperazione(OperazioneVideoteca.MODIFICA,filmDaModificare);
                } catch (Exception e) {
                    System.out.println("Errore: Modifica non valida, gli attributi potrbbero avere valori invalidi o non definiti");
                    mostraMessaggioDiErroreFormattazione();
                    return null;
                }
            }
            return null;
        });
    }



    private void mostraMessaggioDiErroreFormattazione() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errore di formattazione");
        alert.setHeaderText("Impossibile modificare il film");
        alert.setContentText("Errore di formattazione negli attributi inseriti");
        alert.showAndWait();
    }




    @Override
    public Dialog<?> getProdotto() {
        return dialog;
    }




}
