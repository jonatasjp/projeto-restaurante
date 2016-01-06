package proj.unipe.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "categoria")
@SequenceGenerator(sequenceName = "categoria_seq", name = "categoria_id", allocationSize = 1)
public class Categoria extends AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categoria_id")
	private Long id;

	@NotEmpty(message="O nome da categoria é obrigatório")
	@Column( length = 30)
	private String nome;
	
	@Column( length = 30)
	private String status;
	
	@OneToMany(mappedBy = "categoria", cascade = CascadeType.REMOVE)
	private List<Cardapio> cardapio;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Cardapio> getCardapio() {
		return cardapio;
	}

	public void setCardapio(List<Cardapio> cardapio) {
		this.cardapio = cardapio;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
