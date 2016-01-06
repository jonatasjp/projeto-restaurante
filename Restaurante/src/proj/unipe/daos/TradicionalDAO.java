package proj.unipe.daos;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import proj.unipe.entities.Tradicional;

@Repository
public class TradicionalDAO extends AbstractDAO<Tradicional> {

	private Tradicional tradicional;
	
	public TradicionalDAO() {
		super(Tradicional.class);

	}
	
	

}
