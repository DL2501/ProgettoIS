package main.builder;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import main.videoteca.Film;
import main.videoteca.StatoVisione;

public class AggiungiFilmDialogBuilder implements DialogBuilder {

    private Dialog<Film> dialog;
    private TextField campoTitolo;
    private TextField campoGenere;
    private TextField campoRegista;
    private TextField campoAnnoUscita;
    private TextField campoValutazione;
    private ComboBox<StatoVisione> statoVisioneBox;
    private ButtonType bottoneAggiungi;
    private ButtonType bottoneIndietro;



    public AggiungiFilmDialogBuilder() {
        dialog = new Dialog<>();
    }



    @Override
    public void buildTitolo() {
        dialog.setTitle("Aggiungi Film");
        dialog.setHeaderText("Inserisci i 6 attributi richiesti");
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
        statoVisioneBox.getItems().addAll(StatoVisione.values());
        statoVisioneBox.setPromptText("Stato Visione");
        layout.getChildren().addAll(campoTitolo,campoGenere,campoRegista,campoAnnoUscita,campoValutazione,statoVisioneBox);
        dialog.getDialogPane().setContent(layout);
    }



    @Override
    public void buildBottoni() {
        bottoneAggiungi = new ButtonType("Aggiungi", ButtonBar.ButtonData.OK_DONE);
        bottoneIndietro = new ButtonType("Indietro", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(bottoneAggiungi,bottoneIndietro);
    }


    @Override
    public void buildConvertitore() {
        dialog.setResultConverter(bottone -> {
            if (bottone == bottoneAggiungi) {
                try {
                    String titolo = campoTitolo.getText().trim();
                    String genere = campoGenere.getText().trim();
                    String regista = campoRegista.getText().trim();
                    int annoUscita = Integer.parseInt(campoAnnoUscita.getText());
                    int valuatzione = Integer.parseInt(campoValutazione.getText());
                    StatoVisione statoVisione = statoVisioneBox.getValue();
                    return new Film(titolo,genere,regista,annoUscita,valuatzione,statoVisione);
                } catch (Exception e) {
                    System.out.println("Errore: conversione fallita");
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
        alert.setHeaderText("Impossibile aggiungere il film");
        alert.setContentText("Errore di formattazione negli attributi inseriti");
        alert.showAndWait();
    }




    @Override
    public Dialog<?> getProdotto() {
        return dialog;
    }





}
