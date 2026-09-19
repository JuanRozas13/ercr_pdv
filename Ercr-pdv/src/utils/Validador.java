package utils;

import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class Validador extends PlainDocument {

	private static final long serialVersionUID = 1L;

	private int limite;
	private String tipo;

	public Validador(int limite) {
		super();
		this.limite = limite;
		this.tipo = "texto";
	}

	public Validador(int limite, String tipo) {
		super();
		this.limite = limite;
		this.tipo = tipo;
	}

	@Override
	public void insertString(int ofs, String str, AttributeSet a)
			throws BadLocationException {

		if (str == null) {
			return;
		}

		// =========================
		// TEXTO
		// =========================
		if (tipo.equals("texto")) {

			if (getLength() + str.length() <= limite) {
				super.insertString(ofs, str, a);
			}
		}

		// =========================
		// INTEIRO
		// =========================
		else if (tipo.equals("inteiro")) {

			if (getLength() + str.length() > limite) {
				return;
			}

			if (str.matches("[0-9]+")) {
				super.insertString(ofs, str, a);
			}
		}

		// =========================
		// DECIMAL
		// =========================
		else if (tipo.equals("decimal")) {

			String textoAtual = getText(0, getLength());

			String novoTexto = textoAtual.substring(0, ofs)
					+ str
					+ textoAtual.substring(ofs);

			if (novoTexto.length() > limite) {
				return;
			}

			if (novoTexto.matches("[0-9]*([,.][0-9]*)?")) {
				super.insertString(ofs, str, a);
			}
		}
	}
}