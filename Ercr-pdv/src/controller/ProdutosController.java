package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import database.Database;
import model.Produto;
//import 
public class ProdutosController {
	// Intanciar o banco de dados
	private Database database;
	
	// Construtor
	public ProdutosController() {
		// reutilizar o database no CRUD
		database = new Database();
	}
	
		// métodos (funções)CRUD

		// ==================================
		// Adicionar cliente (CRUD create)===
		// ==================================

		public void adicionar(Produto produto) throws SQLException {
			// comando sql (passo1)
			String sql = """
					insert into produtos(
					idProduto, codigoBarras, descricao,
					categoria, precoCusto, precoVenda, 
					quantidade, estoqueMin, idFornecedor)
					values(?,?,?,?,?,?,?,?,?)
					""";
			
			// abrir coxão com o banco (passo 2)
			Connection con = database.conectar();
			
			// executar o comando sql (passo 3)
			PreparedStatement stmt = con.prepareStatement(sql);
			// 1,2,3 = (?,?,?)
			stmt.setInt(1, produto.getIdProduto());
			stmt.setString(2, produto.getCodigoBarras());
			stmt.setString(3, produto.getDescricao());
			stmt.setString(4, produto.getCategoria());
			stmt.setDouble(5, produto.getPrecoCusto());
			stmt.setDouble(6, produto.getPrecoVenda());
			stmt.setInt(7, produto.getQuantidade());
			stmt.setInt(8, produto.getEstoqueMin());
			stmt.setInt(9, produto.getIdFornecedor());
			stmt.executeUpdate();
			
			// fechar a coxão (passo 4)
			stmt.close();
			con.close();
	}
}
