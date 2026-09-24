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
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

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
		
		public void gerarRelatorioProdutos() {
			try {
				//Consulta sql com inner join
				String sql = """
					select	
						p.codigoBarras,
						p.descricao,
					    p.categoria,
					    f.nome as fornecedor,
					    p.precoCusto,
					    p.precoVenda,
						p.quantidade,
					    p.estoqueMin
					from produtos p 
					right join fornecedores f on p.idFornecedor = f.idFornecedor
					order by descricao
					""";
				
				//abrir conexão com o banco 
				Connection con = database.conectar();
				
				//preparar o comando SQL
				PreparedStatement stmt = con.prepareStatement(sql);
				
				//executar a conslta
				ResultSet rs = stmt.executeQuery();
					
				// criar o objeto documento (pdf)
				Document documento = new Document(PageSize.A4.rotate());	
				
				// caminho e nome do arquivp
				String caminho = "relatorio_produtos.pdf";
				
				//criar o arquivo pdf
				PdfWriter.getInstance(documento, new FileOutputStream(caminho));
				
				// Abrir o documento (formatar o documento pdf)
				documento.open();
				
				// titulo 
				Font fonteTitulo = new Font(Font.HELVETICA, 16, Font.BOLD);
				Paragraph titulo = new Paragraph("RÉLATORIO DE CONTROLE DE ESTOQUE", fonteTitulo);
				titulo.setAlignment(Element.ALIGN_CENTER);
				documento.add(titulo);
				
				// Data e hora
				DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
				String dataHora = LocalDateTime.now().format(formato);
				Paragraph data = new Paragraph("Data de emissão: " + dataHora);
				data.setAlignment(Element.ALIGN_CENTER);
				documento.add(data);
				
				//espaço
				documento.add(new Paragraph(" "));
				
				// tabela ajustada para 8 colunas
				PdfPTable tabela = new PdfPTable(8);
				
				// ajustar larguras das colunas 
				tabela.setWidths(new float[] { 2.2f, 3.5f, 2.3f, 3.0f, 1.8f, 1.8f, 2.0f, 1.8f});
				tabela.setWidthPercentage(100);
				
				//cabeçalho personalizado da tabela
				Font fonteCabecalho = new Font(Font.HELVETICA, 9, Font.BOLD, java.awt.Color.WHITE);
				String[] colunas = {"Código de barras", "Descrição", "Categoria", "Fornecedor", "Custo (R$)", "Venda (R$)", "Qtd. Estoque", "Estoque Min"};
				for (String nomeColuna: colunas) {
					PdfPCell cellHeader = new PdfPCell(new Paragraph(nomeColuna, fonteCabecalho));
					cellHeader.setBackgroundColor(java.awt.Color.DARK_GRAY);
					cellHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
					cellHeader.setHorizontalAlignment(Element.ALIGN_MIDDLE);
					cellHeader.setPadding(5f); //espaçamento interno
					tabela.addCell(cellHeader);
				}
				
				// cores de background para identificação rápida
				java.awt.Color corEstoqueZerado = new java.awt.Color(220, 53, 69);
				java.awt.Color corAlertaEstoque = new java.awt.Color(255, 193, 7);
				
				
				//estilo de fontes para os dados e alertas
				Font fonteNormal = new Font(Font.HELVETICA, 9, Font.NORMAL);
				Font fonteAlertaBranca = new Font(Font.HELVETICA, 9, Font.BOLD, java.awt.Color.WHITE);
				Font fonteAlertaEscura = new Font(Font.HELVETICA, 9, Font.BOLD, java.awt.Color.BLACK);
				
				// Dados da tabela
				while (rs.next()) {
					//Capturar dados para personalizar alertas (variaveis de apoio)
					int quantidade = rs.getInt("quantidade");
					int estoqueMin = rs.getInt("estoqueMin");
					
					tabela.addCell(new Paragraph(rs.getString("codigoBarras"), fonteNormal));
					tabela.addCell(new Paragraph(rs.getString("descricao"), fonteNormal));
					tabela.addCell(new Paragraph(rs.getString("categoria"), fonteNormal));
					tabela.addCell(new Paragraph(rs.getString("fornecedor"), fonteNormal));
					//String.format("%.2f") converte para String e formata 2 casas decímal
					tabela.addCell(new Paragraph(String.format("%.2f",rs.getDouble("precoCusto"),fonteNormal)));
					tabela.addCell(new Paragraph(String.format("%.2f",rs.getDouble("precoVenda"), fonteNormal)));
					//logica para mudar a formatação da célula se estoque zerado ou menor que estoque 
					PdfPCell cellQtde;
					if (quantidade == 0) {
						cellQtde = new PdfPCell(new Paragraph(quantidade + " ( Zerado ) ", fonteAlertaBranca));
						cellQtde.setBackgroundColor(corAlertaEstoque);
						
					} else if (quantidade <= estoqueMin) {
						cellQtde = new PdfPCell(new Paragraph(quantidade + " ( Repor ) ", fonteAlertaEscura));
						cellQtde.setBackgroundColor(corEstoqueZerado);
					}else {
						cellQtde = new PdfPCell(new Paragraph(String.valueOf(quantidade),fonteNormal));
					}
					
					cellQtde.setVerticalAlignment(Element.ALIGN_MIDDLE);
					tabela.addCell(cellQtde);
					//tabela.addCell(new Paragraph(String.valueOf(rs.getInt("quantidade"))));
					tabela.addCell(new Paragraph(String.valueOf(estoqueMin), fonteNormal));
				}
				
				//adicionar a tabela ao documento
				documento.add(tabela);
				
				// fechar o documento (fim da formatação)
				documento.close();
				
				//fechar os recursos do banco de dados
				rs.close();
				stmt.close();
				con.close();
				
				// abrir o documento (pdf) no leitor padrão
				File arquivo = new File(caminho);
				Desktop.getDesktop().open(arquivo);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
}
