package proj.unipe.daos;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import proj.unipe.entities.Mesa;

//classe responsável por extender abstractDAO, desta forma ela terá acesso aos métodos de abstractDAO.
//podendo assim chamar esses métodos para realizar operaçoes do CRUD.

@Repository
public class MesaDAO extends AbstractDAO<Mesa> {

	private Mesa mesa;

	private Session session;

	public MesaDAO() {
		super(Mesa.class);

	}

	// Buscando mesa por numero com CRITERIA(hibernate)
	public List<Mesa> buscaPorNumMesa(int numero) {
		this.session = (Session) manager.getDelegate();

		Criteria crit = session.createCriteria(Mesa.class);
		crit.add(Restrictions.eq("capacidade", numero));
		List results = crit.list();

		return results;
	}
	
	
	public List<Mesa> buscaPorMesaDeReserva(boolean b) {
		this.session = (Session) manager.getDelegate();

		Criteria crit = session.createCriteria(Mesa.class);
		crit.add(Restrictions.eq("deReserva", b));
		List results = crit.list();

		return results;
	}
	
	
	
	
	
	
	
	

}
