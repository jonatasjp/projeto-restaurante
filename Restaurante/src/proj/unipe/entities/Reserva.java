package proj.unipe.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name ="reserva")
@SequenceGenerator(sequenceName = "reserva_seq", name = "reserva_id", allocationSize = 1)
public class Reserva extends AbstractEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reserva_id")
	private Long id;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dataInicial;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dataFinal;
	
	
	@NotNull(message="Campo obrigatório")
	@Column(length = 5)
	private int num_Pessoas;
	
	@NotEmpty(message="Campo obrigatório")
	@Column(length = 50)
	private String nome_responsavel;
	
	@ManyToOne
	@JoinColumn(name = "mesa_id")
	private Mesa mesa;
	
	@ManyToOne
	@JoinColumn(name = "funcionario_id")
	private Funcionario funcionario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public int getNum_Pessoas() {
		return num_Pessoas;
	}

	public void setNum_Pessoas(int num_Pessoas) {
		this.num_Pessoas = num_Pessoas;
	}

	public String getNome_responsavel() {
		return nome_responsavel;
	}

	public void setNome_responsavel(String nome_responsavel) {
		this.nome_responsavel = nome_responsavel;
	}

	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	
}
