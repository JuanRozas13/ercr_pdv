package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

// importar o fornecedorController
import controller.FornecedorController;
// importar o modelo de dados
import model.Fornecedor;
import utils.Validador;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import java.awt.Desktop;

public class frmFornecedores extends JDialog {

	private JTextField txtNome;
	private JTextField txtFone;
	private JTextField txtEmail;
	private JTextField txtID;
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtSite;
	private JButton btnAdicionar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			frmFornecedores dialog = new frmFornecedores();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public frmFornecedores() {
		setModal(true);
		setResizable(false);
		setTitle("Fornecedores");
		setBounds(100, 100, 640, 480);
		getContentPane().setLayout(null);

		// criar o objeto controller
		// controller = new FornecedorController(); //sintaxe moderna
		FornecedorController controller = new FornecedorController();

		// Criar o objeto fornecedor
		Fornecedor fornecedor = new Fornecedor();

		// iniciar centralizado
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(52, 93, 46, 14);
		getContentPane().add(lblNome);

		JLabel lblFone = new JLabel("Fone");
		lblFone.setBounds(52, 150, 46, 14);
		getContentPane().add(lblFone);

		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(52, 207, 46, 14);
		getContentPane().add(lblEmail);

		JLabel lblSite = new JLabel("Site");
		lblSite.setBounds(52, 257, 46, 14);
		getContentPane().add(lblSite);

		txtNome = new JTextField();
		txtNome.setBounds(108, 86, 352, 25);
		getContentPane().add(txtNome);
		txtNome.setColumns(10);
		// validação do número máximo de caracteres
		txtNome.setDocument(new Validador(50));
		
		JButton btnBuscar = new JButton("Buscar");
		// =============================
		// CRUD Read - Buscar Cliente==
		// =============================
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// validação
				if (txtNome.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "Informe o nome do fornecedor");
					txtNome.requestFocus();
				} else {
					// capturar o nome para busca
					String nome = txtNome.getText();

					// Instanciar o fornecedor executando a busca através do controller
					Fornecedor fornecedor = controller.buscar(nome);

					// se existir um fornecedor cadastrado
					if (fornecedor != null) {
						// setar os campos do formulário
						txtID.setText(String.valueOf(fornecedor.getIdFornecedor()));
						txtNome.setText(fornecedor.getNome());
						txtFone.setText(fornecedor.getFone());
						txtEmail.setText(fornecedor.getEmail());
						txtSite.setText(fornecedor.getSite());
						
						// esconder botão de adicionar
						btnAdicionar.setEnabled(false);

					} else {
						JOptionPane.showMessageDialog(null, "Fornecedor não cadastrado");
						int resposta = JOptionPane.showConfirmDialog(null, "Deseja cadastrar esse fornecedor?",
						"Atenção", JOptionPane.YES_OPTION);
						if(resposta == JOptionPane.YES_OPTION) {
							limparCampos();
							txtNome.requestFocus();
						}
					}
				}
				
			}
		});// Fim CRUD Read ====================================
		btnBuscar.setBounds(478, 89, 89, 23);
		getContentPane().add(btnBuscar);

		txtFone = new JTextField();
		txtFone.setBounds(108, 143, 239, 25);
		getContentPane().add(txtFone);
		txtFone.setColumns(10);
		// validação do número máximo de caracteres
		txtFone.setDocument(new Validador(20));

		txtEmail = new JTextField();
		txtEmail.setBounds(108, 201, 483, 25);
		getContentPane().add(txtEmail);
		txtEmail.setColumns(10);
		// validação do número máximo de caracteres
		txtEmail.setDocument(new Validador(50));

		txtSite = new JTextField();
		txtSite.setColumns(10);
		txtSite.setBounds(108, 252, 368, 25);
		getContentPane().add(txtSite);
		txtSite.setDocument(new Validador(200));
		
		JButton btnAcessar = new JButton("Acessar");
		btnAcessar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String site = txtSite.getText();
				if(site == null || site.isBlank()) {
					JOptionPane.showMessageDialog(null, "Site não cadastrado para este cliente");
					return;
				}
				
				link(site);
			}
		});
		btnAcessar.setBounds(489, 253, 89, 23);
		getContentPane().add(btnAcessar);
		

		btnAdicionar = new JButton("");
		btnAdicionar.setToolTipText("Adicionar");
		btnAdicionar.setContentAreaFilled(false);
		btnAdicionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdicionar.setBorderPainted(false);
		btnAdicionar.setIcon(new ImageIcon(frmFornecedores.class.getResource("/img/iconadd.png")));

		// ======================================================
		// CRUD Create - Cadastrar fornecedor ===================
		// ======================================================
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// validação de campos obrigatórios
				if (txtNome.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "Preencha o nome do fornecedor");
					txtNome.requestFocus();
				} else if (txtFone.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "Preencha o telefone do fornecedor");
					txtFone.requestFocus();
				} else {
					// lógica principal se os os campos obrigatórios estiverem preenchidos
					try {
						// Transferir os dados da tela para o objeto
						fornecedor.setNome(txtNome.getText());
						fornecedor.setFone(txtFone.getText());
						fornecedor.setEmail(txtEmail.getText());
						fornecedor.setSite(txtSite.getText());
						// Enviar o objeto para o controller
						controller.adicionar(fornecedor);
						// Mensagem de confirmação
						JOptionPane.showMessageDialog(null, "Fornecedor adicionado com sucesso.");
						// Limpar campos
						limparCampos();
					} catch (Exception e2) {
						System.out.println(e2);
					}
				}
			}
		});
		// Fim - CRUD Create ====================================

		btnAdicionar.setBounds(108, 342, 64, 64);
		getContentPane().add(btnAdicionar);

		JButton btnEditar = new JButton("");
		btnEditar.setToolTipText("Editar");
		btnEditar.setContentAreaFilled(false);
		btnEditar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEditar.setIcon(new ImageIcon(frmFornecedores.class.getResource("/img/iconedit.png")));
		btnEditar.setBorderPainted(false);

		// ======================================================
		// CRUD Update - Editar fornecedor ======================
		// ======================================================
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// validação de campos obrigatórios
				if (txtNome.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "Preencha o nome do fornecedor");
					txtNome.requestFocus();
				} else if (txtFone.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "Preencha o telefone do fornecedor");
					txtFone.requestFocus();
				} else {
					// lógica principal se os os campos obrigatórios estiverem preenchidos
					// Transferir os dados da tela para o Model
					fornecedor.setIdFornecedor(Integer.parseInt(txtID.getText()));
					fornecedor.setNome(txtNome.getText());
					fornecedor.setFone(txtFone.getText());
					fornecedor.setEmail(txtEmail.getText());
					fornecedor.setSite(txtSite.getText());

					// Enviar o objeto para o Controller
					controller.editarFornecedor(fornecedor);

					// Mensagem para o usuário
					JOptionPane.showMessageDialog(null, "Dados do fornecedor alterados");

					// limpar campos
					limparCampos();
				}
			}
		});
		// ======================================================

		btnEditar.setBounds(203, 342, 64, 64);
		getContentPane().add(btnEditar);
		JButton btnExcluir = new JButton("");
		btnExcluir.setToolTipText("Excluir");
		btnExcluir.setContentAreaFilled(false);
		btnExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExcluir.setIcon(new ImageIcon(frmFornecedores.class.getResource("/img/iconremov.png")));
		btnExcluir.setBorderPainted(false);

		// ======================================================
		// CRUD Delete - Excluir fornecedor =====================
		// ======================================================
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// validação
				if (txtNome.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "Digite o nome do fornecedor");
					txtNome.requestFocus();
				} else {
					// capturar o id do fornecedor
					int idFornecedor = Integer.parseInt(txtID.getText());

					// confirmação de exclusão
					int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir\neste fornecedor?",
							"Atenção!", JOptionPane.YES_OPTION);
					if (resposta == JOptionPane.YES_OPTION) {
						// excluir através do controller
						controller.excluir(idFornecedor);
						// limpar os campos
						limparCampos();
						// mensagem para o usuário
						JOptionPane.showMessageDialog(null, "Fornecedor excluído com sucesso.");
					}

				}
			}
		});
		// ======================================================

		btnExcluir.setBounds(297, 342, 64, 64);
		getContentPane().add(btnExcluir);

		JButton btnRelatorio = new JButton("");
		btnRelatorio.setToolTipText("Relatório");
		btnRelatorio.setContentAreaFilled(false);
		btnRelatorio.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRelatorio.setIcon(new ImageIcon(frmFornecedores.class.getResource("/img/iconbuscar.png")));
		btnRelatorio.setBorderPainted(false);
		btnRelatorio.setBounds(390, 342, 64, 64);
		getContentPane().add(btnRelatorio);

		JButton btnLimpar = new JButton("");
		btnLimpar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();
			}
		});
		btnLimpar.setContentAreaFilled(false);
		btnLimpar.setIcon(new ImageIcon(frmFornecedores.class.getResource("/img/Clear.png")));
		btnLimpar.setBorderPainted(false);
		btnLimpar.setToolTipText("Limpar campo");
		btnLimpar.setBounds(478, 342, 64, 64);
		getContentPane().add(btnLimpar);
		
		JLabel lblID = new JLabel("ID");
		lblID.setBounds(52, 46, 46, 14);
		getContentPane().add(lblID);

		txtID = new JTextField();
		txtID.setEnabled(false);
		txtID.setBounds(108, 43, 86, 20);
		getContentPane().add(txtID);
		txtID.setColumns(10);

		//definir um botão padrão (Associar o enter a este botão)
		getRootPane().setDefaultButton(btnBuscar);
		

	} // fim do construtor

	// ==================================================
	// Limpar campos ====================================
	// ==================================================
	void limparCampos() {
		txtID.setText(null);
		txtNome.setText(null);
		txtFone.setText(null);
		txtEmail.setText(null);
		txtSite.setText(null);
		txtNome.requestFocus(); // posicionar o cursor no nome
		btnAdicionar.setEnabled(true);
		
	}

	// =================================================
	// função(método) para abrir um link no navegador===
	// =================================================
	private void link(String url) {

		// alinha abaixo obtem o desktop do cliente
		Desktop desktop = Desktop.getDesktop();
		// uso do try catch(tratamento de exceções)
		try {

			// objeto uri para acessar os métodos necessarios para estabelecer uma conexão
			// com a url (link)
			URI uri = new URI(url);
			// abrir link no navegador padrao do clinte
			desktop.browse(uri);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Digite uma Url valída\\nExemplo: https://www.google.com");
			txtNome.requestFocus();
		}
	}
}
