package br.com.cnt.web.jsf.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.cnt.model.dao.DaoException;
import br.com.cnt.model.dao.balanco.ContaDAO;
import br.com.cnt.model.dao.balanco.EmpresaDAO;
import br.com.cnt.model.dao.balanco.ExercicioDAO;
import br.com.cnt.model.dao.balanco.PlanoContasDAO;
import br.com.cnt.model.entity.balanco.Conta;
import br.com.cnt.model.entity.balanco.ContaOrigem;
import br.com.cnt.model.entity.balanco.ContaTipo;
import br.com.cnt.model.entity.balanco.Empresa;
import br.com.cnt.model.entity.balanco.Exercicio;
import br.com.cnt.model.entity.balanco.PlanoContas;
import br.com.cnt.model.utils.ContaUtil;
import br.com.cnt.model.utils.Filtro;
import br.com.cnt.web.jsf.managedbeans.BaseManagedBean;

@Named
//@javax.enterprise.context.RequestScoped
@javax.enterprise.context.SessionScoped
//@javax.enterprise.context.ConversationScoped
//@javax.faces.bean.ViewScoped
//@javax.faces.view.ViewScoped
public class ContaAction extends BaseManagedBean<Conta, ContaDAO> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Conta conta ;
	private List<Conta> contaList;
	
	private ContaOrigem selContaOrigem;
	private List<SelectItem> siContaOrigem;

	private PlanoContas selPlanoContas;
	private List<SelectItem> siPlanoContas;

	private ContaTipo selContaTipo;
	private List<SelectItem> siContaTipo;

	private Empresa selEmpresa;
	private List<SelectItem> siEmpresa;

	private Exercicio selExercicio;
	private List<SelectItem> siExercicio;
	
	
	
	protected LazyDataModel<Conta> model;
	protected Filtro filtro;
	
	@javax.inject.Inject
	private ContaDAO dao;
	
//	@Inject
//    private Conversation conversation;

	
	@PostConstruct
	private void init() throws NumberFormatException, DaoException {
		//conversation.begin();
		this.contaList = new ArrayList<Conta>();
		novo(null);
		inicializarCombos();
		
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String parameter = request.getParameter("id");
		if(StringUtils.isNotBlank(parameter)){
			buscarConta(new Long(parameter)); 
			selPlanoContas = conta.getPlanoContas();
			selEmpresa = conta.getEmpresa();
			selExercicio = conta.getExercicio();
		}
		//dao = new CategoriaDAO();
		loadLazyModel();
	}

	private void loadLazyModel() {
		model = new LazyDataModel<Conta>() {
			private static final long serialVersionUID = 1L;
			@Override
			@SuppressWarnings({ "rawtypes", "unchecked" })
			public List<Conta> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
				filtro = new Filtro(Conta.class, first, pageSize, sortField, sortOrder, filters);
				setRowCount(dao.getQuantidade2(filtro));
				return dao.buscar2(filtro);
			}
		    public Conta getRowData(String rowKey) {
		    	Conta obj = new Conta();
		    	obj.setId(new Long(rowKey));
		    	 try {
					obj = dao.buscar(obj, new Long(rowKey));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (br.com.cnt.model.dao.DaoException e) {
					e.printStackTrace();
				}
		    	return obj;
		    }
		    public Object getRowKey(Conta object) {
		    	return String.valueOf(object.getId());
		    }
		};
	}
	

	private void buscarConta(Long id) throws DaoException {
		this.conta = dao.buscar(id);
	}

	private void inicializarCombos() {
		siContaOrigem = new ArrayList<SelectItem>();
		for (ContaOrigem obj : ContaOrigem.values())
			siContaOrigem.add(new SelectItem(obj.name(), obj.name()));

		selPlanoContas = new PlanoContas();
		siPlanoContas = new ArrayList<SelectItem>();
 		List<PlanoContas> planoContasList = new PlanoContasDAO().buscarTodos();
		siPlanoContas.add(new SelectItem(null, "[Selecione]"));
 		for (PlanoContas obj : planoContasList)
 			siPlanoContas.add(new SelectItem(obj.getId(), obj.getNome()));

		siContaTipo = new ArrayList<SelectItem>();
		for (ContaTipo obj : ContaTipo.values())
			siContaTipo.add(new SelectItem(obj.name(), obj.name()));

		selEmpresa = new Empresa();
		siEmpresa = new ArrayList<SelectItem>();
 		List<Empresa> empresaList = new EmpresaDAO().buscarTodasEmpresas();
		siEmpresa.add(new SelectItem(null, "[Selecione]"));
 		for (Empresa obj : empresaList)
 			siEmpresa.add(new SelectItem(obj.getId(), obj.getRazaoSocial()));

		selExercicio = new Exercicio();
		siExercicio = new ArrayList<SelectItem>();
 		List<Exercicio> exercicioList = new ExercicioDAO().buscarTodos();
		siExercicio.add(new SelectItem(null, "[Selecione]"));
 		for (Exercicio obj : exercicioList)
 			siExercicio.add(new SelectItem(obj.getId(), obj.getAno().toString()));
	}

	public void novo(ActionEvent evt){
		System.out.println("novo");
 		try {
			this.conta = new Conta();
 		} catch (Exception e) {
 			e.printStackTrace();
 			String msg = !"".equals(e.getMessage())?e.getMessage():e.toString();
 			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
 		}
	}

	public void salvar(ActionEvent evt){
		System.out.println("salvar");
		
 		try {
			if(new Long(0).equals(conta.getId()))
				this.conta.setId(null);
			
			this.conta.setNivel(ContaUtil.retornarNivel(conta));
 			this.conta.setContaOrigem(selContaOrigem);
 			this.conta.setContaTipo(selContaTipo);
 			this.conta.setPlanoContas(selPlanoContas.getId()!=null?selPlanoContas:null);
 			this.conta.setEmpresa(selEmpresa.getId()!=null?selEmpresa:null);
 			this.conta.setExercicio(selExercicio.getId()!=null?selExercicio:null);
 			
			ContaDAO dao = new ContaDAO();
			dao.salvar(conta);
 		} catch (Exception e) {
 			e.printStackTrace();
 			String msg = !"".equals(e.getMessage())?e.getMessage():e.toString();
 			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
 		}
	}

	public void excluir(ActionEvent evt){
		System.out.println("excluir");
 		try {
			ContaDAO dao = new ContaDAO();
			dao.excluir(conta);
 		} catch (Exception e) {
 			e.printStackTrace();
 			String msg = !"".equals(e.getMessage())?e.getMessage():e.toString();
 			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
 		}
	}

	public void listar(ActionEvent evt){
		System.out.println("listar");
 		try {
			ContaDAO dao = new ContaDAO();
			this.contaList = dao.buscarTodasContas(new Conta());
 		} catch (Exception e) {
 			e.printStackTrace();
 			String msg = !"".equals(e.getMessage())?e.getMessage():e.toString();
 			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
 		}
 		//conversation.end();
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public List<Conta> getContaList() {
		return contaList;
	}

	public void setContaList(List<Conta> contaList) {
		this.contaList = contaList;
	}

	public ContaOrigem getSelContaOrigem() {
		return selContaOrigem;
	}

	public void setSelContaOrigem(ContaOrigem selContaOrigem) {
		this.selContaOrigem = selContaOrigem;
	}

	public List<SelectItem> getSiContaOrigem() {
		return siContaOrigem;
	}

	public void setSiContaOrigem(List<SelectItem> siContaOrigem) {
		this.siContaOrigem = siContaOrigem;
	}

	public PlanoContas getSelPlanoContas() {
		return selPlanoContas;
	}

	public void setSelPlanoContas(PlanoContas selPlanoContas) {
		this.selPlanoContas = selPlanoContas;
	}

	public List<SelectItem> getSiPlanoContas() {
		return siPlanoContas;
	}

	public void setSiPlanoContas(List<SelectItem> siPlanoContas) {
		this.siPlanoContas = siPlanoContas;
	}

	public ContaTipo getSelContaTipo() {
		return selContaTipo;
	}

	public void setSelContaTipo(ContaTipo selContaTipo) {
		this.selContaTipo = selContaTipo;
	}

	public List<SelectItem> getSiContaTipo() {
		return siContaTipo;
	}

	public void setSiContaTipo(List<SelectItem> siContaTipo) {
		this.siContaTipo = siContaTipo;
	}

	public Empresa getSelEmpresa() {
		return selEmpresa;
	}

	public void setSelEmpresa(Empresa selEmpresa) {
		this.selEmpresa = selEmpresa;
	}

	public List<SelectItem> getSiEmpresa() {
		return siEmpresa;
	}

	public void setSiEmpresa(List<SelectItem> siEmpresa) {
		this.siEmpresa = siEmpresa;
	}

	public Exercicio getSelExercicio() {
		return selExercicio;
	}

	public void setSelExercicio(Exercicio selExercicio) {
		this.selExercicio = selExercicio;
	}

	public List<SelectItem> getSiExercicio() {
		return siExercicio;
	}

	public void setSiExercicio(List<SelectItem> siExercicio) {
		this.siExercicio = siExercicio;
	}

	/* TODO: Encapsular atributos */
}
