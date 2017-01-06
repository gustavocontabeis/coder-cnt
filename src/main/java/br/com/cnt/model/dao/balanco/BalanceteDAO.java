package br.com.cnt.model.dao.balanco;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import br.com.cnt.model.dao.BaseDAO;
import br.com.cnt.model.dao.DaoException;
import br.com.cnt.model.entity.balanco.Empresa;
import br.com.cnt.model.entity.balanco.Exercicio;
import br.com.cnt.model.entity.balanco.dto.Balancete;
import br.com.cnt.model.entity.balanco.dto.SaldoContabil;

public class BalanceteDAO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Balancete buscarBalancete(Exercicio exercicio, Date de , Date ate) throws DaoException{
		
		LancamentoDAO daoLancamentos = new LancamentoDAO();
		ExercicioDAO daoExercicio = new ExercicioDAO();
		exercicio = daoExercicio.buscar(exercicio.getId());
		Empresa empresa = exercicio.getEmpresa();
		exercicio.setEmpresa(empresa);
		
		Balancete balancete = new Balancete();
		balancete.setExercicio(exercicio);
		balancete.setDe(de);
		balancete.setAte(ate);
		
		List<SaldoContabil> saldosContabeis = daoLancamentos.buscarSaldosBalancete(exercicio, de, ate);
		balancete.setSaldos(saldosContabeis);
		
		return balancete;
	}

}
