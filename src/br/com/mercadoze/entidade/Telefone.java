package br.com.mercadoze.entidade;

public class Telefone {
		private long id;
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		public int getDdd() {
			return ddd;
		}
		public void setDdd(int ddd) {
			this.ddd = ddd;
		}
		public int getNumero() {
			return numero;
		}
		public void setNumero(int numero) {
			this.numero = numero;
		}
		public String getTipo() {
			return tipo;
		}
		public void setTipo(String tipo) {
			this.tipo = tipo;
		}
		public int getRamal() {
			return ramal;
		}
		public void setRamal(int ramal) {
			this.ramal = ramal;
		}
		private int ddd;
		private int numero;
		private String tipo;
		private int ramal;
}
