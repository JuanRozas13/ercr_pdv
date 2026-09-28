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
import java.util.ArrayList;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
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
			stmt.setInt(5, fornecedor.getIdFornecedor());
			
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
				2.5f, 2.0f, 3.5f, 4.0f	
			});
			
			// ocupar toda a largura disponivel
			tabela.setWidthPercentage(100);
			
			java.awt.Color corCabecalho = new java.awt.Color(45, 62, 80);
			Font fonteCabecalho = new Font(Font.HELVETICA, 9, Font.BOLD, java.awt.Color.WHITE); 
			String[] colunas = {"Nome", "Telefone", "Email", "Site"};
			for (String nomeColuna: colunas) {
				PdfPCell cellHeader = new PdfPCell(new Paragraph(nomeColuna, fonteCabecalho));
				cellHeader.setBackgroundColor(corCabecalho);
				cellHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
				cellHeader.setHorizontalAlignment(Element.ALIGN_MIDDLE);
				cellHeader.setPadding(5f); //espaçamento interno
				tabela.addCell(cellHeader);
			}
			
			//dados do fornecedor
			int quantidade = 0; //variavel de apoio
			
			// fonte dos dados
			Font fonteNormal = new Font(Font.HELVETICA, 9, Font.NORMAL);
			
			//enquanto existir fornecedores, adiconar a tabela
			while (rs.next()) {
				tabela.addCell(new Paragraph((rs.getString("nome")), fonteNormal));
				tabela.addCell(new Paragraph((rs.getString("fone")), fonteNormal));
				tabela.addCell(new Paragraph((rs.getString("email")), fonteNormal));
				tabela.addCell(new Paragraph((rs.getString("site")), fonteNormal));
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
			Desktop.getDesktop().open(arquivo);
			
		} catch (Exception e) {
			System.out.println(e);
		}
	} // fim relatório fornecedores
	
	//=================================
	// Listar fornecedores -> produtos=
	//=================================
	
	
	//Para crirar uma lista é necessário instancia um Array no método e também criar um objeto que será usado no array
	public ArrayList <Fornecedor> listarFornecedores() {
		//objeto lista
		ArrayList<Fornecedor> lista = new ArrayList<>();
		
		try {
			
			// buscar o id e o nome dos fornecedores
			String sql = """
					select idFornecedor, nome
					from fornecedores
					order by nome
					""";
			
			// Abrir conexão com o JDBC
			Connection con = database.conectar();			
			
			// Preparar o comando sql[
			PreparedStatement stmt = con.prepareStatement(sql);
			
			// Obter os dados di banco
			ResultSet rs = stmt.executeQuery();
			
			// enquanto existir fornecedores cadastrados
			while (rs.next()) {
				// criar o objeto fornecedor
				Fornecedor fornecedor= new Fornecedor();
				
				// Armazenar no objeto os Ids e nomes
				fornecedor.setIdFornecedor(rs.getInt("idFornecedor"));
				fornecedor.setNome(rs.getString("nome"));
				
				// Adicionar os fornecedores a lista(array)
				lista.add(fornecedor);
				
			}
			//encerrar os recursos do JDBC
			rs.close();
			stmt.close();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}

		// apoio a logica e depuração (debug) "observação"
//		System.out.println(lista);
		// retornar a lista de fornecedores
		return lista;
	}
}
