package balam.games.ready;

//FileManager.java

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.res.AssetManager;
import android.os.Environment;

import balam.games.skeleton.FileIO;

/**
 *@verison 1.0
 *@author Angel Eduardo Perez Cruz
 *Balam Software
 *
 *Clase para manipular archivos en el sistema android
 *implementacion de los metodos heredados de FileIO
 **/
 
 public class FileManager implements FileIO {
    public AssetManager asm; //Directorio interno de la aplicacion
    public String SDPath; //Directorio SD
    public String LocalPath; //Directorio Local
    
    /**Constructor para inicializar los objetos de direccionado*/
    public FileManager( AssetManager asm ){
        this.asm = asm;
        this.SDPath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
        this.LocalPath = Environment.getRootDirectory().getAbsolutePath() +File.separator;   
    }
    
    /**@return InputStream Object, con el dato leido
     * Metodo para leer un archivo en la carpeta Assets*/
    @Override
    public InputStream readAsset( String fileName ) throws IOException {
        return asm.open(fileName);
    }
    
    /**@return InputStream Object, con el dato leido
     * Metodo para Leer un Archivo en External o Local Storage*/
    @Override
    public InputStream readFile( String fileName ) throws IOException {
        if( Environment.getExternalStorageState().equalsIgnoreCase(Environment.MEDIA_MOUNTED_READ_ONLY) || !Environment.getExternalStorageState().equalsIgnoreCase(Environment.MEDIA_MOUNTED) ){
            return new FileInputStream( LocalPath + fileName );
        }
        
        return new FileInputStream( SDPath + fileName );    
    }
    
    /**@return OutputStream Object, con el dato para guardar en la memoria
     * Metodo para Guardar un archivo*/
    @Override
    public OutputStream writeFile( String fileName ) throws IOException {
        if( Environment.getExternalStorageState().equalsIgnoreCase(Environment.MEDIA_MOUNTED_READ_ONLY) || !Environment.getExternalStorageState().equalsIgnoreCase(Environment.MEDIA_MOUNTED) )
            return new FileOutputStream( LocalPath + fileName );
      
        return new FileOutputStream( SDPath + fileName );
    }
    
 }