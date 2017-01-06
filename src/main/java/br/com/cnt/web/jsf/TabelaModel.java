package br.com.cnt.web.jsf;

import org.primefaces.model.LazyDataModel;

import br.com.cnt.model.dao.BaseDAO;
import br.com.cnt.model.entity.BaseEntity;

public class TabelaModel<T> extends LazyDataModel<T>{
	
	private BaseDAO<BaseEntity>dao; 

}
