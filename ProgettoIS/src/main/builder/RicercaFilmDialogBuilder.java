package main.builder;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import main.support.FiltriRicerca;
import main.videoteca.StatoVisione;

public class RicercaFilmDialogBuilder implements DialogBuilder {


    private Dialog<FiltriRicerca> dialog;
    private TextField campoTitolo;
    private TextField campoGenere;
    private TextField campoRegista;
    private TextField campoAnnoUscita;
    private TextField campoValutazione;
    private ComboBox<StatoVisione> statoVisioneBox;
    private ButtonType bottoneRicerca;
    private ButtonType bottoneIndietro;


    public RicercaFilmDialogBuilder() {
        dialog = new Dialog<>();
    }


    @Override
    public void buildTitolo() {
        dialog.setTitle("Ricerca Film");
        dialog.setHeaderText("Filtra per i 6 attributi (lascia vuoto per ignorare)");
    }



    @Override
    public void buildCampiInsrimento() {
        VBox layout = new VBox(10);
        campoTitolo = new TextField();
        campoTitolo.setPromptText("Titolo");
        campoGenere = new TextField();
        campoGenere.setPromptText("Genere");
        campoRegista = new TextField();
        campoRegista.setPromptText("Regista");
        campoAnnoUscita = new TextField();
        campoAnnoUscita.setPromptText("Anno Uscita");
        campoValutazione = new TextField();
        campoValutazione.setPromptText("Valutazione (1-5)");
        statoVisioneBox = new ComboBox<>();
        statoVisioneBox.getItems().add(null);
        statoVisioneBox.getItems().addAll(StatoVisione.values());
        statoVisioneBox.setPromptText("Stato Visione");
        layout.getChildren().addAll(campoTitolo,campoGenere,campoRegista,campoAnnoUscita,campoValutazione,statoVisioneBox);
        dialog.getDialogPane().setContent(layout);
    }


    @Override
    public void buildBottoni() {
        bottoneRicerca = new ButtonType("Cerca", ButtonBar.ButtonData.OK_DONE);
        bottoneIndietro = new ButtonType("Indietro", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(bottoneRicerca,bottoneIndietro);
    }



    @Override
    public void buildConvertitore() {
        dialog.setResultConverter(bottone -> {
            if (bottone == bottoneRicerca) {
                try {
                    String titolo = campoTitolo.getText().trim();
                    String genere = campoGenere.getText().trim();
                    String regista = campoRegista.getText().trim();
                    Integer annoUscita = campoAnnoUscita.getText().isEmpty() ? null : Integer.parseInt(campoAnnoUscita.getText());
                    Integer valutazione = campoValutazione.getText().isEmpty() ? null : Integer.parseInt(campoValutazione.getText());
                    StatoVisione statoVisione = statoVisioneBox.getValue();
                    return new FiltriRicerca(titolo,genere,regista,annoUscita,valutazione,statoVisione);
                } catch (Exception e) {
                    System.out.println("Errore: conversione fallita");
                    return null;
                }
            }
            return null;
        });
    }



    @Override
    public Dialog<?> getProdotto() {
        return dialog;
    }



}
