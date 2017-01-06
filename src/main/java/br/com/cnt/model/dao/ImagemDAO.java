//ADICIONAR O PACOTE!<bean id="imagemDAO" class="ImagemDAO"></bean>
package br.com.cnt.model.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;

import br.com.cnt.model.entity.Imagem;

public class ImagemDAO extends BaseDAO<Imagem> {

	/**
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Imagem> buscarTodos() {
		Session session = getSession();
		List list = session.getNamedQuery("todosImagem").list();
		session.close();
		return list;
	}

}

