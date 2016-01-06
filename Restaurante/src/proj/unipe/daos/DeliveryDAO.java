package proj.unipe.daos;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import proj.unipe.entities.Delivery;

@Repository
public class DeliveryDAO extends AbstractDAO<Delivery> {

	private Delivery delivery;
	
	
	public DeliveryDAO() {
	
		super(Delivery.class);
		
	}
	


}
