package br.com.mercadoze.iu;

import javax.swing.JOptionPane;

public class Dialogo {

	public static final int TENTATIVAS = 3;

	public static void exibeMensagem(String mensagem) {
		JOptionPane.showMessageDialog(null, mensagem);
	}

	public static String pegaValor(String texto) {

		for (int i = 0; i < TENTATIVAS; i++) {
			String v = JOptionPane.showInputDialog(texto);
			if (!v.trim().equals("")) {
				return v;
			}
		}

		return null;

	}

	public static Long pegaValorLong(String texto) {

		for (int i = 0; i < TENTATIVAS; i++) {
			String v = pegaValor(texto);

			try {
				Long l = Long.parseLong(v);
				return l;
			} catch (Exception e) {
				// nada faz
			}

		}

		return null;
	}

	public static Integer pegaValorInt(String texto) {

		for (int i = 0; i < TENTATIVAS; i++) {
			String v = pegaValor(texto);

			try {
				Integer l = Integer.parseInt(v);
				return l;
			} catch (Exception e) {
				// nada faz
			}

		}

		return null;
	}

	public static Double pegaValorDouble(String texto) {

		for (int i = 0; i < TENTATIVAS; i++) {
			String v = pegaValor(texto);

			try {
				Double l = Double.parseDouble(v);
				return l;
			} catch (Exception e) {
				// nada faz
			}

		}

		return null;
	}

}
