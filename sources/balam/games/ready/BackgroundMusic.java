package balam.games.ready;

//BackgroundMusic.java

import java.io.IOException;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.content.res.AssetFileDescriptor;

import balam.games.skeleton.Music;
import balam.software.specialized.MemoryObject;

/**
 *@verison 1.0
 *@author Angel Eduardo Perez Cruz
 *Balam Software
 *
 *Clase para manejar el audio en background
 **/
 
 public class BackgroundMusic implements Music,OnCompletionListener {
    public MediaPlayer mediaPlayer;
    public boolean isPrepared = false;
    
    /**Constructor para cargar y preparar la reproduccion del audio*/
    public BackgroundMusic(AssetFileDescriptor afd){
        mediaPlayer = new MediaPlayer();
        try{
        mediaPlayer.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
        mediaPlayer.prepare();
        isPrepared = true;
        mediaPlayer.setOnCompletionListener(this);
        }catch(Exception e){
            throw new RuntimeException("Error al cargar");
        }
    }
    
    /**@return True, si esta ciclado
     * Metodo bandera para el ciclado*/
    @Override
    public boolean isLooping(){
        return mediaPlayer.isLooping();
    }
    
    /**@return True, si se esta reproduciendo
     * Metodo bandera para reproduccion*/
    @Override
    public boolean isPlaying(){
        return mediaPlayer.isPlaying();
    }
    
    /**@return True, si esta detenido
     * Metodo bandera para la detencion*/
    @Override
    public boolean isStopped(){
        return !isPrepared;
    }
    
    /**Metodo para ciclar o no la reprouccion*/
    @Override
    public void setLooping(boolean looping){
        mediaPlayer.setLooping(looping);
    }
    
    /**Metodo para ajustar el volumen*/
    @Override
    public void setVolume(float volume){
        mediaPlayer.setVolume(volume,volume);
    }
    
    @Override
    public void onCompletion(MediaPlayer mp){
        synchronized(this){
            isPrepared = false;
        }
    }
    
    /**Metodo para detener el archivo*/
    @Override
    public void stop(){
        mediaPlayer.stop();
        synchronized(this){
            isPrepared = false;
        }
    }
    
    /**Metodo para pausar el archivo*/
    @Override
    public void pause(){
        mediaPlayer.pause();
    }
    
    /**Metodo para continuar la preproduccion del archivo*/
    @Override
    public void resume(){
        mediaPlayer.start();
    }
    
    /**Metodo para reproducir el archivo*/
    @Override
    public void play(){
        if(mediaPlayer.isPlaying())
            return;
        
        try{
            synchronized(this){
                if(!isPrepared)
                    mediaPlayer.prepare();
                
                mediaPlayer.start();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**Metodo para Liberar los recursos*/
	@Override
	public void release() {
		if(mediaPlayer.isPlaying())
			mediaPlayer.stop();
		mediaPlayer.release();
		
	}
    
    
 }
