package br.com.cnt.web.jsf.managedbeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import org.primefaces.model.LazyDataModel;

import br.com.cnt.model.dao.BaseDAO;
import br.com.cnt.model.dao.balanco.ContaDAO;
import br.com.cnt.model.dao.balanco.EmpresaDAO;
import br.com.cnt.model.dao.balanco.PlanoContasDAO;
import br.com.cnt.model.entity.balanco.Conta;
import br.com.cnt.model.entity.balanco.ContaOrigem;
import br.com.cnt.model.entity.balanco.ContaTipo;
import br.com.cnt.model.entity.balanco.Empresa;
import br.com.cnt.model.entity.balanco.PlanoContas;
import br.com.cnt.model.utils.ContaUtil;

@javax.inject.Named @javax.faces.view.ViewScoped
public class ContaManagedBean extends CrudManagedBean<Conta, ContaDAO> {

	/**
	 */
	private static final long serialVersionUID = 1L;

	@Inject private ContaDAO dao;
	@Inject private EmpresaDAO empresaDAO;
	@Inject private PlanoContasDAO planoContasDAO;

	private List<Empresa> empresas;
	private List<PlanoContas> planocontas;

	@PostConstruct
	private void init() {
		System.out.println("ContaManagedBean.init() ");
		empresas = getPopularComboEmpresa();
		planocontas = getPopularComboPlanoContas();
	}
	
	@Override
	protected BaseDAO<Conta> getDao() {
		return dao;
	}

	public void novo(ActionEvent evt) {
		entity = new Conta();
	}
	
	@Override
	protected void salvarAntes(Conta entity) {
		entity.setNivel(ContaUtil.retornarNivel(entity));
	}

	public ContaOrigem[] getPopularComboContaOrigem() {
		return ContaOrigem.values();
	}

	public ContaTipo[] getPopularComboContaTipo() {
		return ContaTipo.values();
	}

	public List<Empresa> getPopularComboEmpresa() {
		return empresaDAO.buscarTodos();
	}

	public List<PlanoContas> getPopularComboPlanoContas() {
		planocontas = planoContasDAO.buscarTodos();
		return planocontas;
	}

	public List<PlanoContas> getPlanocontas() {
		return planocontas;
	}

	public void setPlanocontas(List<PlanoContas> planocontas) {
		this.planocontas = planocontas;
	}

	public List<Empresa> getEmpresas() {
		return empresas;
	}

}
