package balam.games.ready;

//EventHandler.java

import java.util.List;

import android.content.Context;
import android.view.View;

import balam.games.skeleton.Input;
import balam.games.skeleton.TouchHandler;
import balam.games.ready.SingleTouch;
import balam.games.ready.MultiTouch;
import balam.games.ready.Keyboard;
import balam.games.ready.Accelerometer;
import android.os.Build.VERSION;

/**
 *@verison 1.0
 *@author Angel Eduardo Perez Cruz
 *Balam Software
 *
 *Clase para manipular eventos del dispositivo
 *de los sensores, teclado y pantalla
 **/
 

public class EventHandler implements Input {
    private Accelerometer acel; //Objeto de control de Acelerometro
    private Keyboard keys; //Objeto de control del Teclado
    private TouchHandler touch; //Objeto de control del Touch
    
    /**Constructor con asignacion del dispositivo, y dimensiones en puxeles*/
    public EventHandler(Context context,View view,float sx,float sy){
        acel = new Accelerometer(context);
        keys = new Keyboard(view);
        /*if( Integer.parseInt(VERSION.SDK) < 5 )
            touch = new SingleTouch(view,sx,sy);
        else*/
        touch = new MultiTouch(view,sx,sy);
    }
    
    /**@return True , si se esta pulsando esa tecla
     * Metodo para saber si se pulsa una tecla*/
    @Override
    public boolean isKeyPressed(int keyCode){
        return keys.isKeyPressed(keyCode);
    }
    
    /**@return True, si esta en ese punto tocando la pantalla
     * Metodo para saber si se esta pulsando la pantalla*/
    @Override
    public boolean isTouchDown(int ID){
        return touch.isTouchDown(ID);
    }
    
    /**@return la posicion en X, del evento con el ID
     * Metodo para Obtener la posicion en X del evento con dicho ID*/
    @Override
    public int getTouchX(int ID){
        return touch.getTouchX(ID);
    }
    
    /**@return la posicion en Y, del evento con el ID
     * Metodo para Obetener la posicion en Y del Evento con dicho ID*/
    @Override
    public int getTouchY(int ID){
        return touch.getTouchY(ID);
    }
    
    /**@return el valor de la aceleracion en X
     * Metodo para obtener el valor del eje X del Acelerometro*/
    @Override
    public float getAccelX(){
        return acel.gX();
    }
    
    /**@return el valor de la aceleracion en Y
     * Metodo para obtener le valor del eje Y del Acelerometro*/
    @Override
    public float getAccelY(){
        return acel.gY();
    }
    
    /**@return el valor de la aceleracion en Z
     * Metodo para obtener el valor del eje Z del Acelerometro*/
    @Override
    public float getAccelZ(){
        return acel.gZ();
    }
    
    /**@return List, lista de eventos producidos en la entrada tactil
     * Metodo para obtener los eventos que se han producido*/
    @Override
    public List<TouchEvent> getTouchEvents(){
        return touch.getTouchEvents();
    }
    
    /**@return List, lista de enventos producidos en el teclado
     * Metodo para obtener los eventos que se han producido*/
    @Override
    public List<KeyEvent> getKeyEvents(){
        return keys.getKeyEvents();
    }
    
    /**@return numero si esta girando,0 sigue igual
     * Metodo para saber a que lado se encuentra actualmente orientada la pantalla*/
    @Override
    public int isTurningTo(){
        return 0;
    }
    
    /**@return Tienpo del dia, morning, noon, night,midnight
     * Metodo para saber la hora del dia*/
    @Override
    public int timeOfDay(){
        return 0;
    }
    
}