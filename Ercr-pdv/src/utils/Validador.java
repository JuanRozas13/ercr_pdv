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
		

		// =========================
		// TELEFONE (máscara automática)
		// =========================
		else if (tipo.equals("telefone")) {
 
			// texto atual do campo (já formatado)
			String textoAtual = getText(0, getLength());
 
			// junta o texto atual com o que está sendo digitado, na posição correta
			String novoTexto = textoAtual.substring(0, ofs) + str + textoAtual.substring(ofs);
 
			// remove tudo que não for número (parênteses, espaço, traço etc.)
			String apenasNumeros = novoTexto.replaceAll("[^0-9]", "");
 
			// limita a 11 dígitos (2 do DDD + 9 do número)
			if (apenasNumeros.length() > 11) {
				apenasNumeros = apenasNumeros.substring(0, 11);
			}
 
			// monta o texto já formatado com a máscara
			String textoFormatado = formatarTelefone(apenasNumeros);
 
			// substitui todo o conteúdo do campo pelo texto formatado
			super.remove(0, getLength());
			super.insertString(0, textoFormatado, a);
		}
	}
 
	// monta a máscara (DD) DDDDD-DDDD a partir dos números puros
	private String formatarTelefone(String numeros) {
		StringBuilder sb = new StringBuilder();
 
		for (int i = 0; i < numeros.length(); i++) {
 
			if (i == 0) {
				sb.append("(");
			}
 
			sb.append(numeros.charAt(i));
 
			if (i == 1) {
				sb.append(") ");
			}
 
			// se for celular (11 dígitos), o traço vem depois do 7º dígito
			// se for fixo (10 dígitos), o traço vem depois do 6º dígito
			if (numeros.length() == 11) {
				if (i == 6) {
					sb.append("-");
				}
			} else {
				if (i == 5) {
					sb.append("-");
				}
			}
		}
 
		return sb.toString();
	}
}