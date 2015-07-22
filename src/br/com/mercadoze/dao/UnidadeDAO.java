package br.com.mercadoze.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.mercadoze.entidade.Unidade;
import br.com.mercadoze.exception.ErroCadastroException;
import br.com.mercadoze.iu.Dialogo;

public class UnidadeDAO extends ConexaoBanco {

	public void incluir(Unidade unidade) throws ErroCadastroException{
		
		String sql = "insert into unidade (id, sigla, descricao)"
				+ " values (nextval('seq_unidade'),?,?)";
		
		Connection con = null;
		
		try{
			con =conectar();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, unidade.getSigla());
			ps.setString(2, unidade.getDescricao());
					
			ps.execute();	
		} catch(SQLException e){
			ErroCadastroException ex = 
					new ErroCadastroException("Erro no banco", "Unidade");
			throw ex;		
		} catch(Exception e){
			ErroCadastroException ex = 
					new ErroCadastroException("Erro no cadastro", "Unidade");
			throw ex;
		} finally{
			desconectar(con);
		}
		
	}

	public List buscar(Unidade unidade) {	
		String sql = "select * from unidade ";
		  
		List unidades = new ArrayList();
		Connection con = null;
		try{
			con = conectar();
			Object[] params = new Object[2];
			if (unidade != null){
				 
			sql += " where 1=1 ";
			if (unidade.getSigla() != null){
				sql += " and sigla like ?";
				params[0] = unidade.getSigla();
			}
			if (unidade.getDescricao() != null){
				sql += " and descricao like ?";
				params[1] = unidade.getDescricao();
				}
			}
			PreparedStatement ps = con.prepareStatement(sql);
			preencheParams(ps,params);
			 
			ResultSet rs = ps.executeQuery();
			 
			while(rs.next()){
				Unidade un = new Unidade();
				un.setSigla(rs.getString("sigla"));
				un.setDescricao(rs.getString("descricao"));
				
				unidades.add(un);
			}
			  
			rs.close();
		 }catch(Exception e){
			 e.printStackTrace();
		 }finally{
			 desconectar(con);
		 }
		  
		 return unidades;
	}
		
	private void preencheParams(PreparedStatement ps, Object[] params) throws SQLException {
		int cont = 1;
		for(int i=0; i<params.length; i++){
			if(params[i] != null){
				if(params[i].getClass() == String.class){
					ps.setString(cont, "%" + params[i] + "%");
				}else{
					ps.setObject(cont, params[i]);
				}
				cont++;
			}
		}
		
	}
}
