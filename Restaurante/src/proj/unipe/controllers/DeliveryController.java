package proj.unipe.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import proj.unipe.entities.Cardapio;
import proj.unipe.entities.Cliente;
import proj.unipe.entities.Delivery;
import proj.unipe.entities.ItemCardapio;
import proj.unipe.entities.Pedido;
import proj.unipe.entities.Usuario;
import proj.unipe.services.CardapioService;
import proj.unipe.services.ClienteService;
import proj.unipe.services.ItemCardapioService;
import proj.unipe.services.PedidoService;

@Controller
@RequestMapping(value = "delivery")
public class DeliveryController {

	@Autowired
	private PedidoService pedidoService;

	@Autowired
	private CardapioService cardapioService;

	@Autowired
	private ItemCardapioService itemCardapioService;

	@Autowired
	private ClienteService clienteService;

	public static int idItem = 0;

	@RequestMapping(method = RequestMethod.GET, value = { "listar" })
	public String list(ModelMap map) {

		List<Delivery> pedidos = pedidoService.listarDelivery();

		map.addAttribute("pedidos", pedidos);
		map.addAttribute("selectStatus", selectStatus());
		map.addAttribute("filtro", new Pedido());

		return "/delivery/listar";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}/removerItem")
	public String remove(@ModelAttribute("item") ItemCardapio itemCardapio, @PathVariable("id") Long id,
			HttpSession sessao, ModelMap map) {

		List<ItemCardapio> carrinho = (List<ItemCardapio>) sessao.getAttribute("carrinho");

		carrinho.remove(idItem);

		--idItem;

		map.addAttribute("carrinho", carrinho);
		map.addAttribute("selectStatus", selectStatus());
		map.addAttribute("selectCardapio", selectCardapio());

		return "/delivery/novo";
	}

	@RequestMapping(method = RequestMethod.GET, value = { "/adicionarItem" })
	public String adicionarItem(@ModelAttribute("item") ItemCardapio itemCardapio, HttpSession sessao, ModelMap map) {

		List<ItemCardapio> carrinho = null;

		Cardapio cardapio = cardapioService.buscarPorId(itemCardapio.getCardapio().getId());

		itemCardapio.setCardapio(cardapio);
		itemCardapio.setPreco(cardapio.getPreco());

		if (sessao.getAttribute("carrinho") == null) {

			carrinho = new ArrayList<ItemCardapio>();

			carrinho.add(itemCardapio);
			sessao.setAttribute("carrinho", carrinho);
			sessao.setAttribute("delivery", itemCardapio.getDelivery());
			sessao.setAttribute("id", idItem);

		} else {

			carrinho = (List<ItemCardapio>) sessao.getAttribute("carrinho");
			carrinho.add(itemCardapio);
			sessao.setAttribute("carrinho", carrinho);
			sessao.setAttribute("id", idItem++);

		}

		map.addAttribute("carrinho", carrinho);
		map.addAttribute("selectStatus", selectStatus());
		map.addAttribute("selectCardapio", selectCardapio());

		return "/delivery/novo";
	}

	@RequestMapping(method = RequestMethod.GET, value = { "finalizarPedido" })
	public String finalizarPedido(HttpSession sessao, ModelMap map) {

		List<ItemCardapio> carrinho = (List<ItemCardapio>) sessao.getAttribute("carrinho");

		Cliente cliente = new Cliente();
		Usuario usuario = (Usuario) sessao.getAttribute("usuario");
		if (usuario instanceof Cliente) {

			cliente = (Cliente) usuario;

		}

		Delivery delivery = (Delivery) sessao.getAttribute("delivery");
		delivery.setData(new Date());

		for (ItemCardapio itemCardapio : carrinho) {
			itemCardapio.setDelivery(delivery);
			if (usuario instanceof Cliente) {
				itemCardapio.getDelivery().setCliente(cliente);
			}
		}

		delivery.setItemCardapio(carrinho);
		pedidoService.inserir(delivery);

		List<Delivery> pedidos = pedidoService.listarDelivery();
		map.addAttribute("pedidos", pedidos);
		map.addAttribute("selectStatus", selectStatus());
		map.addAttribute("filtro", new Pedido());

		return "/delivery/listar";

	}

	public List<String> selectStatus() {

		List<String> status = new ArrayList<String>();

		status.add("pendente");
		status.add("atendido");
		status.add("cancelado");

		return status;
	}

	public Map<Long, String> selectCardapio() {
		List<Cardapio> itens = cardapioService.listar();
		Map<Long, String> mapa = new HashMap<Long, String>();
		mapa.put(0L, "selecione");
		for (Cardapio cardapio : itens) {

			mapa.put(cardapio.getId(), cardapio.getNome());
		}

		return mapa;
	}

	@RequestMapping(method = RequestMethod.GET, value = { "filtrar" })
	public String filtrar(@ModelAttribute("filtro") Pedido filtro, ModelMap map) {
		try {
			List<Pedido> pedidos = pedidoService.buscarPedido(filtro);

			map.addAttribute("pedidos", pedidos);
			map.addAttribute("selectStatus", selectStatus());
			map.addAttribute("filtro", filtro);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/delivery/listar";

	}

	@RequestMapping(method = RequestMethod.GET, value = { "novoDelivery" })
	public String novoDelivery(ModelMap map, HttpSession sessao) {

		Cliente cliente = new Cliente();
		Usuario usuario = (Usuario) sessao.getAttribute("usuario");
		if (usuario instanceof Cliente) {

			cliente = (Cliente) usuario;

		}

		sessao.setAttribute("carrinho", null);

		ItemCardapio item = new ItemCardapio();
		Delivery delivery = new Delivery();
		delivery.setCliente(cliente);
		item.setDelivery(delivery);
		item.setCardapio(new Cardapio());

		map.addAttribute("item", item);
		map.addAttribute("selectStatus", selectStatus());
		map.addAttribute("selectCardapio", selectCardapio());

		return "/delivery/novo";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}/detalharDelivery")
	public String detalharPedido(@PathVariable("id") Long id, ModelMap map) {

		Delivery delivery = (Delivery) pedidoService.buscarPorID(id);

		List<ItemCardapio> itensDoPedido = itemCardapioService.buscarItensPorIdDoPedido(id);

		map.addAttribute("delivery", delivery);
		map.addAttribute("itensDoPedido", itensDoPedido);

		return "delivery/detalhar";
	}

}
