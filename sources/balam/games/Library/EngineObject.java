package balam.games.Library;

public abstract class  EngineObject {
	
	public double X;
	public double Y;
	public int sizeX;
	public int sizeY;
	
	public EngineObject(int x, int y, int width, int height) {
		X = x;
		Y = y;
		sizeX = width;
		sizeY = height;
	}
	
	public void modify(double X,double Y){
		this.X = X;
		this.Y = Y;
	}
}
