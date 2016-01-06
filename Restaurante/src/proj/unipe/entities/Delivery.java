package proj.unipe.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Delivery extends Pedido {
	
	@Column
	private Float trocoPara;

	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;

	public Float getTrocoPara() {
		return trocoPara;
	}

	public void setTrocoPara(Float trocoPara) {
		this.trocoPara = trocoPara;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
