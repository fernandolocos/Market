package br.com.mercadoze.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.mercadoze.entidade.Produto;
import br.com.mercadoze.entidade.Unidade;
import br.com.mercadoze.exception.ErroCadastroException;

public class ProdutoDAO extends ConexaoBanco {

	public void incluir(Produto produto) throws ErroCadastroException {

		String sql = "insert into produto"
				+ "(id,descricao,valor,estoque,estoque_minimo, unidade_id,categoria_id) "
				+ " values(nextval('seq_produto'),?,?,?,?,?,?)";
		
		Connection con = null;
		try{
		con = conectar();
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, produto.getDescricao());
		ps.setDouble(2, produto.getValor());
		ps.setLong(3, produto.getEstoque());
		ps.setLong(4, produto.getEstoqueMinimo());
		ps.setInt(5, produto.getUnidade().getId());
		ps.setInt(6, produto.getCategoria().getId());

		ps.execute();
		}catch(SQLException e){
			ErroCadastroException ex = 
					new ErroCadastroException("Erro no banco","Produto");
			throw ex;
		}catch(Exception e){
			ErroCadastroException ex = 
					new ErroCadastroException("Erro no cadastro","Produto");
			throw ex;
		}finally{
			desconectar(con);
		}

	}

	public List<Produto> produtoBaixoEstoque() {
		 List<Produto> lst = new ArrayList<Produto>();
		
		 String sql  = "Select p.*,u.id unidade_id, u.sigla unidade_sigla, u.descricao unidade_descricao "
		 		+ "  from produto p inner join unidade u on u.id = p.unidade_id"
		 		+ " where p.estoque <= p.estoque_minimo "
		 		+ " order by id desc";
		 Connection con = null;
		try {
			con = conectar();

			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()){
				Produto p = new Produto();
				p.setId(rs.getLong("id"));
				p.setDescricao(rs.getString("descricao"));
				p.setEstoque(rs.getLong("estoque"));
				p.setEstoqueMinimo(rs.getLong("estoque_minimo"));
				
				Unidade un = new Unidade();
				un.setId(rs.getInt("unidade_id"));
				un.setSigla(rs.getString("unidade_sigla"));
				un.setDescricao(rs.getString("unidade_descricao"));
				p.setUnidade(un);
				
				lst.add(p);
			}
			
			rs.close();
			st.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			desconectar(con);
		}

		 
		
		return null;
	}

}
