package balam.games.canvas;

//CImage.java

import balam.games.canvas.Graphics.Format;
import balam.software.specialized.MemoryObject;


/**
 *@verison 1.0
 *@author Angel Eduardo Perez Cruz
 *Balam Software
 *
 *Clase para manipular imagenes
 **/
 
 public interface CImage extends MemoryObject{
    /**@return el ancho de la imagen*/
    public int getWidth();
    /**@return el alto de la imagen*/
    public int getHeight();
    /**@retunr el formato de la imagen*/
    public Format getFormat();
    /**Metodo para liberar el recurso*/
    public void release();
 }