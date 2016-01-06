package proj.unipe.services;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import proj.unipe.bd.JPAUtil;
import proj.unipe.daos.GerenteDAO;
import proj.unipe.entities.Gerente;

@Service
@Transactional
public class GerenteService {

	@Autowired
	private GerenteDAO gerenteDAO;

	public void inserir(Gerente gerente) {

		gerenteDAO.inserir(gerente);

	}

	public void atualizar(Gerente gerente) {

		gerenteDAO.atualizar(gerente);

	}

	public void excluir(Gerente gerente) {

		gerenteDAO.excluir(gerente);

	}

	public Gerente buscarPorId(Long id) {

		Gerente gerente = null;

		gerente = (Gerente) gerenteDAO.buscarPorID(id);

		return gerente;

	}

	public List<Gerente> listar() {

		List<Gerente> gerente = null;

		gerente = gerenteDAO.listar();

		return gerente;

	}

}
