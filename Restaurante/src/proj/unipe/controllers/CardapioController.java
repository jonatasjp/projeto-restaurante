package proj.unipe.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import proj.unipe.entities.Cardapio;
import proj.unipe.entities.Categoria;
import proj.unipe.services.CardapioService;
import proj.unipe.services.CategoriaService;

@Controller
@RequestMapping(value = "cardapio")
public class CardapioController {

	@Autowired
	private CardapioService cardapioService;

	@Autowired
	private CategoriaService categoriaService;

	@RequestMapping(method = RequestMethod.GET, value = { "listar" })
	public String list(ModelMap map) {

		List<Cardapio> cardapios = cardapioService.listar();
		map.addAttribute("cardapios", cardapios);
		map.addAttribute("selectDeCategoria", selectDeCategoria());
		map.addAttribute("filtro", new Cardapio());
		return "/cardapio/listar";

	}

	public Map<Long, String> selectDeCategoria() {

		List<Categoria> categoriasAtivas = categoriaService.buscarCategoriasAtivas();

		Map<Long, String> mapa = new HashMap<Long, String>();
		mapa.put(0L, "selecione");
		for (Categoria categoria : categoriasAtivas) {

			mapa.put(categoria.getId(), categoria.getNome());
		}

		return mapa;
	}

	public List<String> selectDeStatus() {

		List<String> lista = new ArrayList<String>();
		lista.add("Selecione");
		lista.add("Ativo");
		lista.add("Não-Ativo");

		return lista;
	}

	@RequestMapping(method = RequestMethod.GET, value = { "filtrar" })
	public String filtrar(@ModelAttribute("filtro") Cardapio filtro, ModelMap map) {

		try {
			if (filtro.getNome() == null && filtro.getNome().equals("")) {
			} else {
				List<Cardapio> cardapios = cardapioService.buscaPorNome(filtro.getNome());

				map.addAttribute("cardapios", cardapios);
				map.addAttribute("selectDeCategoria", selectDeCategoria());
				map.addAttribute("filtro", filtro);
			}
			if (!filtro.getCategoria().temIdValido()) {
			} else {

				List<Cardapio> cardapios = cardapioService.buscarCardapioPorCategoria(filtro);

				map.addAttribute("cardapios", cardapios);
				map.addAttribute("selectDeCategoria", selectDeCategoria());
				map.addAttribute("filtro", filtro);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/cardapio/listar";

	}

	@RequestMapping(method = RequestMethod.GET, value = { "novo" })
	public String insertForm(ModelMap map) {
		Cardapio cardapio = new Cardapio();
		cardapio.setCategoria(new Categoria());
		map.addAttribute("cardapio", cardapio);
		map.addAttribute("selectDeCategoria", selectDeCategoria());
		map.addAttribute("selectDeStatus", selectDeStatus());
		return "/cardapio/novo";
	}

	@RequestMapping(method = RequestMethod.GET, value = { "salvar" })
	public String save(@ModelAttribute("cardapio") @Valid Cardapio cardapio, BindingResult result, ModelMap map) {
		if (result.hasErrors()) {
			map.addAttribute("cardapio", cardapio);
			map.addAttribute("selectDeCategoria", selectDeCategoria());
			map.addAttribute("selectDeStatus", selectDeStatus());
			return "/cardapio/novo";
		}
		try {
			if (cardapio.temIdValido() && !cardapio.getStatus().equals("Selecione")
					&& cardapio.getCategoria().temIdValido()) {
				cardapioService.atualizar(cardapio);
			} else if (!cardapio.getStatus().equals("Selecione") && !cardapio.temIdValido() && cardapio.getCategoria().temIdValido()) {
				cardapioService.inserir(cardapio);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/cardapio/listar";
	}

	@RequestMapping(method = RequestMethod.GET, value = { "/{id}/updateForm" })
	public String updateForm(@PathVariable("id") Long id, ModelMap map) {
		Cardapio cardapio = cardapioService.buscarPorId(id);
		map.addAttribute("cardapio", cardapio);
		map.addAttribute("selectDeCategoria", selectDeCategoria());
		map.addAttribute("selectDeStatus", selectDeStatus());
		return "/cardapio/novo";
	}

	@RequestMapping(method = RequestMethod.GET, value = { "/{id}/ativar" })
	public String ativar(@PathVariable("id") Long id, ModelMap map) {
		Cardapio cardapio = cardapioService.buscarPorId(id);
		cardapio.setStatus("Ativo");

		try {
			cardapioService.atualizar(cardapio);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/cardapio/listar";

	}

	@RequestMapping(method = RequestMethod.GET, value = { "/{id}/desativar" })
	public String desativar(@PathVariable("id") Long id, ModelMap map) {
		Cardapio cardapio = cardapioService.buscarPorId(id);
		cardapio.setStatus("Não-Ativo");

		try {
			cardapioService.atualizar(cardapio);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "redirect:/cardapio/listar";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}/remove")
	public String remove(@PathVariable("id") Long id) {
		Cardapio cardapio = cardapioService.buscarPorId(id);
		if (cardapio != null) {
			try {
				cardapioService.excluir(cardapio);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "redirect:/cardapio/listar";
	}

}
