package main.builder;

import javafx.scene.control.Dialog;

import java.awt.*;

public interface DialogBuilder {

    void buildTitolo();

    void buildCampiInsrimento();

    void buildBottoni();

    void buildConvertitore();

    Dialog<?> getProdotto();











}
