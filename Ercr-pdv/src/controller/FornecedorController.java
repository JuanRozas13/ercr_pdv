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
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;

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
	
	
	// =========================================
	// =============== GERAR RELATORIOS DE FORNECEDORES (PDF) ================
	// =========================================
	public void gerarRelatorioFornecedores(){
		try {
			
			String sql = """
					select nome, fone, email, site
					from fornecedores order by nome
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
			String caminho = "relatorio_fornecedores.pdf";
			
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
				"RELATÓRIO DE FORNECEDORES",
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
			
			//criar a tabela com 4 colunas
			PdfPTable tabela = new PdfPTable(4);
			
			//definir largura das colunas
			tabela.setWidths(new float[] {
				2.5f, 2.0f, 3.0f, 4.0f	
			});
			
			// ocupar toda a largura disponivel
			tabela.setWidthPercentage(100);
			
			//cabeçalho da tabela
			tabela.addCell("Nome");
			tabela.addCell("Fone");
			tabela.addCell("E-mail");
			tabela.addCell("Site");
			
			//dados do fornecedor
			int quantidade = 0; //variavel de apoio
			
			//enquanto existir fornecedores, adiconar a tabela
			while (rs.next()) {
				tabela.addCell(rs.getString("nome"));
				tabela.addCell(rs.getString("fone"));
				tabela.addCell(rs.getString("email"));
				tabela.addCell(rs.getString("site"));
				//somar a quantidade, atribuindo a variavel
				quantidade++;
			}			
			
			//adicionar a tabela ao documento
			documento.add(tabela);
			// tabela fim -------
		
			
			documento.add(new Paragraph(" "));
		
			//total de fornecedores
			Font fonteTotal = new Font(
				Font.HELVETICA,
				10,
				Font.BOLD
			);
			
			Paragraph total = new Paragraph(
					"Total de fornecedores: " + quantidade,
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
