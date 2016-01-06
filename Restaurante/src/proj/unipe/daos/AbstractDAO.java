package proj.unipe.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import proj.unipe.entities.AbstractEntity;
//classe generica, todos os daos irão se utilizar dessa classe para fazer as chamadas de métodos
//que são responsáveis pelas operações do CRUD, o tipo generico desta classe herda de abstractEntity
//pois, assim garante que as entidades que forem passadas para os métodos herdem de abstractEntity
//uma vez que a entidade herde de abstractEntity ela obrigatoriamente terá que implementar os metodos setID e getID
//isso é necessário pois alguns metodos como por exemplo o método excluir necessita que a entidade tenha obrigatoriamente
//o método getId.
public class AbstractDAO<T extends AbstractEntity> {
	
	@PersistenceContext
	protected EntityManager manager;
	
	
	private Class<T> classe;

	public AbstractDAO( Class<T> classe) {
		this.classe = classe;
	}

	public void inserir(T entidade) {
		manager.persist(entidade);
	}

	public void atualizar(T entidade) {
		manager.merge(entidade);
	}

	public void excluir(T entidade) {
		entidade = manager.find(classe, entidade.getId());
		//System.out.println(entidade.getId());
		manager.remove(entidade);
	}
	
	public T buscarPorID(Long id) {
		return manager.find(classe, id);
	}

	public List<T> listar() {

		Query query = manager.createQuery("select e from "
				+ classe.getSimpleName() + " e");

		return query.getResultList();

	}

}
