package br.com.mercadoze.entidade;

public class Saida {

		private long id;
		private Produto produto;
		private Pedido pedido;
		private long qtde;
		private double valor;
		private double desconto;
		
		
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		public Produto getProduto() {
			return produto;
		}
		public void setProduto(Produto produto) {
			this.produto = produto;
		}
		public Pedido getPedido() {
			return pedido;
		}
		public void setPedido(Pedido pedido) {
			this.pedido = pedido;
		}
		public long getQtde() {
			return qtde;
		}
		public void setQtde(long qtde) {
			this.qtde = qtde;
		}
		public double getValor() {
			return valor;
		}
		public void setValor(double valor) {
			this.valor = valor;
		}
		public double getDesconto() {
			return desconto;
		}
		public void setDesconto(double desconto) {
			this.desconto = desconto;
		}

}
