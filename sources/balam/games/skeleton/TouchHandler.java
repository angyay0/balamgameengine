package balam.games.skeleton;

//TouchHandler.java

import java.util.List;

import android.view.View.OnTouchListener;

import balam.games.skeleton.Input.TouchEvent;

/**
 *@verison 1.0
 *@author Angel Eduardo Perez Cruz
 *Balam Software
 *
 *Clase para Controlar los eventos tactiles
 **/
 
 public interface TouchHandler extends OnTouchListener {
     /**@return True, si se esta pulsando la pantalla con el evento de dicho ID
      *Metodo para saber si el evento(ID) esta tocando la pantalla*/
    public boolean isTouchDown(int id);
    /**Getter en X
      *@return valor en X*/
    public int getTouchX(int id);
    /**Getter en Y
      *@return valor en Y*/
    public int getTouchY(int id);
    /**@return List, los eventos tactiles que se generan
     * Metodo para obtener los eventos producidos*/
	public List<TouchEvent> getTouchEvents();
 }
 