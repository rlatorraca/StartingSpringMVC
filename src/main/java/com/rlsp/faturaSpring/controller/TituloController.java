package com.rlsp.faturaSpring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.rlsp.faturaSpring.TitulosRepository;
import com.rlsp.faturaSpring.model.Titulo;

@Controller
@RequestMapping("/titulos")
public class TituloController {
	
	@Autowired
	private TitulosRepository titulos;

	@RequestMapping("/novo") // Mapea a pagina para acesso via WEB (browser)
	public String novo() {
		return "CadastroTitulo"; // Nome da VIEW sem .html
	}
	
	/**
	 * ModelAndView ==> retorma uma VIEW e mais INFORMACOES junto 
	 * 
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView salvar(Titulo titulo) {
		titulos.save(titulo);
		
		ModelAndView mv = new ModelAndView("CadastroTitulo");
		mv.addObject("mensagem", "Titulo salvo com sucesso");
		return mv;
	}
	
}
