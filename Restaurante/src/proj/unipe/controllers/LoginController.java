package proj.unipe.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import proj.unipe.entities.Usuario;
import proj.unipe.services.UsuarioService;

/**
 * Servlet implementation class LoginServlet
 */

@Controller
public class LoginController {
	
	@Autowired
	private UsuarioService usuarioService;

	@RequestMapping(method = RequestMethod.GET, value = {"/","/telaLogin"})
	public String loginPage(ModelMap map) {
		Usuario usuario = new Usuario();
		map.addAttribute("usuario", usuario);
		return "login";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/login")
	public String logar(@ModelAttribute(value = "usuario") Usuario usuario, HttpSession sessao) {
		
		Usuario usuarioBD = usuarioService.validaLogin(usuario);

		if (usuarioBD == null) {
			
			return "redirect:/telaLogin";
		} else{
			
			sessao.setAttribute("usuario", usuarioBD);
			sessao.setMaxInactiveInterval(1000);
			
			return "redirect:/cliente/listar";
		}

	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/logout")
	public String logout(HttpSession session) {
	  session.invalidate();
	  return "redirect:/telaLogin";
	}
	
	

}
