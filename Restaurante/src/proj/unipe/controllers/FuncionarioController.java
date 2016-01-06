package proj.unipe.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import proj.unipe.entities.Funcionario;
import proj.unipe.services.FuncionarioService;

@Controller
@RequestMapping(value = "funcionario")
public class FuncionarioController {

	@Autowired
	FuncionarioService funcionarioService;

	@RequestMapping(method = RequestMethod.GET, value = {"listar"})
	public String list(ModelMap map) {

		List<Funcionario> funcionarios = funcionarioService.listar();
		map.addAttribute("funcionarios", funcionarios);
		map.addAttribute("filtro", new Funcionario());
		return "/funcionario/listar";
	}

	@RequestMapping(method = RequestMethod.GET, value = {"novo"})
	public String novo(ModelMap map) {

		Funcionario funcionario = new Funcionario();
		map.addAttribute("funcionario", funcionario);
		return "/funcionario/novo";

	}

	@RequestMapping(method = RequestMethod.POST, value = {"salvar"})
	public String salvar(@ModelAttribute("funcionario") Funcionario funcionario) {

		try {
			if (funcionario.temIdValido()) {
				funcionarioService.atualizar(funcionario);
			} else {
				funcionarioService.inserir(funcionario);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/funcionario/listar";

	}

	@RequestMapping(method = RequestMethod.GET, value = {"/{id}/editar"})
	public String editar(@PathVariable("id") Long id, ModelMap map) {
		Funcionario funcionario = funcionarioService.buscarPorId(id);
		map.addAttribute("funcionario", funcionario);
		return "/funcionario/novo";
	}

	@RequestMapping(method = RequestMethod.GET, value = {"/{id}/remover"})
	public String remover(@PathVariable("id") Long id) {
		Funcionario funcionario = funcionarioService.buscarPorId(id);
		if (funcionario != null) {
			try {
				funcionarioService.excluir(funcionario);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "redirect:/funcionario/listar";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = {"/filtrar"})
	public String filtrar(@ModelAttribute("filtro") Funcionario filtro, ModelMap map){
		
		try {
			if(filtro.getNome() == null || filtro.getNome().equals("")){
				return "redirect:/funcionario/listar";
			}
			List<Funcionario> funcionarios = funcionarioService.buscaPorNome(filtro.getNome());
			map.addAttribute("funcionarios", funcionarios);
			map.addAttribute("filtro", filtro);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "/funcionario/listar";
		
	}
	
	

}
