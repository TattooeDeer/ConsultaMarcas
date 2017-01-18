package utility;

import java.util.ArrayList;


/******************Constructor Dinamico de Sentencias en SQL*************************/
/*
 * > Sobre la clase "SqlBuilder"...
 * 
 * 	Esta clase nace de la necesidad de tener un constructor dinamico de querys de SQL que permita
 * realizar consultas sobre una BD segun los campos completados y no completados por un usuario,
 * en un formulario.
 * 
 * > Variables de la Clase:
 * 		- String SELECT_stmt: Fragmento de la sentencia correspondiente a "SELECT " += Columnas en las tablas de la BD.
 * 		- String FROM_stmt: Analogo a SELECT_stmt, pero para el fragmento correspondiente a "FROM " += Tablas.
 * 		- String WHERE_stmt: Idem a los anteriores, para "WHERE " +=    
 * 
 * > (Metodo Principal) 'buildQuery':
 * 
 * 	@ Valor de Retorno: "String", corresponde a la sentencia SQL lista para ser ejecutada en la BD.
 *  @ Parametros: 
 *  	- ArrayList fields: ArrayList con los beans que corresponden a los campos llenados por el usuario.  
 * 		- ArrayList responses: ArrayList, en el mismo orden que 'fields' con los valores dados por el usuario
 * 						a cada campo.
 * */
public class SqlBuilder {
	private String SELECT_stmt = "SELECT ";
	private String FROM_stmt = "FROM ";
	private String WHERE_stmt = "WHERE ";
	

	
	public String buildQuery(ArrayList<String> columns, String table_name, ArrayList<String> fields_names, ArrayList<String> responses){
		if(fields_names.size() != responses.size()){
			System.out.println("No son del mismo tamaño!");
			return "";
		}
		
		/************SELECT************/
		boolean aux2 = false;
		for(int i = 0; i < columns.size(); i++){
			if(aux2 == true)
				AddSELECT_stmt(",");
			AddSELECT_stmt(columns.get(i).toString());
			aux2=true;
			}
		
		/*************FROM**************/
		AddFROM_stmt(table_name); 
		
		
		/************WHERE***********/
		boolean aux = false;
		for(int i = 0; i < fields_names.size(); i++){
			if(responses.get(i) == null)
				continue;
			else{
				if (aux == true)
					AddWHERE_stmt("AND");
				AddWHERE_stmt(fields_names.get(i).toString());
				AddWHERE_stmt("="+responses.get(i).toString());	
				aux = true;
			}
			
			
		}
		
			
			
		
		return SELECT_stmt + FROM_stmt + WHERE_stmt;
		
	}



	public String getSELECT_stmt() {
		return SELECT_stmt;
	}
	public void AddSELECT_stmt(String s){
		this.SELECT_stmt+=s;
	}


	public void setSELECT_stmt(String sELECT_stmt) {
		SELECT_stmt = sELECT_stmt;
	}



	public String getFROM_stmt() {
		return FROM_stmt;
	}
	public void AddFROM_stmt(String s){
		this.FROM_stmt+=s;
	}
	public void setFROM_stmt(String fROM_stmt) {
		FROM_stmt = fROM_stmt;
	}



	public String getWHERE_stmt() {
		return WHERE_stmt;
	}
	public void AddWHERE_stmt(String s){
		this.WHERE_stmt+=s;
	}
	public void setWHERE_stmt(String wHERE_stmt) {
		WHERE_stmt = wHERE_stmt;
	}; 
	
}
