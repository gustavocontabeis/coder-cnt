package br.com.cnt.web.jsf.managedbeans;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.cnt.model.dao.DaoException;
import br.com.cnt.model.dao.balanco.EmpresaDAO;
import br.com.cnt.model.dao.balanco.ExercicioDAO;
import br.com.cnt.model.dao.usuarios.UsuarioDAO;
import br.com.cnt.model.entity.balanco.Empresa;
import br.com.cnt.model.entity.balanco.Exercicio;
import br.com.cnt.model.entity.usuarios.Usuario;
import br.com.cnt.model.utils.Filtro;

import javax.faces.application.FacesMessage;

@javax.inject.Named @javax.faces.view.ViewScoped
//@ManagedBean @ViewScoped
public class EmpresaManagedBean extends BaseManagedBean<Empresa, EmpresaDAO> {

	/**
	 */
	private static final long serialVersionUID = 1L;

	private Empresa empresa;
	private List<Empresa> empresas;
	
	protected LazyDataModel<Empresa> model;
	protected Filtro filtro;
	
	@Inject
	private EmpresaDAO empresaDAO;
	@Inject
	private UsuarioDAO usuarioDAO;
	@Inject
	private ExercicioDAO exercicioDAO;

	@PostConstruct
	private void init() {
//		dao = (EmpresaDAO) SpringUtil.getBean("empresaDAO");
//		empresaDAO = (EmpresaDAO) SpringUtil.getBean("empresaDAO");
//		usuarioDAO = (UsuarioDAO) SpringUtil.getBean("usuarioDAO");
//		usuarioDAO = (UsuarioDAO) SpringUtil.getBean("usuarioDAO");
//		exercicioDAO = (ExercicioDAO) SpringUtil.getBean("exercicioDAO");
		novo(null);
		loadLazyModel();
		empresas = getPopularComboEmpresa();
	}

	private void loadLazyModel() {
		model = new LazyDataModel<Empresa>() {
			private static final long serialVersionUID = 1L;
			@Override
			public List<Empresa> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
				filtro = new Filtro(Empresa.class, first, pageSize, sortField, sortOrder, filters);
				setRowCount(empresaDAO.getQuantidade2(filtro));
				return empresaDAO.buscar2(filtro);
			}
		    public Empresa getRowData(String rowKey) {
		    	Empresa obj = new Empresa();
		    	obj.setId(new Long(rowKey));
		    	 try {
					obj = empresaDAO.buscar(obj, new Long(rowKey));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (DaoException e) {
					e.printStackTrace();
				}
		    	return obj;
		    }
		    public Object getRowKey(Empresa object) {
		    	return String.valueOf(object.getId());
		    }
		};
	}
	
	public void novo(ActionEvent evt) {
		empresa = new Empresa();
		empresa.setExercicios(new ArrayList<Exercicio>());
		empresa.setMatriz(new Empresa());
	}

	public void salvar(ActionEvent evt) throws DaoException {
		try {
			if(empresa.getMatriz()!=null && empresa.getMatriz().getId()==null){
				empresa.setMatriz(null);
			}
			prepararEntidade(empresa);
			prepararEntidade(empresa.getMatriz());
			empresaDAO.salvar(empresa);
			message(null, "Registro salvo com sucesso.");
		} catch (Exception e) {
			message(e);
		}
	}


	public void excluir(ActionEvent evt) throws DaoException {
		try {
			empresaDAO.excluir(empresa);
			novo(null);
			message(null, "Registro excluído com sucesso.");
		} catch (Exception e) {
			message(e);
		}
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public LazyDataModel<Empresa> getModel() {
		return model;
	}

	public void setModel(LazyDataModel<Empresa> model) {
		this.model = model;
	}
	
	public List<Empresa> getPopularComboEmpresa() {
		return empresaDAO.buscarTodos();
	}

	public List<Usuario> getPopularComboUsuario() {
		return usuarioDAO.buscarTodos();
	}

	public List<Empresa> getEmpresas() {
		return empresas;
	}

}

