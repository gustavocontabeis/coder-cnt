package br.com.cnt.model.entity.usuarios;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.cnt.model.entity.BaseEntity;

@XmlRootElement
@Entity @Table(name="PERFIL_ACESSO")
@NamedQueries(value={
		@NamedQuery(name="todosPerfilAcesso", query="select obj from PerfilAcesso obj ")
	})
public class PerfilAcesso extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="SEQ_PERFIL_ACESSO", sequenceName="SEQ_PERFIL_ACESSO", initialValue=100) 
	@Column(name="ID_PERFIL") 
	private Long id;

	@NotEmpty 
	@Column(name="NOME", length=15, nullable=false, unique=true)
	private String nome;

	@NotEmpty 
	@Column(name="DESCRICAO", length=255, nullable=false)
	private String descricao;

	public Long getId(){
		return this.id;
	}
	public void setId(Long id){
		this.id = id;
	}
	public String getNome(){
		return this.nome;
	}
	public void setNome(String nome){
		this.nome = nome;
	}
	public String getDescricao(){
		return this.descricao;
	}
	public void setDescricao(String descricao){
		this.descricao = descricao;
	}
	
//	@Override
//	public int hashCode() {
//		return Objects.hashCode(this);
//	}
//	@Override
//	public boolean equals(Object obj) {
//		return Objects.equals(this, obj);
//	}

}

