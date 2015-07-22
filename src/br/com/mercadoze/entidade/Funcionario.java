package br.com.mercadoze.entidade;

public class Funcionario extends PessoaFisica {
	
	private long matricula;
	private long CTPS;
	private double salario;
	
	public long getMatricula() {
		return matricula;
	}
	public void setMatricula(long matricula) {
		this.matricula = matricula;
	}
	public long getCTPS() {
		return CTPS;
	}
	public void setCTPS(long cTPS) {
		CTPS = cTPS;
	}
	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}
	
		
}
