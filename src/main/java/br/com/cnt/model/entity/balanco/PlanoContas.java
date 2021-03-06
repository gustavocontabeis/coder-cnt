/* ::::::::::::::::::::::::::::::::::::::::::: */
package br.com.cnt.model.entity.balanco;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.cnt.model.entity.BaseEntity;

@Entity
@Table(name="PLANO_CONTAS")
@NamedQueries(value={
		@NamedQuery(name="todosPlanoContas", query="from PlanoContas")
})
public class PlanoContas extends BaseEntity {
	
 	private static final long serialVersionUID = 1L;
 	
 	@Id 
 	@GeneratedValue(generator="SEQ_PLANO_CONTAS", strategy=GenerationType.SEQUENCE) 
	@SequenceGenerator(name="SEQ_PLANO_CONTAS", sequenceName="SEQ_PLANO_CONTAS", initialValue=100)  
 	@Column(name="ID_PLANO_CONTAS", length=10,  nullable=false)
 	private Long id;
 
 	@NotEmpty
 	@Column(name="NOME", length=80,  nullable=false)
 	private String nome;
 	
 	public PlanoContas() {
		super();
	}
 	
 	public PlanoContas(Long id) {
		super();
		this.id = id;
	}

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
	@Override
	public String toString() {
		return "PlanoContas [id=" + id + ", nome=" + nome + "]";
	}
	
}
 