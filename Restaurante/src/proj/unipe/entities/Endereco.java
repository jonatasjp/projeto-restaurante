package proj.unipe.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;

@Embeddable
@Table(name = "endereco")
public class Endereco {

	@Column(length = 30)
	private String rua;
	
	@Column(length = 30)
	private String bairro;
	
	@Column(length = 30)
	private int numero;
	
	@Column(length = 30)
	private String cep;
	
	@Column(length = 20)
	private String complemento;
	// @Column(nullable = false, length = 20)
	// private String telefone;

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	/*
	 * public String getTelefone() { return telefone; }
	 * 
	 * public void setTelefone(String telefone) { this.telefone = telefone; }
	 */

}
