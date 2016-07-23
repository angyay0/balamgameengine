package balam.games.Library;

/**
 * @verison 1.0
 * @author Angel E. Perez Cruz
 * 
 * Clase para manipular los estilos de juegos
 * */

public class GameModes {
	private GameModes(){}
	
	/**Opciones de Juego*/
	public static enum Mode{ 
		Easy,Normal,Hard,Expert,Geek,Insane,Hero
	}
	
	/**Cantidad de Jugadores*/
	public static enum Players{
		One,Two,Four,Eight,Twelve,Massive
	}
}
