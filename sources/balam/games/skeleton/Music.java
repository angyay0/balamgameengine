package balam.games.skeleton;

import balam.software.specialized.MemoryObject;

//Music.java

/**
 *@verison 1.0
 *@author Angel Eduardo Perez Cruz
 *Balam Software
 *
 *Clase para manipular audio OGG/MP3
 **/
 
 public interface Music extends MemoryObject{
    /**Metodo para reproducir el archivo*/
    public void play();
    /**Metodo para detener la reproduccion*/
    public void stop();
    /**Metodo para pausar la reproduccion*/
    public void pause();
    /**Metodo para reanudar la reproduccion*/
    public void resume();
    /**Metodo para ciclar o no la reprouccion*/
    public void setLooping( boolean looping );
    /**Metodo para ajustar el volumen*/
    public void setVolume( float volume );
    /**@return True, si se esta reproduciendo
     * Metodo bandera para reproduccion*/
    public boolean isPlaying();
    /**@return True, si esta detenido
     * Metodo bandera para la detencion*/
    public boolean isStopped();
    /**@return True, si esta ciclado
     * Metodo bandera para el ciclado*/
    public boolean isLooping();
    /**Metodod para liberar espacio*/
    public void release();
 }