package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.bridge.CSVImplementor;
import main.bridge.FileImplementor;
import main.gui.VideotecaFX;
import main.videoteca.VideotecaVirtuale;
import main.videoteca.VideotecaVirtualeMap;

public class VideotecaApp extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        VideotecaVirtuale videoteca = new VideotecaVirtualeMap();
        FileImplementor fileImplementor = new CSVImplementor();
        VideotecaFX GUI = new VideotecaFX(videoteca,fileImplementor);
        Scene scene = new Scene(GUI.getLayoutPrincipale(),1000,600);
        primaryStage.setTitle("Videoteca Virtuale");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }



}
