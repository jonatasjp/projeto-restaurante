package proj.unipe.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "item_cardapio")
@SequenceGenerator(sequenceName = "itemCardapio_seq", name = "itemCardapio_id", allocationSize = 1)
public class ItemCardapio extends AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "itemCardapio_id")
	private Long id;

	@Column(length = 5)
	private int quantidade;

	@Transient
	private Tradicional tradicional;
	
	@Transient
	private Delivery delivery;
	
	@Column(length = 10)
	private float preco;

	@ManyToOne
	@JoinColumn(name="pedido_id")
	private Pedido pedido;

	@ManyToOne
	@JoinColumn(name = "cardapio_id")
	private Cardapio cardapio;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQuantidade() {
		return quantidade;
	}
	
	public void setTradicional(Tradicional pedido) {
		this.pedido = pedido;
		this.tradicional = pedido;
	}
	
	public Tradicional getTradicional() {
		if(pedido != null && pedido instanceof Tradicional)
			return (Tradicional) pedido;
		return tradicional;
	}
	
	public void setDelivery(Delivery pedido) {
		this.pedido = pedido;
		this.delivery = pedido;
	}
	
	public Delivery getDelivery() {
		if(pedido != null && pedido instanceof Delivery)
			return (Delivery) pedido;
		return delivery;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Cardapio getCardapio() {
		return cardapio;
	}

	public void setCardapio(Cardapio cardapio) {
		this.cardapio = cardapio;
	}

}
