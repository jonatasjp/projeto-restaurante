package proj.unipe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import proj.unipe.daos.FuncionarioDAO;
import proj.unipe.daos.MesaDAO;
import proj.unipe.daos.ReservaDAO;
import proj.unipe.entities.Funcionario;
import proj.unipe.entities.Mesa;
import proj.unipe.entities.Reserva;

@Service
@Transactional
public class ReservaService {

	@Autowired
	private ReservaDAO reservaDAO;
	@Autowired
	private MesaDAO mesaDao;
	@Autowired
	private FuncionarioDAO funcionarioDAO;

	public void inserir(Reserva reserva) {
		
		/*
		Mesa mesa = (Mesa) mesaDao.buscarPorID(id_mesa);

		reserva.setMesa(mesa);*/
	
		reservaDAO.inserir(reserva);

	}

	public void atualizar(Reserva reserva) {
		reservaDAO.atualizar(reserva);

	}

	public void excluir(Reserva reserva) {
		reservaDAO.excluir(reserva);

	}
	
	public List<Reserva> buscarReserva(Reserva filtro) {
		return reservaDAO.buscarReserva(filtro);
	}
	
	

	public Reserva buscarPorId(Long id) {
		Reserva reserva = null;
		reserva = (Reserva) reservaDAO.buscarPorID(id);
		return reserva;

	}

	public List<Reserva> listar() {
		return reservaDAO.listar();

	}

	public List<Reserva> buscarPorMesa(Reserva mesa) {
		return reservaDAO.buscarReservaPorMesa(mesa);
	}
	
	
	public List<Reserva> buscarPorNomeResponsavel(String filtro) {
		return reservaDAO.buscarPorNomeResponsavel(filtro);
	}

}
