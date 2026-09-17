package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

//importar o fornecedorcontroller (combo box)
import controller.FornecedorController;
//importar o ProdutoController (Crud produtos)
import controller.ProdutosController;
//importar os modelos de dados
import model.Fornecedor;
import model.Produto;
//importar a classe modelo Validador
import utils.Validador;

public class frmProdutos extends JDialog {

	//criar os objetos controller(fornecedores e produtos)
	FornecedorController controllerFornecedor = new FornecedorController();
	ProdutosController controllerProduto = new ProdutosController();
	
	//criar os objetos model(fornecedor e produto)
	Fornecedor fornecedor = new Fornecedor();
	Produto produto = new Produto();
	
	private static final long serialVersionUID = 1L;
	private JTextField txtIDProduto;
	private JTextField txtBarcode;
	private JTextField txtProduto;
	private JTextField txtIdFornecedor;
	private JTextField txtPrecoCusto;
	private JTextField txtPrecoVenda;
	private JTextField txtQuantidade;
	private JTextField txtEstoqueMin;
	private JTextField txtCategoria;
	private JComboBox cBoxFornecedor;
	private JButton btnAdicionarProduto;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			frmProdutos dialog = new frmProdutos();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public frmProdutos() {
		setModal(true);
		setResizable(false);
		setTitle("Produtos");
		getContentPane().setBackground(new Color(240, 240, 240));
		getContentPane().setIgnoreRepaint(true);
		getContentPane().setForeground(Color.BLACK);
		setBounds(100, 100, 740, 527);
		getContentPane().setLayout(null);
		
		JLabel lblIDProduto = new JLabel("ID");
		lblIDProduto.setBounds(32, 45, 22, 14);
		getContentPane().add(lblIDProduto);
		
		txtIDProduto = new JTextField();
		txtIDProduto.setEnabled(false);
		txtIDProduto.setBounds(54, 42, 86, 20);
		getContentPane().add(txtIDProduto);
		txtIDProduto.setColumns(10);
		
		txtBarcode = new JTextField();
		txtBarcode.setBounds(237, 42, 375, 20);
		getContentPane().add(txtBarcode);
		txtBarcode.setColumns(10);
		txtBarcode.setDocument(new Validador(20));
		
		JLabel lblBarcode = new JLabel("");
		lblBarcode.setIcon(new ImageIcon(frmProdutos.class.getResource("/img/barcode.png")));
		lblBarcode.setBounds(632, 28, 64, 45);
		getContentPane().add(lblBarcode);
		
		JLabel lblProduto = new JLabel("Produto");
		lblProduto.setBounds(32, 93, 46, 14);
		getContentPane().add(lblProduto);
		
		JPanel painelProduto = new JPanel();
		painelProduto.setBounds(122, 90, 251, 20);
		painelProduto.setLayout(new BorderLayout());

		txtProduto = new JTextField();
		txtProduto.setColumns(10);
		txtProduto.setDocument(new Validador(100));

		JButton btnBuscarProduto = new JButton();
		// =============================
		// CRUD Read - Buscar Cliente===
		// =============================
		btnBuscarProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// validação
				if (txtProduto.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "Informe o nome do Produto");
					txtProduto.requestFocus();
				} else {
					// capturar o nome para busca
					String nome = txtProduto.getText();

					// Instanciar o Produto executando a busca através do controller
					Produto produto = controllerProduto.buscar(nome);

					// se existir um fornecedor cadastrado
					if (produto != null) {
						// setar os campos do formulário
						txtIDProduto.setText(String.valueOf(produto.getIdProduto()));
						txtBarcode.setText(produto.getCodigoBarras());
						txtProduto.setText(produto.getDescricao());
						txtCategoria.setText(produto.getCategoria());
						txtIdFornecedor.setText(String.valueOf(produto.getIdFornecedor()));
						txtPrecoCusto.setText(String.valueOf(produto.getPrecoCusto()));
						txtPrecoVenda.setText(String.valueOf(produto.getPrecoVenda()));
						txtQuantidade.setText(String.valueOf(produto.getQuantidade()));
						txtEstoqueMin.setText(String.valueOf(produto.getEstoqueMin()));
						
						// esconder botão de adicionar
//						btnAdicionarProduto.setEnabled(false);
					} else {
						JOptionPane.showMessageDialog(null, "Produto não cadastrado");
						int resposta = JOptionPane.showConfirmDialog(null, "Deseja cadastrar esse Produto?",
						"Atenção", JOptionPane.YES_OPTION);
						if(resposta == JOptionPane.YES_OPTION) {
							txtProduto.requestFocus();
							
						}
					}
				}
			}
		}); // fim crud read
		btnBuscarProduto.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBuscarProduto.setIcon(new ImageIcon(frmProdutos.class.getResource("/img/lupa.png")));
		btnBuscarProduto.setBorderPainted(false);
		btnBuscarProduto.setContentAreaFilled(false);
		btnBuscarProduto.setFocusPainted(false);

		painelProduto.add(txtProduto, BorderLayout.CENTER);
		painelProduto.add(btnBuscarProduto, BorderLayout.EAST);
		getContentPane().add(painelProduto);
		
		JLabel lblCategoria = new JLabel("Categória");
		lblCategoria.setBounds(422, 93, 64, 14);
		getContentPane().add(lblCategoria);
		
		txtCategoria = new JTextField();
		txtCategoria.setBounds(519, 90, 130, 20);
		getContentPane().add(txtCategoria);
		txtCategoria.setColumns(10);
		txtCategoria.setDocument(new Validador(50));
		
		JLabel lblFornecedor = new JLabel("Fornecedor");
		lblFornecedor.setBounds(32, 154, 64, 14);
		getContentPane().add(lblFornecedor);
		
		cBoxFornecedor = new JComboBox();
		//Evento que seleciona um item da lista (preencher o id do fornecedor)
		cBoxFornecedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Obter o ítem selecionado do combo box
				String selecionado = (String) cBoxFornecedor.getSelectedItem();				
				
				// se foi selecionado o ítem
				if (selecionado != null && !selecionado.equals("Selecione...") ) {
					//separar o id do nome (índice [0] do vetor)
					String id = selecionado.split(" - ")[0];
					//setar (preencher) o id do fornecedor
					txtIdFornecedor.setText(id);
				} else {
				//nenhum fornecedor selecionado
					txtIdFornecedor.setText("");
				}
			}
		});// Fim
		cBoxFornecedor.setBounds(122, 150, 251, 22);
		getContentPane().add(cBoxFornecedor);
		
		JLabel lblIDFornecedor = new JLabel("ID Fornecedor");
		lblIDFornecedor.setBounds(422, 154, 87, 14);
		getContentPane().add(lblIDFornecedor);
		
		txtIdFornecedor = new JTextField();
		txtIdFornecedor.setEnabled(false);
		txtIdFornecedor.setBounds(519, 151, 86, 20);
		getContentPane().add(txtIdFornecedor);
		txtIdFornecedor.setColumns(10);
		
		JLabel lblPrecoCusto = new JLabel("Preço de custo");
		lblPrecoCusto.setBounds(32, 213, 89, 14);
		getContentPane().add(lblPrecoCusto);
		
		txtPrecoCusto = new JTextField();
		txtPrecoCusto.setBounds(122, 210, 130, 20);
		getContentPane().add(txtPrecoCusto);
		txtPrecoCusto.setColumns(10);
		txtPrecoCusto.setDocument(new Validador(10, "decimal"));
		
		JLabel lblPrecoVenda = new JLabel("Preço de venda");
		lblPrecoVenda.setBounds(423, 213, 89, 14);
		getContentPane().add(lblPrecoVenda);
		
		txtPrecoVenda = new JTextField();
		txtPrecoVenda.setBounds(519, 210, 130, 20);
		getContentPane().add(txtPrecoVenda);
		txtPrecoVenda.setColumns(10);
		txtPrecoVenda.setDocument(new Validador(10, "decimal"));
		
		JLabel lblQuantidade = new JLabel("Quantidade");
		lblQuantidade.setBounds(32, 279, 70, 14);
		getContentPane().add(lblQuantidade);
		
		txtQuantidade = new JTextField();
		txtQuantidade.setBounds(122, 276, 130, 20);
		getContentPane().add(txtQuantidade);
		txtQuantidade.setColumns(10);
		txtQuantidade.setDocument(new Validador(5, "inteiro"));
		
		JLabel lblEstoqueMin = new JLabel("Estoque Min");
		lblEstoqueMin.setBounds(422, 279, 77, 14);
		getContentPane().add(lblEstoqueMin);
		
		txtEstoqueMin = new JTextField();
		txtEstoqueMin.setBounds(519, 276, 130, 20);
		getContentPane().add(txtEstoqueMin);
		txtEstoqueMin.setColumns(10);
		
		JButton btnAdicionarProduto = new JButton("");
		btnAdicionarProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					// Converter o valor do JTextField
					//atenção converter a virgurla para ponto
					double precoCusto = Double.parseDouble(txtPrecoCusto.getText().replace(",", "."));
					double precoVenda = Double.parseDouble(txtPrecoVenda.getText().replace(",", "."));
					int quantidade = Integer.parseInt(txtQuantidade.getText());
					int estoqueMin = Integer.parseInt(txtEstoqueMin.getText());
					int idFornecedor = Integer.parseInt(txtIdFornecedor.getText());

					// Transferir os dados da tela para o objeto
					produto.setCodigoBarras(txtBarcode.getText());	
					produto.setDescricao(txtProduto.getText());
					produto.setCategoria(txtCategoria.getText());
					produto.setPrecoCusto(precoCusto);
					produto.setPrecoVenda(precoVenda);
					produto.setQuantidade(quantidade);
					produto.setEstoqueMin(estoqueMin);
					produto.setIdFornecedor(idFornecedor);
			
					// enviar o objeto para o controller
					controllerProduto.adicionar(produto);
					// Mensagem de confirmação
					JOptionPane.showMessageDialog(null, "Produto adicionado com Sucesso!");
					// Limpar campos
//					limparCampos();
					
				} catch (Exception e2) {
					System.out.println(e2);
				}
			}
		});
		btnAdicionarProduto.setToolTipText("Adicionar");
		btnAdicionarProduto.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdicionarProduto.setContentAreaFilled(false);
		btnAdicionarProduto.setIcon(new ImageIcon(frmProdutos.class.getResource("/img/boxadd.png")));
		btnAdicionarProduto.setBounds(32, 392, 77, 73);
		getContentPane().add(btnAdicionarProduto);
		
		JButton btnEditarProduto = new JButton("");
		btnEditarProduto.setToolTipText("Editar");
		btnEditarProduto.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEditarProduto.setContentAreaFilled(false);
		btnEditarProduto.setIcon(new ImageIcon(frmProdutos.class.getResource("/img/boxupdate.png")));
		btnEditarProduto.setBounds(122, 392, 77, 73);
		getContentPane().add(btnEditarProduto);
		
		JButton btnExcluirProduto = new JButton("");
		btnExcluirProduto.setToolTipText("Excluir");
		btnExcluirProduto.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExcluirProduto.setContentAreaFilled(false);
		btnExcluirProduto.setIcon(new ImageIcon(frmProdutos.class.getResource("/img/boxdel.png")));
		btnExcluirProduto.setBounds(209, 392, 77, 73);
		getContentPane().add(btnExcluirProduto);
		
		JButton btnRelatorioProduto = new JButton("Relatório");
		btnRelatorioProduto.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRelatorioProduto.setBackground(new Color(255, 255, 255));
		btnRelatorioProduto.setOpaque(false);
		btnRelatorioProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRelatorioProduto.setIcon(null);
		btnRelatorioProduto.setBounds(296, 402, 89, 33);
		getContentPane().add(btnRelatorioProduto);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			}
		});
		btnLimpar.setIcon(null);
		btnLimpar.setToolTipText("Limpar campo");
		btnLimpar.setBounds(395, 402, 89, 32);
		getContentPane().add(btnLimpar);
	
		
		setLocationRelativeTo(null);//centralizar a tela
		
		// Executar o método para carregar o Id e nome dos fornecedores
		carregarFornecedores();
		
	}//fim do construtor
	
	// ==================================================
	// Preencher o combo box com a lista de fornecedores=
	//===================================================
	
	private void carregarFornecedores() {
		//limpando o combo box
		cBoxFornecedor.removeAllItems();
		
		//opção padrão
		cBoxFornecedor.addItem("Selecione...");
		
		// executar o método para buscar a lista de fornecedores(array)
		ArrayList<Fornecedor> lista = controllerFornecedor.listarFornecedores();
		
		// Percorrer o vetor e adicionar os Fornecedores ao combobox
		//Uso do laço foreach (simplificaçãp do laço for
		for (Fornecedor fornecedor : lista) {
			//exibir o Id e o nome do fornecedor no combo box
			cBoxFornecedor.addItem(fornecedor.getIdFornecedor() + " - " + fornecedor.getNome());
		}
	}
}
