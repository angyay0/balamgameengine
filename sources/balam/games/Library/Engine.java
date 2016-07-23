package balam.games.Library;
//TouchBoundsChecker.java

import balam.games.skeleton.Input.TouchEvent;
import android.app.Activity;
import android.os.Vibrator;

/**
 * @version 1.0
 * @author Angel Eduardo Perez Cruz
 * Clase con metodos para saber si se pulsa en cierta parte de 
 * la entrada tactil del dispositivo
 * */

public class Engine{

	/**Clase con instanciamiento protegido*/
	private Engine(){
		//Nada que hacer
	}
	
	/**Metodo que revisa si se clickeo en ese espacio
	 * @return True, si se clickeo ahi*/
	public static boolean touchOnBounds(TouchEvent event, int x, int y, int width, int height) {
		if(event.X > x && event.X < x + width - 1 && event.Y > y && event.Y < y + height - 1) 
			return true;
		else
			return false;
	}
	
	/**Metodo para Colocar Una Vibracion controlada*/
	public static boolean vibrateDevice(int millis,Activity activity){
		Vibrator vib;
		vib = (Vibrator) activity.getSystemService(activity.VIBRATOR_SERVICE);
		if( vib == null )
			return false;
		
		vib.vibrate(millis);
		return true;
	}
	
	/**Metodo para revisar colision de objetos
	 * @return True, si detecto colision, False, en caso contrario*/
	public static boolean collideBulletObjects(EngineObject objectA,EngineObject objectB,int side){
		boolean collide = false;
		switch(side){
			case 0: //Right
				if( objectB.X <= objectA.X+objectA.sizeX-1 ){
					if( objectB.Y < objectA.Y+objectA.sizeY && objectB.Y >= objectA.Y )
						collide = true;
				}
				break;
			case 1: //Left
				if( objectB.X+objectB.sizeX-1 >= objectA.X ){
					if( objectB.Y < objectA.Y+objectA.sizeY && objectB.Y >= objectA.Y )
						collide = true;
				}
				break;
			case 2: //Top
				if( objectB.Y+objectB.sizeY >= objectA.Y ){
					if( objectB.X < objectA.X+objectA.sizeX-1 && objectB.X >= objectA.X )
						collide = true;
				}
				break;
			case 3: //Bottom 
				if( objectB.Y <= objectA.Y+objectA.sizeY-1 ){
					if( objectB.X < objectA.X+objectA.sizeX-1 && objectB.X >= objectA.X )
						collide = true;
				}
				break;
		}
		return collide;
	}
	
}
