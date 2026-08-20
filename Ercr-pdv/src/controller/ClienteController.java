package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
	
	// ===============================
	// Buscar o Fornecedor (CRUD Read)
	// ===============================
	
	public Cliente buscar(String nome) {
		try {
		String sql = """
				select idClientes, nome, fone, email 
				from clientes
				where nome like ?; 
				""";
		// iniciar um objeto como nulo
		Cliente cliente = null;
		
		//JDBC (Connectio e PreparedDtatement)
		Connection con = database.conectar();
		PreparedStatement stmt = con.prepareStatement(sql);
		
		//setar a consulta
		stmt.setString(1, "%" + nome + "%");
		
		//JDBC (ResultSet) = responsavel por trazer os dados do banco
		ResultSet rs = stmt.executeQuery();
		
		//se existir um cliente com o nome pesquisado
		if (rs.next()) {
			//setar o model
			cliente = new Cliente();
			cliente.setIdClientes(rs.getInt("idClientes"));
			cliente.setNome(rs.getString("nome"));
			cliente.setFone(rs.getString("fone"));
			cliente.setEmail(rs.getString("email"));
		}
		
		// fechar as conexões
		rs.close();
		stmt.close();
		con.close();
		
		return cliente;
		
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

}
