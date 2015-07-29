package br.com.mercadoze.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

import br.com.mercadoze.entidade.Entrada;
import br.com.mercadoze.entidade.Pessoa;
import br.com.mercadoze.entidade.PessoaJuridica;

public class EntradaDAO extends ConexaoBanco {

	public void incluir(Entrada entrada) throws Exception {
		String sql = "insert into entrada"
				+ "(id,fornecedor_id,produto_id,quantidade,data) "
				+ " values(nextval('seq_produto'),?,?,?,?)";
		
		String updateSql = "update produto set quantidade = quantidade + ? where id = ?";
		
		Connection con = null;
		try{
		con = conectar();
		con.setAutoCommit(false);
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setLong(1, entrada.getFornecedor().getId());
		ps.setLong(2, entrada.getProduto().getId());
		ps.setLong(3, entrada.getQtde());
		ps.setTimestamp(4, new Timestamp(System.currentTimeMillis()));

		ps.execute();
		
		ps = con.prepareStatement(updateSql);
		ps.setLong(1, entrada.getQtde());
		ps.setLong(2, entrada.getProduto().getId());
		
		ps.execute();
		
		con.commit();
		}catch(Exception e){
			con.rollback();
			throw e;
		}finally{
			desconectar(con);
		}
		
	}

	public Pessoa buscaFornecedor(long codForn) {
	
		PessoaJuridica juridica = new PessoaJuridica();
		juridica.setId(100);
		juridica.setNome("Teste representacoes");
		juridica.setCNPJ(12123000112L);
		juridica.setNomeFantasia("Testes");
		juridica.setTipo('J');
		return juridica;
	}

}
