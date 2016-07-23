 package balam.games.skeleton;

//Input.java

import java.util.List;

/**
 *@verison 1.0
 *@author Angel Eduardo Perez Cruz
 *Balam Software
 *
 *Clase para manipular eventos del dispositivo
 *de los sensores, teclado y pantalla
 **/
 
 public interface Input {
 
    /**Clase interna de un evento producido por una tecla*/
    public class KeyEvent {
        /**Constante para tecla pulsada*/
        public static final int KeyDown = 0;
        /**Constante para tecla libre/no_pulsada*/
        public static final int KeyUp = 1;
        
        /**Identifica el tipo de evento, de las constantes anteriores*/
        public int type;
        /**Identifica la el codigo de la tecla*/
        public int keyCode;
        /**Identifica el caracter de la tecla*/
        public int keyChar;
    }
    
    /**Clase interna de un evento tactil de la pantalla*/
    public class TouchEvent {
        /**Constante para la pulsacion en la pantalla*/
        public static final int TOUCHDOWN = 0;
        /**Constante para la liberacion de un punto*/
        public static final int TOUCHUP = 1;
        /**Constante para el movimiento de un punto*/
        public static final int TOUCHDRAGGED = 2;
        
        /**Tipo del evento, de las constantes anteriores*/
        public int type;
        /**Coordenada en X*/
        public int X;
        /**Coordenada en Y*/
        public int Y;
        /**ID de evento*/
        public int ID;
    }
    
    /**@return True , si se esta pulsando esa tecla
     * Metodo para saber si se pulsa una tecla*/
    public boolean isKeyPressed( int keyCode );
    /**@return True, si esta en ese punto tocando la pantalla
     * Metodo para saber si se esta pulsando la pantalla*/
    public boolean isTouchDown( int ID );
    /**@return numero si esta girando,0 sigue igual
     * Metodo para saber a que lado se encuentra actualmente orientada la pantalla*/
    public int isTurningTo();
    /**@return Tienpo del dia, morning, noon, night,midnight
     * Metodo para saber la hora del dia*/
    public int timeOfDay();
    /**@return la posicion en X, del evento con el ID
     * Metodo para Obtener la posicion en X del evento con dicho ID*/
    public int getTouchX( int ID );
    /**@return la posicion en Y, del evento con el ID
     * Metodo para Obetener la posicion en Y del Evento con dicho ID*/
    public int getTouchY( int ID );
    /**@return el valor de la aceleracion en X
     * Metodo para obtener el valor del eje X del Acelerometro*/
    public float getAccelX();
    /**@return el valor de la aceleracion en Y
     * Metodo para obtener le valor del eje Y del Acelerometro*/
    public float getAccelY();
    /**@return el valor de la aceleracion en Z
     * Metodo para obtener el valor del eje Z del Acelerometro*/
    public float getAccelZ();
    /**@return List, lista de enventos producidos en el teclado
     * Metodo para obtener los eventos que se han producido*/
    public List<KeyEvent> getKeyEvents();
    /**@return List, lista de eventos producidos en la entrada tactil
     * Metodo para obtener los eventos que se han producido*/
    public List<TouchEvent> getTouchEvents();
 }