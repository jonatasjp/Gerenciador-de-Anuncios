package br.com.jonatas.aula.controllers;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.jonatas.aula.beans.Anuncio;
import br.com.jonatas.aula.daos.AnuncioDAO;

@Controller
@Transactional
@RequestMapping("/anuncios")
public class AnuncioController {

	@Autowired
	private AnuncioDAO anuncioDAO;
	
	@RequestMapping("/listar")
	public ModelAndView listagemDeAnuncios() {
		ModelAndView model = new ModelAndView("/anuncios/lista");
		List<Anuncio> anuncios = anuncioDAO.findAllByOrdemDeCadastro();
		model.addObject("anuncios", anuncios);
		model.addObject("anuncio", new Anuncio());
		return model;
	}
	
	@RequestMapping(value = "/buscar", method = RequestMethod.GET)
	public ModelAndView buscar(Anuncio anuncio) {
		ModelAndView model = new ModelAndView("/anuncios/lista");
		List<Anuncio> anuncios = this.anuncioDAO.filtroAnuncioOrderByDesc(anuncio);
		if(anuncios.size() == 0){
			model.addObject("buscaSemResultado", "Desculpe, não foram encontrados resultados para sua busca");
		}
		model.addObject("anuncios", anuncios);
		return model;
	}
}
