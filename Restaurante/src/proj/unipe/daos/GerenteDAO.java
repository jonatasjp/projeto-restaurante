package proj.unipe.daos;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import proj.unipe.entities.Gerente;

@Repository
public class GerenteDAO extends AbstractDAO<Gerente> {

	private Gerente gerente;
	private EntityManager manager2 = null; 
	
	public GerenteDAO() {

		super(Gerente.class);
		
	}


}