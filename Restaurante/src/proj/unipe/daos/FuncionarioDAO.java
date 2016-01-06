package proj.unipe.daos;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import proj.unipe.entities.Funcionario;

@Repository
public class FuncionarioDAO extends AbstractDAO<Funcionario> {

	private Funcionario funcionario;

	private Session session;

	public FuncionarioDAO() {

		super(Funcionario.class);


	}

	// Busca por nome com CRITERIA(hibernate)
	public List<Funcionario> buscaPorNome(String nome) {
		this.session = (Session) manager.getDelegate();

		Criteria crit = session.createCriteria(Funcionario.class);
		crit.add(Restrictions.ilike("nome", nome));
		List results = crit.list();

		return results;
	}

}
