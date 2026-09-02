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

public class frmProdutos extends JDialog {

	private static final long serialVersionUID = 1L;

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
		setTitle("Produto");
		getContentPane().setBackground(new Color(240, 240, 240));
		getContentPane().setIgnoreRepaint(true);
		getContentPane().setForeground(Color.BLACK);
		setBounds(100, 100, 900, 527);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("------------------------------------------------");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(202, 241, 346, 14);
		getContentPane().add(lblNewLabel);
		
		
		
	}
}
