package br.com.cnt.model.dao.usuarios;

import java.util.List;

import org.hibernate.Session;

import br.com.cnt.model.dao.BaseDAOSerializable;
import br.com.cnt.model.entity.usuarios.PerfilAcesso;


public class PerfilAcessoDAO extends BaseDAOSerializable<PerfilAcesso> {

	/**
	 */
	private static final long serialVersionUID = 1L;

	public List<PerfilAcesso> buscarTodos() {
		Session session = getSession();
		List list = session.getNamedQuery("todosPerfilAcesso").list();
		session.close();
		return list;
	}

}

