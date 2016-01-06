package proj.unipe.entities;

import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Cliente extends Usuario {

	@OneToMany(mappedBy = "cliente")
	private List<Delivery> delivery;

	@Embedded
	private Endereco endereco;
	
	
	public List<Delivery> getDelivery() {
		return delivery;
	}

	public void setDelivery(List<Delivery> delivery) {
		this.delivery = delivery;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

}
