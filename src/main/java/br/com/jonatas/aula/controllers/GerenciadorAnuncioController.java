package br.com.jonatas.aula.controllers;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.jonatas.aula.beans.Anuncio;
import br.com.jonatas.aula.beans.Usuario;
import br.com.jonatas.aula.daos.AnuncioDAO;
import br.com.jonatas.aula.security.UserSession;

@Controller
@Transactional
@RequestMapping("/gerenciador")
public class GerenciadorAnuncioController {
	
	@Autowired
	private AnuncioDAO anuncioDAO;
	
	@Autowired
	private UserSession userSession;
	
	@RequestMapping("/meusAnuncios")
	public ModelAndView meusAnuncios() {
		ModelAndView model = new ModelAndView("/gerenciador/listar");
		List<Anuncio> anuncios = null;
		if(userSession.getUsuario() != null){
			anuncios = anuncioDAO.findAnuncioByIdUser(userSession.getUsuario().getId());			
		}
		model.addObject("anuncios", anuncios);
		return model;
	}
	
	@RequestMapping("/todosAnuncios")
	public ModelAndView todosAnuncios() {
		ModelAndView model = new ModelAndView("/gerenciador/todosAnuncios");
		List<Anuncio> anuncios = anuncioDAO.findAllByOrdemDeCadastro();
		model.addObject("anuncios", anuncios);
		return model;
	}
	
	@RequestMapping("/formAnuncio")
	public ModelAndView formAnuncio(Anuncio anuncio){
		ModelAndView model = new ModelAndView("/gerenciador/formAnuncio");
		model.addObject("anuncio", anuncio);
		return model;
	}
	
	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public ModelAndView inserirAnuncio(Anuncio anuncio, RedirectAttributes redirectAttributes){
		ModelAndView model = new ModelAndView("redirect:/gerenciador/meusAnuncios");
		if(anuncio.isIdValido()){
			Usuario usuario = userSession.getUsuario();
			anuncio.setUsuario(usuario);
			anuncioDAO.salvar(anuncio);
			redirectAttributes.addFlashAttribute("anuncioAtualizadoOuCadastrado", "Anúncio atualizado com sucesso!");
		}else{
			Usuario usuario = userSession.getUsuario();
			anuncio.setUsuario(usuario);
			anuncioDAO.salvar(anuncio);
			redirectAttributes.addFlashAttribute("anuncioAtualizadoOuCadastrado", "Anúncio cadastrado com sucesso!");	
		}
		
		return model;
	}
	
	@RequestMapping("/editar/{id}")
	public ModelAndView editarAnuncio(@PathVariable("id") Integer id) {
		Anuncio anuncio = anuncioDAO.findById(id);
		return formAnuncio(anuncio);
	}
	
	@RequestMapping("/excluir/{id}")
	public ModelAndView excluirAnuncio(@PathVariable("id") Integer id) {
		ModelAndView model = new ModelAndView("redirect:/gerenciador/meusAnuncios");
		anuncioDAO.excluir(id);
		return model;
	}

	@RequestMapping(value = "/meusAnuncios/buscar", method = RequestMethod.GET)
	public ModelAndView buscar(Anuncio anuncio) {
		ModelAndView model = new ModelAndView("/gerenciador/listar");
		List<Anuncio> anuncios = this.anuncioDAO.filtroAnunciosDoUsuarioLogado(anuncio, userSession.getUsuario());
		if(anuncios.size() == 0){
			model.addObject("buscaSemResultado", "Desculpe, não foram encontrados resultados para sua busca");
		}
		model.addObject("anuncios", anuncios);
		return model;
	}
	
	@RequestMapping(value = "/todosAnuncios/buscar", method = RequestMethod.GET)
	public ModelAndView busca(Anuncio anuncio) {
		ModelAndView model = new ModelAndView("/gerenciador/listarBuscaTodos");
		List<Anuncio> anuncios = this.anuncioDAO.filtroAnuncioOrderByDesc(anuncio);
		if(anuncios.size() == 0){
			model.addObject("buscaSemResultado", "Desculpe, não foram encontrados resultados para sua busca");
		}
		model.addObject("anuncios", anuncios);
		return model;
	}
	
	@RequestMapping("/sair")
	public ModelAndView sair(){
		ModelAndView model = new ModelAndView("redirect:/anuncios/listar");
		userSession.invalidarSessão();
		return model;
	}
}
