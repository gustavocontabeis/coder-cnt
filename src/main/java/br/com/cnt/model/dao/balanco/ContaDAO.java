package br.com.cnt.model.dao.balanco;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.ResultTransformer;

import br.com.cnt.model.dao.BaseDAO;
import br.com.cnt.model.entity.balanco.Conta;
import br.com.cnt.model.entity.balanco.ContaOrigem;
import br.com.cnt.model.entity.balanco.ContaTipo;
import br.com.cnt.model.entity.balanco.Empresa;
import br.com.cnt.model.entity.balanco.Exercicio;
import br.com.cnt.model.entity.balanco.PlanoContas;

public class ContaDAO extends BaseDAO<Conta> {
	
 	private static final long serialVersionUID = 1L;
 	
 	@SuppressWarnings("unchecked")
	public List<Conta>buscarTodasContas(Conta conta){
 		
 		Map<String, Object> param = new HashMap<String, Object>();
 		
 		String hql = "" +
 				"select " +
 					"conta.id, " +
 					"conta.estrutura, " +
 					"conta.nivel, " +
 					"conta.contaTipo, " +
 					"conta.nome, " +
 					"conta.contaOrigem, " +
 					"conta.descricao, " +
 					"emp.id, " +
 					"emp.razaoSocial, " +
 					"pc.id, " +
 					"ex.id " +
 				"from " +
 					"Conta conta " +
 					"left join conta.empresa emp " +
 					"left join conta.planoContas pc " +
 					"left join conta.exercicio ex " +
 				"where ";
 				
 				if(conta.getPlanoContas() != null){
					hql += " or pc.id = :planoContasId";
			 		param.put("planoContasId", conta.getPlanoContas().getId());
				}
 				
				if(conta.getExercicio() != null){
 					hql += " or ex.id = :exercicioId";
 			 		param.put("exercicioId", conta.getExercicio().getId());
 				}
				
				if(conta.getEmpresa() != null){
 					hql += " or emp.id = :empresaId";
 			 		param.put("empresaId", conta.getEmpresa().getId());
 				}
				
				hql = hql.replace("where  and ", "where ").replace("where  or", "where ");
				if(hql.endsWith("where ")){
					hql = hql.replace("where ", "");
				}
 					
 		
 		Session session = getSession();
 		Query query = session.createQuery(hql + " order by conta.estrutura asc ");
 		query.setProperties(param);
 		
 		query.setResultTransformer(new ResultTransformer() {
			private static final long serialVersionUID = 1L;
			@Override
			public Object transformTuple(Object[] tuple, String[] aliases) {
				Conta conta = new Conta();
				conta.setId((Long)tuple[0]);
				conta.setEstrutura((String)tuple[1]);
				conta.setNivel((Integer)tuple[2]);
				conta.setContaTipo((ContaTipo)tuple[3]);
				conta.setNome((String)tuple[4]);
				conta.setContaOrigem((ContaOrigem)tuple[5]);
				conta.setDescricao(tuple[6]!=null?(String)tuple[6]:null);
				if(tuple[7]!=null)
				conta.setEmpresa(new Empresa((Long)tuple[7], (String)tuple[8]));
				if(tuple[9]!=null)
					conta.setPlanoContas(new PlanoContas((Long)tuple[9]) );
				if(tuple[10]!=null)
					conta.setExercicio(new Exercicio((Long)tuple[10]));
				return conta;
			}
			@SuppressWarnings("rawtypes")
			@Override
			public List<?> transformList(List collection) {
				return collection;
			}
		});
		
 		return query.list();
 	}
}
 