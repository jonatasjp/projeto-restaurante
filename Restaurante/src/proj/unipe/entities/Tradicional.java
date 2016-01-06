package proj.unipe.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name ="tradicional")
public class Tradicional extends Pedido {

	@ManyToOne
	@JoinColumn(name = "mesa_id")
	private Mesa mesa;

	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}

}
