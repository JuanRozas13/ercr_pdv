package view;
import java.net.URI;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Sobre extends JDialog {

	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sobre dialog = new Sobre();
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
	public Sobre() {
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblPDV = new JLabel("Josi -PDV");
		lblPDV.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		lblPDV.setBounds(29, 40, 79, 14);
		getContentPane().add(lblPDV);
		
		JLabel lblDescricao = new JLabel("Sistema para gestão de estoque PDV");
		lblDescricao.setBounds(29, 82, 177, 14);
		getContentPane().add(lblDescricao);
		
		JLabel lblAutor = new JLabel("Autor Juan Rozas Santos");
		lblAutor.setBounds(29, 118, 155, 14);
		getContentPane().add(lblAutor);
		
		JLabel lblVersion = new JLabel("Versão 1.0");
		lblVersion.setBounds(29, 151, 69, 14);
		getContentPane().add(lblVersion);
		
		JLabel lblMit = new JLabel("");
		lblMit.setIcon(new ImageIcon(Sobre.class.getResource("/img/mit.png")));
		lblMit.setBounds(323, 21, 64, 64);
		getContentPane().add(lblMit);
		
		JLabel lblGithub = new JLabel("");
		lblGithub.setIcon(new ImageIcon(Sobre.class.getResource("/img/github.png")));
		lblGithub.setBounds(29, 193, 32, 32);
		getContentPane().add(lblGithub);
		
		JLabel lblRepositorio = new JLabel("github.com/JuanRozas13");
		lblRepositorio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				link("https://github.com/JuanRozas13");
			}
		});
		lblRepositorio.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblRepositorio.setForeground(new Color(0, 51, 255));
		lblRepositorio.setBounds(71, 206, 169, 14);
		getContentPane().add(lblRepositorio);
		
		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//fechar apenas o sobre
				dispose();
			}
		});
		btnOK.setBounds(250, 202, 89, 23);
		getContentPane().add(btnOK);
		
		setLocationRelativeTo(null);

	} // fim do construtor
	
	//função(método) para abrir um link no navegador
	private void link(String url) {
		// alinha abaixo obtem o desktop do cliente
		Desktop desktop = Desktop.getDesktop();
		//uso do try catch(tratamento de exceções)
		try {
			// objeto uri para acessar os métodos necessarios para estabelecer uma conexão com a url (link)
			URI uri = new URI(url);
			//abrir link no navegador padrao do clinte
			desktop.browse(uri);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
