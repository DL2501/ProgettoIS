package main.gui;


import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import main.bridge.FileImplementor;
import main.command.*;
import main.videoteca.Film;
import main.videoteca.StatoVisione;
import main.videoteca.VideotecaVirtuale;

import java.util.List;

public class VideotecaFX  {

    private VideotecaFacade facade;
    private TableView<Film> tabella;
    private BorderPane layoutPrincipale;
    private ToolBar barraOpzioni;
    private Command aggiungiFilm;
    private Command ricerca;


    public VideotecaFX(VideotecaVirtuale videotecaVirtuale, FileImplementor fileImplementor) {
        facade = new VideotecaFacade(videotecaVirtuale, fileImplementor);
        aggiungiFilm = new AggiungiFilmCommand(facade,this::riempiTabella);
        ricerca = new RicercaCommand(facade,this::visualizzaRisultati);
        layoutPrincipale = new BorderPane();
        barraOpzioni = creaBarraOpzioni();
        tabella = creaTabellaFilm();
        layoutPrincipale.setTop(barraOpzioni);
        layoutPrincipale.setCenter(tabella);
        riempiTabella();
    }



    public BorderPane getLayoutPrincipale() {
        return layoutPrincipale;
    }

    public void riempiTabella() {
        tabella.getItems().clear();
        tabella.getItems().addAll(facade.getAllFilm());
    }


    public void aggiornaTabella() {
        tabella.refresh();
    }


    public void visualizzaRisultati(List<Film> risultati) {
        if (risultati == null)
            throw new IllegalArgumentException("Impossibile visulaizzare risultati non definiti");
        tabella.getItems().clear();
        tabella.getItems().addAll(risultati);
    }


    private ToolBar creaBarraOpzioni() {
        Button bottoneAggiunta = new Button("Aggiungi Film");
        Button bottoneModifica = new Button("Modifica Film");
        Button bottoneRicerca = new Button("Ricerca");
        Button bottoneReset = new Button("Reset");
        ComboBox<CriterioOrdinamento> ordinamentoBox = new ComboBox<>();
        ordinamentoBox.setPromptText("Ordina");
        ordinamentoBox.getItems().addAll(CriterioOrdinamento.values());
        ordinamentoBox.setValue(CriterioOrdinamento.NESSUNO);
        bottoneAggiunta.setOnAction(event -> aggiungiFilm.esegui());
        bottoneModifica.setOnAction(event -> {
            Film filmDaModificare = tabella.getSelectionModel().getSelectedItem();
            Command modifica = new ModificaFilmCommand(facade,this::riempiTabella,this::aggiornaTabella,filmDaModificare);
            modifica.esegui();
        });
        bottoneRicerca.setOnAction(event -> ricerca.esegui());
        ordinamentoBox.setOnAction(event -> {
            CriterioOrdinamento criterio = ordinamentoBox.getValue();
            Command ordinamento = new OrdinaCommand(facade,this::visualizzaRisultati,criterio);
            ordinamento.esegui();
        });
        bottoneReset.setOnAction(event -> {
            if (ordinamentoBox.getValue() != CriterioOrdinamento.NESSUNO)
                ordinamentoBox.setValue(CriterioOrdinamento.NESSUNO);
            riempiTabella();
        });
        return new ToolBar(bottoneAggiunta,bottoneModifica,bottoneRicerca,ordinamentoBox,bottoneReset);
    }



    private TableView<Film> creaTabellaFilm() {
        TableView<Film> tabellaFilm = new TableView<>();
        TableColumn<Film,Integer> colonnaId = new TableColumn<>("ID");
        colonnaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colonnaId.setVisible(false);
        TableColumn<Film,String> colonnaTitolo = new TableColumn<>("Titolo");
        colonnaTitolo.setCellValueFactory(new PropertyValueFactory<>("titolo"));
        TableColumn<Film,String> colonnaGenere = new TableColumn<>("Genere");
        colonnaGenere.setCellValueFactory(new PropertyValueFactory<>("genere"));
        TableColumn<Film,String> colonnaRegista = new TableColumn<>("Regista");
        colonnaRegista.setCellValueFactory(new PropertyValueFactory<>("regista"));
        TableColumn<Film,Integer> colonnaAnnoUscita = new TableColumn<>("Anno Uscita");
        colonnaAnnoUscita.setCellValueFactory(new PropertyValueFactory<>("annoUscita"));
        TableColumn<Film,Integer> colonnaValutazione = new TableColumn<>("Valutazione");
        colonnaValutazione.setCellValueFactory(new PropertyValueFactory<>("valutazione"));
        TableColumn<Film,StatoVisione> colonnaStatoVisione = new TableColumn<>("Stato Visione");
        colonnaStatoVisione.setCellValueFactory(new PropertyValueFactory<>("statoVisione"));
        tabellaFilm.getColumns().add(colonnaTitolo);
        tabellaFilm.getColumns().add(colonnaGenere);
        tabellaFilm.getColumns().add(colonnaRegista);
        tabellaFilm.getColumns().add(colonnaAnnoUscita);
        tabellaFilm.getColumns().add(colonnaValutazione);
        tabellaFilm.getColumns().add(colonnaStatoVisione);
        tabellaFilm.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        return tabellaFilm;
    }















}
