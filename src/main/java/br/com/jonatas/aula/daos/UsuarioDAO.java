package br.com.jonatas.aula.daos;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.jonatas.aula.beans.Usuario;

@Repository
public class UsuarioDAO {

	@PersistenceContext
	private EntityManager manager;

	public void cadastrar(Usuario usuario) {
		if (usuario.isIdValido()) {
			this.manager.merge(usuario);
		} else {
			this.manager.persist(usuario);
		}
	}

	public Usuario findUserById(Integer id) {
		return this.manager.find(Usuario.class, id);
	}

	public Usuario logar(Usuario usuario) {
		try {
			return (Usuario) this.manager
					.createQuery("select u from Usuario u where u.email = :email")
					.setParameter("email", usuario.getEmail()).getSingleResult();
		} catch (NoResultException exception) {
			return null;
		}
	}

	public boolean usuarioJaExiste(Usuario usuario) {
		try {
			this.manager.createQuery("select u from Usuario u where u.email = :email")
					.setParameter("email", usuario.getEmail())
					.getSingleResult();
			return true;
		} catch (NoResultException exception) {
			return false;
		}

	}

}
