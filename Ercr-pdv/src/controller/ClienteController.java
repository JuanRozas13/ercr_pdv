package controller;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

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
	// Adicionar cliente (CRUD create)==
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
	// Buscar o cliente (CRUD Read)
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
	
	// ===============================
	// Editar dados (CRUD updates)===
	// ===============================
		
	public void editarCliente(Cliente cliente) {
		try {
			String sql = """
					update clientes
					set nome = ?,
					fone = ?,
					email = ?
					where idClientes = ?
					""";
			
			//Estaberlecer a conexão com o banco
			Connection con = database.conectar();
			
			//executar a instrução sql
			PreparedStatement stmt = con.prepareStatement(sql);
			
			//obter os dados do cliente(model)
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getFone());
			stmt.setString(3, cliente.getEmail());
			stmt.setInt(4, cliente.getIdClientes());
			
			//executa a atualização no banco
			stmt.executeUpdate();
			//encerrar as conexões
			stmt.close();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	// ======================================
	// Excluir dados cliente (CRUD delete)===
	// ======================================

	public void excluirCliente(int idCliente) {
		try {
			String sql ="""
					delete from clientes
					where idClientes = ?
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
	

	// =========================================
	// =============== GERAR RELATORIOS DE CLIENTE (PDF) ================
	// =========================================
	public void gerarRelatorioCliente(){
		try {
			
			String sql = """
					select nome, fone, email
					from clientes order by nome
					""" ;
			
			//abrir conexão com o banco 
			Connection con = database.conectar();
			
			//preparar o comando SQL
			PreparedStatement stmt = con.prepareStatement(sql);
			
			//executar a conslta
			ResultSet rs = stmt.executeQuery();
			
			//nome do arquivo
			// Atenção importar da biblioteca com.lowagie.text
			Document documento = new Document();
			
			// nome do aqrquivo pdf
			String caminho = "relatorio_clientes.pdf";
			
			//criar o arquivo pdf
			PdfWriter.getInstance(documento, new FileOutputStream(caminho));
			
			//abrir o documento >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
			documento.open();
			
			//titulo >>>>>>>>>>>>
			Font fonteTitulo = new Font(
				Font.HELVETICA,
				18,
				Font.BOLD
			);
			
			Paragraph titulo = new Paragraph(
				"RELATÓRIO DE CLIENTES",
				fonteTitulo
			);
			
			titulo.setAlignment(Element.ALIGN_CENTER);
			documento.add(titulo);
			//titulo <<<<<<<<<<
			
			//Data e hora >>>>>>>>>>
			DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
			
			String dataHora = LocalDateTime.now().format(formato);
			
			Paragraph data = new Paragraph(
					"Data de emissão: " + dataHora
			);
			
			data.setAlignment(Element.ALIGN_CENTER);
			
			documento.add(data);
			
			//Espaço
			documento.add(new Paragraph(" "));
			documento.add(new Paragraph(" "));
			
			//Data e hora <<<<<<<<<<<<
			
			//Tabela inicio --------
			
			//criar a tabela com 3 colunas
			PdfPTable tabela = new PdfPTable(3);
			
			//definir largura das colunas
			tabela.setWidths(new float[] {
				2.5f, 2.0f, 3.0f
			});
			
			// ocupar toda a largura disponivel
			tabela.setWidthPercentage(100);
			
			//cabeçalho da tabela
			tabela.addCell("Nome");
			tabela.addCell("Fone");
			tabela.addCell("E-mail");
			
			//dados do cliente
			int quantidade = 0; //variavel de apoio
			
			//enquanto existir clientes, adiconar a tabela
			while (rs.next()) {
				tabela.addCell(rs.getString("nome"));
				tabela.addCell(rs.getString("fone"));
				tabela.addCell(rs.getString("email"));
				//somar a quantidade, atribuindo a variavel
				quantidade++;
			}			
			
			//adicionar a tabela ao documento
			documento.add(tabela);
			// tabela fim -------
		
			
			documento.add(new Paragraph(" "));
		
			//total de clientes
			Font fonteTotal = new Font(
				Font.HELVETICA,
				10,
				Font.BOLD
			);
			
			Paragraph total = new Paragraph(
					"Total de clientes: " + quantidade,
					fonteTotal
			);
			
			total.setAlignment(Element.ALIGN_RIGHT);
			
			documento.add(total);
			
			//fechar o documento <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
			documento.close();
			
			//fechar os recursos do banco de dados
			rs.close();
			stmt.close();
			con.close();
			
			//abrir o pdf automaticamente no leitor padrão do pdf
			File arquivo = new File(caminho);
			Desktop.getDesktop().open(arquivo);;
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
}
