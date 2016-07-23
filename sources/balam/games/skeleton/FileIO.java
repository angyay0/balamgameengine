package balam.games.skeleton;

//FileIO.java

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *@verison 1.0
 *@author Angel Eduardo Perez Cruz
 *Balam Software
 *
 *Clase para manipular los archivos en el dispositivo
 *de la SD, Memoria Interna o Carpeta Assets en la Aplicacion
 **/

public interface FileIO {
    /**@return InputStream Object, con el dato leido
     * Metodo para leer un archivo en la carpeta Assets*/
    public InputStream readAsset( String fileName ) throws IOException;
    /**@return InputStream Object, con el dato leido
     * Metodo para Leer un Archivo en External o Local Storage*/
    public InputStream readFile( String fileName ) throws IOException;
    /**@return OutputStream Object, con el dato para guardar en la memoria
     * Metodo para Guardar un archivo*/
    public OutputStream writeFile( String fileName ) throws IOException;
}