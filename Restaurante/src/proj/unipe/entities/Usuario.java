package proj.unipe.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@SequenceGenerator(sequenceName = "usuario_seq", name = "usuario_id", allocationSize = 1)
public class Usuario extends AbstractEntity{
 
	@Id 
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="usuario_id")
	private Long id;
	
	@Column( length = 20)
	private String login;
	
	@Column(length = 20)
	private String senha;
	
	@NotNull(message="Campo obrigatório")
	@Column(length = 20)
	private String nome;
	
	@Column( length = 20)
	private String email;
	
	@Column(length = 20)
	private String telefone;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}
