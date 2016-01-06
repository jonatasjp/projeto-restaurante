package proj.unipe.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import proj.unipe.entities.Mesa;
import proj.unipe.entities.Reserva;
import proj.unipe.services.MesaService;
import proj.unipe.services.ReservaService;

@Controller
@RequestMapping(value="reserva")
public class ReservaController {

	@Autowired
	private ReservaService reservaService;
	
	@Autowired
	private MesaService mesaService;
	
	
	@RequestMapping(method = RequestMethod.GET, value = {"listar"})
	public String list(ModelMap map) {
		
		List<Reserva> reservas = reservaService.listar();
		map.addAttribute("reservas", reservas);
		map.addAttribute("selectMesas", selectMesas());
		map.addAttribute("filtro", new Reserva());
		return "/reserva/listar";
		
	}
	
	
	public Map<Long, String> selectMesas(){
		List<Mesa> mesas = mesaService.listar();
		Map<Long, String> mapa = new HashMap<Long, String>();
		mapa.put(0L, "selecione");
		for(Mesa mesa: mesas){
			
			mapa.put(mesa.getId(), mesa.getNumero());
		}
		
		return mapa;
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = {"filtrar"})
	public String filter(@ModelAttribute("filtro") Reserva filtro, ModelMap map) {
		try {
			if (filtro.getNome_responsavel() == null && filtro.getNome_responsavel().equals("")) {
			} else {
				List<Reserva> reservas = reservaService.buscarPorNomeResponsavel(filtro.getNome_responsavel());

				map.addAttribute("reservas", reservas);
				map.addAttribute("selectMesas", selectMesas());
				map.addAttribute("filtro", filtro);
			}
			if (!filtro.getMesa().temIdValido()) {
			} else {

				List<Reserva> reservas = reservaService.buscarPorMesa(filtro);

				map.addAttribute("reservas", reservas);
				map.addAttribute("selectMesas", selectMesas());
				map.addAttribute("filtro", filtro);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/reserva/listar";
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = {"novo"})
	public String insertForm(ModelMap map) {
		Reserva reserva = new Reserva();
		reserva.setMesa(new Mesa());
		map.addAttribute("reserva", reserva );
		map.addAttribute("selectMesas", selectMesas());
		return "/reserva/novo";
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = {"salvar"})
	public String save(@ModelAttribute("reserva") @Valid Reserva reserva, BindingResult result, ModelMap map) {
		if(result.hasErrors()){
			map.addAttribute("reserva", reserva);
			map.addAttribute("selectMesas", selectMesas());
			return "reserva/novo";
		}
		try {
			
			if(reserva.temIdValido()){
				reservaService.atualizar(reserva);
			}else{
				reservaService.inserir(reserva);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/reserva/listar";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = {"/{id}/updateForm"})
	public String updateForm(@PathVariable("id") Long id, ModelMap map) {
		Reserva reserva = reservaService.buscarPorId(id);
		map.addAttribute("reserva", reserva );
		map.addAttribute("selectMesas", selectMesas());
		return "/reserva/novo";
	}
	
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}/remove")
	public String remove(@PathVariable("id") Long id){
		Reserva reserva = reservaService.buscarPorId(id);
		if(reserva != null){
			try {
				reservaService.excluir(reserva);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "redirect:/reserva/listar";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
