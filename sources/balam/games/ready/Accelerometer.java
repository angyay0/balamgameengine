package balam.games.ready;

//Accelerometer.java

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.hardware.SensorEventListener;

/**
 *@verison 1.0
 *@author Angel Eduardo Perez Cruz
 *Balam Software
 *
 *Clase para manipular el acelerometro
 **/
 
 public class Accelerometer implements SensorEventListener {
    
    //Variables en los 3 ejes del acelerometro
    public float Ax,Ay,Az;
    
    /**Constructor en donde se genera el control del sensor si lo hay*/
    public Accelerometer(Context context){
        SensorManager sm = (SensorManager) context.getSystemService( context.SENSOR_SERVICE );
        
        if( sm.getSensorList( Sensor.TYPE_ACCELEROMETER).size() != 0){
            Sensor accel = sm.getSensorList( Sensor.TYPE_ACCELEROMETER  ).get(0);
            
            sm.registerListener(this,accel,SensorManager.SENSOR_DELAY_GAME);
        }
    }
    
    @Override
    public void onAccuracyChanged(Sensor sensor,int accuracy){ ; }
    
    /**Metodo para actualizar los valores del sensor*/
    @Override
    public void onSensorChanged(SensorEvent se){
        Ax = se.values[0];
        Ay = se.values[1];
        Az = se.values[2];
    }
    
    /**Getter en eje X
      *@return X */
    public float gX(){  return Ax;  }
    /**Getter en eje Y
      *@return Y*/
    public float gY(){  return Ay;  }
    /**Getter en eje Z
      *@return Z*/
    public float gZ(){  return Az;  }
 }