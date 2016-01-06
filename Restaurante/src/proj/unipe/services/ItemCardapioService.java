package proj.unipe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import proj.unipe.daos.ItemCardapioDAO;
import proj.unipe.entities.ItemCardapio;

@Service
@Transactional
public class ItemCardapioService {

	@Autowired
	private ItemCardapioDAO itemCardapioDAO;

	public void inserir(ItemCardapio itemCardapio) {
		itemCardapioDAO.inserir(itemCardapio);

	}

	public void atualizar(ItemCardapio itemCardapio) {
		itemCardapioDAO.atualizar(itemCardapio);

	}

	public void excluir(ItemCardapio itemCardapio) {
		itemCardapioDAO.excluir(itemCardapio);

	}

	public ItemCardapio buscarPorId(Long id) {
		ItemCardapio itemCardapio = null;
		itemCardapio = (ItemCardapio) itemCardapioDAO.buscarPorID(id);
		return itemCardapio;

	}

	public List<ItemCardapio> listar() {
		List<ItemCardapio> itemCardapio = null;
		itemCardapio = itemCardapioDAO.listar();
		return itemCardapio;

	}
	
	public List<ItemCardapio> buscarItensPorIdDoPedido(Long pedido_id) {
		return itemCardapioDAO.buscarItensPorIdDoPedido(pedido_id);
	}

}