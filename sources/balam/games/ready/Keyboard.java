package balam.games.ready;

//Keyboard.java

import java.util.List;
import java.util.ArrayList;

import android.view.View;
import android.view.View.OnKeyListener;

import balam.games.skeleton.Input.KeyEvent;
import balam.software.specialized.Pool;
import balam.software.specialized.Pool.PoolObjectFactory;

/**
 *@verison 1.0
 *@author Angel Eduardo Perez Cruz
 *Balam Software
 *
 *Clase para controlar los eventos el teclado fisico y virtual de android
 **/


public class Keyboard implements OnKeyListener {
    //Indicador de las 128 teclas
    public boolean[] pressedKeys = new boolean[128];
    public Pool<KeyEvent> keyEventPool; //Pila de eventos
    public List<KeyEvent> buffer = new ArrayList<KeyEvent>(); //Buffer de teclas
    public List<KeyEvent> events = new ArrayList<KeyEvent>(); //eventos
    
    /**Contructor en donde se crear la pila y el generador de objetos
      *y se coloca a la escucha de eventos del teclado*/
    public Keyboard(View view){
        PoolObjectFactory<KeyEvent> factory = new PoolObjectFactory<KeyEvent>(){
            @Override
            public KeyEvent createObject(){
                return new KeyEvent();
            }
        };
        
        keyEventPool = new Pool<KeyEvent>(factory,50);
        view.setOnKeyListener(this);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
    }
    
    /**Metodo empleado para los eventos del teclado*/
    @Override
    public boolean onKey(View v,int keyCode,android.view.KeyEvent event){
        if( event.getAction() == android.view.KeyEvent.ACTION_MULTIPLE)
            return false;
        
        synchronized(this){
            KeyEvent keyEvent = keyEventPool.newObject();
            keyEvent.keyCode = keyCode;
            keyEvent.keyChar = (char) event.getUnicodeChar();
            if( event.getAction() == android.view.KeyEvent.ACTION_DOWN){
                keyEvent.type = KeyEvent.KeyDown;
                if( keyCode > 0 && keyCode < 127 )
                    pressedKeys[keyCode] = true;
            }
            if( event.getAction() == android.view.KeyEvent.ACTION_UP){
                keyEvent.type = KeyEvent.KeyUp;
                if( keyCode > 0 && keyCode < 127 )
                    pressedKeys[keyCode] = false;
            }
        }
        return false;
    }
    
    /**Comprueba si una tecla con el codigo X se pulso
      *@return True, si se esta pulsando la tecla(keyCode)*/
    public boolean isKeyPressed(int keyCode){
        if(keyCode < 0 || keyCode > 127 )
            return false;
        
        return pressedKeys[keyCode];
    }
    
    /**@return List, con eventos producidos en el teclado
     * Metodo para mostrar los eventos*/
    public List<KeyEvent> getKeyEvents(){
        synchronized(this){
            for(int i=0;i<events.size();i++)
                keyEventPool.free(events.get(i));
            events.clear();
            events.addAll(buffer);
            buffer.clear();
            return events;
        }
    }
    
    
}