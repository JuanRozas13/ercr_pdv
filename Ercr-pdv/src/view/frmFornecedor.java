package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class frmFornecedor extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmFornecedor dialog = new frmFornecedor();
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
	public frmFornecedor() {
		setResizable(false);
		setTitle("Fornecedores");
		setBounds(100, 100, 640, 480);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setBounds(52, 122, 46, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Fone");
		lblNewLabel_1.setBounds(52, 183, 46, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("E-mail");
		lblNewLabel_2.setBounds(52, 245, 46, 14);
		getContentPane().add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(100, 119, 368, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(103, 180, 239, 20);
		getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(100, 242, 483, 20);
		getContentPane().add(textField_2);
		
		JButton btnNewButton = new JButton("Adicionar");
		btnNewButton.setBounds(103, 358, 89, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(239, 358, 89, 23);
		getContentPane().add(btnEditar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(369, 358, 89, 23);
		getContentPane().add(btnExcluir);
		
		JButton btnRelario = new JButton("Relatório");
		btnRelario.setBounds(494, 358, 89, 23);
		getContentPane().add(btnRelario);
		
		JLabel lblNewLabel_3 = new JLabel("ID");
		lblNewLabel_3.setBounds(52, 58, 46, 14);
		getContentPane().add(lblNewLabel_3);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(100, 55, 92, 20);
		getContentPane().add(textField_3);
		
		JButton btnNewButton_1 = new JButton("Buscar");
		btnNewButton_1.setBounds(494, 118, 89, 23);
		getContentPane().add(btnNewButton_1);
		
		// iniciar centralizado
		setLocationRelativeTo(null);

	}// Fim do construtor
}
