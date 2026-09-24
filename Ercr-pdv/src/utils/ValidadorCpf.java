package utils;

public class ValidadorCpf {
	// impedir instância da classe (só métodos estáticos)
		private ValidadorCpf() {}

		/**
		 * Valida se uma string de CPF é matematicamente válida.
		 * Aceita CPF com ou sem formatação (com ou sem pontos/traço).
		 */
		public static boolean isValido(String cpf) {
			if (cpf == null) {
				return false;
			}

			// remover tudo que não for número (pontos, traço, espaços)
			String numeros = cpf.replaceAll("[^0-9]", "");

			// CPF precisa ter exatamente 11 dígitos
			if (numeros.length() != 11) {
				return false;
			}

			// rejeitar sequências repetidas (111.111.111-11, 222.222.222-22, etc)
			// esses números passam no cálculo mas não são CPFs reais
			if (numeros.matches("(\\d)\\1{10}")) {
				return false;
			}

			try {
				int[] digitos = new int[11];
				for (int i = 0; i < 11; i++) {
					digitos[i] = Character.getNumericValue(numeros.charAt(i));
				}

				// ===== calcular o 1º dígito verificador =====
				int soma = 0;
				for (int i = 0; i < 9; i++) {
					soma += digitos[i] * (10 - i);
				}
				int resto = soma % 11;
				int primeiroDigito = (resto < 2) ? 0 : 11 - resto;

				if (primeiroDigito != digitos[9]) {
					return false;
				}

				// ===== calcular o 2º dígito verificador =====
				soma = 0;
				for (int i = 0; i < 10; i++) {
					soma += digitos[i] * (11 - i);
				}
				resto = soma % 11;
				int segundoDigito = (resto < 2) ? 0 : 11 - resto;

				return segundoDigito == digitos[10];

			} catch (Exception e) {
				return false;
			}
		}

		/**
		 * Formata um CPF (só números) para o padrão 000.000.000-00.
		 * Retorna o texto original se não tiver 11 dígitos.
		 */
		public static String formatar(String cpf) {
			if (cpf == null) {
				return cpf;
			}
			String numeros = cpf.replaceAll("[^0-9]", "");
			if (numeros.length() != 11) {
				return cpf;
			}
			return numeros.replaceFirst("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
		}
}
