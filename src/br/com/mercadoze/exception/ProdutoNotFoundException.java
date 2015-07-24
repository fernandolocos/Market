package br.com.mercadoze.exception;

public class ProdutoNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long codProduto = 0;

	public ProdutoNotFoundException(long codProduto) {
		super("Produto não encontrado!");
		this.codProduto = codProduto;
	}

	public ProdutoNotFoundException(long codProduto, Throwable e) {
		super("Produto não encontrado!", e);
		this.codProduto = codProduto;
	}

	public long getCodigoProduto() {
		return this.codProduto;
	}
}
