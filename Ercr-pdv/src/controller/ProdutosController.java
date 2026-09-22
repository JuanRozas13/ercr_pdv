package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import database.Database;
import model.Fornecedor;
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
		// Adicionar Produto (CRUD create)===
		// ==================================

		public boolean adicionar(Produto produto) throws SQLException {
			// comando sql (passo1)
			try {
				String sql = """
						insert into produtos(
						codigoBarras, descricao,
						categoria, precoCusto, precoVenda, 
						quantidade, estoqueMin, idFornecedor)
						values(?,?,?,?,?,?,?,?)
						""";
				
				// abrir coxão com o banco (passo 2)
				Connection con = database.conectar();
				
				// executar o comando sql (passo 3)
				PreparedStatement stmt = con.prepareStatement(sql);
				// 1,2,3 = (?,?,?)
				
				//stmt.setString(1, produto.getCodigoBarras()); BUG
				
				//coreção de BUG se existir um ou mais produtos sem códigos de barras
				//para evitar a duplicidade (UNIQUE no banco), converter o campo de
				//texto não preenchido em null
				
				if (produto.getCodigoBarras().isBlank()) {
					stmt.setNull(1, java.sql.Types.VARCHAR);
				} else {
					stmt.setString(1, produto.getCodigoBarras());
				}
				
				stmt.setString(2, produto.getDescricao());
				stmt.setString(3, produto.getCategoria());
				stmt.setDouble(4, produto.getPrecoCusto());
				stmt.setDouble(5, produto.getPrecoVenda());
				stmt.setInt(6, produto.getQuantidade());
				stmt.setInt(7, produto.getEstoqueMin());
				stmt.setInt(8, produto.getIdFornecedor());
				stmt.executeUpdate();
				
				// fechar a coxão (passo 4)
				stmt.close();
				con.close();
				
				return true;
			} catch (Exception e) {
				System.out.println(e);
				return false;
			}
	} // fim crud adicionar cliente
		
	

	// ==================================
	// Buscar Produto (CRUD buscar)======
	// ==================================

		public Produto buscar(String nome) {
			try {
				String sql = """
						select idProduto, codigoBarras, descricao, categoria, precoCusto,
						precoVenda, quantidade, estoqueMin, idFornecedor
						from produtos
						where descricao like ?
						""";
				// Iniciar um objeto fornecedor como nulo
				Produto produto = null;

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
					produto = new Produto();
					produto.setIdProduto(rs.getInt("idProduto"));
					produto.setCodigoBarras(rs.getString("codigoBarras"));
					produto.setDescricao(rs.getString("descricao"));
					produto.setCategoria(rs.getString("categoria"));
					produto.setPrecoCusto(rs.getDouble("precoCusto"));
					produto.setPrecoVenda(rs.getDouble("precoVenda"));
					produto.setQuantidade(rs.getInt("quantidade"));
					produto.setEstoqueMin(rs.getInt("estoqueMin"));
					produto.setIdFornecedor(rs.getInt("idFornecedor"));
				}

				// fechar as conexões
				rs.close();
				stmt.close();
				con.close();

				return produto;

			} catch (Exception e) {
				System.out.println(e);
				return null;
			}
		}// =========================================

		// ==================================
		// Editar Produto (CRUD update)======
		// ==================================

		public void editarProduto(Produto produto) {
			try {
				String sql = """
						 update produtos
						 set codigoBarras = ?, descricao = ?, categoria = ?, precoCusto = ?,
						 precoVenda = ?, quantidade = ?, estoqueMin = ?
						 where idProduto = ?
						""";
				//Estabelecer a conexão com o banco
				Connection con = database.conectar();
				
				//Executar a instrução sql
				PreparedStatement stmt = con.prepareStatement(sql);
				
				//Obter os dados do fornecedor (Model)
				stmt.setString(1, produto.getCodigoBarras());
				stmt.setString(2, produto.getDescricao());
				stmt.setString(3, produto.getCategoria());
				stmt.setDouble(4, produto.getPrecoCusto());
				stmt.setDouble(5, produto.getPrecoVenda());
				stmt.setInt(6, produto.getQuantidade());
				stmt.setInt(7, produto.getEstoqueMin());
				stmt.setInt(8, produto.getIdProduto());
				
				
				//executa a atualização no banco
				stmt.executeUpdate();
				
				//encerrar as conexões
				stmt.close();
				con.close();
				
			} catch (Exception e) {
				System.out.println(e);
			}
		}// =========================================
		
		
		// ==============================================
		// Buscar Produto pelo barcode(CRUD buscar)======
		// ==============================================
		
		public Produto buscarCodigoBarras(String codigoBarras) {
			try {
				String sql = """
						select idProduto, descricao, categoria, precoCusto, precoVenda, quantidade, estoqueMin, idFornecedor
						from produtos
						where codigoBarras = ?
						""";
				
				//iniciar um onjeto como nulo
				Produto produto = null;
						
				// JDBC (Connection e PreparedStatement)
				Connection con = database.conectar();
				PreparedStatement stmt = con.prepareStatement(sql);
				
				// setar o código (?)
				stmt.setString(1, codigoBarras);
				
				// JDBC (ResultSet) = "trazer os dados do banco"
				ResultSet rs = stmt.executeQuery();
				
				//se existir um produto cadastrado
				if(rs.next()) {
					//setar o model
					produto = new Produto();
					produto.setIdProduto(rs.getInt("idProduto"));
					produto.setDescricao(rs.getString("descricao"));
					produto.setCategoria(rs.getString("categoria"));
					produto.setPrecoCusto(rs.getDouble("precoCusto"));
					produto.setPrecoVenda(rs.getDouble("precoVenda"));
					produto.setQuantidade(rs.getInt("quantidade"));
					produto.setEstoqueMin(rs.getInt("estoqueMin"));
					produto.setIdFornecedor(rs.getInt("idFornecedor"));
				}
				
				// fechar as conexões
				rs.close();
				stmt.close();
				con.close();
				
				//retornar o objeto produto (contém os atributos)
				return produto;
				
			} catch (Exception e) {
				System.out.println(e);
				return null;
			}
		}// =========================================
		
		
		// ======================================
		// Excluir dados cliente (CRUD delete)===
		// ======================================

		public void excluirCliente(int idCliente) {
			try {
				String sql ="""
						delete from produtos
						where idProduto = ?
						""";
				
				//Estaberlecer a conexão com o banco
				Connection con = database.conectar();
				
				//executar a instrução sql
				PreparedStatement stmt = con.prepareStatement(sql);
				
				//setar o id do cliente no (model)
				stmt.setInt(1, idCliente);
				
				//executa a atualização no banco
				stmt.executeUpdate();
				//encerrar as conexões
				stmt.close();
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
}
