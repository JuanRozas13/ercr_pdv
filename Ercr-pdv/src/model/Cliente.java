package model;

/**
 * 
 */
public class Cliente {
	//modelo de dados da tabela fornecedores
	private int idClientes;
	private String nome;
	private String fone;
	private String email;
	
	
	// gerar getters e setters de forma automática
	// botão direito -> source -> generate getters e setters
	public int getIdClientes() {
		return idClientes;
	}
	public void setIdClientes(int idClientes) {
		this.idClientes = idClientes;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getFone() {
		return fone;
	}
	public void setFone(String fone) {
		this.fone = fone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
