package proj.unipe.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import proj.unipe.entities.Usuario;

@Repository
public class UsuarioDAO extends AbstractDAO {

	private Usuario usuario;
	
	public UsuarioDAO() {
		super(Usuario.class);
		
	}
	
	public Usuario validaLogin(Usuario usuario) {
		String s = "select u from Usuario u where u.login = :login and u.senha = :senha";
		Query query = manager.createQuery(s);
		query.setParameter("login", usuario.getLogin());
		query.setParameter("senha", usuario.getSenha());
		List<Usuario> usuarios = query.getResultList();
		
		if(!usuarios.isEmpty()){
			return usuarios.get(0);
		}
		return null;
	}
	
}
