package proj.unipe.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import proj.unipe.entities.Cliente;
import proj.unipe.entities.Endereco;
import proj.unipe.services.ClienteService;

/**
 * Servlet implementation class LoginServlet
 */

@Controller
@RequestMapping(value = "cliente")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@RequestMapping(method = RequestMethod.GET, value = { "listar" })
	public String list(ModelMap map) {

		List<Cliente> clientes = clienteService.listar();
		map.addAttribute("clientes", clientes);
		map.addAttribute("filtro", new Cliente());
		return "/cliente/listar";
	}

	@RequestMapping(method = RequestMethod.GET, value = { "novo" })
	public String insertForm(ModelMap map) {
		Cliente cliente = new Cliente();
		cliente.setEndereco(new Endereco());
		map.addAttribute("cliente", cliente);
		return "/cliente/novo";
	}

	@RequestMapping(method = RequestMethod.GET, value = { "/{id}/updateForm" })
	public String updateForm(@PathVariable("id") Long id, ModelMap map) {
		Cliente cliente = clienteService.buscarPorId(id);
		map.addAttribute("cliente", cliente);
		return "/cliente/novo";
	}

	@RequestMapping(method = RequestMethod.POST, value = { "salvar" })
	public String save(@ModelAttribute("cliente") Cliente cliente) {
		try {
			if (cliente.temIdValido()) {
				clienteService.atualizar(cliente);
			} else {
				clienteService.inserir(cliente);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/cliente/listar";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}/remove")
	public String remove(@PathVariable("id") Long id) {
		Cliente cliente = clienteService.buscarPorId(id);
		if (cliente != null) {
			try {
				clienteService.excluir(cliente);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "redirect:/cliente/listar";
	}

	@RequestMapping(method = RequestMethod.GET, value = { "filtrar" })
	public String filter(@ModelAttribute("filtro") Cliente filtro, ModelMap map) {
		try {
			if (filtro.getNome() == null || filtro.getNome().equals("")) {
				return "redirect:/cliente/listar";
			}
			List<Cliente> clientes = clienteService.buscarPorNome(filtro.getNome());
			map.addAttribute("clientes", clientes);
			map.addAttribute("filtro", filtro);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/cliente/listar";
	}

	@RequestMapping(method = RequestMethod.GET, value = { "cadastro" })
	public String cadastro(ModelMap map) {
		Cliente cliente = new Cliente();
		cliente.setEndereco(new Endereco());
		map.addAttribute("cliente", cliente);
		return "/cliente/cadastrar";
	}

	@RequestMapping(method = RequestMethod.POST, value = { "confirmarcadastro" })
	public String confirmarCadastro(@ModelAttribute("cliente") Cliente cliente) {

		try {
			clienteService.inserir(cliente);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "redirect:/telaLogin";
	}

	

}
