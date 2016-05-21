package br.com.jonatas.aula.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.jonatas.aula.beans.Anuncio;
import br.com.jonatas.aula.beans.Usuario;

@Repository
public class AnuncioDAO {

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private UsuarioDAO usuarioDAO;

	public void salvar(Anuncio anuncio) {
		if (anuncio.isIdValido()) {
			this.manager.merge(anuncio);
		} else {
			this.manager.persist(anuncio);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Anuncio> findAll() {
		return this.manager.createQuery("select a from Anuncio a").getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Anuncio> findAllByOrdemDeCadastro() {
		return this.manager.createQuery("select a from Anuncio a order by a.id desc").getResultList();
	}

	public Anuncio findById(Integer id) {
		return this.manager.find(Anuncio.class, id);
	}

	public void excluir(Integer id) {
		Anuncio anuncio = findById(id);
		if (anuncio != null) {
			this.manager.remove(anuncio);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Anuncio> findAnuncioByIdUser(Integer id) {
		Usuario usuario = usuarioDAO.findUserById(id);
		List<Anuncio> anuncios = this.manager.createQuery("select a from Anuncio a where a.usuario = :usuario")
				.setParameter("usuario", usuario).getResultList();
		return anuncios;
	}

	@SuppressWarnings("unchecked")
	public List<Anuncio> filtroAnuncio(Anuncio anuncio) {

		boolean temModelo = (anuncio.getModelo() != null && !anuncio.getModelo().isEmpty()) ? true : false;
		boolean temValor = (anuncio.getValor() != null && !anuncio.getValor().isEmpty()) ? true : false;

		StringBuffer sql = new StringBuffer("select a from Anuncio a where 1=1 ");

		if (temModelo) {
			sql.append("and a.modelo like :modelo");
		}
		if (temValor) {
			sql.append("and a.valor = :valor");
		}

		Query q = this.manager.createQuery(sql.toString());

		if (temModelo) {
			q.setParameter("modelo", "%" + anuncio.getModelo() + "%");
		}
		if (temValor) {
			q.setParameter("valor", anuncio.getValor());
		}

		return q.getResultList();

	}

	@SuppressWarnings("unchecked")
	public List<Anuncio> filtroAnuncioOrderByDesc(Anuncio anuncio) {

		boolean temModelo = (anuncio.getModelo() != null && !anuncio.getModelo().isEmpty()) ? true : false;
		boolean temValor = (anuncio.getValor() != null && !anuncio.getValor().isEmpty()) ? true : false;

		StringBuffer sql = new StringBuffer("select a from Anuncio a where 1=1 ");

		if (temModelo) {
			sql.append("and a.modelo like :modelo ");
		}
		if (temValor) {
			sql.append("and a.valor = :valor ");
		}
		sql.append("order by a.id desc");

		Query q = this.manager.createQuery(sql.toString());

		if (temModelo) {
			q.setParameter("modelo", "%" + anuncio.getModelo() + "%");
		}
		if (temValor) {
			q.setParameter("valor", anuncio.getValor());
		}

		return q.getResultList();

	}
	
	@SuppressWarnings("unchecked")
	public List<Anuncio> filtroAnunciosDoUsuarioLogado(Anuncio anuncio, Usuario usuario) {

		boolean temModelo = (anuncio.getModelo() != null && !anuncio.getModelo().isEmpty()) ? true : false;
		boolean temValor = (anuncio.getValor() != null && !anuncio.getValor().isEmpty()) ? true : false;

		StringBuffer sql = new StringBuffer("select a from Anuncio a where 1=1 ");

		if (temModelo) {
			sql.append("and a.modelo like :modelo ");
		}
		if (temValor) {
			sql.append("and a.valor = :valor ");
		}
		
		sql.append("and a.usuario = :usuario");

		Query q = this.manager.createQuery(sql.toString());

		if (temModelo) {
			q.setParameter("modelo", "%" + anuncio.getModelo() + "%");
		}
		if (temValor) {
			q.setParameter("valor", anuncio.getValor());
		}
		q.setParameter("usuario", usuario);

		return q.getResultList();

	}

}
