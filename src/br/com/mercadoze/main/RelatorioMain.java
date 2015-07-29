package br.com.mercadoze.main;

import java.util.List;

import br.com.mercadoze.controle.ControleFuncionario;
import br.com.mercadoze.controle.ControleProduto;
import br.com.mercadoze.entidade.Funcionario;
import br.com.mercadoze.entidade.Produto;
import br.com.mercadoze.iu.Dialogo;

public class RelatorioMain {

	public void menu() {
		Funcionario f = login();
		Dialogo.exibeMensagem("Funcionario: " + f.getNome());
		

		String menu = "Digite as opções: \n\n"
				+ "0 - Voltar ao menu anterior\n" 
				+ "1 - Produtos com estoque minimo\n"
				;
		boolean sair = false;
		while (!sair) {
			Integer opcao = Dialogo.pegaValorInt(menu);
			switch (opcao) {
			case 0:
				sair = true;
				break;
			case 1:
				produtoEstoqueMinimo();
				break;
			default:
				Dialogo.exibeMensagem("Opção Inválida");
				break;
			}

		}

		
	}

	private void produtoEstoqueMinimo() {
		
		List<Produto>  resultado = (new ControleProduto()).produtoBaixoEstoque();
		StringBuffer msg = new StringBuffer("Produto com estoque baixo:\n\n");
		if (resultado == null || resultado.size() == 0){
			msg.append(" Não há produtos com estoque baixo.");
		}else{
			msg.append("ID - DESCRICAO - UNIDADE - ESTOQUE - ESTQUE MINIMO\n\n");
			for(Produto p: resultado){
				msg.append(p.getId()).append(" - ");
				msg.append(p.getDescricao()).append(" - ");
				msg.append(p.getUnidade().getSigla()).append(" - ");
				msg.append(p.getEstoque()).append(" - ");
				msg.append(p.getEstoqueMinimo()).append("\n");
			}
			
		}
		
		Dialogo.exibeMensagem(msg.toString());
		
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
