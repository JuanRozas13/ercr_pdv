package view;

import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import utils.Validador;
import controller.ClienteController;
import model.Cliente;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class frmClientes extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField txtNome;
	private JTextField txtFone;
	private JTextField txtEmail;
	private JTextField txtID;
	// instanciado objeto controller
	private ClienteController controller;
	private Cliente cliente;
	private JButton btnAdd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmClientes dialog = new frmClientes();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public frmClientes() {
		setModal(true);
		setResizable(false);
		setTitle("Clientes");
		setBounds(100, 100, 640, 480);
		getContentPane().setLayout(null);

		// criar o objeto controller
		controller = new ClienteController();
		// criar um objeto cliente
		cliente = new Cliente();
		
		JLabel lblID = new JLabel("ID");
		lblID.setBounds(52, 58, 46, 14);
		getContentPane().add(lblID);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(52, 122, 46, 14);
		getContentPane().add(lblNome);

		JLabel lblFone = new JLabel("Fone");
		lblFone.setBounds(52, 183, 46, 14);
		getContentPane().add(lblFone);

		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(52, 245, 46, 14);
		getContentPane().add(lblEmail);

		txtID = new JTextField();
		txtID.setEnabled(false);
		txtID.setColumns(10);
		txtID.setBounds(100, 55, 92, 20);
		getContentPane().add(txtID);
		
		txtNome = new JTextField();
		txtNome.setBounds(103, 117, 368, 25);
		getContentPane().add(txtNome);
		txtNome.setColumns(10);
		// validação do número maxímo de caracacteres
		txtNome.setDocument(new Validador(50));

		txtFone = new JTextField();
		txtFone.setColumns(10);
		txtFone.setBounds(103, 180, 239, 25);
		getContentPane().add(txtFone);
		// validação do número maxímo de caracacteres
		txtFone.setDocument(new Validador(20));

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(103, 240, 483, 25);
		getContentPane().add(txtEmail);
		// validação do número maxímo de caracacteres
		txtEmail.setDocument(new Validador(50));

		btnAdd = new JButton("");
		btnAdd.setToolTipText("Adicionar");
		btnAdd.addActionListener(new ActionListener() {

			// ==================================
			// CRUD Create - Cadastrar cliente ==
			// ==================================
			public void actionPerformed(ActionEvent e) {
				// Validação de campos obrigatórios
				if (txtNome.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "Preencha o nome do cliente");
					txtNome.requestFocus();
				} else if (txtFone.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "Preencha o telefone do cliente");
					txtFone.requestFocus();
				} else {
					// lógica principla se os campos obrigatorios estiverem preenchidos
					try {
						// CRUD create
						// transferir os dados da tela para o objeto
						cliente.setNome(txtNome.getText());
						cliente.setFone(txtFone.getText());
						cliente.setEmail(txtEmail.getText());
	
						// enviar o objeto para o controller
						controller.adicionar(cliente);
						// Mensagem de confirmação
						JOptionPane.showMessageDialog(null, "Cliente adicionado com Sucesso!");
						// Limpar campos
						limparCampos();
				
						
					} catch (Exception e2) {
						System.out.println(e2);
					}

				}

			}

		});

		btnAdd.setBorderPainted(false);
		btnAdd.setDefaultCapable(false);
		btnAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdd.setContentAreaFilled(false);
		btnAdd.setIcon(new ImageIcon(frmClientes.class.getResource("/img/iconadd.png")));
		btnAdd.setBounds(103, 358, 64, 64);
		getContentPane().add(btnAdd);

		JButton btnEditar = new JButton("");
		btnEditar.setToolTipText("Editar");
		// ==================================
		// CRUD update - Editar cliente =====
		// ==================================
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (txtNome.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "Preencha o nome do cliente");
					txtNome.requestFocus();
				} else if (txtFone.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "Preencha o telefone do cliente");
					txtFone.requestFocus();
				} else {
					// lógica principla se os campos obrigatorios estiverem preenchidos
					// transferir os dados da tela para o model
					cliente.setIdClientes(Integer.parseInt(txtID.getText()));
					cliente.setNome(txtNome.getText());
					cliente.setFone(txtFone.getText());
					cliente.setEmail(txtEmail.getText());

					// enviar o objeto pora o controller
					controller.editarCliente(cliente);

					// mensagem de sucesso para o usuario
					JOptionPane.showMessageDialog(null, "Dados do cliente alterado");

					// limpar campos
					limparCampos();
				}

			}
		});
		btnEditar.setContentAreaFilled(false);
		btnEditar.setBorderPainted(false);
		btnEditar.setIcon(new ImageIcon(frmClientes.class.getResource("/img/iconedit.png")));
		btnEditar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEditar.setBounds(206, 358, 64, 64);
		getContentPane().add(btnEditar);

		JButton btnExcluir = new JButton("");
		btnExcluir.setToolTipText("Excluir");
		// =============================
		// CRUD Read - Excluir Cliente==
		// =============================
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// validação
				if (txtNome.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "Preencha o nome do cliente");
					txtNome.requestFocus();
				} else {
					// capturar o id do fornecedor
					int idCliente = Integer.parseInt(txtID.getText());
					// confirmação de exclusão
					int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente exluir\neste cliente",
							"Atenção", JOptionPane.YES_OPTION);
					if (resposta == JOptionPane.YES_OPTION) {
						// excluir atraves do controller
						controller.excluirCliente(idCliente);
						// limpar campos
						limparCampos();
						// mensagem para o usuario
						JOptionPane.showMessageDialog(null, "Fornecedor excluido com sucesso");
					}
				}

			}
		});
		btnExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExcluir.setIcon(new ImageIcon(frmClientes.class.getResource("/img/iconremov.png")));
		btnExcluir.setContentAreaFilled(false);
		btnExcluir.setBorderPainted(false);
		btnExcluir.setBounds(309, 358, 64, 64);
		getContentPane().add(btnExcluir);

		JButton btnRelario = new JButton("");
		btnRelario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.gerarRelatorioCliente();
			}
		});
		btnRelario.setToolTipText("Relatório");
		btnRelario.setIcon(new ImageIcon(frmClientes.class.getResource("/img/iconbuscar.png")));
		btnRelario.setBorderPainted(false);
		btnRelario.setContentAreaFilled(false);
		btnRelario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRelario.setBounds(407, 358, 64, 64);
		getContentPane().add(btnRelario);

		JButton btnLimpar = new JButton("");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();

			}
		});
		btnLimpar.setContentAreaFilled(false);
		btnLimpar.setBorderPainted(false);
		btnLimpar.setIcon(new ImageIcon(frmClientes.class.getResource("/img/Clear.png")));
		btnLimpar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLimpar.setToolTipText("Limpar");
		btnLimpar.setBounds(498, 358, 64, 64);
		getContentPane().add(btnLimpar);

		JButton btnBuscar = new JButton("Buscar");
		// =============================
		// CRUD Read - Buscar Cliente==
		// =============================
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// validação
				if (txtNome.getText().isBlank()) {
					JOptionPane.showConfirmDialog(null, "Informe o nome do Cliente");
					txtNome.requestFocus();
				} else {
					// logica principal
					
					// capturar o nome para busca
					String nome = txtNome.getText();

					// instanciar o cliente executando a busca atraves do controller
					Cliente cliente = controller.buscar(nome);

					// se existir um cliente cadastrado
					if (cliente != null) {
						// setar os campos do formulario
						txtID.setText(String.valueOf(cliente.getIdClientes()));
						txtNome.setText(cliente.getNome());
						txtFone.setText(cliente.getFone());
						txtEmail.setText(cliente.getEmail());

						// desativar o botão adicionar
						btnAdd.setEnabled(false);
						
					} else {
						JOptionPane.showMessageDialog(null, "Cliente não cadastrado ");
						int resposta = JOptionPane.showConfirmDialog(null, "Deseja cadastrar esse cliente?", "Atenção",
								JOptionPane.YES_OPTION);
						if (resposta == JOptionPane.YES_OPTION) {
							limparCampos();
							txtNome.requestFocus();
						}
					}

				}

			}
		});
		// fim CRUD Read ============

		btnBuscar.setBounds(494, 118, 89, 23);
		getContentPane().add(btnBuscar);

		

		// iniciar centralizado
		setLocationRelativeTo(null);

		// definir um botão padrão (Associar o enter a este botão)
		getRootPane().setDefaultButton(btnBuscar);

	}// fim do construtor

	// ==================================
	// Limpar campos
	// ==================================

	void limparCampos() {
		txtID.setText(null);
		txtNome.setText(null);
		txtFone.setText(null);
		txtEmail.setText(null);
		txtNome.requestFocus(); // posicionar o cursor no nome
		btnAdd.setEnabled(true);
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
			JOptionPane.showMessageDialog(null, "Digite uma Url valída\nExemplo: https://www.google.com");
			txtNome.requestFocus();
		}
	}
}
