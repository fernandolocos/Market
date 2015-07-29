package br.com.mercadoze.entidade;

public class Produto {

		private long id;
		private String descricao;
		private double valor;
		private Unidade unidade;
		private Categoria categoria;
		private long estoque;
		private long estoqueMinimo;
		
		
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		public String getDescricao() {
			return descricao;
		}
		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}
		public double getValor() {
			return valor;
		}
		public void setValor(double valor) {
			this.valor = valor;
		}
		public Unidade getUnidade() {
			return unidade;
		}
		public void setUnidade(Unidade unidade) {
			this.unidade = unidade;
		}
		public Categoria getCategoria() {
			return categoria;
		}
		public void setCategoria(Categoria categoria) {
			this.categoria = categoria;
		}
		public long getEstoque() {
			return estoque;
		}
		public void setEstoque(long estoque) {
			this.estoque = estoque;
		}
		public long getEstoqueMinimo() {
			return estoqueMinimo;
		}
		public void setEstoqueMinimo(long estoqueMinimo) {
			this.estoqueMinimo = estoqueMinimo;
		}

}
