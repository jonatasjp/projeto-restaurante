package proj.unipe.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Funcionario extends Usuario {

	@Column( length = 10)
	private Double salario;

	@Column( length = 14)
	private String cpf;

	@Column( length = 30)
	private String cargo;

	@OneToMany(mappedBy = "funcionario")
	private List<Reserva> reserva;

	@OneToMany(mappedBy = "funcionario")
	private List<Pedido> pedidos;

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getCpf() {
		return cpf;
	}

	public List<Reserva> getReserva() {
		return reserva;
	}

	public void setReserva(List<Reserva> reserva) {
		this.reserva = reserva;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

}
