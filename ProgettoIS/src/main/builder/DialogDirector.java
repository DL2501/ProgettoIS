package main.builder;

import javafx.scene.control.Dialog;

public class DialogDirector {

    private DialogBuilder builder;


    public DialogDirector(DialogBuilder builder) {
        if (builder == null)
            throw new IllegalArgumentException("Il builder non può avere un valore non definito.");
        this.builder = builder;
    }


    public Dialog<?> buildDialog() {
        builder.buildTitolo();
        builder.buildBottoni();
        builder.buildCampiInsrimento();
        builder.buildConvertitore();
        return builder.getProdotto();
    }












}
