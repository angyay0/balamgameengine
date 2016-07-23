package balam.games.ready;

//SoundFX.java

import android.media.SoundPool;

import balam.games.skeleton.Sound;
import balam.software.specialized.MemoryObject;

/**
 *@verison 1.0
 *@author Angel Eduardo Perez Cruz
 *Balam Software
 *
 *Clase para llevar a cabo la reproduccion de Efectos
 *de sonido para las aplicaciones o juegos
 **/
 
 public class SoundFX implements Sound {
    public int IDSound;
    public SoundPool spool;
    
    /**Constructor en donde se inicializan los objetos
     *con el archivo del usuario*/
     public SoundFX(SoundPool spool,int IDsound){
        this.spool = spool;
        IDSound = IDsound;
     }
     
     /**Metodo para reproducir el sonido cargado*/
     public void play(float volume){
        spool.play(IDSound,volume,volume,0,0,1);
     }
     
     /**Metodo para liberar recursos*/
     public void release(){
        spool.unload(IDSound);
     }
 }