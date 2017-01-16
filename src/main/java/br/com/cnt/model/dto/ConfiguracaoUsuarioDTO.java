package br.com.cnt.model.dto;

import java.io.Serializable;

public class ConfiguracaoUsuarioDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long empresa, exercicio;
	private String periodo;

	public Long getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Long empresa) {
		this.empresa = empresa;
	}

	public Long getExercicio() {
		return exercicio;
	}

	public void setExercicio(Long exercicio) {
		this.exercicio = exercicio;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	
}
