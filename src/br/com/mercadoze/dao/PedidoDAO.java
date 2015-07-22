package br.com.mercadoze.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.mercadoze.entidade.Produto;
import br.com.mercadoze.entidade.Unidade;

public class PedidoDAO extends ConexaoBanco{
	
	private Connection conexao; // vai segurar a conexão até a finalização do pedido
	
	public PedidoDAO(){
		conexao = conectar();
	}
	
	public Produto buscaProduto(long codProduto){
		
		String sql = "select * form produto where id = ?";
		
		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setLong(1, codProduto);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				Produto p = new Produto();
				p.setId(codProduto);
				p.setDescricao(rs.getString("descricao"));
				p.setEstoque(rs.getLong("estoque"));
				p.setValor(rs.getDouble("valor"));
				
				Unidade un = new Unidade();
				un.setSigla(rs.getString("sigla"));
				p.setUnidade(un);
				
				rs.close();
				ps.close();
				return p;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	public void finalizar(){
		desconectar(conexao);
	}
}
