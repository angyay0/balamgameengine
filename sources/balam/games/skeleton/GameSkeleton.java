package balam.games.skeleton;

//Game.java
import android.app.Activity;

/**
 *@verison 1.0
 *@author Angel Eduardo Perez Cruz
 *Balam Software
 *
 *Esqueleto principal de desarrollo de juegos
 **/
 
 public interface GameSkeleton {
    /**@return Input Handler Object
     * Metodo para obtener los eventos de interacion*/
    public Input getInput();
    /**@return FileIO Handler Object
     * Metodo para obtener el acceso a Lectura/Escritura de archivos*/
    public FileIO getFileIO();
    /**@return Media Object
     * Metodo para obtener disposicion del hardware y software para reproducir medios*/
    public Media getMedia();
    /**@return GameGraphics Object
     * Metodo para obtener disposicion de graficar en pantalla*/
    public GameGraphics getGraphics();
    /**@return Screen Object
     * Metodo que regresa la pantalla actual del juego*/
    public Screen getCurrentScreen();
    /**@return Screen Object
     * Metodo que regresa la pantalla actual del juego*/
    public Screen getStartScreen();
    /**Metodo para establecer la escena que se ejecutara*/
    public void setScreen( Screen screen );
    /**@return Activity Object, el hilo principal
     * Metodo para obtener el hilo principal*/
    public Activity getActivity();
 }