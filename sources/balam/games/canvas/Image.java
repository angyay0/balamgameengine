package balam.games.canvas;

//Image.java

import android.graphics.Bitmap;

import balam.games.canvas.CImage;
import balam.games.canvas.Graphics.Format;
import balam.software.specialized.MemoryObject;

/**
 *@verison 1.0
 *@author Angel Eduardo Perez Cruz
 *Balam Software
 *
 *Clase para manipular imagenes
 **/

public class Image implements CImage,MemoryObject {
    public Bitmap bitmap;
    public Format format;
    
    public Image(Bitmap bitmap,Format format){
        this.bitmap = bitmap;
        this.format = format;
    }
    
    /**@return el ancho de la imagen*/
    @Override
    public int getWidth(){
        return bitmap.getWidth();
    }
    
    /**@return el alto de la imagen*/
    @Override
    public int getHeight(){
        return bitmap.getHeight();
    }
    
    /**@retunr el formato de la imagen*/
    @Override
    public Format getFormat(){
        return format;
    }
    
    /**Metodo para liberar el recurso*/
    @Override
    public void release(){
        bitmap.recycle();
    }
}