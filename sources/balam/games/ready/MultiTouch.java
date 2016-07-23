package balam.games.ready;

//MultiTouch.java

import java.util.List;
import java.util.ArrayList;

import android.view.View;
import android.view.MotionEvent;

import balam.games.skeleton.Input.TouchEvent;
import balam.games.skeleton.TouchHandler;
import balam.software.specialized.Pool;
import balam.software.specialized.Pool.PoolObjectFactory;

/**
 *@verison 1.0
 *@author Angel Eduardo Perez Cruz
 *Balam Software
 *
 *Clase para llevar a cabo el procesamiento de muchos eventos tactiles
 **/

public class MultiTouch implements TouchHandler {
    public boolean[] touched; //Bandera  para los puntos tactiles
    public int Size; //Cantidad de eventos que procesara
    public int[] tX; //Posiciones en X de los eventos
    public int[] tY; //Posiciones en Y de los eventos
    public float sX,sY; //Dimensiones reales de la pantallna pixeles X,Y
    public Pool<TouchEvent> tePool; //Pila de Eventos Tactiles
    public List<TouchEvent> buffer = new ArrayList<TouchEvent>(); //Buffer para los eventos
    public List<TouchEvent> events = new ArrayList<TouchEvent>(); //Lista de Eventos
    
    /**Constructor con la asignacion de la pantalla y sus dimensiones en pixeles*/
    public MultiTouch(View view,float sx,float sy){
        PoolObjectFactory<TouchEvent> factory = new PoolObjectFactory<TouchEvent>(){
            @Override
            public TouchEvent createObject(){
                return new TouchEvent();
            }
        };//Objeto que servira para producir eventos tacticles, reciclando IDs
        tePool = new Pool<TouchEvent>(factory,100);
        view.setOnTouchListener(this);
        this.sX = sx;
        this.sY = sy;
        this.Size = 100; //Cantidad de eventos soportados
        
        touched = new boolean[Size];
        tX = new int[Size];
        tY = new int[Size];
        
    }
    
    /**Metodo para Codificar la accion, cuando se producen los eventos*/
    @Override
    public boolean onTouch(View v,MotionEvent event){
        synchronized(this){
            TouchEvent tEvent;
            int ACTION = event.getAction() & MotionEvent.ACTION_MASK;
            int idindex = (event.getAction() & MotionEvent.ACTION_POINTER_ID_MASK) >> MotionEvent.ACTION_POINTER_ID_SHIFT;
            int id = event.getPointerId(idindex);
            
            switch(ACTION){
                case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_POINTER_DOWN:
                    tEvent = tePool.newObject();
                    tEvent.type = TouchEvent.TOUCHDOWN;
                    tEvent.ID = id;
                    tEvent.X = tX[id] = (int) (event.getX(idindex) * sX);
                    tEvent.Y = tY[id] = (int) (event.getY(idindex) * sY);
                    touched[id] = true;
                    buffer.add(tEvent);
                    break;
                
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_POINTER_UP:
                    tEvent = tePool.newObject();
                    tEvent.type = TouchEvent.TOUCHUP;
                    tEvent.ID = id;
                    tEvent.X = tX[id] = (int) (event.getX(idindex) * sX);
                    tEvent.Y = tY[id] = (int) (event.getY(idindex) * sY);
                    touched[id] = false;
                    buffer.add(tEvent);
                    break;
                
                case MotionEvent.ACTION_MOVE:
                    for(int i=0;i<event.getPointerCount();i++){
                        idindex = i;
                        id = event.getPointerId(idindex);
                        
                        tEvent = tePool.newObject();
                        tEvent.type = TouchEvent.TOUCHDRAGGED;
                        tEvent.ID = id;
                        tEvent.X = tX[id] = (int) (event.getX(idindex) * sX);
                        tEvent.Y = tY[id] = (int) (event.getY(idindex) * sY);
                        buffer.add(tEvent);
                    }
                    break;
            }
            return true;
        }    
    }
    
    /**@return True, si se esta pulsando la pantalla con el evento de dicho ID
     *Metodo para saber si el evento(ID) esta tocando la pantalla*/
    @Override
    public boolean isTouchDown(int id){
        synchronized(this){
            if( id < 0 || id > 99 )
                return false;
            else
                return touched[id];
        }
    }
    
    /**Getter en X
     *@return valor en X*/
    @Override
    public int getTouchX(int id){
        synchronized(this){
            if( id < 0 || id > 99)
                return 0;
             else
                return tX[id];
        }
    }
    
    /**Getter en Y
     *@return valor en Y*/
    @Override
    public int getTouchY(int id){
        synchronized(this){
            if( id < 0 || id > 99)
                return 0;
            else
                return tY[id];
        }
    }
    
    /**@return List, los eventos tactiles que se generan
     * Metodo para obtener los eventos producidos*/
    @Override
    public List<TouchEvent> getTouchEvents(){
        synchronized(this){
            for(int i=0;i<events.size();i++)
                tePool.free(events.get(i));
            events.clear();
            events.addAll(buffer);
            buffer.clear();
            
            return events;
        }
    }
}