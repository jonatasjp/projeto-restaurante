package proj.unipe.services;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import proj.unipe.bd.JPAUtil;
import proj.unipe.daos.TradicionalDAO;
import proj.unipe.entities.Tradicional;

@Service
@Transactional
public class TradicionalService {

	@Autowired
	private TradicionalDAO tradicionalDAO;

	public void inserir(Tradicional tradicional) {

		tradicionalDAO.inserir(tradicional);

	}

	public void atualizar(Tradicional tradicional) {

		tradicionalDAO.atualizar(tradicional);

	}

	public void excluir(Tradicional tradicional) {

		tradicionalDAO.excluir(tradicional);

	}

	public Tradicional buscarPorId(Long id) {

		Tradicional tradicional = null;

		tradicional = (Tradicional) tradicionalDAO.buscarPorID(id);

		return tradicional;

	}

	public List<Tradicional> listar() {

		List<Tradicional> tradicional = null;

		tradicional = tradicionalDAO.listar();

		return tradicional;

	}

}
