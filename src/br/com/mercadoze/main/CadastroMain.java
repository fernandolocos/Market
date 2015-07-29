package br.com.mercadoze.main;

import java.util.List;

import br.com.mercadoze.controle.ControleCategoria;
import br.com.mercadoze.controle.ControleFuncionario;
import br.com.mercadoze.controle.ControlePedido;
import br.com.mercadoze.controle.ControleProduto;
import br.com.mercadoze.entidade.Categoria;
import br.com.mercadoze.entidade.Funcionario;
import br.com.mercadoze.entidade.Pessoa;
import br.com.mercadoze.entidade.PessoaFisica;
import br.com.mercadoze.entidade.PessoaJuridica;
import br.com.mercadoze.entidade.Produto;
import br.com.mercadoze.entidade.Unidade;
import br.com.mercadoze.exception.ErroCadastroException;
import br.com.mercadoze.iu.Dialogo;

public class CadastroMain {

	public void menu() {

		Funcionario f = login();
		Dialogo.exibeMensagem("Funcionario: " + f.getNome());

		String menu = "Digite as opções: \n\n"
				+ "0 - Voltar ao menu anterior\n" + "1 - Pesquisar Cliente\n"
				+ "2 - Cadastrar Categoria\n" + "3 - Pesquisar Categoria\n"
				+ "4 - Cadastrar Unidade\n" + "5 - Pesquisar Unidade\n"
				+ "6 - Cadastrar Produto\n";
		boolean sair = false;
		while (!sair) {
			Integer opcao = Dialogo.pegaValorInt(menu);
			switch (opcao) {
			case 0:
				sair = true;
				break;
			case 1:
				pesquisaCliente();
				break;
			case 2:
				cadastraCategoria();
				break;
			case 3:
				pesquisaCategoria();
				break;
			case 4:
				break;
			case 5:
				//nao foi feito
				break;
			case 6:
				cadastraProduto();
				break;
			default:
				Dialogo.exibeMensagem("Opção Inválida");
				break;
			}

		}

	}

	private void cadastraProduto() {
		try {
			String descricao = Dialogo.pegaValor("Digite a descrição:");
			double valor = Dialogo.pegaValorDouble("Digite o valor:");
			long quantidade = Dialogo.pegaValorLong("Quantidade em estoque:");
			long qtdeMin = Dialogo
					.pegaValorLong("Quantidade minima em estoque:");
			Categoria cat = pesquisaCategoria(Dialogo
					.pegaValorInt("Digite o ID da Categoria:"));
			Unidade un = pesquisaUnidade(Dialogo
					.pegaValor("Digite a sigla da Unidade:"));

			Produto p = new Produto();
			p.setDescricao(descricao);
			p.setValor(valor);
			p.setEstoque(quantidade);
			p.setEstoqueMinimo(qtdeMin);
			p.setCategoria(cat);
			p.setUnidade(un);

			(new ControleProduto()).incluir(p);
			
			Dialogo.exibeMensagem("Produto Cadastrado!");
		} catch (ErroCadastroException e) {
			Dialogo.exibeMensagem(e.getMessage());
			e.printStackTrace();
		}

	}

	private Unidade pesquisaUnidade(String sigla) {
		Unidade un = new Unidade();
		un.setId(1);
		un.setSigla("CX");
		un.setDescricao("Caixa");

		return un;

	}

	private Categoria pesquisaCategoria(int id) {
		Categoria c = new Categoria();
		c.setId(id);

		List ret = (new ControleCategoria()).buscar(c);
		if (ret.size() > 0) {
			return (Categoria) ret.get(0);
		}
		return null;
	}

	private void pesquisaCategoria() {
		String descricao = Dialogo.pegaValor("Digite a descrição:");
		Categoria c = new Categoria();
		c.setDescricao(descricao);

		List ret = (new ControleCategoria()).buscar(c);

		if (ret.size() > 0) {
			StringBuffer resposta = new StringBuffer();
			for (Object item : ret) {
				Categoria cat = (Categoria) item;

				resposta.append(cat.getId()).append(",")
						.append(cat.getDescricao()).append("\n");

			}

			Dialogo.exibeMensagem(resposta.toString());
		} else {
			Dialogo.exibeMensagem("Registro não encontrado!!!");
		}

	}

	private void cadastraCategoria() {
		String descricao = Dialogo
				.pegaValor("Digite a descrição da categoria:");
		Categoria c = new Categoria();
		c.setDescricao(descricao);

		(new ControleCategoria()).incluir(c);

		Dialogo.exibeMensagem("Categoria cadastrada!!!");

	}

	private void pesquisaCliente() {
		long codigo = Dialogo.pegaValorLong("Digite o CPF/CNPJ ou ID:");
		Pessoa cliente = (new ControlePedido()).buscaCliente(codigo);

		if (cliente != null) {
			StringBuffer resposta = new StringBuffer();
			resposta.append("ID: ").append(cliente.getId()).append("\n");
			resposta.append("Nome: ").append(cliente.getNome()).append("\n");
			if (cliente.getTipo() == 'J') {
				resposta.append("CNPJ: ")
						.append(((PessoaJuridica) cliente).getCNPJ())
						.append("\n");
			} else {
				resposta.append("CPF: ")
						.append(((PessoaFisica) cliente).getCPF()).append("\n");
			}

			Dialogo.exibeMensagem(resposta.toString());
		} else {
			Dialogo.exibeMensagem("Cliente não encontrado!");
		}

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
