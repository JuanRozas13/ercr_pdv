package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//importação de database
import database.Database;
//importação do modelo de dados
import model.Fornecedor;

public class FornecedorController {
	// Instanciar o banco de dados
	private Database database;

	// Construtor
	public FornecedorController() {
		// reutilizar o database no CRUD
		database = new Database();
	}

	// Métodos(funções) CRUD

	// =========================================
	// Adicionar fornecedor (CRUD Create)
	// =========================================

	public void adicionar(Fornecedor fornecedor) throws SQLException {
		// comando sql (passo 1)
		String sql = """
				insert into fornecedores (nome, fone, email, site)
				values (?,?,?,?)
				""";
		// abrir a conexão com o banco (passo 2)
		Connection con = database.conectar();

		// executar o comando sql (passo 3)
		PreparedStatement stmt = con.prepareStatement(sql);
		// 1,2,3 = (?,?,?)
		stmt.setString(1, fornecedor.getNome());
		stmt.setString(2, fornecedor.getFone());
		stmt.setString(3, fornecedor.getEmail());
		stmt.setString(4, fornecedor.getSite());
		stmt.executeUpdate();

		// fechar a conexão (passo 4)
		stmt.close();
		con.close();
	}
	// Fim CRUD Create ==========================

	// =========================================
	// Buscar fornecedor (CRUD Read)
	// =========================================
	public Fornecedor buscar(String nome) {
		try {
			String sql = """
					select idFornecedor, nome, fone, email, site
					from fornecedores
					where nome like ?
					""";
			// Iniciar um objeto fornecedor como nulo
			Fornecedor fornecedor = null;

			// JDBC (Connection e PreparedStatement)
			Connection con = database.conectar();
			PreparedStatement stmt = con.prepareStatement(sql);

			// setar a consulta (% coringa)
			stmt.setString(1, "%" + nome + "%");

			// JDBC (ResultSet) = "trazer os dados do banco"
			ResultSet rs = stmt.executeQuery();

			// se existir um fornecedor com o nome pesquisado
			if (rs.next()) {
				// setar o model
				fornecedor = new Fornecedor();
				fornecedor.setIdFornecedor(rs.getInt("idFornecedor"));
				fornecedor.setNome(rs.getString("nome"));
				fornecedor.setFone(rs.getString("fone"));
				fornecedor.setEmail(rs.getString("email"));
				fornecedor.setSite(rs.getString("site"));
			}

			// fechar as conexões
			rs.close();
			stmt.close();
			con.close();

			return fornecedor;

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	// =========================================

	
	// =========================================
	// CRUD Update - Editar os dados ===========
	// =========================================
	public void editarFornecedor(Fornecedor fornecedor) {
		try {
			String sql = """
					 update fornecedores
					 set nome = ?, fone = ?, email = ?, site = ?
					 where idFornecedor = ?
					""";
			//Estabelecer a conexão com o banco
			Connection con = database.conectar();
			
			//Executar a instrução sql
			PreparedStatement stmt = con.prepareStatement(sql);
			
			//Obter os dados do fornecedor (Model)
			stmt.setString(1, fornecedor.getNome());
			stmt.setString(2, fornecedor.getFone());
			stmt.setString(3, fornecedor.getEmail());
			stmt.setString(4, fornecedor.getSite());
			stmt.setInt(4, fornecedor.getIdFornecedor());
			
			//executa a atualização no banco
			stmt.executeUpdate();
			
			//encerrar as conexões
			stmt.close();
			con.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	// =========================================
	
	
	// =========================================
	// CRUD Delete - Excluir o fornecedor ======
	// =========================================
	public void excluir(int idFornecedor) {
		try {
			
			String sql = """
						delete from fornecedores
						where idFornecedor = ?
					""";
		
			//Abrir a conexão com o banco
			Connection con = database.conectar();
			
			//Executar a query (instrução sql)
			PreparedStatement stmt = con.prepareStatement(sql);
			
			//setar o id do fornecedor (model)
			stmt.setInt(1, idFornecedor);
			
			//executar o delete
			stmt.executeUpdate();
			
			//encerrar as conexões
			stmt.close();
			con.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	// =========================================
}
