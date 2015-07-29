package br.com.mercadoze.main;

public class ProcessoParalelo implements Runnable {

	private String nome;
	
	
	public ProcessoParalelo(String nome){
		this.nome = nome;
	}
	
	@Override
	public void run() {
		while (true) {
			System.out.println(this.nome);
			try {
				Thread.currentThread().sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
