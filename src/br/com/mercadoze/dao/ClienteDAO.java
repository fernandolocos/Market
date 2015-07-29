package br.com.mercadoze.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.mercadoze.entidade.Funcionario;
import br.com.mercadoze.entidade.Pessoa;
import br.com.mercadoze.entidade.PessoaFisica;
import br.com.mercadoze.entidade.PessoaJuridica;

public class ClienteDAO {
		
	/**
	 * Busca o cliente
	 * @param codigo - CPF ou CNPJ do cliente
	 * @return cliente ou null se nao encontrar
	 */
	public Pessoa buscaCliente(long codigo){
		
		PessoaFisica fisica = new PessoaFisica();
		fisica.setId(1);
		fisica.setNome("Joselino");
		fisica.setCPF(12312312333L);
		fisica.setTipo('F');
		
		PessoaJuridica juridica = new PessoaJuridica();
		juridica.setId(100);
		juridica.setNome("Teste representacoes");
		juridica.setCNPJ(12123000112L);
		juridica.setNomeFantasia("Testes");
		juridica.setTipo('J');
		
		Funcionario fun = new Funcionario();
		fun.setNome("Ana");
		fun.setId(30);
		fun.setCPF(123123123L);
		fun.setMatricula(1234L);
		fun.setTipo('C');
		
		List<Pessoa> clientes = new ArrayList<Pessoa>();
		clientes.add(fisica);
		clientes.add(juridica);
		clientes.add(fun);
		
		
		for(Pessoa p: clientes){
			if (p instanceof PessoaFisica){
				PessoaFisica f = (PessoaFisica) p;
				if (f.getCPF() == codigo || f.getId() == codigo){
					return f;
				}
			}else if (p instanceof PessoaJuridica){
				PessoaJuridica j = (PessoaJuridica) p;
				if (j.getCNPJ() == codigo || j.getId() == codigo){
					return j;
				}
			}
		}
		
//		for(Pessoa p: clientes){
//			if (p.getTipo() == 'F' || p.getTipo() == 'C'){
//				PessoaFisica f = (PessoaFisica) p;
//				if (f.getCPF() == codigo){
//					return f;
//				}
//			}else if (p.getTipo() == 'J'){
//				PessoaJuridica j = (PessoaJuridica) p;
//				if (j.getCNPJ() == codigo){
//					return j;
//				}
//			}
//		}
		
		return null;
				
	}
}
