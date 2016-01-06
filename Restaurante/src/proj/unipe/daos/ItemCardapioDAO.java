package proj.unipe.daos;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import proj.unipe.entities.Funcionario;
import proj.unipe.entities.ItemCardapio;
import proj.unipe.entities.Reserva;

@Repository
public class ItemCardapioDAO extends AbstractDAO<ItemCardapio> {

	private ItemCardapio itemCardapio;
	
	private Session session;

	public ItemCardapioDAO() {

		super(ItemCardapio.class);

	}
	
	
/*	public List<ItemCardapio> buscarItensPorIdDoPedido(Long pedido_id) {
		this.session = (Session) manager.getDelegate();

		Criteria crit = session.createCriteria(ItemCardapio.class);
		crit.add(Restrictions.eq("pedido_id", pedido_id));
		List results = crit.list();

		return results;
	}*/
	
	public List<ItemCardapio> buscarItensPorIdDoPedido(Long pedido_id) {
		String sql = "select p from ItemCardapio p where p.pedido.id = :pedido_id";
		Query query = manager.createQuery(sql);
		query.setParameter("pedido_id", pedido_id);
		return query.getResultList();
	}
	
	
	
	
	
	
}