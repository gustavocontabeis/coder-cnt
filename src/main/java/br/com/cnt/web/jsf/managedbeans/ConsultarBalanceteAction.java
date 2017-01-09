package br.com.cnt.web.jsf.managedbeans;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import br.com.cnt.model.dao.DaoException;
import br.com.cnt.model.dao.balanco.BalanceteDAO;
import br.com.cnt.model.dao.balanco.ExercicioDAO;
import br.com.cnt.model.dao.balanco.LancamentoDAO;
import br.com.cnt.model.entity.balanco.Exercicio;
import br.com.cnt.model.entity.balanco.dto.Balancete;
import br.com.cnt.model.entity.balanco.dto.SaldoContabil;
import br.com.cnt.model.utils.ConstantesComuns;

//@ManagedBean @ViewScoped
@javax.inject.Named @javax.enterprise.context.RequestScoped //@javax.faces.view.ViewScoped
public class ConsultarBalanceteAction {
	
	private Balancete balancete;
	
	//@Inject 
	BalanceteDAO dao;
	
//	private IndexAction indexAction;
	
	
	
	public ConsultarBalanceteAction() {
		super();
		System.out.println("ConsultarBalanceteAction");
		HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		Exercicio exercicio = (Exercicio) sessao.getAttribute(ConstantesComuns.EXERCICIO_SESSAO);
		if(exercicio == null){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Selecione uma empresa para trabalhar."));
		}else{
			exibirBalancete(null);
		}
	}
	
	public void exibirBalancete(ActionEvent evt){

		HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		Exercicio exercicio = (Exercicio) sessao.getAttribute(ConstantesComuns.EXERCICIO_SESSAO);
		Date de = (Date) sessao.getAttribute(ConstantesComuns.PERIODO_SESSAO_DE);
		Date ate = (Date) sessao.getAttribute(ConstantesComuns.PERIODO_SESSAO_ATE);
		
		try {
			dao = new BalanceteDAO(new LancamentoDAO(), new ExercicioDAO());
			this.balancete = dao.buscarBalancete(exercicio, de, ate);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		for(SaldoContabil sc : this.balancete.getSaldos()){
			Integer nivel = sc.getConta().getNivel();
			String nome = StringUtils.leftPad(sc.getConta().getNome(), nivel, " ");
			sc.getConta().setNome(nome);
		}
		
	}

	public Balancete getBalancete() {
		return balancete;
	}

	public void setBalancete(Balancete balancete) {
		this.balancete = balancete;
	}

	public BalanceteDAO getDao() {
		return dao;
	}

	public void setDao(BalanceteDAO dao) {
		this.dao = dao;
	}

//	public IndexAction getIndexAction() {
//		return indexAction;
//	}
//
//	public void setIndexAction(IndexAction indexAction) {
//		this.indexAction = indexAction;
//	}
	

}
