package balam.games.skeleton;

//Screen.java

/**
 *@verison 1.0
 *@author Angel Eduardo Perez Cruz
 *Balam Software
 *
 *Clase para disenhar las escenas de los juegos
 *utilizadas por el esqueleto Game.java
 **/
 
 public abstract class Screen{
    protected final GameSkeleton game;
    
    /**Constructor para mantener el hilo principal del juego*/
    public Screen( GameSkeleton game ){
        this.game = game;
    }
    
    /**Metodo abstracto para actualizar la pantalla en un detonador*/
    public abstract void update( float deltaTime );
    /**Metodo abstracto para mostrar la pantalla en un detonador*/
    public abstract void present( float deltaTime );
    /**Metodo abstracto para pausar la pantalla*/
    public abstract void pause();
    /**Metodo abstracto para regresar de la pausa*/
    public abstract void resume();
    /**Metodo usado para limpiar la RAM*/
    public abstract void release();
 }