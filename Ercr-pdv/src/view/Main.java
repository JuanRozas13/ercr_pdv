package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import java.awt.Cursor;

//IMPORTAR A CLASSE DATABASE DO PACOTE DATABASE
import database.Database;



public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblData;

	// Criação de um objeto para lidar com a conexão
	Database db = new Database();
	private JLabel lblConect;
	private JLabel lblMysql;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		

		// Uso da biblioteca flatlaf (swing moderno)
		FlatLightLaf.setup();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setResizable(false);
		setTitle("ErCr-PDV");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 873, 620);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelLeft = new JPanel();
		panelLeft.setBackground(new Color(18, 59, 74));
		panelLeft.setBounds(0, 0, 190, 543);
		contentPane.add(panelLeft);
		panelLeft.setLayout(null);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setBounds(10, 11, 64, 64);
		panelLeft.add(lblLogo);
		lblLogo.setIcon(new ImageIcon(Main.class.getResource("/img/perfume.png")));
		
		JLabel lblName = new JLabel("ErCr-PDV");
		lblName.setFont(new Font("Yu Gothic UI", Font.PLAIN, 21));
		lblName.setForeground(new Color(255, 255, 255));
		lblName.setBounds(70, 26, 97, 23);
		panelLeft.add(lblName);
		
		JLabel lblSistemaPdv = new JLabel("Sistema de PDV");
		lblSistemaPdv.setFont(new Font("Yu Gothic UI", Font.PLAIN, 12));
		lblSistemaPdv.setForeground(new Color(255, 255, 255));
		lblSistemaPdv.setBounds(70, 49, 91, 14);
		panelLeft.add(lblSistemaPdv);
		
		JButton btnClientes = new JButton("Clientes");
		// Acessar a classe cliente
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmClientes cliente = new frmClientes();
				cliente.setVisible(true);
			}
		});
		btnClientes.setBorderPainted(false);
		btnClientes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnClientes.setForeground(Color.BLACK);
		btnClientes.setBackground(Color.WHITE);
		btnClientes.setIconTextGap(12);
		btnClientes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnClientes.setHorizontalAlignment(SwingConstants.LEFT);
		btnClientes.setIcon(new ImageIcon(Main.class.getResource("/img/cliente.png")));
		btnClientes.setBounds(10, 86, 170, 54);
		panelLeft.add(btnClientes);
		
		JButton btnProdutos = new JButton("Produtos");
		btnProdutos.setBorderPainted(false);
		btnProdutos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnProdutos.setIconTextGap(12);
		btnProdutos.setIcon(new ImageIcon(Main.class.getResource("/img/shipping.png")));
		btnProdutos.setHorizontalAlignment(SwingConstants.LEFT);
		btnProdutos.setForeground(Color.BLACK);
		btnProdutos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnProdutos.setBackground(Color.WHITE);
		btnProdutos.setBounds(10, 151, 170, 54);
		panelLeft.add(btnProdutos);
		
		JButton btnPdv = new JButton("PDV");
		btnPdv.setBorderPainted(false);
		btnPdv.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPdv.setIconTextGap(12);
		btnPdv.setIcon(new ImageIcon(Main.class.getResource("/img/cash-machin.png")));
		btnPdv.setHorizontalAlignment(SwingConstants.LEFT);
		btnPdv.setForeground(Color.BLACK);
		btnPdv.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnPdv.setBackground(Color.WHITE);
		btnPdv.setBounds(10, 216, 170, 54);
		panelLeft.add(btnPdv);
		
		JButton btnVendas = new JButton("Vendas");
		btnVendas.setBorderPainted(false);
		btnVendas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnVendas.setIconTextGap(12);
		btnVendas.setIcon(new ImageIcon(Main.class.getResource("/img/delivery-box.png")));
		btnVendas.setHorizontalAlignment(SwingConstants.LEFT);
		btnVendas.setForeground(Color.BLACK);
		btnVendas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnVendas.setBackground(Color.WHITE);
		btnVendas.setBounds(10, 281, 170, 54);
		panelLeft.add(btnVendas);
		
		JButton btnExit = new JButton("Sair");
		btnExit.setBorderPainted(false);
		btnExit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExit.addActionListener(new ActionListener() {
			// Quando clicar no botão
			public void actionPerformed(ActionEvent e) {
				int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente dair do sistema?", "Confirmar saida", JOptionPane.YES_NO_OPTION);
				// apoio ao entendimento da logica
				System.out.println(resposta);
				if (resposta == 0) {
					System.exit(0);// encerra o sistema
				}
					
			}
		});
		
		 
		btnExit.setIconTextGap(12);
		btnExit.setIcon(new ImageIcon(Main.class.getResource("/img/logout.png")));
		btnExit.setHorizontalAlignment(SwingConstants.LEFT);
		btnExit.setForeground(Color.BLACK);
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnExit.setBackground(Color.WHITE);
		btnExit.setBounds(10, 346, 170, 54);
		panelLeft.add(btnExit);
		
		JButton btnSobre = new JButton("Sobre PDV");
		btnSobre.addActionListener(new ActionListener() {
			// Ativar a tela sobre
			public void actionPerformed(ActionEvent e) {
				Sobre sobre = new Sobre();
				sobre.setVisible(true);
			}
		});
		btnSobre.setBorderPainted(false);
		btnSobre.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSobre.setIconTextGap(12);
		btnSobre.setIcon(new ImageIcon(Main.class.getResource("/img/information.png")));
		btnSobre.setHorizontalAlignment(SwingConstants.LEFT);
		btnSobre.setForeground(Color.BLACK);
		btnSobre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSobre.setBackground(Color.WHITE);
		btnSobre.setBounds(10, 455, 170, 54);
		panelLeft.add(btnSobre);
		
		JLabel lblNewLabel_2 = new JLabel("------------------------");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(13, 420, 177, 14);
		panelLeft.add(lblNewLabel_2);
		
		JPanel panelRodape = new JPanel();
		panelRodape.setBackground(Color.WHITE);
		panelRodape.setBounds(0, 542, 857, 39);
		contentPane.add(panelRodape);
		panelRodape.setLayout(null);
		
		JLabel lblDatabase = new JLabel("");
		lblDatabase.setIcon(new ImageIcon(Main.class.getResource("/img/database.png")));
		lblDatabase.setBounds(10, 0, 36, 39);
		panelRodape.add(lblDatabase);
		
		lblMysql = new JLabel("");
		lblMysql.setFont(new Font("Yu Gothic UI", Font.PLAIN, 13));
		lblMysql.setBounds(38, 12, 151, 14);
		panelRodape.add(lblMysql);
		
		lblConect = new JLabel("●");
		lblConect.setForeground(Color.RED);
		lblConect.setToolTipText("");
		lblConect.setBounds(165, 12, 12, 14);
		panelRodape.add(lblConect);
		
		JPanel panelCard1 = new JPanel();
		panelCard1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelCard1.setBackground(Color.WHITE);
		panelCard1.setForeground(new Color(0, 0, 0));
		panelCard1.setBounds(200, 59, 209, 131);
		contentPane.add(panelCard1);
		panelCard1.setLayout(null);
		
		JLabel lblProduto = new JLabel("");
		lblProduto.setIcon(new ImageIcon(Main.class.getResource("/img/boX.png")));
		lblProduto.setBounds(10, 11, 64, 64);
		panelCard1.add(lblProduto);
		
		JLabel lblTxtProduto = new JLabel("Produtos");
		lblTxtProduto.setForeground(new Color(43, 101, 243));
		lblTxtProduto.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		lblTxtProduto.setBounds(77, 23, 64, 14);
		panelCard1.add(lblTxtProduto);
		
		JLabel lblValuecart1 = new JLabel("4");
		lblValuecart1.setForeground(new Color(43, 101, 243));
		lblValuecart1.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 30));
		lblValuecart1.setBounds(77, 43, 32, 32);
		panelCard1.add(lblValuecart1);
		
		JPanel panelCard2 = new JPanel();
		panelCard2.setBackground(Color.WHITE);
		panelCard2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelCard2.setBounds(419, 59, 209, 131);
		contentPane.add(panelCard2);
		panelCard2.setLayout(null);
		
		JLabel lblLowEstoque = new JLabel("");
		lblLowEstoque.setIcon(new ImageIcon(Main.class.getResource("/img/alerTa.png")));
		lblLowEstoque.setBounds(10, 11, 64, 64);
		panelCard2.add(lblLowEstoque);
		
		JLabel lblTxtLowEstoque = new JLabel("Estoque baixo");
		lblTxtLowEstoque.setForeground(new Color(253, 129, 32));
		lblTxtLowEstoque.setFont(new Font("Yu Gothic UI", Font.BOLD, 12));
		lblTxtLowEstoque.setBounds(77, 23, 86, 14);
		panelCard2.add(lblTxtLowEstoque);
		
		JLabel lblValuecart2 = new JLabel("1");
		lblValuecart2.setForeground(new Color(253, 129, 32));
		lblValuecart2.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 30));
		lblValuecart2.setBounds(77, 43, 32, 32);
		panelCard2.add(lblValuecart2);
		
		JPanel panelCard3 = new JPanel();
		panelCard3.setBackground(Color.WHITE);
		panelCard3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelCard3.setBounds(638, 59, 209, 131);
		contentPane.add(panelCard3);
		panelCard3.setLayout(null);
		
		JLabel lblNoEstoque = new JLabel("");
		lblNoEstoque.setIcon(new ImageIcon(Main.class.getResource("/img/failEd.png")));
		lblNoEstoque.setBounds(10, 11, 64, 64);
		panelCard3.add(lblNoEstoque);
		
		JLabel lblTxtSemEstoque = new JLabel("Sem estoque");
		lblTxtSemEstoque.setForeground(new Color(243, 59, 58));
		lblTxtSemEstoque.setFont(new Font("Yu Gothic UI", Font.BOLD, 12));
		lblTxtSemEstoque.setBounds(77, 23, 86, 14);
		panelCard3.add(lblTxtSemEstoque);
		
		JLabel lblValuecart3 = new JLabel("1");
		lblValuecart3.setForeground(new Color(243, 59, 58));
		lblValuecart3.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 30));
		lblValuecart3.setBounds(77, 43, 32, 32);
		panelCard3.add(lblValuecart3);
		
		JPanel panelCard4 = new JPanel();
		panelCard4.setBackground(Color.WHITE);
		panelCard4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelCard4.setBounds(200, 201, 209, 131);
		contentPane.add(panelCard4);
		panelCard4.setLayout(null);
		
		JLabel lblVendas = new JLabel("");
		lblVendas.setIcon(new ImageIcon(Main.class.getResource("/img/bag.png")));
		lblVendas.setBounds(10, 11, 64, 64);
		panelCard4.add(lblVendas);
		
		JLabel lblTxtVendas = new JLabel("Vendas hoje");
		lblTxtVendas.setForeground(new Color(19, 146, 24));
		lblTxtVendas.setFont(new Font("Yu Gothic UI", Font.BOLD, 12));
		lblTxtVendas.setBounds(73, 22, 73, 14);
		panelCard4.add(lblTxtVendas);
		
		JLabel lblValuecart4 = new JLabel("3");
		lblValuecart4.setForeground(new Color(19, 146, 24));
		lblValuecart4.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 30));
		lblValuecart4.setBounds(73, 43, 32, 32);
		panelCard4.add(lblValuecart4);
		
		JPanel panelCard5 = new JPanel();
		panelCard5.setBackground(Color.WHITE);
		panelCard5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelCard5.setBounds(419, 201, 209, 131);
		contentPane.add(panelCard5);
		panelCard5.setLayout(null);
		
		JLabel lblItensVendidos = new JLabel("");
		lblItensVendidos.setIcon(new ImageIcon(Main.class.getResource("/img/shopping-car.png")));
		lblItensVendidos.setBounds(10, 11, 64, 64);
		panelCard5.add(lblItensVendidos);
		
		JLabel lblTxtItensVnd = new JLabel("Itens vendidos hoje");
		lblTxtItensVnd.setForeground(new Color(115, 68, 228));
		lblTxtItensVnd.setFont(new Font("Yu Gothic UI", Font.BOLD, 12));
		lblTxtItensVnd.setBounds(73, 22, 117, 14);
		panelCard5.add(lblTxtItensVnd);
		
		JLabel lblValuecart5 = new JLabel("11");
		lblValuecart5.setForeground(new Color(115, 68, 228));
		lblValuecart5.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 30));
		lblValuecart5.setBounds(72, 35, 50, 50);
		panelCard5.add(lblValuecart5);
		
		JPanel panelCard6 = new JPanel();
		panelCard6.setBackground(Color.WHITE);
		panelCard6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelCard6.setBounds(638, 201, 209, 131);
		contentPane.add(panelCard6);
		panelCard6.setLayout(null);
		
		JLabel lblFaturamentoHoje = new JLabel("");
		lblFaturamentoHoje.setIcon(new ImageIcon(Main.class.getResource("/img/real.png")));
		lblFaturamentoHoje.setBounds(10, 11, 64, 64);
		panelCard6.add(lblFaturamentoHoje);
		
		JLabel lblNewLabel_3 = new JLabel("Faturamento Hoje");
		lblNewLabel_3.setForeground(new Color(18, 148, 137));
		lblNewLabel_3.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		lblNewLabel_3.setBounds(70, 23, 117, 14);
		panelCard6.add(lblNewLabel_3);
		
		JLabel lblValuecart6 = new JLabel("4");
		lblValuecart6.setForeground(new Color(18, 148, 137));
		lblValuecart6.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 30));
		lblValuecart6.setBounds(70, 43, 32, 32);
		panelCard6.add(lblValuecart6);
		
		JPanel panelCard7 = new JPanel();
		panelCard7.setBackground(Color.WHITE);
		panelCard7.setBounds(200, 343, 647, 188);
		contentPane.add(panelCard7);
		panelCard7.setLayout(null);
		
		JLabel lblIconDash = new JLabel("");
		lblIconDash.setIcon(new ImageIcon(Main.class.getResource("/img/speedometer.png")));
		lblIconDash.setBounds(207, 16, 32, 32);
		contentPane.add(lblIconDash);
		
		JLabel lblDashboard = new JLabel("Dashboard");
		lblDashboard.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		lblDashboard.setBounds(245, 10, 89, 21);
		contentPane.add(lblDashboard);
		
		JLabel lblCalendar = new JLabel("");
		lblCalendar.setToolTipText("");
		lblCalendar.setIcon(new ImageIcon(Main.class.getResource("/img/calendario.png")));
		lblCalendar.setBounds(652, 24, 24, 24);
		contentPane.add(lblCalendar);
		
		lblData = new JLabel("");
		lblData.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
		lblData.setBounds(684, 23, 73, 25);
		contentPane.add(lblData);
		
		JLabel lblDashText = new JLabel("Visão geral do negócio");
		lblDashText.setBounds(245, 31, 136, 14);
		contentPane.add(lblDashText);

		// iniciar centralizado
		setLocationRelativeTo(null);
		
		// Atualizar data
		atualizarData();
		
		// Status do banco (mudar testo e cor da bolinha)
		if (db.testarConexao() == true) {
			System.out.println("Banco conectado");
			lblMysql.setText("MySql Conectado");
			lblConect.setForeground(Color.green);
		} else {
			System.out.println("Erro na conexão");
			lblMysql.setText("MySql Desconectado");
			lblConect.setForeground(Color.red);
		}
		
		
	} //Fim do construtor
	
	// Função (método) para atualizar data do sistema
	private void atualizarData() {
		//Obtem a data do sistema operacional
		LocalDate hoje = LocalDate.now();
		// Formatar data dia/mes/ano(4 digitos)
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		// Alterar o texto de lblData
		lblData.setText(hoje.format(formato));
	}
} // Fim da classe Main(principal)
