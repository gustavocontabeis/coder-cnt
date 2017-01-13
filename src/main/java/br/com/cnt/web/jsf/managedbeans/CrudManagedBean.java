package br.com.cnt.web.jsf.managedbeans;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ComponentSystemEvent;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.cnt.model.dao.BaseDAO;
import br.com.cnt.model.dao.DaoException;
import br.com.cnt.model.entity.BaseEntity;
import br.com.cnt.model.utils.Filtro;

public abstract class CrudManagedBean <T extends BaseEntity, D extends BaseDAO<T>> extends BaseManagedBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected T entity;
	protected LazyDataModel<T> model;
	protected Filtro<T> filtro;
	
	protected BaseDAO<T> dao;
	
	protected Long id;
	
	@PostConstruct
	private void init() {
		System.out.println("ContaManagedBean.init() ");
		novo(null);
		loadLazyModel();
	}
	
	public void listener(ComponentSystemEvent evt) throws AbortProcessingException{
		if(id != null){
			try {
				entity = getDao().buscar(new Long(id));
			} catch (NumberFormatException e) {
				e.printStackTrace();
				message(e);
			} catch (DaoException e) {
				e.printStackTrace();
				message(e);
			}
		}
	}
	
	private void loadLazyModel() {
		model = new LazyDataModel<T>() {
			private static final long serialVersionUID = 1L;

			@Override
			public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {
				filtro = new Filtro(getEntity().getClass(), first, pageSize, sortField, sortOrder, filters);
				dao = getDao();
				setRowCount(dao.getQuantidade2(filtro));
				return dao.buscar2(filtro);
			}

			public T getRowData(String rowKey) {
				novo(null);
				entity.setId(new Long(rowKey));
				try {
					entity = dao.buscar(new Long(rowKey));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (DaoException e) {
					e.printStackTrace();
				}
				return entity;
			}

			public Object getRowKey(T object) {
				return String.valueOf(object.getId());
			}
		};
	}
	
	public abstract void novo(ActionEvent evt);
	
	public void salvar(ActionEvent evt) throws DaoException {
		try {
			salvarAntes(entity);
			dao.salvar(entity);
			salvarApos(entity);
			message(null, "Registro salvo com sucesso.");
		} catch (Exception e) {
			message(e);
		}
	}

	protected void salvarAntes(T entity) {
		
	}
	
	protected void salvarApos(T entity) {
		
	}
	
	public void excluir(ActionEvent evt) throws DaoException {
		try {
			excluirAntes(entity);
			getDao().excluir(entity);
			excluirApos(entity);
			novo(null);
			message(null, "Registro excluído com sucesso.");
		} catch (Exception e) {
			message(e);
		}
	}

	private void excluirApos(T entity) {}

	private void excluirAntes(T entity) {}

	protected abstract BaseDAO<T> getDao();
	
	public T getEntity() {
		return entity;
	}
	public void setEntity(T entity) {
		this.entity = entity;
	}
	
	public LazyDataModel<T> getModel() {
		return model;
	}
	public void setModel(LazyDataModel<T> model) {
		this.model = model;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

}
