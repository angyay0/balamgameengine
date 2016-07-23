package balam.games.canvas;

//CanvasGraphics.java

import java.io.IOException;
import java.io.InputStream;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;

/**
 *@verison 1.0
 *@author Angel Eduardo Perez Cruz
 *Balam Software
 *
 *Clase para manipular los elementos graficos
 *Con canvas
 **/

public class CanvasGraphics implements Graphics {
    private AssetManager asm;
    private Bitmap fBuffer;
    private Canvas canvas;
    private Paint paint;
    private Rect srcRect = new Rect();
    private Rect dstRect = new Rect();
    
    
    public CanvasGraphics(AssetManager asm,Bitmap fBuffer){
        this.asm = asm;
        this.fBuffer = fBuffer;
        this.canvas = new Canvas(fBuffer);
        this.paint = new Paint();
    }
    
    @Override
    public CImage newCImage( String fileName, Format format ){
        Config config = null;
        if( format == Format.RGB565 )
            config = Config.RGB_565;
        else if( format == Format.ARGB4444)
            config = Config.ARGB_4444;
        else
            config = Config.ARGB_8888;
            
        Options ops = new Options();
        ops.inPreferredConfig = config;
        
        InputStream is = null;
        Bitmap bm = null;
        try{
            is = asm.open(fileName);
            bm = BitmapFactory.decodeStream(is);
            if(bm == null)
                throw new RuntimeException("No se pudo cargar: "+fileName);
        }catch(IOException e){
            throw new RuntimeException("No se pudo cargar: "+fileName);
        }finally{
            if( is != null){
                try{
                    is.close();
                }catch(IOException e){
                }
            }
        }
        
        if(bm.getConfig() == Config.RGB_565)
            format = Format.RGB565;
        else if(bm.getConfig() == Config.ARGB_4444)
            format = Format.ARGB4444;
        else
            format = Format.ARGB8888;
            
        return new Image(bm,format);
        
    }
    
    @Override
    public void clear(int color){
        canvas.drawRGB( (color & 0xff0000) >> 16,(color & 0xff00) >> 8,(color & 0xff));
    }
    
    @Override
    public void drawPixel(int x,int y,int color){
        paint.setColor(color);
        canvas.drawPoint(x,y,paint);
    }
    
    @Override
    public void drawLine( int x1,int y1,int x2,int y2,int color ){
        paint.setColor(color);
        canvas.drawLine(x1,y1,x2,y2,paint);
    }
    
    @Override
    public void drawRect( int x,int y,int w,int h,int color ){
        paint.setColor(color);
        paint.setStyle(Style.FILL);
        canvas.drawRect(x,y,x+w-1,y+h-1,paint);
    }
    
    @Override
    public void drawCImage( CImage image, int x,int y,int imgX,int width,int imgY,int height ){
        srcRect.left = imgX;
        srcRect.top = imgY;
        srcRect.right = imgX+width-1;
        srcRect.bottom = imgY+height-1;
        
        dstRect.left = x;
        dstRect.top = y;
        dstRect.right = x+width-1;
        dstRect.bottom = y+height-1;
        
        canvas.drawBitmap( ((balam.games.canvas.Image) image).bitmap,srcRect,dstRect,null);
    }
    
    @Override
    public void drawCImage( CImage image,int x,int y ){
        canvas.drawBitmap( ((balam.games.canvas.Image) image).bitmap,x,y,null );
    }
    
    @Override
    public int getWidth(){
        return fBuffer.getWidth();
    }
    
    @Override
    public int getHeight(){
        return fBuffer.getHeight();
    }
    
    
}