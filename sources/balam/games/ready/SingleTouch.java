package balam.games.ready;

//SingleTouch.java

import java.util.List;
import java.util.ArrayList;

import android.view.View;
import android.view.MotionEvent;

import balam.games.skeleton.TouchHandler;
import balam.games.skeleton.Input.TouchEvent;
import balam.software.specialized.Pool;
import balam.software.specialized.Pool.PoolObjectFactory;

/**
 *@verison 1.0
 *@author Angel Eduardo Perez Cruz
 *Balam Software
 *
 *Clase para llevar a cabo el procesamiento de un evento tactil
 **/

public class SingleTouch implements TouchHandler {
    public boolean touchDown; //Bandera de control para saber si se esta pulsando
    public int tX,tY; //Posicion X,Y del evento
    public float sX,sY; //Dimension real de la pantalla en pixeles X,Y
    public Pool<TouchEvent> tePool; //Pila de eventos
    public List<TouchEvent> buffer = new ArrayList<TouchEvent>();//Buffer de eventos ocurridos
    public List<TouchEvent> events = new ArrayList<TouchEvent>();//eventos  tactiles
    
    /**Constructor con la asignacion de la pantalla y sus dimensiones en pixeles*/
    public SingleTouch(View view,float sx,float sy){
        PoolObjectFactory<TouchEvent> factory = new PoolObjectFactory<TouchEvent>(){
            @Override
            public TouchEvent createObject(){
                return new TouchEvent();
            }
        };//Objeto que nos servira para producir eventos, con reciclamiento de ID, al ya ser procesados
        tePool = new Pool<TouchEvent>(factory,5);
        view.setOnTouchListener(this);
        this.sX = sx;
        this.sY = sy;
    }
    
    /**Metodo para Codificar la accion de un evento producido*/
    @Override
    public boolean onTouch(View v,MotionEvent event){
        synchronized(this){
            TouchEvent tEvent = tePool.newObject(); //Evento para manejar el evento producido
            switch(event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    tEvent.type = TouchEvent.TOUCHDOWN;
                    touchDown = true;
                    break;
                case MotionEvent.ACTION_MOVE:
                    tEvent.type = TouchEvent.TOUCHDRAGGED;
                    touchDown = true;
                    break;
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_UP:
                    tEvent.type = TouchEvent.TOUCHUP;
                    touchDown = false;
                    break;
            }
            tEvent.X = tX = (int) (event.getX() * sX);/*Getter X*/
            tEvent.Y = tY = (int) (event.getY() * sY);/*Getter Y*/
            buffer.add(tEvent); 
            return true;
        }    
    }
    
    /**@return True, si se esta pulsando la pantalla con el evento de dicho ID
     *Metodo para saber si el evento(ID) esta tocando la pantalla*/
    @Override
    public boolean isTouchDown(int id){
        synchronized(this){
            if( id == 0 )
                return touchDown;
            else
                return false;
        }
    }
    
    /**Getter en X
     *@return valor en X*/
    @Override
    public int getTouchX(int id){
        synchronized(this){
            return tX;
        }
    }
    
    /**Getter en Y
     *@return valor en Y*/
    @Override
    public int getTouchY(int id){
        synchronized(this){
            return tY;
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