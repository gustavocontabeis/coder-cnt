package br.com.cnt.web.jsf.managedbeans;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.cnt.model.dao.DaoException;
import br.com.cnt.model.dao.balanco.ContaDAO;
import br.com.cnt.model.dao.balanco.ExercicioDAO;
import br.com.cnt.model.dao.balanco.LancamentoDAO;
import br.com.cnt.model.entity.balanco.Conta;
import br.com.cnt.model.entity.balanco.Exercicio;
import br.com.cnt.model.entity.balanco.Lancamento;
import br.com.cnt.model.entity.balanco.LancamentoTipo;
import br.com.cnt.model.utils.Filtro;

//@ManagedBean @ViewScoped
@javax.inject.Named @javax.faces.view.ViewScoped
public class LancamentoManagedBean extends BaseManagedBean {

	/**
	 */
	private static final long serialVersionUID = 1L;

	private Lancamento lancamento;
	protected LazyDataModel<Lancamento> model;
	protected Filtro<Lancamento> filtro;
	private List<Conta> contas;
	private List<Exercicio> exercicios;
	private List<Lancamento> lancamentos;
	
	@Inject private LancamentoDAO dao;
	@Inject private ContaDAO contaDAO;
	@Inject private ExercicioDAO exercicioDAO;


	@PostConstruct
	private void init() {
		novo(null);
		loadLazyModel();
		getPopularComboConta();
		getPopularComboExercicio();
		getPopularComboLancamento();
	}

	private void loadLazyModel() {
		model = new LazyDataModel<Lancamento>() {
			private static final long serialVersionUID = 1L;
			@Override
			public List<Lancamento> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
				filtro = new Filtro<Lancamento>(Lancamento.class, first, pageSize, sortField, sortOrder, filters);
				setRowCount(dao.getQuantidade2(filtro));
				return dao.buscar2(filtro);
			}
		    public Lancamento getRowData(String rowKey) {
		    	Lancamento obj = new Lancamento();
		    	obj.setId(new Long(rowKey));
		    	 try {
					obj = dao.buscar(new Long(rowKey));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
		    	return obj;
		    }
		    public Object getRowKey(Lancamento object) {
		    	return String.valueOf(object.getId());
		    }
		};
	}
	
	public void novo(ActionEvent evt) {
		lancamento = new Lancamento();
		lancamento.setExercicio(loginBean.getExercicio());
	}

	public void clonar(ActionEvent evt) {
		Lancamento lancamento = new Lancamento();
		lancamento.setId(null);
		lancamento.setCredito(this.lancamento.getCredito());
		lancamento.setDate(this.lancamento.getDate());
		lancamento.setDebito(this.lancamento.getDebito());
		lancamento.setExercicio(this.lancamento.getExercicio());
		lancamento.setHistorico(null);
		lancamento.setLancamentoTipo(this.lancamento.getLancamentoTipo());
		lancamento.setValor(this.lancamento.getValor());
		this.lancamento = lancamento;
	}

	public void salvar(ActionEvent evt) throws DaoException {
		try {
			
			if(lancamento.getDebito()!=null&&lancamento.getCredito()!=null){
				lancamento.setLancamentoTipo(LancamentoTipo.SIMPLES);
			}else{
				lancamento.setLancamentoTipo(LancamentoTipo.COMPOSTO);
			}
			dao.salvar(lancamento);
			message(null, "Registro salvo com sucesso.");
		} catch (Exception e) {
			message(e);
		}
	}


	public void excluir(ActionEvent evt) throws DaoException {
		try {
			dao.excluir(lancamento);
			novo(null);
			message(null, "Registro excluído com sucesso.");
		} catch (Exception e) {
			message(e);
		}
	}

	public Lancamento getLancamento() {
		return lancamento;
	}

	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}

	public LazyDataModel<Lancamento> getModel() {
		return model;
	}

	public void setModel(LazyDataModel<Lancamento> model) {
		this.model = model;
	}
	
	public List<Conta> getPopularComboConta() {
		contas = contaDAO.buscarTodos();
		return contas;
	}

	public List<Exercicio> getPopularComboExercicio() {
		exercicios = exercicioDAO.buscarTodos();
		return exercicios;
	}

	public List<Lancamento> getPopularComboLancamento() {
		lancamentos = dao.buscarTodos();
		return lancamentos;
	}

	public LancamentoTipo[] getPopularComboLancamentoTipo() {
		return LancamentoTipo.values();
	}

	public List<Conta> getContas() {
		return contas;
	}

	public void setContas(List<Conta> contas) {
		this.contas = contas;
	}

	public List<Exercicio> getExercicios() {
		return exercicios;
	}

	public void setExercicios(List<Exercicio> exercicios) {
		this.exercicios = exercicios;
	}

}

