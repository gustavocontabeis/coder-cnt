package br.com.cnt.model.dao.balanco;

import static org.junit.Assert.*;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Test;

import br.com.cnt.model.dao.DaoException;
import br.com.cnt.model.entity.balanco.Conta;
import br.com.cnt.model.entity.balanco.Empresa;
import br.com.cnt.model.entity.balanco.Exercicio;
import br.com.cnt.model.entity.balanco.Lancamento;
import br.com.cnt.model.entity.balanco.LancamentoTipo;
import br.com.cnt.model.entity.balanco.PlanoContas;
import br.com.cnt.model.entity.balanco.dto.Balancete;
import br.com.cnt.model.entity.balanco.dto.SaldoContabil;
import br.com.cnt.model.utils.Filtro;
import br.com.cnt.model.utils.JSONUtil;

public class BalanceteDAOTest {

	private Balancete balancete;
	private BalanceteDAO daoBalancete;
	private LancamentoDAO daoLancamentos;
	private ExercicioDAO daoExercicio;
	
	@Before
	public void setup(){
		this.daoLancamentos = new LancamentoDAO();
		this.daoExercicio = new ExercicioDAO();
		this.daoBalancete = new BalanceteDAO(daoLancamentos, daoExercicio);
	}
	
	@Test
	public void testBuscarBalancete() throws DaoException {
		
		daoLancamentos.buscar2(new Filtro<Lancamento>(Lancamento.class));
		Exercicio exercicio2 = buildExercicio(null, null, null, null, null);
		Lancamento lancamento = buildLancamento(null, exercicio2, new Date(), buildConta(), buildConta(), "historico", null, LancamentoTipo.SIMPLES, 123.45f);
		//String json2 = "{'date':'Mar 3, 2017 7:00:27 PM','exercicio':{'empresa':{'razaoSocial':'Razao Social'},'ano':2017},'debito':{'id':1,'nome':'debitoNome'},'credito':{'id':2,'nome':'creditoNome'},'valor':123.45,'historico':'historico'}".replace("'", "\"");
		//Lancamento lancamento = (Lancamento) JSONUtil.toObject(json2, Lancamento.class);
		daoLancamentos.salvar(lancamento);
		
		Exercicio exercicio = new Exercicio(1L);
		Date de = new Date();
		Date ate = new Date();
		
		try {
			this.balancete  = this.daoBalancete.buscarBalancete(exercicio, de, ate);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		for(SaldoContabil sc : this.balancete.getSaldos()){
			Integer nivel = sc.getConta().getNivel();
			String nome = StringUtils.leftPad(sc.getConta().getNome(), nivel, " ");
			sc.getConta().setNome(nome);
		}
	}

	private Conta buildConta() {
		// TODO Auto-generated method stub
		return null;
	}

	private Exercicio buildExercicio(Long id, Empresa empresa, Integer ano, Boolean fechado, PlanoContas planoContas) {
		Exercicio obj = new Exercicio();
		obj.setId(id);
		obj.setEmpresa(empresa);
		obj.setAno(ano);
		obj.setFechado(fechado);
		obj.setPlanoContas(planoContas);
		return obj;
	}

	private Lancamento buildLancamento(Long id, Exercicio exercicio, Date date, Conta debito, Conta credito, String historico, Lancamento lancamentoPrincipal, LancamentoTipo lancamentoTipo, Float valor) {
		Lancamento obj = new Lancamento();
		obj.setId(id);
		obj.setExercicio(exercicio);
		obj.setDate(date);
		obj.setDebito(debito);
		obj.setCredito(credito);
		obj.setHistorico(historico);
		obj.setLancamentoPrincipal(lancamentoPrincipal);
		obj.setLancamentoTipo(lancamentoTipo);
		obj.setValor(valor);
		return obj;
	}

}
