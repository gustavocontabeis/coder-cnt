package br.com.cnt.model.dao.balanco;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import br.com.cnt.model.entity.balanco.Conta;
import br.com.cnt.model.entity.balanco.dto.Razao;
import br.com.cnt.model.entity.balanco.dto.SaldoRazao;

public class RazaoDAO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Razao retornarRazao(Conta conta, Date de, Date ate){
		LancamentoDAO dao = new LancamentoDAO();
		
		SaldoRazao saldoInicial = dao.retornarSaldoInicialRazaoDebido(conta, de);
		SaldoRazao saldoInicialRazaoCredito = dao.retornarSaldoInicialRazaoCredito(conta, de);
		saldoInicial.setVlrCredito(saldoInicialRazaoCredito.getVlrCredito());
		
		
		List<SaldoRazao> saldosRazao = dao.retornarSaldosRazao(conta, de, ate);
		Razao razao = new Razao();
		razao.setConta(conta);
		razao.setDe(de);
		razao.setAte(ate);
		razao.setSaldoInicial(saldoInicial);
		razao.setSaldosRazao(saldosRazao);
		return razao;
	}

}
