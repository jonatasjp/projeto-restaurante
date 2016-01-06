package proj.unipe.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "cardapio")
@SequenceGenerator(sequenceName = "cardapio_seq", name = "cardapio_id", allocationSize = 1)
public class Cardapio extends AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cardapio_id")
	private Long id;
	
	@NotEmpty(message="O nome do cardápio é obrigatório")
	@Column(length = 30)
	private String nome;

	@NotNull(message="O preço é obrigatório")
	@Column(nullable = false, length = 30)
	private float preco;
	
	@Column( length = 30)
	private String status;

	@OneToMany(mappedBy = "cardapio")
	private List<ItemCardapio> itemCardapio;

	@ManyToOne
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;

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

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

	public List<ItemCardapio> getItemCardapio() {
		return itemCardapio;
	}

	public void setItemCardapio(List<ItemCardapio> itemCardapio) {
		this.itemCardapio = itemCardapio;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
