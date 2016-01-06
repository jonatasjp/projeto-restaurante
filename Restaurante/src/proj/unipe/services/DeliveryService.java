package proj.unipe.services;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import proj.unipe.bd.JPAUtil;
import proj.unipe.daos.DeliveryDAO;
import proj.unipe.entities.Delivery;

@Service
@Transactional
public class DeliveryService {

	@Autowired
	private DeliveryDAO deliveryDAO;

	public void inserir(Delivery delivery) {

		deliveryDAO.inserir(delivery);

	}

	public void atualizar(Delivery delivery) {

		deliveryDAO.atualizar(delivery);

	}

	public void excluir(Delivery delivery) {

		deliveryDAO.excluir(delivery);

	}

	public Delivery buscarPorId(Long id) {

		Delivery delivery = null;

		return delivery = (Delivery) deliveryDAO.buscarPorID(id);

	}

	public List<Delivery> listar() {

		List<Delivery> delivery = null;

		return delivery = deliveryDAO.listar();

	}

}
