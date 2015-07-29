package br.com.mercadoze.main;

import br.com.mercadoze.iu.Dialogo;

public class MercadoMain {

	public static void main(String[] args) {
	
		String menu = "Digite as opções: \n\n" +
				  "0 - Sair\n" +
				  "1 - Cadastros\n"+
				  "2 - Pedidos\n" +
		 		  "3 - Relatorios\n";
				
	boolean sair = false;
	while(!sair){
		Integer opcao = Dialogo.pegaValorInt(menu);
		switch(opcao){
			case 0:
				sair = true;
				break;
			case 1:
				(new CadastroMain()).menu();
				break;
			case 2:
				(new PedidoMain()).menu();
				break;
			case 3:
				(new RelatorioMain()).menu();
				break;
			default:
				Dialogo.exibeMensagem("Opção Inválida");
				break;
		}
		
	}
	
	System.exit(0);

	}

}
