package br.com.mercadoze.main;

import java.util.List;

import br.com.mercadoze.controle.ControleCategoria;
import br.com.mercadoze.controle.ControleCliente;
import br.com.mercadoze.controle.ControleFuncionario;
import br.com.mercadoze.controle.ControleProduto;
import br.com.mercadoze.controle.ControleUnidade;
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
		
		String menu = "Digite as opções: \n\n" +
					  "0 - Voltar ao menu anterior\n" +
					  "1 - Pesquisar Cliente\n" +
					  "2 - Cadastrar Categoria\n"+
					  "3 - Pesquisar Categoria\n"+
					  "4 - Cadastrar Unidade\n"+
					  "5 - Pesquisar Unidade\n"+
					  "6 - Cadastrar Produto\n"+
					  "7 - Pesquisar Produto\n"+
					  "8 - Excluir Produto\n";
		boolean sair = false;
		
		while(!sair){
			String opcao = Dialogo.pegaValor(menu);
			switch(opcao){
				case "0":
					sair = true;
					break;
				case "1":
					pesquisaCliente();
					break;
				case "2":
					cadastraCategoria();
					break;
				case "3":
					pesquisaCategoria();
					break;
				case "4":
					cadastraUnidade();
					break;
				case "5":
					pesquisaUnidade();
					break;
				case "6":
					cadastraProduto();
					break;
				case "7":
					pesquisaProduto();
					break;
				case "8":
					excluirProduto();
					break;
				default:
					Dialogo.exibeMensagem("Opção Inválida");
					break;
			}
			
		}
		
		
	}
	private void excluirProduto() {
		Produto prod = pesquisaProduto(
				Dialogo.pegaValorInt("Digite o id do Produto"));
		
		if (prod == null){
			Dialogo.exibeMensagem("Registro não encontrado!!!");
			return;
		}
		else {
			(new ExcluirProdutoMain()).menu(prod);
			return;
		}		
	}
	private Produto pesquisaProduto(int id) {
		Produto p = new Produto();
		p.setId(id);
		
		List ret = (new ControleProduto()).buscar(p);
		if (ret.size() > 0){
			return (Produto) ret.get(0);
		}
		return null;
	}
	private void pesquisaProduto() {
		String descricao = Dialogo.pegaValor("Digite a descrição do produto:");
		Produto p = new Produto();
		p.setDescricao(descricao);
		
		List ret = (new ControleProduto()).buscar(p);
		
		if (ret.size() > 0){
			StringBuffer resposta = new StringBuffer();
			for(Object item: ret){
				Produto produto = (Produto) item;
				
				resposta.append(produto.getId()).append(",")
				.append(produto.getDescricao()).append("\n");
				
			}
			
			Dialogo.exibeMensagem(resposta.toString());
		}else{
			Dialogo.exibeMensagem("Registro não encontrado!!!");
		}
		
	}
	private void cadastraUnidade() {
		try{
			String sigla = Dialogo.pegaValor("Digite a sigla:");
			String descricao = Dialogo.pegaValor("Digite a descrição da unidade:");
			
			Unidade un = new Unidade();
			un.setSigla(sigla);
			un.setDescricao(descricao);
			
			(new ControleUnidade()).incluir(un);
			
			Dialogo.exibeMensagem("Unidade cadastrada!");
			
		} catch(ErroCadastroException e){
			Dialogo.exibeMensagem(e.getMessage());
			e.printStackTrace();
		}
		
	}
	private void cadastraProduto() {
		try{
			String descricao = Dialogo.pegaValor("Digite a descrição:");
			double valor = Dialogo.pegaValorDouble("Digite o valor:");
			long quantidade = Dialogo.pegaValorLong("Quantidade em estoque:");
			long qtdeminima = Dialogo.pegaValorLong("Quantidade mínima em estoque:");
			Categoria cat = pesquisaCategoria(
					Dialogo.pegaValorInt("Digite o id da Categoria"));
			Unidade un = pesquisaUnidade(
					Dialogo.pegaValor("Digite a sigla da Unidade"));
			
			Produto p = new Produto();
			p.setDescricao(descricao);
			p.setValor(valor);
			p.setEstoque(quantidade);
			p.setEstoqueMinimo(qtdeminima);
			p.setCategoria(cat);
			p.setUnidade(un);
			
			(new ControleProduto()).incluir(p);
			
			Dialogo.exibeMensagem("Produto cadastrado!");
			
		} catch(ErroCadastroException e){
			Dialogo.exibeMensagem(e.getMessage());
			e.printStackTrace();
		}
		
		
	}
	private Unidade pesquisaUnidade(String sigla) {
		Unidade un = new Unidade();
		un.setSigla(sigla);
		
		List ret = (new ControleUnidade()).buscar(un);
		if (ret.size() > 0){
			return (Unidade) ret.get(0);
		}
		return null;
		/*Unidade un = new Unidade();
		un.setId(1);
		un.setSigla("CX");
		un.setDescricao("Caixa");
		
		return un;*/
	}
	private void pesquisaUnidade() {
		String descricao = Dialogo.pegaValor("Digite a descrição da sigla:");
		Unidade un = new Unidade();
		un.setDescricao(descricao);
		
		List ret = (new ControleUnidade()).buscar(un);
		
		if (ret.size() > 0){
			StringBuffer resposta = new StringBuffer();
			for(Object item: ret){
				Unidade unidade = (Unidade) item;
				
				resposta.append(unidade.getId()).append(", ")
				.append(unidade.getSigla()).append(", ")
				.append(unidade.getDescricao()).append("\n");
				
			}
			
			Dialogo.exibeMensagem(resposta.toString());
		}else{
			Dialogo.exibeMensagem("Registro não encontrado!!!");
		}
		
	}
	private Categoria pesquisaCategoria(int id){
		Categoria c = new Categoria();
		c.setId(id);
		
		List ret = (new ControleCategoria()).buscar(c);
		if (ret.size() > 0){
			return (Categoria) ret.get(0);
		}
		return null;
	}
	private void pesquisaCategoria() {
		String descricao = Dialogo.pegaValor("Digite a descrição da categoria:");
		Categoria c = new Categoria();
		c.setDescricao(descricao);
		
		List ret = (new ControleCategoria()).buscar(c);
		
		if (ret.size() > 0){
			StringBuffer resposta = new StringBuffer();
			for(Object item: ret){
				Categoria cat = (Categoria) item;
				
				resposta.append(cat.getId()).append(",")
				.append(cat.getDescricao()).append("\n");
				
			}
			
			Dialogo.exibeMensagem(resposta.toString());
		}else{
			Dialogo.exibeMensagem("Registro não encontrado!!!");
		}
		
	}
	private void cadastraCategoria() {
		String descricao = Dialogo.pegaValor("Digite a descrição da categoria:");
		Categoria c = new Categoria();
		c.setDescricao(descricao);
		
		(new ControleCategoria()).incluir(c);
		
		Dialogo.exibeMensagem("Categoria cadastrada!!!");
		
	}
	private void pesquisaCliente() {
		long codigo = Dialogo.pegaValorLong("Digite o CPF/CNPJ ou ID:");
		Pessoa cliente = (new ControleCliente()).buscaCliente(codigo);
		
		if (cliente != null){
			StringBuffer resposta = new StringBuffer();
			resposta.append("ID: ").append(cliente.getId()).append("\n");
			resposta.append("Nome: ").append(cliente.getNome()).append("\n");
			if (cliente.getTipo() == 'J'){
				resposta.append("CNPJ: ")
				.append( ( (PessoaJuridica) cliente) .getCNPJ() )
				.append("\n");
			}else{
				resposta.append("CPF: ")
				.append( ( (PessoaFisica) cliente) .getCPF() )
				.append("\n");
			}
			
			Dialogo.exibeMensagem(resposta.toString());
		}else{
			Dialogo.exibeMensagem("Cliente não encontrado!");
		}
		
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
