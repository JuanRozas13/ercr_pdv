package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// importação de database
import database.Database;
// importação do modelo de dados
import model.Cliente;

public class ClienteController {
	// Intanciar o banco de dados
	private Database database;

	// Construtor
	public ClienteController() {
		// reutilizar o database no CRUD
		database = new Database();
	}

	// métodos (funções)CRUD

	// ==================================
	// Adicionar cliente (CRUD create)
	// ==================================

	public void adicionar(Cliente cliente) throws SQLException {
		// comando sql (passo1)
		String sql = """
				insert into clientes (nome, fone, email)
				values(?,?,?)
				""";
		
		// abrir coxão com o banco (passo 2)
		Connection con = database.conectar();
		
		// executar o comando sql (passo 3)
		PreparedStatement stmt = con.prepareStatement(sql);
		// 1,2,3 = (?,?,?)
		stmt.setString(1, cliente.getNome());
		stmt.setString(2, cliente.getFone());
		stmt.setString(3, cliente.getEmail());
		stmt.executeUpdate();
		
		// fechar a coxão (passo 4)
		stmt.close();
		con.close();
	}
	
	// Fim CRUD create ===============

}
