package br.com.cnt.model.dao.balanco;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.cnt.model.dao.BaseDAO;
import br.com.cnt.model.entity.balanco.Conta;
import br.com.cnt.model.entity.balanco.Exercicio;
import br.com.cnt.model.entity.balanco.PlanoContas;

public class PlanoContasDAO extends BaseDAO<PlanoContas> {
 	
	private static final long serialVersionUID = 1L;
 	
	/**
	 * 
 		Conta conta = null;
 		conta.getEmpresa().getId();
 		conta.getPlanoContas().getId();
 		conta.getExercicio().getId();
	 * @param empresa
	 * @param exercicio
	 * @param planoContas
	 * @return
	 */
 	@SuppressWarnings("unchecked")
	public List<Conta>retornarContas(Exercicio exercicio){
 		
 		String hql = "from Conta conta where ";
 		if(exercicio.getEmpresa()!=null)
 			hql += "or conta.empresa.id = :empresa ";
 		if(exercicio.getPlanosContas()!=null)
 			hql += "or conta.planoContas.id = :planoContas ";
 		if(exercicio.getId()!=null)
 			hql += "or conta.exercicio.id = :exercicio ";
 		
 		Session session = getSession();
 		Query query = session.createQuery(hql.replace("where or", "where "));
 		
 		if(exercicio.getEmpresa()!=null)
 			query.setLong("empresa", exercicio.getEmpresa().getId());
 		if(exercicio.getPlanosContas()!=null)
 			query.setLong("planoContas", exercicio.getPlanosContas().getId());
 		if(exercicio.getId()!=null)
 			query.setLong("exercicio", exercicio.getId());
 		
 		return query.list();
 	}

	public List<PlanoContas> buscarTodos() {
 		Session session = getSession();
 		Query query = session.createQuery("select obj from PlanoContas obj");
 		List list = query.list();
		session.close();
		return list;
	}
	
}
 