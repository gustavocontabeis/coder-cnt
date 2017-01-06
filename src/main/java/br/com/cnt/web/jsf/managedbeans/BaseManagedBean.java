package br.com.cnt.web.jsf.managedbeans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.cnt.model.dao.BaseDAO;
import br.com.cnt.model.entity.BaseEntity;
import br.com.cnt.model.utils.Filtro;
import br.com.cnt.model.utils.I18nUtils;

public class BaseManagedBean<T extends BaseEntity, D extends BaseDAO<T>> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	protected T entity;
	
	//@Inject
	protected BaseDAO<T> dao;
	
	@Inject
	@ManagedProperty(value="#{loginManagedBean}")
	protected LoginManagedBean loginBean;

	public LoginManagedBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginManagedBean loginBean) {
		this.loginBean = loginBean;
	}
	
	protected void message(Exception e) {
		message(null, e);
	}

	protected void message(String component, Exception exception) {
		Throwable error = exception;
		while(error.getCause()!=null && error.getLocalizedMessage() != null){
			error = error.getCause();
		}
		messageError(component, error.getLocalizedMessage());
	}

	protected void message(String component, String msg) {
		msg = I18nUtils.getMessage(msg); 
		FacesContext.getCurrentInstance().addMessage(component, new FacesMessage(msg));
	}

	protected void messageError(String component, String msg) {
		msg = I18nUtils.getMessage(msg);
		FacesContext.getCurrentInstance().addMessage(component, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
	}
	
	public List<T> autocomplete(String search){
		Filtro<T> filtro = new Filtro<T>(getClasse());
		filtro.addFilter(filtrarAutocompletePor(), search);
		List<T> buscar = getDAO().buscar(filtro);
		return buscar;
	}
	
	protected BaseDAO<T> getDAO() {
		return null;
	}

	protected Class<? extends Serializable> getClasse() {
		return null;
	}

	protected String filtrarAutocompletePor() {
		return "nome";
	}
	
	protected void prepararEntidade(BaseEntity entity) {
		if(entity!=null){
			if(entity.getId()==null){
				entity.setUsuarioInclusao(loginBean.getUsuario());
				entity.setDtIclusao(new Date());
			}else{
				entity.setUsuarioAlteracao(loginBean.getUsuario());
				entity.setDtAlteracao(new Date());
			}
		}
	}

}
