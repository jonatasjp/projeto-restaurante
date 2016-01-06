package proj.unipe.services;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import proj.unipe.bd.JPAUtil;
import proj.unipe.daos.CategoriaDAO;
import proj.unipe.entities.Categoria;

@Service
@Transactional
public class CategoriaService {

	@Autowired
	private CategoriaDAO categoriaDAO;

	public void inserir(Categoria categoria) throws Exception {

		categoriaDAO.inserir(categoria);

	}

	public void atualizar(Categoria categoria) throws Exception {

		categoriaDAO.atualizar(categoria);

	}

	public void excluir(Categoria categoria) {

		categoriaDAO.excluir(categoria);

	}

	public Categoria buscarPorId(Long id) {

		Categoria categoria = null;

		return categoria = (Categoria) categoriaDAO.buscarPorID(id);

	}

	// método para realizar a busca por nome da categoria com CRITERIA
	public List<Categoria> buscaPorNome(String nome) {

		List<Categoria> categorias = null;

		return categorias = categoriaDAO.buscaPorNome(nome);

	}

	public List<Categoria> listar() {

		List<Categoria> categorias = null;

		return categorias = categoriaDAO.listar();

	}
	

	public List<Categoria> buscarCategoriasAtivas(){
		
		return categoriaDAO.buscarCategoriasAtivas();
		
	}

}