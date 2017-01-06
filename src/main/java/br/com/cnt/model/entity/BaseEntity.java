package br.com.cnt.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import br.com.cnt.model.entity.usuarios.Usuario;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {
	
	/**
	 */
	private static final long serialVersionUID = 1L;
	
	public abstract Long getId();
	public abstract void setId(Long id);
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dt_inclusao", nullable=false)
	private Date dtIclusao;
	
	@Version
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dt_alteracao", nullable=true)
	private Date dtAlteracao;
	
	@NotNull
	@OneToOne(cascade={CascadeType.DETACH}, fetch=FetchType.EAGER)
	@JoinColumn(name="id_pessoa_inclusao")
	private Usuario usuarioInclusao;
	
	@OneToOne(cascade={CascadeType.DETACH}, fetch=FetchType.LAZY)
	@JoinColumn(name="id_pessoa_alteracao", nullable=true)
	private Usuario usuarioAlteracao;
	
	public Date getDtIclusao() {
		return dtIclusao;
	}
	public void setDtIclusao(Date dtIclusao) {
		this.dtIclusao = dtIclusao;
	}
	
	
	public Date getDtAlteracao() {
		return dtAlteracao;
	}
	public void setDtAlteracao(Date dtAlteracao) {
		this.dtAlteracao = dtAlteracao;
	}
	
	public Usuario getUsuarioInclusao() {
		return usuarioInclusao;
	}
	public void setUsuarioInclusao(Usuario usuarioInclusao) {
		this.usuarioInclusao = usuarioInclusao;
	}
	
	public Usuario getUsuarioAlteracao() {
		return usuarioAlteracao;
	}
	public void setUsuarioAlteracao(Usuario usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + getClass().getName().hashCode();
		result = prime * result + ( getId()!=null ? getId().intValue():1 );
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		BaseEntity other = (BaseEntity) obj;
		
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		
		return true;
	}
	
}
