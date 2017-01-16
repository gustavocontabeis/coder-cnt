package br.com.cnt.model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import br.com.cnt.model.entity.Configuracao;
import br.com.cnt.model.entity.balanco.Conta;

public class ConfiguracaoDAO extends BaseDAO<Conta> {
	
 	private static final long serialVersionUID = 1L;
 	
 	@Override
 	public Conta buscar(Long id) throws DaoException {
 		Session session = getSession();
 		Query query = session.getNamedQuery("Conta-buscar");
 		query.setLong("id", id);
 		Conta singleResult = (Conta) query.getSingleResult();
 		session.close();
 		return singleResult;
 	}

	public List<Conta> buscarTodos() {
		Session session = getSession();
		List list = session.createCriteria(Conta.class).list();
		session.close();
		return list;
	}
	
 	public Configuracao buscarPorChave(String chave) throws DaoException {
 		Session session = getSession();
 		Query query = session.getNamedQuery("Configuracao-buscarPorChave");
 		query.setString("chave", chave);
 		Configuracao singleResult = (Configuracao) query.getSingleResult();
 		session.close();
 		return singleResult;
 	}
	
}
 