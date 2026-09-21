package view;

import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.ClienteController;
import model.Cliente;
import utils.Validador;

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
	private JTextField txtEndereco;
	private JTextField txtNumeroEndereco;
	private JTextField txtCpf;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JTextField txtCep;
	private JTextField txtReferencia;
	private JComboBox cBoxComplemento;
	private JComboBox cBoxUF;

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
		setBounds(100, 100, 810, 555);
		getContentPane().setLayout(null);

		// criar o objeto controller
		controller = new ClienteController();
		// criar um objeto cliente
		cliente = new Cliente();
		
		JLabel lblID = new JLabel("ID");
		lblID.setBounds(33, 47, 46, 14);
		getContentPane().add(lblID);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(178, 47, 46, 14);
		getContentPane().add(lblNome);

		JLabel lblFone = new JLabel("Fone");
		lblFone.setBounds(425, 108, 46, 14);
		getContentPane().add(lblFone);

		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(33, 170, 46, 14);
		getContentPane().add(lblEmail);

		//novos componentes adicionados
		// requer adicionar a classe validador no setDocument
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setBounds(33, 108, 46, 14);
		getContentPane().add(lblCpf);
		
		JLabel lblendereco = new JLabel("Endereço");
		lblendereco.setBounds(33, 231, 64, 14);
		getContentPane().add(lblendereco);
		
		JLabel lblNumeroEndereco = new JLabel("Nº");
		lblNumeroEndereco.setBounds(473, 231, 31, 14);
		getContentPane().add(lblNumeroEndereco);
		
		JLabel lblComplemento = new JLabel("Complemento");
		lblComplemento.setBounds(593, 231, 83, 14);
		getContentPane().add(lblComplemento);
		
		JLabel lblBairro = new JLabel("Bairro");
		lblBairro.setBounds(33, 292, 46, 14);
		getContentPane().add(lblBairro);
		
		JLabel lblCidade = new JLabel("Cidade");
		lblCidade.setBounds(473, 292, 46, 14);
		getContentPane().add(lblCidade);
		
		JLabel lblCep = new JLabel("CEP");
		lblCep.setBounds(136, 352, 46, 14);
		getContentPane().add(lblCep);
		
		JLabel lblUF = new JLabel("UF");
		lblUF.setBounds(33, 352, 46, 14);
		getContentPane().add(lblUF);
		
		JLabel lblReferencia = new JLabel("Referência");
		lblReferencia.setBounds(369, 352, 76, 14);
		getContentPane().add(lblReferencia);
		
		
		txtID = new JTextField();
		txtID.setEnabled(false);
		txtID.setColumns(10);
		txtID.setBounds(33, 61, 92, 25);
		getContentPane().add(txtID);
		
		txtNome = new JTextField();
		txtNome.setBounds(178, 61, 409, 25);
		getContentPane().add(txtNome);
		txtNome.setColumns(10);
		// validação do número maxímo de caracacteres
		txtNome.setDocument(new Validador(50));

		txtFone = new JTextField();
		txtFone.setColumns(10);
		txtFone.setBounds(424, 123, 208, 25);
		getContentPane().add(txtFone);
		// validação do número maxímo de caracacteres
		txtFone.setDocument(new Validador(20));

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(33, 184, 568, 25);
		getContentPane().add(txtEmail);
		// validação do número maxímo de caracacteres
		txtEmail.setDocument(new Validador(250));
		
		//novos componentes adicionados
		// requer adicionar a classe validador no setDocument
		txtCpf = new JTextField();
		txtCpf.setBounds(33, 123, 240, 25);
		getContentPane().add(txtCpf);
		txtCpf.setColumns(10);
		txtCpf.setDocument(new Validador(14));
		
		txtEndereco = new JTextField();
		txtEndereco.setBounds(33, 245, 409, 25);
		getContentPane().add(txtEndereco);
		txtEndereco.setColumns(10);
		txtEndereco.setDocument(new Validador(100));
		
		txtNumeroEndereco = new JTextField();
		txtNumeroEndereco.setBounds(473, 245, 86, 25);
		getContentPane().add(txtNumeroEndereco);
		txtNumeroEndereco.setColumns(10);
		txtNumeroEndereco.setDocument(new Validador(5, "inteiro"));
		
		cBoxComplemento = new JComboBox();
		cBoxComplemento.setBounds(593, 245, 131, 25);
		getContentPane().add(cBoxComplemento);
		
		txtBairro = new JTextField();
		txtBairro.setBounds(33, 306, 409, 25);
		getContentPane().add(txtBairro);
		txtBairro.setColumns(10);
		txtBairro.setDocument(new Validador(50));
		
		txtCidade = new JTextField();
		txtCidade.setBounds(473, 306, 251, 25);
		getContentPane().add(txtCidade);
		txtCidade.setColumns(10);
		txtCidade.setDocument(new Validador(30));
		
		txtCep = new JTextField();
		txtCep.setBounds(136, 365, 191, 25);
		getContentPane().add(txtCep);
		txtCep.setColumns(10);
		txtCep.setDocument(new Validador(9));
		
		cBoxUF = new JComboBox();
		cBoxUF.setBounds(33, 365, 69, 25);
		getContentPane().add(cBoxUF);
		
		txtReferencia = new JTextField();
		txtReferencia.setBounds(369, 365, 355, 25);
		getContentPane().add(txtReferencia);
		txtReferencia.setColumns(10);
		txtReferencia.setDocument(new Validador(50));

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
						
						int NumeroEndereco = Integer.parseInt(txtNumeroEndereco.getText());
						// CRUD create
						// transferir os dados da tela para o objeto
						cliente.setNome(txtNome.getText());
						cliente.setCpf(txtCpf.getText());
						cliente.setFone(txtFone.getText());
						cliente.setEmail(txtEmail.getText());
						cliente.setEndereco(txtEndereco.getText());
						cliente.setNumeroEndereco(NumeroEndereco);
						cliente.setBairro(txtBairro.getText());
						cliente.setCidade(txtCidade.getText());
						cliente.setCep(txtCep.getText());
						cliente.setReferencia(txtReferencia.getText());
						
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
		btnAdd.setIcon(new ImageIcon(frmClientes.class.getResource("/img/boxadd.png")));
		btnAdd.setBounds(46, 426, 64, 64);
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
		btnEditar.setIcon(new ImageIcon(frmClientes.class.getResource("/img/boxupdate.png")));
		btnEditar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEditar.setBounds(129, 426, 64, 64);
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
		btnExcluir.setIcon(new ImageIcon(frmClientes.class.getResource("/img/boxdel.png")));
		btnExcluir.setContentAreaFilled(false);
		btnExcluir.setBorderPainted(false);
		btnExcluir.setBounds(209, 426, 64, 64);
		getContentPane().add(btnExcluir);

		JButton btnRelario = new JButton("Relatório");
		btnRelario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.gerarRelatorioCliente();
			}
		});
		btnRelario.setToolTipText("Relatório");
		btnRelario.setIcon(null);
		btnRelario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRelario.setBounds(283, 438, 89, 32);
		getContentPane().add(btnRelario);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();

			}
		});
		btnLimpar.setIcon(null);
		btnLimpar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLimpar.setToolTipText("Limpar");
		btnLimpar.setBounds(382, 438, 89, 32);
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
						txtCpf.setText(cliente.getCpf());
						txtFone.setText(cliente.getFone());
						txtEmail.setText(cliente.getEmail());
						txtEndereco.setText(cliente.getEndereco());
						txtNumeroEndereco.setText(String.valueOf(cliente.getNumeroEndereco()));
						//cBoxComplemento
						txtBairro.setText(cliente.getBairro());
						txtCidade.setText(cliente.getCidade());
						//cBoxUF
						txtCep.setText(cliente.getCep());
						txtReferencia.setText(cliente.getReferencia());
						
						// desativar o botão adicionar
						btnAdd.setEnabled(false);
						
					} else {
						JOptionPane.showMessageDialog(null, "Cliente não cadastrado ");
						int resposta = JOptionPane.showConfirmDialog(null, "Deseja cadastrar esse cliente?", "Atenção",
								JOptionPane.YES_OPTION);
						if (resposta == JOptionPane.YES_OPTION) {
							txtNome.requestFocus();
						}
					}

				}

			}
		});
		// fim CRUD Read ============

		btnBuscar.setBounds(617, 62, 107, 23);
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
		txtCpf.setText(null);
		txtFone.setText(null);
		txtEmail.setText(null);
		txtEndereco.setText(null);
		txtNumeroEndereco.setText(null);
		cBoxComplemento.setSelectedItem("São Paulo");
		txtBairro.setText(null);
		txtCidade.setText(null);
		cBoxUF.setSelectedItem("SP");
		txtCep.setText(null);
		txtReferencia.setText(null);
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
