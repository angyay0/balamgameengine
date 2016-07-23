package balam.games.canvas;

//Graphics.java

import balam.games.skeleton.GameGraphics;

/**
 *@verison 1.0
 *@author Angel Eduardo Perez Cruz
 *Balam Software
 *
 *Clase para manipular los elementos graficos
 **/

public interface Graphics extends GameGraphics {
    /**ENUM, que contiene los formatos de Imagen*/
    public static enum Format {
        ARGB8888, ARGB4444, RGB565
    }
    /**@return Una imagen cargada*/
    public CImage newCImage( String fileName, Format format );
    /**Metodo para limpiar la pantalla*/
    public void clear( int color );
    /**Metodo para pintar un pixel de un color*/
    public void drawPixel( int x,int y, int color );
    /**Metodo para dibujar una linea de un color*/
    public void drawLine( int x1,int y1,int x2,int y2,int color );
    /**Metodo para dibujar una figura de 4 lados*/
    public void drawRect( int x,int y,int w,int h,int color );
    /**Metodo para dibujar una imagen, por partes*/
    public void drawCImage( CImage image, int x,int y,int imgX,int width,int imgY,int height );
    /**Metodo para dibujar todfa la imagen*/
    public void drawCImage( CImage image,int x,int y );
    /**@return el ancho de la pantalla*/
    public int getWidth();
    /**@return el alto de la pantalla*/
    public int getHeight();
}
