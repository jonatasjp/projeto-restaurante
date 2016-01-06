package proj.unipe.services;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import proj.unipe.bd.JPAUtil;
import proj.unipe.daos.UsuarioDAO;
import proj.unipe.entities.Usuario;

@Service
@Transactional
public class UsuarioService {

	@Autowired
	private UsuarioDAO usuarioDAO;

	public Usuario validaLogin(Usuario usuario) {

		return usuarioDAO.validaLogin(usuario);

	}

	public void inserir(Usuario usuario) {

		usuarioDAO.inserir(usuario);

	}

	public void atualizar(Usuario usuario) {

		usuarioDAO.atualizar(usuario);

	}

	public void excluir(Usuario usuario) {

		usuarioDAO.excluir(usuario);

	}

	public Usuario buscarPorID(Long id) {

		Usuario user = null;

		user = (Usuario) usuarioDAO.buscarPorID(id);

		return user;
	}

	public List<Usuario> listar() {

		List<Usuario> usuarios = null;

		usuarios = usuarioDAO.listar();

		return usuarios;

	}

}
