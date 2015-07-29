package br.com.mercadoze.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBanco {
	
	private static final String PostgreSQL = "org.postgresql.Driver";
	
	protected Connection conectar(){
		
		 Connection con = null;
		 try{
		  Class.forName(PostgreSQL);
	       
	      con = DriverManager.getConnection(
	    "jdbc:postgresql://localhost:5432/mercado", "java01", "java");
	      
		 }catch (ClassNotFoundException e){
			 e.printStackTrace();
		 }catch (SQLException e){
			 e.printStackTrace();
		 }
	      
	     return con;
		
	}
	
	protected void desconectar(Connection con){
		if (con != null){
			try{
				if (!con.isClosed()){
					con.close();
				}
			}catch (Exception e){
				e.printStackTrace();
			}
		}
	}

}
