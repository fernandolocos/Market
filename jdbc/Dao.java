package br.com.conexaoBanco.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Dao {

	private Connection con;

	private Statement comando;
	
    private static final String PostgreSQL = "org.postgresql.Driver";
    private static final String MySQL = "com.mysql.jdbc.Driver";
    
    public static Connection conexao() throws ClassNotFoundException, SQLException {
        Class.forName(PostgreSQL);
        //jdbc:postgresql://localhost:5432/<nome do bando de dados>", "<usuario>", "<senha>"
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/agenda", "dextra", "123");
    }

//	public static Connection conexao() throws ClassNotFoundException,
//			SQLException {
//
//		Context ctx;
//		Connection con = null;
//		try {
//			ctx = new InitialContext();
//			DataSource ds = (DataSource) ctx.lookup("jdbc/mysqltest");
//			con = ds.getConnection();
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//		return con;
//	}

	public void conectar() {
		try {
			con = Dao.conexao();
			comando = con.createStatement();
			System.out.println("Conectado");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void fechar() {
		try {
			comando.close();
			con.close();
			System.out.println("Conexao fechada");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Statement getComando() {
		return comando;
	}

	public void setComando(Statement comando) {
		this.comando = comando;
	}
}
