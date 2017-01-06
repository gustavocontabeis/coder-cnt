package br.com.cnt.model.entity.balanco.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import br.com.cnt.model.entity.balanco.Conta;
import br.com.cnt.model.entity.balanco.Exercicio;

public class Razao implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Exercicio exercicio;
	private Conta conta;
	private Date de, ate;
	private List<SaldoRazao>saldosRazao;
	
	public Exercicio getExercicio() {
		return exercicio;
	}
	public void setExercicio(Exercicio exercicio) {
		this.exercicio = exercicio;
	}
	public Conta getConta() {
		return conta;
	}
	public void setConta(Conta conta) {
		this.conta = conta;
	}
	public Date getDe() {
		return de;
	}
	public void setDe(Date de) {
		this.de = de;
	}
	public Date getAte() {
		return ate;
	}
	public void setAte(Date ate) {
		this.ate = ate;
	}
	public List<SaldoRazao> getSaldosRazao() {
		return saldosRazao;
	}
	public void setSaldosRazao(List<SaldoRazao> saldosRazao) {
		this.saldosRazao = saldosRazao;
	}

}
