package balam.games.Library;
//DataLib.java

import java.io.BufferedReader; 
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader; 
import java.io.OutputStreamWriter;

import balam.games.skeleton.FileIO;
import android.app.Activity;
import android.util.Log;

/**
 * @version 1.0
 * @author Angel Eduardo Perez Cruz
 * 
 * Clase para Guardar/cargar los datos de una aplicacion
 * Tambien Contiene algoritmos de Seguridad,compresion,encriptacion
 * Cifrado de contrasenhas y canales seguros de transmision de informacion
 * */

public class DataLib {
	
	/**Clase para elementos leidos del archivo*/
	public static class LoadedData{

		private String[] mainData; //Datos primordiales de la app
		private String[] extraData; //Datos extras de la app
		private int mainCounter; //Contador en lectura main
		private int extraCounter; //Contaor en lectura extra
		
		/**Constructor para almacenara un objeto con valores de un archivo*/
		public LoadedData(int mainSize,int extraSize){
			mainData = new String[mainSize];
			extraData = new String[extraSize];
			mainCounter = 0;
			extraCounter = 0;
		}
		
		/**Inserta datos al arreglo principal*/
		public void insertMain(String data){
			if( mainCounter < mainData.length ){
				mainData[mainCounter] = data;
				mainCounter++;
			}
		}
		
		/**Inserta Datos al arreglo Secundario*/
		public void insertExtra(String data){
			if( extraCounter < extraData.length ){
				extraData[extraCounter] = data;
				extraCounter++;
			}
		}
		
		/**Obtiene los valores principales
		 * @return String[], con los datos principales*/
		public String[] getMainData(){	return mainData; }
		/**Obtiene los valores secundarios si existen
		 * @return String[], con los valores secundarios( Si los hay )*/
		public String[] getExtraData(){	return extraData; }
		
	}
	
	/**Constructor protegido de instanciamiento*/
	private DataLib(){;}
	
	/**Metodo para guardar los datos ingresados como Main y Extra*/
	public static void saveData(String[] mainData,String[] extraData,FileIO fio,String savedData){
		BufferedWriter writer = null;
		
		try{
			writer = new BufferedWriter( new OutputStreamWriter(fio.writeFile(savedData) ));
			for( int i=0;i<mainData.length;i++){
				writer.write(mainData[i]); 
				writer.write("\n");
			}
			for( int i=0;i<extraData.length;i++){
				writer.write(extraData[i]); writer.write("\n");
			}
		}catch(Exception e){
			
		}finally{
			try{
				if( writer != null)
					writer.close();
			}catch(IOException e){}
		}
	}
	
	/**Metodo para leer datos de un archivo guardado
	 * @return LoadedData, con los datos leidos, main y extra*/
	public static LoadedData loadData(int mainSize,int extraSize,FileIO fio,String savedData){
		LoadedData loaded = new LoadedData(mainSize,extraSize);
		BufferedReader reader = null;
		
		try{
			reader = new BufferedReader( new InputStreamReader(fio.readFile(savedData) ));
			String tmp = null;
			int mc = 0;
			int ec = 0;
			
			while( (tmp = reader.readLine()) != null ){
				if( mc < mainSize ){
					loaded.insertMain(tmp);
					mc++;
				}
				
				if( ec < extraSize && mc >= mainSize){
					loaded.insertExtra(tmp);
					ec++;
				}
			}
		
		}catch(Exception e){

		}finally{
			try{
				if( reader != null)
					reader.close();
			}catch(IOException e){
				
			}
		}
		return loaded;
	}
	
	/**Metodo que recupera la informacion de un usuario que almaceno datos en la nube
	 * Servicio disponible para usuarios registrados 
	 * @return True, si se efectuo la recuperacion con exito*/
	public boolean RestoreFromCloud(String user,String password,Activity activity){
		return false;
	}
	
	/**Metodo para guardar la informacion de un usuario en la nube
	 * Servicio disponible para usuarios registrados
	 * @return True, si se efectuo la recuperacion con Exito*/
	public boolean SaveToCloud(String user,String password,Activity activity){
		return false;
	}
	
	/**Metodo para encriptar la informacion a guardar local o en la nube
	 * @return String[], con los datos encriptados*/
	public String[] encryptData(String[] myData){
		return null;
	}
	
	/**Metodo para desecncriptar la informacion a cargar local o en la nube
	 * @return String[], con los datos desencriptados*/
	public String[] decryptData(String[] encryptedData){
		return null;
	}
}
