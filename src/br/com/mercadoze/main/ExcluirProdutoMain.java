package br.com.mercadoze.main;

import br.com.mercadoze.controle.ControleProduto;
import br.com.mercadoze.entidade.Produto;
import br.com.mercadoze.exception.ErroCadastroException;
import br.com.mercadoze.iu.Dialogo;

public class ExcluirProdutoMain {

	public void menu(Produto prod) {
					
		String menu = "Tem certeza que deseja exluir o produto?\n\n" +
				  "0 - Não\n" +
				  "1 - Sim\n";
		
		
		String opcao = Dialogo.pegaValor(menu);
		switch(opcao){
			case "0":
				break;
			case "1":
				excluirProduto(prod);
				break;
			default:
				Dialogo.exibeMensagem("Opção Inválida");
				break;
			
		}	
	}
	
	public void excluirProduto(Produto p){
		
		try{		
			(new ControleProduto()).excluir(p);
			
			Dialogo.exibeMensagem("Produto excluído!");
			
		} catch(ErroCadastroException e){
			Dialogo.exibeMensagem(e.getMessage());
			e.printStackTrace();
		}
	}
}
