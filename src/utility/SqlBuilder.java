package utility;


import beans.SearchBean;

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
 *   
 * 		- ArrayList responses: ArrayList, en el mismo orden que 'fields' con los valores dados por el usuario
 * 						a cada campo.
 * */
public class SqlBuilder {
	private  String SELECT_stmt;
	private  String FROM_stmt;
	private  String WHERE_stmt;
	private  String final_stmt;

	
	public SqlBuilder(){
		setSELECT_stmt("SELECT ");
		setFROM_stmt(" FROM ");
		setWHERE_stmt(" WHERE ");
		setFinal_stmt("");
		
	}
	
	//OBS: las columnas deben venir con el formato: "alias.campo"
	public void SELECT_insert(String columnas){
		AddSELECT_stmt(SearchBean.getColumnas());
	}
	
	//OBS: los nombres de las tablas deben venir con el formato: "nombre_tabla alias"
	public void FROM_insert(String... nombres_tablas){
		if(getFROM_stmt() != " FROM "){
			AddFROM_stmt(", ");
		}
		for(String nombre : nombres_tablas){
			AddFROM_stmt(nombre + " ");
		}
	}
	
	//OBS: el nombre de la tabla y los campos tienen que estar en el formato: 'nombre_tabla alias' y 'alias.campo' 
	public void FROM_JOIN(String nombre_tabla, String campo_TablaA, String campo_TablaB, String tipo_JOIN){
		AddFROM_stmt(" " + tipo_JOIN + " " + nombre_tabla + " ON " + campo_TablaA + " = " + campo_TablaB);
	}
	
	
	public void WHERE_insert(String nombre_tabla, String valor){

		if(valor.toString() == "" || valor.toString().equals("0"))
			return;
		else if(!getWHERE_stmt().equals(" WHERE ")){
			AddWHERE_stmt(" AND");
		}
		
		AddWHERE_stmt(" " + nombre_tabla + " = " + "'"+valor.toString()+"'");
	}
	
	
	
	
	
	public String buildQuery(){
		//Simplemente se arma la query
		AddFinal_stmt(getSELECT_stmt());
		AddFinal_stmt(getFROM_stmt());
		AddFinal_stmt(getWHERE_stmt());
		AddFinal_stmt(" LIMIT 50;");
		
		return getFinal_stmt();
	}
		
		
		
		/************SELECT************/
		
		//AddSELECT_stmt(SearchBean.getColumnas());
		
		
		/*************FROM**************/
		
		//AddFROM_stmt(table_name); 
		
		
		/************WHERE***********/
		/*boolean aux = false;
		for(int i = 0; i < fields_names.size(); i++){
			if(responses.get(i).equals("0") || responses.get(i) == "")
				continue;
			else{
				if (aux == true)
					AddWHERE_stmt(" AND ");
				AddWHERE_stmt(table_name + "." + fields_names.get(i).toString());
				AddWHERE_stmt("="+responses.get(i).toString());	
				aux = true;
			}
			
			
		}
		
			
			
		return SELECT_stmt + FROM_stmt + WHERE_stmt;
		
	}

	
*/
	
	/**************GETTER/SETTER****************/
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
	}

	
	public String getFinal_stmt() {
		return final_stmt;
	}
	public void setFinal_stmt(String final_stmt) {
		this.final_stmt = final_stmt;
	}
	public void AddFinal_stmt(String stmt){
		this.final_stmt += stmt;
	}
	
}
