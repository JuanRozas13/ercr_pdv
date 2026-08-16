package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {	

	// Informações do banco de dados
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/ercrPdv";
	private String user = "dba";
	private String password = "Mint2836";
	
	// método (FUnção) para conectar o  banco
	public Connection conectar() {
		try {
			Class.forName(driver);
			return DriverManager.getConnection(url, user, password); // abre a conexão com banco
			
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	// método para testar a conexão (status de conexão)
	public boolean testarConexao() {
		// a linha abaixo cria o obto con que é responsavel por estabelecer a conexão com o banco
		Connection con = conectar(); 
		
		// se não conseguir estabelecer a conexão
		if (con == null) {
			return false;
		}
		
		// se não conseguir fechar a conexão
		try {
			con.close(); // encerrar conexão após o teste
			return true; //retorno verdadeiro para main
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
}
