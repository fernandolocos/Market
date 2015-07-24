package br.com.mercadoze.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.mercadoze.entidade.Produto;
import br.com.mercadoze.exception.ErroCadastroException;

public class ProdutoDAO extends ConexaoBanco {

	public void incluir(Produto produto) throws ErroCadastroException {

		String sql = "insert into produto (id, descricao, valor, "
				+ "estoque, estoque_minimo, unidade_id, categoria_id)"
				+ " values (nextval('seq_produto'),?,?,?,?,?,?)";

		Connection con = null;

		try {
			con = conectar();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, produto.getDescricao());
			ps.setDouble(2, produto.getValor());
			ps.setLong(3, produto.getEstoque());
			ps.setLong(4, produto.getEstoqueMinimo());
			ps.setInt(5, produto.getUnidade().getId());
			ps.setInt(6, produto.getCategoria().getId());

			ps.execute();

		} catch (SQLException e) {
			ErroCadastroException ex = new ErroCadastroException(
					"Erro no banco", "Produto");
			throw ex;
		} catch (Exception e) {
			ErroCadastroException ex = new ErroCadastroException(
					"Erro no cadastro", "Produto");
			throw ex;
		} finally {
			desconectar(con);
		}

	}

	public void excluir(Produto produto) throws ErroCadastroException {
		String sql = "delete from produto where id = ?";

		Connection con = null;

		try {
			con = conectar();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, produto.getId());

			ps.execute();

		} catch (SQLException e) {
			ErroCadastroException ex = new ErroCadastroException(
					"Erro no banco", "Produto");
			throw ex;
		} catch (Exception e) {
			ErroCadastroException ex = new ErroCadastroException(
					"Erro no cadastro", "Produto");
			throw ex;
		} finally {
			desconectar(con);
		}
	}

	public List buscar(Produto produto) {
		String sql = "select * from produto ";

		List produtos = new ArrayList();
		Connection con = null;
		try {
			con = conectar();
			Object[] params = new Object[2];
			if (produto != null) {

				sql += " where 1=1 ";
				if (produto.getId() > 0) {
					sql += " and id = ?";
					params[0] = produto.getId();
				}
				if (produto.getDescricao() != null) {
					sql += " and descricao like ?";
					params[1] = produto.getDescricao();
				}
			}
			PreparedStatement ps = con.prepareStatement(sql);
			preencheParams(ps, params);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Produto p = new Produto();
				p.setId(rs.getInt("id"));
				p.setDescricao(rs.getString("descricao"));

				produtos.add(p);
			}

			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			desconectar(con);
		}

		return produtos;
	}

	private void preencheParams(PreparedStatement ps, Object[] params)
			throws SQLException {
		int cont = 1;
		for (int i = 0; i < params.length; i++) {
			if (params[i] != null) {
				if (params[i].getClass() == String.class) {
					ps.setString(cont, "%" + params[i] + "%");
				} else {
					ps.setObject(cont, params[i]);
				}
				cont++;
			}
		}

	}
}
