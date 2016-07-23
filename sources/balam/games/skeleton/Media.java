package balam.games.skeleton;

//Media.java

/**
 *@verison 1.0
 *@author Angel Eduardo Perez Cruz
 *Balam Software
 *
 *Clase para manipular objetos multimedia
 **/

public interface Media {
    /**@return un objeto con un sonido MIDI
     * Metodo para cargar y/o crear Efecto de Sonido*/
    public Sound newSound( String fileName );
    /**@return un objeto con un sonido OGG/MP3
     * Metodo para Cargar un archivo de Audio*/
    public Music newMusic( String fileName );
    /**@return un objeto con un video en MP4,AVI
     * Metodo para cargar un Video*/
    public Video newVideo( String fileName );
}