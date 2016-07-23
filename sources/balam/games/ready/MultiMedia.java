package balam.games.ready;

//Multimedia.java

import java.io.IOException;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.SoundPool;
import android.media.AudioManager;

import balam.games.skeleton.Media;
import balam.games.skeleton.Music;
import balam.games.skeleton.Sound;
import balam.games.skeleton.Video;

/**
 *@verison 1.0
 *@author Angel Eduardo Perez Cruz
 *Balam Software
 *
 *Clase que maneja los archivos a reproducir
 *en background o algun video
 **/
 
public class MultiMedia implements Media {
    public AssetManager asm; //Lector de Archivos
    public SoundPool spool; //Pila de Sonidos MIDI
    public Activity activity; //Hilo de la aplicacion
    
    /**Construtor para configurar el control de volumen
      *e inicializar los objetos necesarios para la clase*/
    public MultiMedia(Activity activity){
        activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        asm = activity.getAssets();
        spool = new SoundPool(20,AudioManager.STREAM_MUSIC,0);
        this.activity = activity;
    }
    
    /**@return un objeto con un sonido MIDI
     * Metodo para cargar y/o crear Efecto de Sonido*/
    @Override
    public Sound newSound(String fileName){
        try{
            AssetFileDescriptor afd = asm.openFd(fileName);
            int IDSound = spool.load(afd,0);
            return new SoundFX(spool,IDSound);
        }catch(IOException e){
            throw new RuntimeException("No se puede cargar el archivo: "+fileName);
        }
    }
    
    /**@return un objeto con un sonido OGG/MP3
     * Metodo para Cargar un archivo de Audio*/
    @Override
    public Music newMusic(String fileName){
        try{
            AssetFileDescriptor afd = asm.openFd(fileName);
            return new BackgroundMusic(afd);
        }catch(IOException e){
            throw new RuntimeException("No se puede cargar el archivo: "+fileName);
        }
    }
    
    /**@return un objeto con un video en MP4,AVI
     * Metodo para cargar un Video*/
    @Override 
    public Video newVideo(String fileName){
     /*   try{
            AssetFileDescriptor afd = asm.openFd(fileName);
            return new VideoPlayer(afd,activity);
        }catch(IOException e){
            throw new RuntimeException("No se puede cargar el archivo: "+fileName);
        }*/return null;
    }
    
    
}