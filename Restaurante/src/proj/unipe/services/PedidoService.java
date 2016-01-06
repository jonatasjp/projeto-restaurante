package proj.unipe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import proj.unipe.daos.PedidoDAO;
import proj.unipe.entities.Delivery;
import proj.unipe.entities.Pedido;
import proj.unipe.entities.Tradicional;

@Service
@Transactional
public class PedidoService {

	@Autowired
	private PedidoDAO pedidoDAO;

	public void inserir(Pedido pedido) {

		pedidoDAO.inserir(pedido);

	}

	public void atualizar(Pedido pedido) {
		pedidoDAO.atualizar(pedido);

	}

	public void excluir(Pedido pedido) {
		pedidoDAO.excluir(pedido);

	}

	public Pedido buscarPorID(Long id) {
		
		return pedidoDAO.buscarPorID(id);
	}

	public List<Pedido> listar() {
		List<Pedido> pedidos = null;
		pedidos = pedidoDAO.listar();
		return pedidos;

	}
	
	public List<Pedido> buscaPorNumeroPedido(Long numPedido) {
		return pedidoDAO.buscaPorNumeroPedido(numPedido);
		
	}

	public List<Pedido> buscarPedido(Pedido pedido){
		return pedidoDAO.buscarPedido(pedido);
	}
	
	public List<Delivery> listarDelivery(){
		return pedidoDAO.listarDelivery();
	}
	
	public List<Tradicional> listarTradicional(){
		return pedidoDAO.listarTradicional();
	}
}
