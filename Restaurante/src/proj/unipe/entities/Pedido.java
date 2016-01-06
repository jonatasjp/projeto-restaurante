package proj.unipe.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Pedido extends AbstractEntity {

	@Id @GeneratedValue
	private Long id;

	@Temporal(TemporalType.DATE)
	private Date data;

	@Column(length = 20)
	private String status;
	
	
	private float total;

	@ManyToOne
	@JoinColumn(name = "funcionario_id")
	private Funcionario funcionario;

	
	@OneToMany(fetch =FetchType.EAGER, mappedBy = "pedido", cascade = CascadeType.PERSIST)
	private List<ItemCardapio> itemCardapio;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<ItemCardapio> getItemCardapio() {
		return itemCardapio;
	}

	public void setItemCardapio(List<ItemCardapio> itemCardapio) {
		this.itemCardapio = itemCardapio;
	}

	public float getTotal() {
		float total = 0;
		for (ItemCardapio item : itemCardapio) {
			total += item.getCardapio().getPreco() * item.getQuantidade();
		}
		return total;
	}

}
