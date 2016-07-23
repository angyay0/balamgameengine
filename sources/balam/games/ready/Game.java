package balam.games.ready;

//Video.java

import balam.games.skeleton.*;
import balam.games.canvas.*;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.Window;
import android.view.WindowManager;

/**
 *@verison 1.0
 *@author Angel Eduardo Perez Cruz
 *Balam Software
 *
 *Clase Principal del juego
 **/

public abstract class Game extends Activity implements GameSkeleton{
    public Renderer renderView; //Renderizador GL
    public GameGraphics graphics; // Objeto para Graficar
    public Media media; //Control de Multimedia
    public Input input; //Control de Eventos
    public FileIO fileIO; //Control de Carga/Guardado de Archivos
    public Screen screen; //Pantalla que se encuentra trabajando actualmente
    public WakeLock wakeLock; //Control de Energia para GL-Game
    
    /**Constructor para desarrollar y asignar las funciones*/
    @Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		boolean isLandscape= getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
	
		int bw= isLandscape ? 1920: 1200;
		int bh= isLandscape ? 1200: 1920;
		
		Bitmap bitmapBuffer= Bitmap.createBitmap(bw,bh,Config.RGB_565);
		float scaleX= (float) bw/ getWindowManager().getDefaultDisplay().getWidth();
		float scaleY= (float) bh/ getWindowManager().getDefaultDisplay().getHeight();
		
		renderView = new Renderer(this, bitmapBuffer);
		graphics = new CanvasGraphics(getAssets(), bitmapBuffer);
		fileIO = new FileManager(getAssets());
		media = new MultiMedia(this);
		input = new EventHandler(this, renderView, scaleX, scaleY);
		screen = getStartScreen();
		setContentView(renderView);
		
		PowerManager controlEnergia= (PowerManager) getSystemService(Context.POWER_SERVICE);
		wakeLock= controlEnergia.newWakeLock(PowerManager.FULL_WAKE_LOCK, "GLGame");
	}
    
    @Override
    public void onResume() {
        super.onResume();
        wakeLock.acquire();
        screen.resume();
        renderView.resume();
    }
    
    @Override
    public void onPause() {
        super.onPause();
        wakeLock.release();
        renderView.pause();
        screen.pause();
        if (isFinishing())
            screen.release();
    }
    
    /**@return Input Handler Object
     * Metodo para obtener los eventos de interacion*/
    @Override
    public Input getInput() {
        return input;
    }
    
    /**@return FileIO Handler Object
     * Metodo para obtener el acceso a Lectura/Escritura de archivos*/
    @Override
    public FileIO getFileIO() {
        return fileIO;
    }
    
    /**@return GameGraphics Object
     * Metodo para obtener disposicion de graficar en pantalla*/
    @Override
    public GameGraphics getGraphics() {
        return graphics;
    }
    
    /**@return Media Object
     * Metodo para obtener disposicion del hardware y software para reproducir medios*/
    @Override
    public Media getMedia() {
        return media;
    }

    /**@return Activity Object, el hilo principal
     * Metodo para obtener el hilo principal*/
    @Override
    public Activity getActivity() {
        return this;
    }

    /**Metodo para establecer la escena que se ejecutara*/
    @Override
    public void setScreen(Screen screen) {
        if (screen == null)
            throw new IllegalArgumentException("La pantalla no debe estar vacia");
        
        this.screen.pause();
        this.screen.release();
        screen.resume();
        screen.update(0);
        this.screen = screen;
    }
    
    /**@return Screen Object
     * Metodo que regresa la pantalla actual del juego*/
    @Override
    public Screen getCurrentScreen() {
        return screen;
    }

    
    
}