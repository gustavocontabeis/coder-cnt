package br.com.cnt.web.jsf.managedbeans;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.cnt.model.dao.DaoException;
import br.com.cnt.model.dao.balanco.EmpresaDAO;
import br.com.cnt.model.dao.balanco.ExercicioDAO;
import br.com.cnt.model.dao.balanco.PlanoContasDAO;
import br.com.cnt.model.entity.balanco.Empresa;
import br.com.cnt.model.entity.balanco.Exercicio;
import br.com.cnt.model.entity.balanco.PlanoContas;
import br.com.cnt.model.utils.Filtro;

//@ManagedBean @ViewScoped
@javax.inject.Named @javax.faces.view.ViewScoped
public class ExercicioManagedBean extends BaseManagedBean {

	/**
	 */
	private static final long serialVersionUID = 1L;

	private Exercicio exercicio;
	protected LazyDataModel<Exercicio> model;
	protected Filtro filtro;
	
	@Inject private ExercicioDAO dao;
	@Inject private EmpresaDAO empresaDAO;

	private List<Empresa> empresas;
	private List<PlanoContas> planosContas;

	@PostConstruct
	private void init() {
		novo(null);
		loadLazyModel();
		getPopularComboEmpresa();
		getPopularComboPlanoContas();
	}

	private void loadLazyModel() {
		model = new LazyDataModel<Exercicio>() {
			private static final long serialVersionUID = 1L;
			@Override
			public List<Exercicio> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
				filtro = new Filtro<Exercicio>(Exercicio.class, first, pageSize, sortField, sortOrder, filters);
				setRowCount(dao.getQuantidade2(filtro));
				return dao.buscar2(filtro);
			}
		    public Exercicio getRowData(String rowKey) {
		    	Exercicio obj = new Exercicio();
		    	obj.setId(new Long(rowKey));
		    	 try {
					obj = dao.buscar(obj, new Long(rowKey));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (DaoException e) {
					e.printStackTrace();
				}
		    	return obj;
		    }
		    public Object getRowKey(Exercicio object) {
		    	return String.valueOf(object.getId());
		    }
		};
	}
	
	public void novo(ActionEvent evt) {
		exercicio = new Exercicio();
	}

	public void salvar(ActionEvent evt) throws DaoException {
		try {
			dao.salvar(exercicio);
			message(null, "Registro salvo com sucesso.");
		} catch (Exception e) {
			message(e);
		}
	}


	public void excluir(ActionEvent evt) throws DaoException {
		try {
			dao.excluir(exercicio);
			novo(null);
			message(null, "Registro excluído com sucesso.");
		} catch (Exception e) {
			message(e);
		}
	}

	public Exercicio getExercicio() {
		return exercicio;
	}

	public void setExercicio(Exercicio exercicio) {
		this.exercicio = exercicio;
	}

	public LazyDataModel<Exercicio> getModel() {
		return model;
	}

	public void setModel(LazyDataModel<Exercicio> model) {
		this.model = model;
	}
	
	public List<Empresa> getPopularComboEmpresa() {
		empresas = empresaDAO.buscarTodos();
		return empresas;
	}

	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}

	public List<PlanoContas> getPlanosContas() {
		return planosContas;
	}

	public void setPlanosContas(List<PlanoContas> planosContas) {
		this.planosContas = planosContas;
	}
	
	@Inject private PlanoContasDAO planoContasDAO;

	private List<PlanoContas> planocontas;
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


}