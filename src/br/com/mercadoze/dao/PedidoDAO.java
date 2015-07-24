package br.com.mercadoze.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import br.com.mercadoze.entidade.Produto;
import br.com.mercadoze.entidade.Unidade;
import br.com.mercadoze.entidade.Pedido;
import br.com.mercadoze.entidade.Saida;

public class PedidoDAO extends ConexaoBanco {

	private Connection conexao; // vai segurar a conexão até a finalização do
								// pedido

	public PedidoDAO() {
		conexao = conectar();
	}

	public Produto buscaProduto(long codProduto) {

		String sql = "select * form produto where id = ?";

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setLong(1, codProduto);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
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

	public void finalizar() {
		desconectar(conexao);
	}

	public void persistePedido(Pedido pedido) {

		try {
			conexao.setAutoCommit(false);

			// resgatar o novo id do pedido
			long pedidoId = 0;
			ResultSet rs = conexao.createStatement().executeQuery(
					"select nextval ('seq_pedido')");
			if (rs.next()) {
				pedidoId = rs.getLong(1);
				pedido.setId(pedidoId);
			}
			rs.close();

			String sqlInsPedido = "insert into pedido (id,cliente_id,data,valor,funcionario_id) "
					+ " values (?,?,?,?,?)";

			PreparedStatement psPed = conexao.prepareStatement(sqlInsPedido);

			psPed.setLong(1, pedidoId);
			psPed.setLong(2, pedido.getCliente().getId());

			// pega a data atual
			Timestamp data = new Timestamp(System.currentTimeMillis());
			psPed.setTimestamp(3, data);
			psPed.setDouble(4, pedido.getValor());
			psPed.setLong(5, pedido.getFuncionario().getId());

			psPed.execute();

			String sqlInSaida = "insert into saida (id,produto_id,pedido_id,quantidade,valor,desconto) values (nextval('seq_saida'),?,?,?,?,?)";

			PreparedStatement psSaida = conexao.prepareStatement(sqlInSaida);

			String sqlAtualizaEstoque = "update produto set estoque = estoque - ? where id = ?";

			PreparedStatement psEstoque = conexao
					.prepareStatement(sqlAtualizaEstoque);

			for (Saida item : pedido.getSaida()) {
				psSaida.setLong(1, item.getProduto().getId());
				psSaida.setLong(2, pedido.getId());
				psSaida.setLong(3, item.getQtde());
				psSaida.setDouble(4, item.getValor());
				psSaida.setDouble(5, item.getDesconto());

				psSaida.execute();

				psEstoque.setLong(1, item.getQtde());
				psEstoque.setLong(2, item.getProduto().getId());

				psEstoque.execute();

			}

			conexao.commit();

		} catch (SQLException e) {

			try {
				if (!conexao.getAutoCommit()) {
					conexao.rollback();
				}
			} catch (SQLException e1) {

				e1.printStackTrace();
			}

			e.printStackTrace();
		}

	}
}
