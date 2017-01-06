package br.com.cnt.model.dao.balanco;

import java.util.Date;
import java.util.List;

import br.com.cnt.model.entity.balanco.Conta;
import br.com.cnt.model.entity.balanco.dto.Razao;
import br.com.cnt.model.entity.balanco.dto.SaldoRazao;

public class RazaoDAO {
	
	public Razao retornarRazao(Conta conta, Date de, Date ate){
		LancamentoDAO dao = new LancamentoDAO();
		List<SaldoRazao> saldosRazao = dao.retornarSaldosRazao(conta, de, ate);
		Razao razao = new Razao();
		razao.setConta(conta);
		razao.setDe(de);
		razao.setAte(ate);
		razao.setSaldosRazao(saldosRazao);
		return razao;
	}

}
