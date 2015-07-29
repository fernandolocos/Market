package br.com.mercadoze.main;

import br.com.mercadoze.controle.ControleEntrada;
import br.com.mercadoze.controle.ControleFornecedor;
import br.com.mercadoze.controle.ControleFuncionario;
import br.com.mercadoze.controle.ControlePedido;
import br.com.mercadoze.entidade.Entrada;
import br.com.mercadoze.entidade.Funcionario;
import br.com.mercadoze.entidade.Pedido;
import br.com.mercadoze.entidade.Pessoa;
import br.com.mercadoze.entidade.Saida;
import br.com.mercadoze.exception.EstoqueInsuficienteException;
import br.com.mercadoze.exception.ProdutoNotFoundException;
import br.com.mercadoze.iu.Dialogo;

public class PedidoMain {
	
	public void menu() {

		Funcionario f = login();
		Dialogo.exibeMensagem("Funcionario: " + f.getNome());

		String menu = "Digite as opções: \n\n"
				+ "0 - Voltar ao menu anterior\n" 
				+ "1 - Lançar Pedido\n"
				+ "2 - Lançar Entrada\n"
				;
		boolean sair = false;
		while (!sair) {
			Integer opcao = Dialogo.pegaValorInt(menu);
			switch (opcao) {
			case 0:
				sair = true;
				break;
			case 1:
				lancaPedido(f);
				break;
			case 2:
				lancaEntrada();
			default:
				Dialogo.exibeMensagem("Opção Inválida");
				break;
			}

		}

	}
	
	private void lancaEntrada() {
		
		Entrada entrada = new Entrada();
		long codForn = Dialogo.pegaValorLong("Digite o CPF/CNPJ ou ID do fornecedor:");
		Pessoa fornecedor = (new ControleEntrada()).buscaFornecedor(codForn);
		entrada.setFornecedor(fornecedor);
		
		String menu = "Digite o codigo produto:\n " 
				+"[qtde][x] codigo. "
				+"<ESC> ou Cancelar  para sair:";
		String bip;
		
		try{
			bip = Dialogo.pegaValor(menu);
		}catch(NullPointerException e){
			bip = null;
		}
		
		int qtdeBip = 1;
		long prodBip = 0;

		try{
		if (bip == null || bip.trim().equals("")) throw new Exception("Codigo desconhecido");
		
		if ( bip.toLowerCase().contains("x")){
			String[] bipSep  = bip.toLowerCase().split("x");
			qtdeBip = Integer.parseInt(bipSep[0]); //valor antes de x, é a qtde
		
			if(bipSep.length == 2){
				prodBip = Long.parseLong(bipSep[1]); //valor depois de x, é o produto
			}
			
		}else{
			prodBip = Long.parseLong(bip); //sem x é apenas o produto
		}
		
		(new ControleEntrada()).processaEntrada(entrada, prodBip,qtdeBip);
		
		}catch(Exception e){
			Dialogo.exibeMensagem("Erro ao lançar entrada");
		}
		
		
		
	}

	private void lancaPedido(Funcionario f) {
		
		ControlePedido control = new ControlePedido();
		
		Pedido p = control.getPedido();
		p.setFuncionario(f);
		
		long codCli = Dialogo.pegaValorLong("Digite o CPF/CNPJ ou ID:");
		Pessoa cliente = control.buscaCliente(codCli);
		p.setCliente(cliente);
		//TODO: Verificar se o cliente existe.
		
		String  bip;
		StringBuffer detalhes = new StringBuffer();
		long totalGeral = 0;
		do {
			
			String menu = "Digite o codigo produto:\n " 
						+"[qtde][x][desconto][x]codigo. "
						+"<ESC> ou Cancelar  para sair:";
			
			String cabecalho = "ID - DESCRICAO - QTDE - VALOR\n";
			detalhes = new StringBuffer();
			totalGeral = 0;
			try{
				detalhes.append(cabecalho);
				for(Saida item: p.getSaida()){
					detalhes.append(item.getId()).append(" - ");
					detalhes.append(item.getProduto().getDescricao())
						.append(" - ");
					detalhes.append(item.getQtde()).append(" - ");
					//calcula o total do item
					double valortot = 
							(item.getValor() - item.getDesconto())
								* item.getQtde();
					detalhes.append(valortot).append("\n");
					
					totalGeral += valortot;
				}
			}
			catch(NullPointerException e){
				detalhes = new StringBuffer();
			}
			
			try{
				bip = Dialogo.pegaValor(detalhes.toString() + menu);
			}catch(NullPointerException e){
				bip = null;
			}
			int qtdeBip = 1;
			long prodBip = 0;
			double descBip = 0;
			
			if (bip == null || bip.trim().equals("")) break;
			
			if ( bip.toLowerCase().contains("x")){
				String[] bipSep  = bip.toLowerCase().split("x");
				qtdeBip = Integer.parseInt(bipSep[0]); //valor antes de x, é a qtde
			
				if(bipSep.length == 2){
					prodBip = Long.parseLong(bipSep[1]); //valor depois de x, é o produto
				}else if (bipSep.length == 3){
					descBip = Double.parseDouble(bipSep[1]);//valor depois de x, é desconto
					//valor ultimo depois de x, é o produto
					prodBip = Long.parseLong(bipSep[2]); 
				}
				
			}else{
				prodBip = Long.parseLong(bip); //sem x é apenas o produto
			}
			
			try {
				control.processaSaida(qtdeBip, descBip, prodBip);
	
			}catch(ProdutoNotFoundException e){
				Dialogo.exibeMensagem(e.getMessage() + ": " 
										+ e.getCodigoProduto());
				
			}catch(EstoqueInsuficienteException e){
				
				Dialogo.exibeMensagem(e.getMessage() + ": " 
							+ e.getProduto().getDescricao());
			}
		
		}while(bip != null);
			
		long desconto = 0;
		try{
			desconto = Dialogo.pegaValorLong("Desconto:");
		}catch(NullPointerException e){
			//nada faz
		}
		
		totalGeral -= desconto;
		
		p.setValor(totalGeral);
		
		control.finalizaPedido();
		
		String mensagem = "Pedido Finalizado! Numero: " + p.getId() + "\n\n";
		String linhaTotal = "\n\nDesconto: R$ " + desconto +"\n";
		linhaTotal += "Valor Total do Pedido: R$ " + totalGeral;
		
		Dialogo.exibeMensagem(mensagem + detalhes.toString() + linhaTotal);
		
		
		
		
		
	}

	private Funcionario login() {

		Long id = Dialogo.pegaValorLong("Digite a matricula do funcionario:");

		Funcionario f = null;
		if (id != null) {
			f = (new ControleFuncionario()).buscaFuncionario(id);
		}

		if (f == null) {
			System.exit(0);
		}

		return f;
	}

}
