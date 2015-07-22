package br.com.mercadoze.entidade;

import java.util.Date;
import java.util.List;

public class Pedido {

		private long id;
		private Pessoa cliente;
		private Date data;
		private double valor;
		private Funcionario funcionario;
		private List<Saida> saida;
		
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		public Pessoa getCliente() {
			return cliente;
		}
		public void setCliente(Pessoa cliente) {
			this.cliente = cliente;
		}
		public Date getData() {
			return data;
		}
		public void setData(Date data) {
			this.data = data;
		}
		public double getValor() {
			return valor;
		}
		public void setValor(double valor) {
			this.valor = valor;
		}
		public Funcionario getFuncionario() {
			return funcionario;
		}
		public void setFuncionario(Funcionario funcionario) {
			this.funcionario = funcionario;
		}
		public List<Saida> getSaida() {
			return saida;
		}
		public void setSaida(List<Saida> saida) {
			this.saida = saida;
		}

}
