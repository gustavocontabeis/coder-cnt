package br.com.cnt.web.jsf.managedbeans;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.cnt.model.dao.DaoException;
import br.com.cnt.model.dao.balanco.ContaDAO;
import br.com.cnt.model.dao.balanco.EmpresaDAO;
import br.com.cnt.model.dao.balanco.ExercicioDAO;
import br.com.cnt.model.dao.balanco.PlanoContasDAO;
import br.com.cnt.model.dao.usuarios.UsuarioDAO;
import br.com.cnt.model.entity.balanco.Conta;
import br.com.cnt.model.entity.balanco.ContaOrigem;
import br.com.cnt.model.entity.balanco.ContaTipo;
import br.com.cnt.model.entity.balanco.Empresa;
import br.com.cnt.model.entity.balanco.PlanoContas;
import br.com.cnt.model.entity.usuarios.Usuario;
import br.com.cnt.model.utils.ContaUtil;
import br.com.cnt.model.utils.Filtro;

//@ManagedBean @ViewScoped
@javax.inject.Named @javax.faces.view.ViewScoped
public class ContaManagedBean extends BaseManagedBean<Conta, ContaDAO> {

	/**
	 */
	private static final long serialVersionUID = 1L;

	private Conta conta;
	protected LazyDataModel<Conta> model;
	protected Filtro<Conta> filtro;
	@Inject private ContaDAO dao;
	@Inject private EmpresaDAO empresaDAO;
	@Inject private ExercicioDAO exercicioDAO;
	@Inject private PlanoContasDAO planoContasDAO;
	@Inject private UsuarioDAO usuarioDAO;

	private List<Empresa> empresas;
	private List<PlanoContas> planocontas;

	@PostConstruct
	private void init() {
		novo(null);
		loadLazyModel();
		empresas = getPopularComboEmpresa();
		planocontas = getPopularComboPlanoContas();
	}

	private Long id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void listener(ComponentSystemEvent evt) throws AbortProcessingException{
		if(id != null){
			try {
				conta = dao.buscar(new Long(id));
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
		model = new LazyDataModel<Conta>() {
			private static final long serialVersionUID = 1L;

			@Override
			public List<Conta> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {
				filtro = new Filtro(Conta.class, first, pageSize, sortField, sortOrder, filters);
				setRowCount(dao.getQuantidade2(filtro));
				return dao.buscar2(filtro);
			}

			public Conta getRowData(String rowKey) {
				Conta obj = new Conta();
				obj.setId(new Long(rowKey));
				try {
					obj = dao.buscar(new Long(rowKey));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (DaoException e) {
					e.printStackTrace();
				}
				return obj;
			}

			public Object getRowKey(Conta object) {
				return String.valueOf(object.getId());
			}
		};
	}

	public void novo(ActionEvent evt) {
		conta = new Conta();
	}

	public void salvar(ActionEvent evt) throws DaoException {
		try {
			conta.setNivel(ContaUtil.retornarNivel(conta));
			dao.salvar(conta);
			message(null, "Registro salvo com sucesso.");
		} catch (Exception e) {
			message(e);
		}
	}

	public void excluir(ActionEvent evt) throws DaoException {
		try {
			dao.excluir(conta);
			novo(null);
			message(null, "Registro excluído com sucesso.");
		} catch (Exception e) {
			message(e);
		}
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public LazyDataModel<Conta> getModel() {
		return model;
	}

	public void setModel(LazyDataModel<Conta> model) {
		this.model = model;
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

	public List<Usuario> getPopularComboUsuario() {
		return usuarioDAO.buscarTodos();
	}

	public List<Empresa> getEmpresas() {
		return empresas;
	}

}
