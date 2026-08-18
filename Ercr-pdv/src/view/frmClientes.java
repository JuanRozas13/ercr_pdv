package view;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.ClienteController;
import model.Cliente;


public class frmClientes extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField txtNome;
	private JTextField txtFone;
	private JTextField txtEmail;
	private JTextField txtID;
	//instanciado objeto controller
	private ClienteController controller;
	private Cliente cliente;


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
		setResizable(false);
		setTitle("Clientes");
		setBounds(100, 100, 640, 480);
		getContentPane().setLayout(null);
		
		//criar o objeto controller
		controller = new ClienteController();
		// criar um objeto cliente
		cliente = new Cliente();
		
		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setBounds(52, 122, 46, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Fone");
		lblNewLabel_1.setBounds(52, 183, 46, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("E-mail");
		lblNewLabel_2.setBounds(52, 245, 46, 14);
		getContentPane().add(lblNewLabel_2);
		
		txtNome = new JTextField();
		txtNome.setBounds(100, 119, 368, 20);
		getContentPane().add(txtNome);
		txtNome.setColumns(10);
		
		txtFone = new JTextField();
		txtFone.setColumns(10);
		txtFone.setBounds(103, 180, 239, 20);
		getContentPane().add(txtFone);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(100, 242, 483, 20);
		getContentPane().add(txtEmail);
		
		JButton btnAdd = new JButton("");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			try {
				// CRUD create
				// transferir os dados da tela para o objeto
				cliente.setNome(txtNome.getText());
				cliente.setFone(txtFone.getText());
				cliente.setEmail(txtEmail.getText());
				// enviar o objeto para o controller
				controller.adicionar(cliente);
				// Mensagem de confirmação
				JOptionPane.showMessageDialog(null, "Fornecedor adicionado com Sucesso!");
			} catch (Exception e2) {
				System.out.println(e2);
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
		btnEditar.setContentAreaFilled(false);
		btnEditar.setBorderPainted(false);
		btnEditar.setIcon(new ImageIcon(frmClientes.class.getResource("/img/iconedit.png")));
		btnEditar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEditar.setBounds(220, 358, 64, 64);
		getContentPane().add(btnEditar);
		
		JButton btnExcluir = new JButton("");
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
		
		JLabel lblNewLabel_3 = new JLabel("ID");
		lblNewLabel_3.setBounds(52, 58, 46, 14);
		getContentPane().add(lblNewLabel_3);
		
		txtID = new JTextField();
		txtID.setColumns(10);
		txtID.setBounds(100, 55, 92, 20);
		getContentPane().add(txtID);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(494, 118, 89, 23);
		getContentPane().add(btnBuscar);
		
		// iniciar centralizado
		setLocationRelativeTo(null);

	}// Fim do construtor
}
