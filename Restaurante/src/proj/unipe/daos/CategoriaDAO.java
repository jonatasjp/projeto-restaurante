package proj.unipe.daos;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import proj.unipe.entities.Categoria;

@Repository
public class CategoriaDAO extends AbstractDAO<Categoria> {

	private Categoria categoria;

	private Session session;

	public CategoriaDAO() {

		super(Categoria.class);

	}

	// Busca por nome da categoria com CRITERIA(hibernate)
	public List<Categoria> buscaPorNome(String nome) {
		this.session = (Session) manager.getDelegate();

		Criteria crit = session.createCriteria(Categoria.class);
		crit.add(Restrictions.ilike("nome", nome));
		List results = crit.list();

		return results;
	}
	
	public List<Categoria> buscarCategoriasAtivas() {

		String jpql = "select c from Categoria c where c.status =:status";
		Query query = manager.createQuery(jpql);

		query.setParameter("status", "Ativo");

		return query.getResultList();

	}

}