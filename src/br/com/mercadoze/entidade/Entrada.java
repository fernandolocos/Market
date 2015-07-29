package br.com.mercadoze.entidade;

import java.util.Date;

public class Entrada {

		private long id;
		private Pessoa fornecedor;
		private Produto produto;
		private long qtde;
		private Date data;
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		public Pessoa getFornecedor() {
			return fornecedor;
		}
		public void setFornecedor(Pessoa fornecedor) {
			this.fornecedor = fornecedor;
		}
		public Produto getProduto() {
			return produto;
		}
		public void setProduto(Produto produto) {
			this.produto = produto;
		}
		public long getQtde() {
			return qtde;
		}
		public void setQtde(long qtde) {
			this.qtde = qtde;
		}
		public Date getData() {
			return data;
		}
		public void setData(Date data) {
			this.data = data;
		}

}
