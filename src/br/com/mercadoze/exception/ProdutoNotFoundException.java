package br.com.mercadoze.exception;

public class ProdutoNotFoundException extends Exception {
	
	private long codProduto = 0;
	public ProdutoNotFoundException(long codProduto){
		super("Produto não Encontrado!!!");
		this.codProduto = codProduto;
	}
	
	public ProdutoNotFoundException(long codProduto, Throwable e){
		super("Produto não Encontrado!!!", e);
		this.codProduto = codProduto;
	}
	
	public long getCodigoProduto(){
		return codProduto;
	}

}
