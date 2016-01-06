package proj.unipe.controllers;

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
import proj.unipe.entities.Mesa;
import proj.unipe.services.MesaService;

@Controller
@RequestMapping(value = "mesa")
public class MesaController {

	@Autowired
	private MesaService mesaService;

	@RequestMapping(method = RequestMethod.GET, value = { "listar" })
	public String list(ModelMap map) {

		List<Mesa> mesas = mesaService.listar();
		map.addAttribute("mesas", mesas);
		map.addAttribute("selectDeReserva", selectDeReserva());
		map.addAttribute("filtro", new Mesa());
		return "/mesa/listar";
	}

	public Map<Boolean, String> selectDeReserva() {

		Map<Boolean, String> mapa = new HashMap<Boolean, String>();
		mapa.put(false, "Não");
		mapa.put(true, "Sim");

		return mapa;
	}

	@RequestMapping(method = RequestMethod.GET, value = { "filtrar" })
	public String filter(@ModelAttribute("filtro") Mesa filtro, ModelMap map) {
		try {

			List<Mesa> mesas = mesaService.buscaPorMesaDeReserva(filtro.getDeReserva());

			map.addAttribute("mesas", mesas);
			map.addAttribute("selectDeReserva", selectDeReserva());
			map.addAttribute("filtro", filtro);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/mesa/listar";
	}

	@RequestMapping(method = RequestMethod.GET, value = { "novo" })
	public String insertForm(ModelMap map) {
		Mesa mesa = new Mesa();
		map.addAttribute("mesa", mesa);
		map.addAttribute("selectDeReserva", selectDeReserva());
		return "/mesa/novo";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = {"salvar"})
	public String save(@ModelAttribute("mesa") @Valid Mesa mesa, BindingResult result, ModelMap map) {
		if(result.hasErrors()){
			map.addAttribute("mesa", mesa);
			map.addAttribute("selectDeReserva", selectDeReserva());
			return "mesa/novo";
		}
		try {
			if(mesa.temIdValido()){
				mesaService.atualizar(mesa);
			}else{
				mesaService.inserir(mesa);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/mesa/listar";
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = {"/{id}/updateForm"})
	public String updateForm(@PathVariable("id") Long id, ModelMap map) {
		Mesa mesa = mesaService.buscarPorId(id);
		map.addAttribute("mesa", mesa );
		map.addAttribute("selectDeReserva", selectDeReserva());
		return "/mesa/novo";
	}
	
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}/remove")
	public String remove(@PathVariable("id") Long id){
		Mesa mesa = mesaService.buscarPorId(id);
		if(mesa != null){
			try {
				mesaService.excluir(mesa);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "redirect:/mesa/listar";
	}
	
	
	
	
	
	
	
	
	

}
