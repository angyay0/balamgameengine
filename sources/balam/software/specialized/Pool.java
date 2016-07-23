package balam.software.specialized;

//Pool.java

import java.util.ArrayList;
import java.util.List;

/**
 *@verison 1.0
 *@author Angel Eduardo Perez Cruz
 *Balam Software
 *
 *Clase para generar un simulador de collector de basura
 *para reusar eventos de los metodos de interaccion
 **/
 
 public class Pool<Object> {
    /**Interfaz para crear los objetos del tipo <Object> */
    public interface PoolObjectFactory<Object> {
        /**Metodo para generar el objeto del tipo <Objeto>*/
        public Object createObject();
    }
    
    /*Lista para los objetos*/
    private final List<Object> freeObjects;
    /*Generador de nuevos objetos*/
    private final PoolObjectFactory<Object> Factory;
    /*Dimension de la pila*/
    private final int FinalSize;
    
    /**Constructor para dimensionar e inicializar el barredor*/
    public Pool(PoolObjectFactory<Object> factory,int maxObjects){
        Factory = factory;
        FinalSize = maxObjects;
        freeObjects = new ArrayList<Object>(maxObjects);
    }
    
    /**Metodo que genera objetos, liberando el ultimo
     *@return ultimo objeto*/
    public Object newObject(){
        Object object = null;
        
        if(freeObjects.size() == 0)
            object = Factory.createObject();
        else
            object = freeObjects.remove(freeObjects.size()-1);
            
        return object;
    }
    
    /**Metodo para reinsertar el objeto en caso de estar disponible el espacio
     *O lo libera*/
    public void free(Object object){
        if(freeObjects.size() < FinalSize)
            freeObjects.add(object);
    }
    
    
 }