package proj.unipe.daos;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import proj.unipe.entities.Delivery;
import proj.unipe.entities.Pedido;
import proj.unipe.entities.Tradicional;

@Repository
public class PedidoDAO extends AbstractDAO<Pedido> {

	private Pedido pedido;
	
	private Session session;
	
	public PedidoDAO() {
		super(Pedido.class);
	
	}
	
	public List<Delivery> listarDelivery(){
		String jpql = "select d from Delivery d";
		Query query = manager.createQuery(jpql);
		List<Delivery> results = query.getResultList();
		return results;
	}
	
	public List<Tradicional> listarTradicional(){
		String jpql = "select t from Tradicional t";
		Query query = manager.createQuery(jpql);
		List<Tradicional> results = query.getResultList();
		return results;
	}
	
	public List<Pedido> buscaPorNumeroPedido(Long numPedido) {
		this.session = (Session) manager.getDelegate();

		Criteria crit = session.createCriteria(Pedido.class);
		crit.add(Restrictions.eq("id", numPedido));
		List results = crit.list();

		return results;
	}
	
	public List<Pedido> buscarPedido(Pedido filtro) {
		String sql = "select p from Pedido p where 1=1";
		if(filtro.getId() != null && filtro.getId().equals("")){
			sql += "and p.id =" + filtro.getId();
		}
		if(filtro.getStatus() != null ){
			sql += "and p.status =" + filtro.getStatus();
		}
		
		Query query = manager.createQuery(sql);
		return query.getResultList();
	}
}
