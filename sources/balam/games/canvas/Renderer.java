package balam.games.canvas;

//Renderer.java

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import balam.games.ready.Game;

public class Renderer extends SurfaceView implements Runnable{
    public Game game;
    public Bitmap fBuffer;
    public Thread renderThread;
    public SurfaceHolder holder;
    volatile boolean running = false;
    
    public Renderer(Game game,Bitmap fBuffer){
        super(game);
        this.game = game;
        this.fBuffer = fBuffer;
        this.holder = getHolder();
    }
    
    public void resume(){
        running = true;
        renderThread = new Thread(this);
        renderThread.start();
    }
    
    public void run(){
        Rect dstRect = new Rect();
        long startTime = System.nanoTime();
        while( running ){
            if( !holder.getSurface().isValid() ){
                continue;
            }
            float deltaTime = (System.nanoTime()-startTime)/1000000000.0f;
            startTime = System.nanoTime();
            
            game.getCurrentScreen().update(deltaTime);
            game.getCurrentScreen().present(deltaTime);
            
            Canvas canvas = holder.lockCanvas();
            canvas.getClipBounds(dstRect);
            canvas.drawBitmap(fBuffer,null,dstRect,null);
            holder.unlockCanvasAndPost(canvas);
        }
    }
    
    public void pause(){
        running = false;
        while(true){
            try{
                renderThread.join();
                break;
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}