package br.com.mercadoze.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

import br.com.mercadoze.entidade.Pedido;
import br.com.mercadoze.entidade.Produto;
import br.com.mercadoze.entidade.Saida;
import br.com.mercadoze.entidade.Unidade;

public class PedidoDAO extends ConexaoBanco {
	
	private Connection conexao;
	
	public PedidoDAO(){
		conexao = conectar();
	}
	
	
	public Produto buscaProduto(long codProduto){
		
		String sql = "Select produto.*, unidade.sigla " +
					" from produto inner join unidade " +
				    " on unidade.id = produto.unidade_id "+
					" where produto.id = ?";
		
		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setLong(1, codProduto);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()){
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


	public void persistePedido(Pedido pedido) {
		
		try {
			conexao.setAutoCommit(false);
			long pedidoId = 0;
			//resgatar o novo id do pedido
			ResultSet rs = 	conexao.createStatement().executeQuery("Select nextval('seq_pedido')");
			if (rs.next()){
				pedidoId = rs.getLong(1);
				pedido.setId(pedidoId);
			}
			rs.close();
			
			String sqlInsPedido = 
			"insert into pedido(id,cliente_id,data,valor,funcionario_id) "+
			" values(?,?,?,?,?)";
			
			PreparedStatement psPed = conexao.prepareStatement(sqlInsPedido);
			psPed.setLong(1, pedidoId);
			psPed.setLong(2, pedido.getCliente().getId());
						
			Timestamp data = new Timestamp(System.currentTimeMillis());
			psPed.setTimestamp(3, data);
			psPed.setDouble(4, pedido.getValor());
			psPed.setLong(5, pedido.getFuncionario().getId());
			
			psPed.execute();
			
			String sqlInsSaida = 
			"insert into saida(id,pedido_id,quantidade,valor,desconto, produto_id) "+
			" values(nextval('seq_saida'),?,?,?,?,?)";
			
			PreparedStatement psSaida = 
					conexao.prepareStatement(sqlInsSaida);
			
			String sqlAtualizaEstoque =
			 "update produto set estoque = estoque - ? "+
			 " where id = ?";
			PreparedStatement psEstoque = 
					conexao.prepareStatement(sqlAtualizaEstoque);
			
			for(Saida item: pedido.getSaida()){
				psSaida.setLong(1, pedidoId);
				psSaida.setLong(2, item.getQtde());
				psSaida.setDouble(3, item.getValor());
				psSaida.setDouble(4, item.getDesconto());
				psSaida.setLong(5, item.getProduto().getId());
				
				psSaida.execute();
				
				psEstoque.setLong(1, item.getQtde());
				psEstoque.setLong(2, item.getProduto().getId());
				
				psEstoque.execute();
				
				
			}
			
			conexao.commit();
			
		} catch (SQLException e) {
			
			try {
				if (! conexao.getAutoCommit()){
					conexao.rollback();
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			e.printStackTrace();
		}
		
		
	}

}
