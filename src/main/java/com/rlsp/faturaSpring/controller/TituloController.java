package com.rlsp.faturaSpring.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.rlsp.faturaSpring.TitulosRepository;
import com.rlsp.faturaSpring.model.StatusTitulo;
import com.rlsp.faturaSpring.model.Titulo;

@Controller
@RequestMapping("/titulos")
public class TituloController {
	
	@Autowired
	private TitulosRepository titulos;

	@RequestMapping("/novo") // Mapea a pagina para acesso via WEB (browser)
	public ModelAndView novo() {
	
		ModelAndView mv = new ModelAndView("CadastroTitulo");
		mv.addObject(new Titulo());
		return mv; // Nome da VIEW sem .html
	}	
	
	/**
	 * ModelAndView ==> retorma uma VIEW e mais INFORMACOES junto 
	 * 
	 */
	@RequestMapping(method = RequestMethod.POST)	
	public ModelAndView salvar(@Validated Titulo titulo, Errors errors) {
		ModelAndView mv = new ModelAndView("CadastroTitulo");
		if (errors.hasFieldErrors()) {
			return mv;
		}
		
		titulos.save(titulo);
		
		mv.addObject("mensagem", "Título salvo com sucesso!");
		return mv;
	}
	
	/** 
	 * Atributo abaixo ficara DISPONIVEL para todas as paginas
	 */
	@ModelAttribute("todosStatusTitulo")
	public List<StatusTitulo> todosStatusTitulo(){
		return Arrays.asList(StatusTitulo.values());
	}
	
	@RequestMapping
	public ModelAndView pesquisar() {
		List<Titulo> todosTitulos = titulos.findAll();
		ModelAndView mv = new ModelAndView("PesquisaTitulo");
		mv.addObject("titulos", todosTitulos);
		return mv;
	}
}
