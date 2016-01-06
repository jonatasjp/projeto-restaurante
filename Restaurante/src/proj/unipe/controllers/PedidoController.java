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
import proj.unipe.entities.ItemCardapio;
import proj.unipe.entities.Mesa;
import proj.unipe.entities.Pedido;
import proj.unipe.entities.Tradicional;
import proj.unipe.services.CardapioService;
import proj.unipe.services.ItemCardapioService;
import proj.unipe.services.MesaService;
import proj.unipe.services.PedidoService;

@Controller
@RequestMapping(value = "pedido")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;

	@Autowired
	private CardapioService cardapioService;

	@Autowired
	private MesaService mesaService;
	
	@Autowired
	private ItemCardapioService itemCardapioService;
	
	public static int idItem = 0;

	@RequestMapping(method = RequestMethod.GET, value = { "listar" })
	public String list(ModelMap map) {

		List<Tradicional> pedidos = pedidoService.listarTradicional();
		
		map.addAttribute("pedidos", pedidos);
		map.addAttribute("selectStatus", selectStatus());
		map.addAttribute("filtro", new Pedido());
		return "/pedido/listar";

	}

	@RequestMapping(method = RequestMethod.GET, value = { "novo" })
	public String novoPedido(ModelMap map, HttpSession sessao) {

		sessao.setAttribute("carrinho", null);
		idItem = 0;
		
		ItemCardapio item = new ItemCardapio();
		Tradicional tradicional = new Tradicional();
		tradicional.setMesa(new Mesa());
		item.setTradicional(tradicional);
		item.setCardapio(new Cardapio());

		map.addAttribute("item", item);
		map.addAttribute("selectMesas", selectMesas());
		map.addAttribute("selectStatus", selectStatus());
		map.addAttribute("selectCardapio", selectCardapio());

		return "/pedido/novo";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}/removerItem")
	public String remove(@ModelAttribute("item") ItemCardapio itemCardapio, @PathVariable("id") Long id, HttpSession sessao, ModelMap map){
		
		List<ItemCardapio> carrinho = (List<ItemCardapio>) sessao.getAttribute("carrinho");
		
		carrinho.remove(idItem);

		--idItem;
		
		
		map.addAttribute("carrinho", carrinho);
		map.addAttribute("selectMesas", selectMesas());
		map.addAttribute("selectStatus", selectStatus());
		map.addAttribute("selectCardapio", selectCardapio());
		
		return "/pedido/novo";
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
			sessao.setAttribute("pedido", itemCardapio.getPedido());
			sessao.setAttribute("id", idItem);
			

		} else {

			carrinho = (List<ItemCardapio>) sessao.getAttribute("carrinho");
			carrinho.add(itemCardapio);
			sessao.setAttribute("carrinho", carrinho);
			sessao.setAttribute("id",idItem++);
			
		}
		
		map.addAttribute("carrinho", carrinho);
		map.addAttribute("selectMesas", selectMesas());
		map.addAttribute("selectStatus", selectStatus());
		map.addAttribute("selectCardapio", selectCardapio());
		

		return "/pedido/novo";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = { "finalizarPedido" })
	public String finalizarPedido( HttpSession sessao, ModelMap map) {
		
		List <ItemCardapio> carrinho = (List<ItemCardapio>) sessao.getAttribute("carrinho");
		
		Pedido pedido = (Pedido) sessao.getAttribute("pedido");
		pedido.setData(new Date());
		
		for (ItemCardapio itemCardapio : carrinho) {
			itemCardapio.setPedido(pedido);
		}
		
		pedido.setItemCardapio(carrinho);
		pedidoService.inserir(pedido);
	
		List<Tradicional> pedidos = pedidoService.listarTradicional();
		map.addAttribute("pedidos", pedidos);
		map.addAttribute("selectStatus", selectStatus());
		map.addAttribute("filtro", new Pedido());
			
		return "/pedido/listar";
	}
	
	public Map<Long, String> selectMesas() {
		List<Mesa> mesas = mesaService.listar();
		Map<Long, String> mapa = new HashMap<Long, String>();
		mapa.put(0L, "selecione");
		for (Mesa mesa : mesas) {

			mapa.put(mesa.getId(), mesa.getNumero());
		}

		return mapa;
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
		try{
			List<Pedido> pedidos = pedidoService.buscaPorNumeroPedido(filtro.getId());

			map.addAttribute("pedidos", pedidos);
			map.addAttribute("selectStatus", selectStatus());
			map.addAttribute("filtro", filtro);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/pedido/listar";

	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}/detalharPedido")
	public String detalharPedido(@PathVariable("id") Long id, ModelMap map) {
		
		//busco o pedido tradicional que tem aquele id para saber qual a mesa daquele pedido
		Tradicional tradicional = (Tradicional) pedidoService.buscarPorID(id);
		
		//apos ter o tradicional que possui uma determinada mesa eu busco o objeto mesa daquele pedido tradicional
		//Mesa mesa = mesaService.buscarPorId(tradicional.getMesa().getId());
		
		//agora seto o nome da mesa que corresponde ao pedido
		//tradicional.setMesa(mesa);
		
		List<ItemCardapio> itensDoPedido = itemCardapioService.buscarItensPorIdDoPedido(id);
		
		map.addAttribute("tradicional", tradicional);
		map.addAttribute("itensDoPedido", itensDoPedido);
		
		return "pedido/detalhar";
	}

}
