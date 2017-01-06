package br.com.cnt.web.jsf.managedbeans;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
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
import br.com.cnt.model.entity.balanco.Exercicio;
import br.com.cnt.model.entity.balanco.PlanoContas;
import br.com.cnt.model.entity.usuarios.Usuario;
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

	@PostConstruct
	private void init() {
//		dao = new ContaDAO();
//		empresaDAO = (new EmpresaDAO());
//		exercicioDAO = (new ExercicioDAO());
//		planoContasDAO = (new PlanoContasDAO());
//		usuarioDAO = (new UsuarioDAO());
		novo(null);
		loadLazyModel();
		empresas = getPopularComboEmpresa();
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
					obj = dao.buscar(obj, new Long(rowKey));
					if(obj.getEmpresa() == null)
						obj.setEmpresa(new Empresa());
					if(obj.getExercicio() == null)
						obj.setExercicio(new Exercicio());
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
		conta.setEmpresa(new Empresa());
		conta.setExercicio(new Exercicio());
		conta.setPlanoContas(new PlanoContas());
		conta.setUsuarioAlteracao(new Usuario());
		conta.setUsuarioInclusao(new Usuario());
	}

	public void salvar(ActionEvent evt) throws DaoException {
		try {
			prepararEntidade(conta);
			if(conta.getEmpresa().getId()==null){
				conta.setEmpresa(null);
			}
			if(conta.getPlanoContas().getId()==null){
				conta.setPlanoContas(null);
			}
			if(conta.getExercicio().getId()==null){
				conta.setExercicio(null);
			}
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

	public List<Exercicio> getPopularComboExercicio() {
		return exercicioDAO.buscarTodos();
	}

	public List<PlanoContas> getPopularComboPlanoContas() {
		return planoContasDAO.buscarTodos();
	}

	public List<Usuario> getPopularComboUsuario() {
		return usuarioDAO.buscarTodos();
	}

	public List<Empresa> getEmpresas() {
		return empresas;
	}
}
