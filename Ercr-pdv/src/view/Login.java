package view;

import java.awt.EventQueue;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JDialog {

	private static final long serialVersionUID = 1L;
	
	// variavel para validar login
	private boolean loginSucesso = false;
	private JTextField textFieldLogin;
	private JTextField textFieldSenha;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the dialog.
	 * 
	 */
	public Login() {
		setModal(true);
		setTitle("Login");
		setBounds(100, 100, 618, 440);
		getContentPane().setLayout(null);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Dialog", Font.BOLD, 14));
		lblLogin.setBounds(81, 74, 60, 17);
		getContentPane().add(lblLogin);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Dialog", Font.BOLD, 14));
		lblSenha.setBounds(81, 152, 60, 17);
		getContentPane().add(lblSenha);
		
		textFieldLogin = new JTextField();
		textFieldLogin.setBounds(149, 72, 338, 21);
		getContentPane().add(textFieldLogin);
		textFieldLogin.setColumns(10);
		
		textFieldSenha = new JTextField();
		textFieldSenha.setBounds(149, 150, 338, 21);
		getContentPane().add(textFieldSenha);
		textFieldSenha.setColumns(10);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginSucesso = true;
				dispose();
			}
		});
		btnEntrar.setBackground(Color.LIGHT_GRAY);
		btnEntrar.setBounds(263, 270, 105, 27);
		getContentPane().add(btnEntrar);
	} // fim do construtor
	
	public boolean isLoginSucesso() {
	    return loginSucesso;
	}
}
