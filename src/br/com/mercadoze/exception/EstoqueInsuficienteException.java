package br.com.mercadoze.exception;

import br.com.mercadoze.entidade.Produto;

public class EstoqueInsuficienteException extends Exception {
	
	private Produto produto;
	public EstoqueInsuficienteException(Produto produto){
		super("Estoque insuficiente!!!");
		this.produto = produto;
	}
	public EstoqueInsuficienteException(Produto produto, Throwable e){
		super("Estoque insuficiente!!!",e);
		this.produto = produto;
		
		
	}
	
	public Produto getProduto(){
		return this.produto;
	}
	
	

}
