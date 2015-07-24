package br.com.mercadoze.main;

import java.sql.Connection;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.swing.JOptionPane;

import br.com.mercadoze.dao.ConexaoBanco;
import br.com.mercadoze.entidade.Pessoa;
import br.com.mercadoze.entidade.Pessoa.TipoPessoa;

public class TestesMain {

	public static void main(String[] args) {

		// Connection c = (new ConexaoBanco()).conectar();
		//
		// if (c != null){
		// System.out.println("Conectado");
		// }else{
		// System.out.println("Erro na conexão");
		// }

		/*
		 * Map mapTest = new HashMap();
		 * 
		 * mapTest.put(12345, "Joao"); mapTest.put(33333, "Maria");
		 * mapTest.put(11, "Onze"); mapTest.put(22, "Dois Patos");
		 * 
		 * //substituir mapTest.put(22, "Outros dois patos");
		 * 
		 * 
		 * // imprimir System.out.println(mapTest.get(11));
		 * 
		 * // pegar todas as chaves for (Object key: mapTest.keySet()){
		 * System.out.println( "chave: " + key.toString() + ", valor: " +
		 * mapTest.get(key)); }
		 * 
		 * Map<TipoPessoa, List<Pessoa>> mapa = new HashMap<TipoPessoa,
		 * List<Pessoa>>();
		 * 
		 * mapa.put(TipoPessoa.Fisica, new ArrayList<Pessoa>());
		 * mapa.put(TipoPessoa.Juridica, new ArrayList<Pessoa>());
		 * mapa.put(TipoPessoa.Funcionario, new ArrayList<Pessoa>());
		 * 
		 * 
		 * List<Pessoa> pessoas = mapa.get(TipoPessoa.Fisica); // não precisa
		 * salvar a list dentro de map, pois Java trabalha com referência, ou
		 * seja, // já está manipulando a própria list dentro de map
		 * pessoas.add(new Pessoa());
		 */

		/*DateFormat dtf = DateFormat.getDateInstance(0,
				Locale.forLanguageTag("pt-BR"));
		System.out.println(dtf.format(new Date()));
		String data = JOptionPane.showInputDialog("Digite uma data");

		try {
			dtf = new SimpleDateFormat("dd/MM/yyyy");
			Date dataDigitada = dtf.parse(data);
			Timestamp tp = new Timestamp(dataDigitada.getTime());
			System.out.println(tp);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		try{
			ProcessoParalelo p = new ProcessoParalelo("teste 1");
			Thread t = new Thread(p);
			t.start();
			Thread.currentThread().sleep(1000);
			p = new ProcessoParalelo("teste 2");
			t = new Thread(p);
			t.start();
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
