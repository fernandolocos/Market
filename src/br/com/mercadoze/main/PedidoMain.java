package br.com.mercadoze.main;

import java.util.List;

import br.com.mercadoze.controle.ControleCliente;
import br.com.mercadoze.controle.ControleFuncionario;
import br.com.mercadoze.controle.ControlePedido;
import br.com.mercadoze.entidade.Funcionario;
import br.com.mercadoze.entidade.Pedido;
import br.com.mercadoze.entidade.Pessoa;
import br.com.mercadoze.entidade.Produto;
import br.com.mercadoze.entidade.Saida;
import br.com.mercadoze.exception.EstoqueInsuficienteException;
import br.com.mercadoze.exception.ProdutoNotFoundException;
import br.com.mercadoze.iu.Dialogo;

public class PedidoMain {

	public void menu() {
		
		Funcionario f = login();
		Dialogo.exibeMensagem("Funcionario: " + f.getNome());
		
		String menu = "Digite as opções: \n\n" +
					  "0 - Voltar ao menu anterior\n" +
					  "1 - Lançar Pedido\n";
		boolean sair = false;
		
		while(!sair){
			String opcao = Dialogo.pegaValor(menu);
			switch(opcao){
				case "0":
					sair = true;
					break;
				case "1":
					lancaPedido(f);
					break;
				default:
					Dialogo.exibeMensagem("Opção Inválida");
					break;
			}	
		}
	}	

	private void lancaPedido(Funcionario f) {
	
		ControlePedido control = new ControlePedido();
		Pedido p = control.getPedido();
		p.setFuncionario(f);
		
		long codCli = Dialogo.pegaValorLong("Digite o CPF/CNPJ ou ID:");
		Pessoa cliente = (new ControleCliente()).buscaCliente(codCli);
		p.setCliente(cliente);
		//TODO: verificar se o cliente existe.
		
		String bip = null;
		StringBuffer menu = null;
		
		do {
			
			bip = Dialogo.pegaValor((menu != null ? menu.toString() : "") + 
					"Digite o código do produto:\n "
					+ "[qtde][x][desconto][x]codigo\n"
					+ "Para sair aperte Esc/Cancelar\n");
			
			int qtdeBip = 1;
			double descBip = 0;
			long prodBip = 0L;
			if(bip != null){
				if(bip.toLowerCase().contains("x")){
					String[] bipSep = bip.toLowerCase().split("x");
					qtdeBip = Integer.parseInt(bipSep[0]); // valor antes do primeiro x é a qtde
					
					if(bipSep.length == 2){	
						prodBip = Long.parseLong(bipSep[1]); // valor depois de x é o código do produto
					}else if(bipSep.length == 3){
						descBip = Double.parseDouble(bipSep[1]); // valor central é o desconto
						prodBip = Long.parseLong(bipSep[2]); // valor depois do segundo x é o código do produto
					}
				}else{
					prodBip = Long.parseLong(bip); //sem é apenas o produto com a qtde 1
				}
			
				try {
					control.processaSaida(qtdeBip, descBip, prodBip);
				} catch (ProdutoNotFoundException e) {
					Dialogo.exibeMensagem(e.getMessage() + ": " 
							+ e.getCodigoProduto());
					e.printStackTrace();
				} catch (EstoqueInsuficienteException e) {
					Dialogo.exibeMensagem(e.getMessage() + ": " 
							+ e.getCodigoProduto().getDescricao());
					e.printStackTrace();
				}
			}

			menu = new StringBuffer("Produtos\n ID - Descrição - Qtde - Valor\n");
			int cont = 0;
			
			List<Saida> lista =  control.getPedido().getSaida();
			
			//for(Object item: control.getPedido().getSaida()){
			for (int i=lista.size(); i>=0; i--){
				if( cont >= 10) break;
				//Saida saida = (Saida) item;
				Saida saida = (Saida) lista.get(i);
				Produto prod = saida.getProduto();
				menu.append(prod.getId() + " - " + prod.getDescricao() +
					" - " + prod.getDescricao() + " - " + prod.getValor() + "\n");
				cont++;
			}

		} while (bip != null);
			
	}

	private Funcionario login(){
	
		Long id = Dialogo.pegaValorLong("Digite a matricula do funcionario:");		
		
		Funcionario f = null;
		if (id != null){
			f = (new ControleFuncionario()).buscaFuncionario(id);
		}
		
		if (f == null){ System.exit(0);}
		
		return f;
	}
}
