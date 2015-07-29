package br.com.mercadoze.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.mercadoze.entidade.Categoria;

public class CategoriaDAO extends ConexaoBanco {

	  public void incluir(Categoria cat){
		  String sql = 
		"insert into categoria(id,descricao) " +
		" values(nextval('seq_categoria'),?)";
		
		try{
			Connection con = conectar();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, cat.getDescricao());
			ps.execute();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		  
		  
	  }
	  
	  public List buscar(Categoria cat){
		  
		  String sql = "select * from categoria ";
		  
		  List categorias = new ArrayList();
		  Connection con = null;
		  try{
			 con = conectar();
			 Object[] params = new Object[2];
			 if (cat != null){
				 
				 sql += " where 1=1 ";
				 if (cat.getId() >0 ){
					 sql += " and id = ?";
					 params[0] = cat.getId();
				 }
				 if (cat.getDescricao() != null){
					 sql += " and descricao like ?";
					 params[1] = cat.getDescricao();
				 }
			 }
			 PreparedStatement ps = con.prepareStatement(sql);
			 preencheParams(ps,params);
			 
			
			 
			 ResultSet rs = ps.executeQuery();
			 
			 while(rs.next()){
				 Categoria c = new Categoria();
				 c.setId(rs.getInt("id"));
				 c.setDescricao(rs.getString("descricao"));
				
				 categorias.add(c);
			 }
			  
			 rs.close();
		  }catch(Exception e){
			  e.printStackTrace();
		  }finally{
			  desconectar(con);
		  }
		  
		 return categorias;
		  
	  }

	private void preencheParams(PreparedStatement ps, Object[] params) throws SQLException {
		int cont = 1;
		for(int i=0; i< params.length; i++){
			if (params[i] != null){
				if (params[i].getClass() == String.class){
					ps.setString(cont, "%" + params[i] + "%");
				}else{
					ps.setObject(cont, params[i]);
				}
				cont++;
			}
		}
		
	}
}
