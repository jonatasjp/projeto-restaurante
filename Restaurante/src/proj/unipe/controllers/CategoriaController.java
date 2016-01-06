package proj.unipe.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import proj.unipe.entities.Categoria;
import proj.unipe.services.CategoriaService;

@Controller
@RequestMapping(value = "categoria")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;

	@RequestMapping(method = RequestMethod.GET, value = { "listar" })
	public String list(ModelMap map) {

		List<Categoria> categorias = categoriaService.listar();
		map.addAttribute("categorias", categorias);
		map.addAttribute("filtro", new Categoria());
		return "/categoria/listar";
	}

	@RequestMapping(method = RequestMethod.GET, value = { "filtrar" })
	public String filter(@ModelAttribute("filtro") Categoria filtro, ModelMap map) {
		try {
			if (filtro.getNome() == null || filtro.getNome().equals("")) {
				return "redirect:/categoria/listar";
			}

			List<Categoria> categorias = categoriaService.buscaPorNome(filtro.getNome());
			map.addAttribute("categorias", categorias);
			map.addAttribute("filtro", filtro);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/categoria/listar";
	}

	@RequestMapping(method = RequestMethod.GET, value = { "novo" })
	public String insertForm(ModelMap map) {
		Categoria categoria = new Categoria();
		map.addAttribute("categoria", categoria);
		map.addAttribute("selectDeStatus", selectDeStatus());
		return "/categoria/novo";
	}

	public List<String> selectDeStatus() {

		List<String> lista = new ArrayList<String>();
		lista.add("Selecione");
		lista.add("Ativo");
		lista.add("Não-Ativo");

		return lista;
	}

	@RequestMapping(method = RequestMethod.GET, value = { "salvar" })
	public String save(@ModelAttribute("categoria") @Valid Categoria categoria, BindingResult result, ModelMap map) {
		if (result.hasErrors()) {
			map.addAttribute("categoria", categoria);
			map.addAttribute("selectDeStatus", selectDeStatus());
			return "/categoria/novo";
		}
		try {
			if (categoria.temIdValido() && !categoria.getStatus().equals("Selecione")) {
				categoriaService.atualizar(categoria);
			} else if (!categoria.getStatus().equals("Selecione")) {
				categoriaService.inserir(categoria);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/categoria/listar";
	}

	@RequestMapping(method = RequestMethod.GET, value = { "/{id}/updateForm" })
	public String updateForm(@PathVariable("id") Long id, ModelMap map) {
		Categoria categoria = categoriaService.buscarPorId(id);
		map.addAttribute("categoria", categoria);
		map.addAttribute("selectDeStatus", selectDeStatus());
		return "/categoria/novo";
	}

	@RequestMapping(method = RequestMethod.GET, value = { "/{id}/ativar" })
	public String ativar(@PathVariable("id") Long id, ModelMap map) {
		Categoria categoria = categoriaService.buscarPorId(id);
		categoria.setStatus("Ativo");

		try {
			categoriaService.atualizar(categoria);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "redirect:/categoria/listar";

	}

	@RequestMapping(method = RequestMethod.GET, value = { "/{id}/desativar" })
	public String desativar(@PathVariable("id") Long id, ModelMap map) {
		Categoria categoria = categoriaService.buscarPorId(id);
		categoria.setStatus("Não-Ativo");

		try {
			categoriaService.atualizar(categoria);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "redirect:/categoria/listar";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}/remove")
	public String remove(@PathVariable("id") Long id) {
		Categoria categoria = categoriaService.buscarPorId(id);
		if (categoria != null) {
			try {
				categoriaService.excluir(categoria);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "redirect:/categoria/listar";
	}

}
