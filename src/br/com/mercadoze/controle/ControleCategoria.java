package br.com.mercadoze.controle;

import java.util.List;

import br.com.mercadoze.entidade.Categoria;
import br.com.mercadoze.dao.CategoriaDAO;

public class ControleCategoria {
	
	public void incluir(Categoria cat){
		(new CategoriaDAO()).incluir(cat);
	}
	
	public List buscar(Categoria cat){
		return (new CategoriaDAO()).buscar(cat);
	}

}
