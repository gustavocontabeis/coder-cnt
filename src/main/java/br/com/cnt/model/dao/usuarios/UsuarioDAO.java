package br.com.cnt.model.dao.usuarios;

import java.util.List;

import javax.inject.Named;
import javax.persistence.NoResultException;

import org.hibernate.NonUniqueResultException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import br.com.cnt.model.dao.BaseDAOSerializable;
import br.com.cnt.model.entity.usuarios.Usuario;

@Named
public class UsuarioDAO extends BaseDAOSerializable<Usuario> {
	/**
	 */
	private static final long serialVersionUID = 1L;

	public List<Usuario> buscarTodos() {
		Session session = getSession();
		List list = session.getNamedQuery("todosUsuario").list();
		session.close();
		return list;
	}

	public Usuario buscarComPerfis(Long id) {
		Session session = getSession();
		Query query = session.getNamedQuery("Usuario.getUsuarioComPerfis");
		query.setParameter("id", id);
		Usuario singleResult = null;
		try {
			singleResult = (Usuario) query.getSingleResult();
		} catch (org.hibernate.NonUniqueResultException e) {
			e.printStackTrace();
			return null;
		}finally {
			session.close();
		}
		return singleResult;
	}

	public Usuario buscarComPerfis(String login) {
		//new Usuario().getUsuarioPerfis().get(0).getPerfil()
		Usuario singleResult;
		Session session = null;
		try {
			session = getSession();
			Query query = session.getNamedQuery("Usuario.getUsuarioComPerfisPorLogin");
			query.setParameter("login", login);
			singleResult = (Usuario) query.getSingleResult();
		} catch (NonUniqueResultException | NoResultException e) {
			return null;
		}finally {
			session.close();
		}
		return singleResult;
	}

}

