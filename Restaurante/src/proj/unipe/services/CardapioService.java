package proj.unipe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import proj.unipe.daos.CardapioDAO;
import proj.unipe.daos.CategoriaDAO;
import proj.unipe.entities.Cardapio;
import proj.unipe.entities.Categoria;

@Service
@Transactional
public class CardapioService {

	@Autowired
	private CardapioDAO cardapioDAO;
	@Autowired
	private CategoriaDAO categoriaDAO;

	public void inserir(Cardapio cardapio) {

		cardapioDAO.inserir(cardapio);

	}

	public void atualizar(Cardapio cardapio) {

		cardapioDAO.atualizar(cardapio);
	}

	public void excluir(Cardapio cardapio) {

		cardapioDAO.excluir(cardapio);

	}

	// método para realizar a busca por numero da mesa com CRITERIA
	public List<Cardapio> buscaPorNome(String nome) {

		List<Cardapio> cardapios = null;

		return cardapios = cardapioDAO.buscarPorNome(nome);

	}

	public Cardapio buscarPorId(Long id) {

		Cardapio cardapio = null;

		return cardapio = (Cardapio) cardapioDAO.buscarPorID(id);

	}

	public List<Cardapio> listar(){

		List<Cardapio> cardapio = null;

		return cardapio = cardapioDAO.listar();

	}
	
	public List<Cardapio> buscarCardapioPorCategoria(Cardapio filtro){
		
		return cardapioDAO.buscarCardapioPorCategoria(filtro);
		
	}
	

}
