package br.com.mercadoze.exception;

public class ErroCadastroException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String objeto;
	
	public ErroCadastroException(){
		super();
	}
	
	public ErroCadastroException(String mensagem){
		super(mensagem);
	}
	
	public ErroCadastroException(String mensagem, String objeto){
		super(mensagem);
		this.objeto = objeto;
	}

	public String getObjeto() {
		return objeto;
	}

	public void setObjeto(String objeto) {
		this.objeto = objeto;
	}
	
	
}
