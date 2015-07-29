package br.com.mercadoze.entidade;

public class PessoaJuridica extends Pessoa {

		private long CNPJ;
		private long IE;
		private long IM;
		private String nomeFantasia;
		
		public long getCNPJ() {
			return CNPJ;
		}
		public void setCNPJ(long cNPJ) {
			CNPJ = cNPJ;
		}
		public long getIE() {
			return IE;
		}
		public void setIE(long iE) {
			IE = iE;
		}
		public long getIM() {
			return IM;
		}
		public void setIM(long iM) {
			IM = iM;
		}
		public String getNomeFantasia() {
			return nomeFantasia;
		}
		public void setNomeFantasia(String nomeFantasia) {
			this.nomeFantasia = nomeFantasia;
		}
		
}
