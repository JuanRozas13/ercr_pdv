package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class frmProdutos extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField txtIDProduto;
	private JTextField txtBarcode;
	private JTextField txtProduto;
	private JTextField textField_2;
	private JTextField txtPrecoCusto;
	private JTextField txtPrecoVenda;
	private JTextField txtQuantidade;
	private JTextField txtEstoqueMin;
	private JTextField txtCategoria;

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

		JButton btnBuscarProduto = new JButton();
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
		
		JLabel lblFornecedor = new JLabel("Fornecedor");
		lblFornecedor.setBounds(32, 154, 64, 14);
		getContentPane().add(lblFornecedor);
		
		JComboBox cBoxFornecedor = new JComboBox();
		cBoxFornecedor.setBounds(122, 150, 251, 22);
		getContentPane().add(cBoxFornecedor);
		
		JLabel lblIDFornecedor = new JLabel("ID Fornecedor");
		lblIDFornecedor.setBounds(422, 154, 77, 14);
		getContentPane().add(lblIDFornecedor);
		
		textField_2 = new JTextField();
		textField_2.setEnabled(false);
		textField_2.setBounds(519, 151, 86, 20);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblPrecoCusto = new JLabel("Preço de custo");
		lblPrecoCusto.setBounds(32, 213, 89, 14);
		getContentPane().add(lblPrecoCusto);
		
		txtPrecoCusto = new JTextField();
		txtPrecoCusto.setBounds(122, 210, 130, 20);
		getContentPane().add(txtPrecoCusto);
		txtPrecoCusto.setColumns(10);
		
		JLabel lblPrecoVenda = new JLabel("Preço de venda");
		lblPrecoVenda.setBounds(423, 213, 89, 14);
		getContentPane().add(lblPrecoVenda);
		
		txtPrecoVenda = new JTextField();
		txtPrecoVenda.setBounds(519, 210, 130, 20);
		getContentPane().add(txtPrecoVenda);
		txtPrecoVenda.setColumns(10);
		
		JLabel lblQuantidade = new JLabel("Quantidade");
		lblQuantidade.setBounds(32, 279, 70, 14);
		getContentPane().add(lblQuantidade);
		
		txtQuantidade = new JTextField();
		txtQuantidade.setBounds(122, 276, 130, 20);
		getContentPane().add(txtQuantidade);
		txtQuantidade.setColumns(10);
		
		JLabel lblEstoqueMin = new JLabel("Estoque Min");
		lblEstoqueMin.setBounds(422, 279, 77, 14);
		getContentPane().add(lblEstoqueMin);
		
		txtEstoqueMin = new JTextField();
		txtEstoqueMin.setBounds(519, 276, 130, 20);
		getContentPane().add(txtEstoqueMin);
		txtEstoqueMin.setColumns(10);
		
		JButton btnAdicionarProduto = new JButton("");
		btnAdicionarProduto.setContentAreaFilled(false);
		btnAdicionarProduto.setBorderPainted(false);
		btnAdicionarProduto.setIcon(new ImageIcon(frmProdutos.class.getResource("/img/boxadd.png")));
		btnAdicionarProduto.setBounds(32, 392, 77, 73);
		getContentPane().add(btnAdicionarProduto);
		
		JButton btnEditarProduto = new JButton("");
		btnEditarProduto.setBorderPainted(false);
		btnEditarProduto.setContentAreaFilled(false);
		btnEditarProduto.setIcon(new ImageIcon(frmProdutos.class.getResource("/img/boxupdate.png")));
		btnEditarProduto.setBounds(122, 392, 77, 73);
		getContentPane().add(btnEditarProduto);
		
		JButton btnExcluirProduto = new JButton("");
		btnExcluirProduto.setBorderPainted(false);
		btnExcluirProduto.setContentAreaFilled(false);
		btnExcluirProduto.setIcon(new ImageIcon(frmProdutos.class.getResource("/img/boxdel.png")));
		btnExcluirProduto.setBounds(209, 392, 77, 73);
		getContentPane().add(btnExcluirProduto);
		
		JButton btnRelatorioProduto = new JButton("Relatório");
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
		
	}//fim do construtor
}
