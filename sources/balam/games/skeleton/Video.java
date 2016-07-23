package balam.games.skeleton;

//Video.java

/**
 *@verison 1.0
 *@author Angel Eduardo Perez Cruz
 *Balam Software
 *
 *Clase para manipular Video
 **/
 
 public interface Video {
    /**Metodo para reproducir el archivo*/
    public void play();
    /**Metodo para detener la reproduccion*/
    public void stop();
    /**Metodo para pausar la reproduccion*/
    public void pause();
    /**Metodo para ajustar el volumen*/
    public void setVolume( float volume );
    /**@return True, si se esta reproduciendo
     * Metodo bandera para reproduccion*/
    public boolean isPlaying();

 }