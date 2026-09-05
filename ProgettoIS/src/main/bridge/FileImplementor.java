package main.bridge;

import main.videoteca.Film;

public interface FileImplementor {

    void apriFile();

    void chiudiFile();

    String leggiRiga();

    Film ottieniFilm(String riga);

    void aggiungiFilm(Film f);

    void svuotaFile();















}
