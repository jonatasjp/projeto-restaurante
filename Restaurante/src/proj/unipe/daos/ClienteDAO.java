package proj.unipe.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import proj.unipe.entities.Cliente;

@Repository
public class ClienteDAO extends AbstractDAO<Cliente> {

	private Cliente cliente;

	public ClienteDAO() {
		super(Cliente.class);

	}
	
	public List<Cliente> buscarPorNome(String filtro) {
		String sql = "select c from Cliente c where upper(c.nome) like upper(:nome)";
		Query query = manager.createQuery(sql);
		query.setParameter("nome", "%"+filtro+"%");
		return query.getResultList();
	}

}
