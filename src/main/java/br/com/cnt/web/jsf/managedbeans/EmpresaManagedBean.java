package br.com.cnt.web.jsf.managedbeans;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.cnt.model.dao.BaseDAO;
import br.com.cnt.model.dao.balanco.EmpresaDAO;
import br.com.cnt.model.entity.balanco.Empresa;
import br.com.cnt.model.entity.balanco.Exercicio;

@Named @ViewScoped
public class EmpresaManagedBean extends CrudManagedBean<Empresa, EmpresaDAO> {

	/**
	 */
	private static final long serialVersionUID = 1L;

	private List<Empresa> empresas;
	
	@Inject private EmpresaDAO empresaDAO;

	@PostConstruct
	private void init() {
		empresas = getPopularComboEmpresa();
	}

	@Override
	protected BaseDAO<Empresa> getDao() {
		return empresaDAO;
	}
	
	@Override
	protected Empresa novo() {
		entity = new Empresa();
		entity.setExercicios(new ArrayList<Exercicio>());
		entity.setMatriz(new Empresa());
		return entity;
	}


	public List<Empresa> getPopularComboEmpresa() {
		return empresaDAO.buscarTodos();
	}

	public List<Empresa> getEmpresas() {
		return empresas;
	}

}

