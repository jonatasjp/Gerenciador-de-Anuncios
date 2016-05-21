package br.com.jonatas.aula.controllers;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.jonatas.aula.beans.Usuario;
import br.com.jonatas.aula.criptografia.Criptografia;
import br.com.jonatas.aula.daos.UsuarioDAO;
import br.com.jonatas.aula.security.UserSession;

@Controller
@Transactional
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Autowired
	private UserSession userSession;

	@Autowired
	private Criptografia criptografia;

	@RequestMapping(value = "/cadastro", method = RequestMethod.POST)
	public ModelAndView cadastro(Usuario usuario, RedirectAttributes redirectAttributes) {
		ModelAndView model;
		if(usuario.isIdValido()){//se tem id válido então vai ser atualizado e redirecionar para meusAnuncios
			model = new ModelAndView("redirect:/gerenciador/meusAnuncios");
			//se ele não alterou salvar com a senha criptografada normalmente
			if(userSession.getUsuario().getSenha().equals(usuario.getSenha())){
				this.usuarioDAO.cadastrar(usuario);	
			}else{//porém se ele alterou a senha criptografa a nova senha para salvar e jogar os novos dados na sessão
				usuario.setSenha(criptografia.encrypt(usuario.getSenha()));
				this.usuarioDAO.cadastrar(usuario);
				userSession.setUsuario(usuario);
			}
			redirectAttributes.addFlashAttribute("dadosAtualizados", "Dados atualizados com sucesso!");
		}else if(usuarioDAO.usuarioJaExiste(usuario)){//tentou se cadastrar porém já existe um cadastro com o email fornecido:
			model = new ModelAndView("/usuario/formCadastro");
			model.addObject("usuarioExistente", "Já existe um cadastrado ativo para este email, por favor informe um email diferente!");
		}else{//se não for nenhum dos casos acima, significa que é um cadastro com um email ainda não existente, então pode se cadastrar
			model = new ModelAndView("redirect:/anuncios/listar");
			//criptografar senha antes de salvar
			usuario.setSenha(criptografia.encrypt(usuario.getSenha()));
			this.usuarioDAO.cadastrar(usuario);			
			redirectAttributes.addFlashAttribute("cadastroRealizado", "Cadastro realizado com sucesso, faça seu login para anunciar seu veículo");
		}
		return model;
	}

	@RequestMapping("/formCadastro")
	public ModelAndView formCadastro(Usuario usuario){
		ModelAndView model = new ModelAndView("/usuario/formCadastro");
		model.addObject("usuario", usuario);
		
		return model;
	}

	@RequestMapping("/login")
	public ModelAndView login(Usuario usuario){
		ModelAndView model = new ModelAndView("/usuario/formLogin");
		model.addObject("usuario", usuario);
		
		return model;
	}
	
	@RequestMapping(value = "/logar", method = RequestMethod.POST)
	public ModelAndView logar(Usuario usuario, RedirectAttributes redirectAttributes){
		ModelAndView model;
		Usuario user = usuarioDAO.logar(usuario);
		if(user != null){
			if(criptografia.decrypt(user.getSenha()).equals(usuario.getSenha())){
				userSession.setUsuario(user);
				model = new ModelAndView("redirect:/gerenciador/meusAnuncios");
			}else{
				model = new ModelAndView("redirect:/usuario/login");
				redirectAttributes.addFlashAttribute("loginInvalido", "Senha inválida, por favor digite sua senha novamente");
			}
		}else{
			model = new ModelAndView("redirect:/usuario/login");
			redirectAttributes.addFlashAttribute("loginInvalido", "Email ou Senha inválidos, por favor tente novamente");
		}
		
		return model;
	}
	
	@RequestMapping(value = "/meusDados")
	public ModelAndView meusDados(){
		ModelAndView model = new ModelAndView("/usuario/meusDados");
		Usuario usuario = userSession.getUsuario();
		model.addObject("usuario", usuario);
		return model;
	}
	
	@RequestMapping("/facaLogin")
	public ModelAndView facaLogin(RedirectAttributes redirectAttributes){
		redirectAttributes.addFlashAttribute("facaLogin", "Por favor faça seu Login para ter acesso a área administrativa");
		return new ModelAndView("redirect:/usuario/login");
	}

}
