package com.rlsp.faturaSpring.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rlsp.faturaSpring.filter.TituloFilter;
import com.rlsp.faturaSpring.model.StatusTitulo;
import com.rlsp.faturaSpring.model.Titulo;
import com.rlsp.faturaSpring.repository.TitulosRepository;
import com.rlsp.faturaSpring.service.CadastroTituloService;

@Controller
@RequestMapping("/titulos")
public class TituloController {
	
	private static final String CADASTRO_VIEW = "CadastroTitulo";
	
	@Autowired
	private TitulosRepository titulos;
	
	@Autowired
	private CadastroTituloService cadastroTituloService;

	@RequestMapping("/novo") // Mapea a pagina para acesso via WEB (browser)
	public ModelAndView novo() {
	
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Titulo());
		return mv; // Nome da VIEW sem .html
	}	
	
	/**
	 * ModelAndView ==> retorma uma VIEW e mais INFORMACOES junto 
	 * 
	 */
	@RequestMapping(method = RequestMethod.POST)	
	public String salvar(@Validated Titulo titulo, Errors errors, RedirectAttributes attributes) {
		//ModelAndView mv = new ModelAndView("CadastroTitulo");
		if (errors.hasFieldErrors()) {
			return CADASTRO_VIEW;
		}
		
		try {
			
			cadastroTituloService.salvar(titulo);
			//mv.addObject("mensagem", "Título salvo com sucesso!");
			attributes.addFlashAttribute("mensagem", "Título salvo com sucesso!"); //deixa no BUFFER para fazer o reload na pagina e manter a mensagem de "Titulo Salvo"
			return "redirect:/titulos/novo";// faz um nova requisacao par NOVO TITULO (para inclusao de novo titulo)
		} catch (IllegalArgumentException e) {
			errors.rejectValue("dataVencimento", null, e.getMessage()); // Trata o ERRO para ser jogada na tela (VIEW)		
			return CADASTRO_VIEW;
		}
		
		
	}
	
	/** 
	 * Atributo abaixo ficara DISPONIVEL para todas as paginas
	 */
	@ModelAttribute("todosStatusTitulo")
	public List<StatusTitulo> todosStatusTitulo(){
		return Arrays.asList(StatusTitulo.values());
	}
	
	/**
	 * Faz a busca para edicao
	 * @PathVariable("codigo") Titulo titulo ==> busca o OBJETO de titutlo e joga na VIEW para que seja editado
	 * @return
	 */
	@RequestMapping("{codigo}")
	public ModelAndView edicao(@PathVariable("codigo") Titulo titulo) {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW); 
		mv.addObject(titulo);
		System.out.println(">>>> Codigo (EDICAO) : " + titulo.getCodigo());
		return mv;
	}
	
	/**
	 * Faz a EXCLUSAO de titutlos
	 * 
	 */
	
	//@DeleteMapping(value="{codigo}")
	@RequestMapping(value="{codigo}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long codigo, RedirectAttributes attributes) {
		cadastroTituloService.excluir(codigo);
		
		attributes.addFlashAttribute("mensagem", "Título excluído com sucesso!");
		return "redirect:/titulos";
	}
	
	/**
	 * @ResponseBody = retorna um CORPO de resposta e nao uma VIEWW
	 * @param codigo
	 * @return
	 */
	@RequestMapping(value = "/{codigo}/receber", method = RequestMethod.PUT)
	public @ResponseBody String receber(@PathVariable Long codigo) {
		return cadastroTituloService.receber(codigo);
	}
	
	@RequestMapping
	public ModelAndView pesquisar(@ModelAttribute("filtro") TituloFilter filtro) {
		List<Titulo> todosTitulos = cadastroTituloService.filtrar(filtro);
		
		ModelAndView mv = new ModelAndView("PesquisaTitulo");
		mv.addObject("titulos", todosTitulos);
		return mv;
	}
	
//	@RequestMapping
//	public ModelAndView pesquisar() {
//		List<Titulo> todosTitulos = titulos.findAll();
//		ModelAndView mv = new ModelAndView("PesquisaTitulo");
//		mv.addObject("titulos", todosTitulos);
//		return mv;
//	}
}
