package balam.games.skeleton;

import balam.software.specialized.MemoryObject;

//Sound.java

/**
 *@verison 1.0
 *@author Angel Eduardo Perez Cruz
 *Balam Software
 *
 *Clase para manipular sonido
 **/

public interface Sound extends MemoryObject{
    /**Metodo para reproducir un sonido cargado*/
    public void play( float volume );
    /**Metodo para liberar recursos*/
    public void release();
}