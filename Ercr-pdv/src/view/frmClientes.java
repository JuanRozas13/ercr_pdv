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
	private JTextField txtSite;

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

		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(52, 122, 46, 14);
		getContentPane().add(lblNome);

		JLabel lblFone = new JLabel("Fone");
		lblFone.setBounds(52, 183, 46, 14);
		getContentPane().add(lblFone);

		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(52, 245, 46, 14);
		getContentPane().add(lblEmail);

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

		JButton btnAdd = new JButton("");
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
						cliente.setSite(txtSite.getText());
						// enviar o objeto para o controller
						controller.adicionar(cliente);
						// Mensagem de confirmação
						JOptionPane.showMessageDialog(null, "Fornecedor adicionado com Sucesso!");

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
		btnAdd.setBounds(88, 358, 64, 64);
		getContentPane().add(btnAdd);

		JButton btnEditar = new JButton("");
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
					cliente.setSite(txtSite.getText());

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
		btnEditar.setBounds(220, 358, 64, 64);
		getContentPane().add(btnEditar);

		JButton btnExcluir = new JButton("");
		// =============================
		// CRUD Read - Buscar Cliente==
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
		btnExcluir.setBounds(344, 358, 64, 64);
		getContentPane().add(btnExcluir);

		JButton btnRelario = new JButton("");
		btnRelario.setIcon(new ImageIcon(frmClientes.class.getResource("/img/iconbuscar.png")));
		btnRelario.setBorderPainted(false);
		btnRelario.setContentAreaFilled(false);
		btnRelario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRelario.setBounds(474, 358, 64, 64);
		getContentPane().add(btnRelario);

		JLabel lblID = new JLabel("ID");
		lblID.setBounds(52, 58, 46, 14);
		getContentPane().add(lblID);

		txtID = new JTextField();
		txtID.setEnabled(false);
		txtID.setColumns(10);
		txtID.setBounds(100, 55, 92, 20);
		getContentPane().add(txtID);

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
						txtSite.setText(cliente.getSite());
					} else {
						JOptionPane.showConfirmDialog(null, "Cliente não cadastrado ");
						// limpar campos
						limparCampos();
					}

				}

			}
		});
		// fim CRUD Read ============

		btnBuscar.setBounds(494, 118, 89, 23);
		getContentPane().add(btnBuscar);

		JLabel lblSite = new JLabel("Site");
		lblSite.setBounds(52, 301, 46, 14);
		getContentPane().add(lblSite);

		txtSite = new JTextField();
		txtSite.setBounds(103, 298, 368, 25);
		getContentPane().add(txtSite);
		txtSite.setColumns(10);

		JButton btnSite = new JButton("Acessar");
		// acessar link externo ======================
		btnSite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String site = txtSite.getText();
				if (site == null || site.isBlank()) {
					JOptionPane.showMessageDialog(null, "Nenhum site cadastrado para este cliente");
					return;
				}
				link(site);
			}
		});
		// =========================================
		btnSite.addMouseListener(new MouseAdapter() {

		});
		btnSite.setBounds(494, 297, 89, 23);
		getContentPane().add(btnSite);

		// iniciar centralizado
		setLocationRelativeTo(null);

	}// Fim do construtor
		// fim crud create

	// ==================================
	// Limpar campos
	// ==================================

	void limparCampos() {
		txtID.setText(null);
		txtNome.setText(null);
		txtFone.setText(null);
		txtEmail.setText(null);
		txtSite.setText(null);
		txtNome.requestFocus(); // posicionar o cursor no nome
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
			System.out.println(e);
		}
	}
}
