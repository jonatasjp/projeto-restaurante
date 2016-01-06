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
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.NotEmpty;

//classe bean, será mapeada para que seja um espelho da tabela no banco de dados.
@Entity
@Table(name = "mesa")
@SequenceGenerator(sequenceName = "mesa_seq", name = "mesa_id", allocationSize = 1)
public class Mesa extends AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mesa_id")
	private Long id;

	@NotEmpty(message="O número da mesa é obrigatório")
	@Column(length = 10)
	private String numero;

	@Column(length = 15)
	private Boolean deReserva;
	
	@NotNull(message="A capacidade da Mesa é obrigatória")
	@Column(length = 5)
	private int capacidade;

	@OneToMany(mappedBy = "mesa")
	private List<Tradicional> tradicional;

	@OneToMany(mappedBy = "mesa", cascade = CascadeType.REMOVE)
	private List<Reserva> reservas;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public int getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}

	public List<Tradicional> getTradicional() {
		return tradicional;
	}

	public void setTradicional(List<Tradicional> tradicional) {
		this.tradicional = tradicional;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	public Boolean getDeReserva() {
		return deReserva;
	}

	public void setDeReserva(Boolean deReserva) {
		this.deReserva = deReserva;
	}
	
}
