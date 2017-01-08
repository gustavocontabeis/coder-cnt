/* ::::::::::::::::::::::::::::::::::::::::::: */
package br.com.cnt.model.entity.balanco;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.cnt.model.entity.BaseEntity;

@Entity
@Table(name="EXERCICIO")
public class Exercicio extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
 	
 	@Id 
 	@GeneratedValue(generator="SEQ_EXERCICIO", strategy=GenerationType.SEQUENCE) 
	@SequenceGenerator(name="SEQ_EXERCICIO", sequenceName="SEQ_EXERCICIO", initialValue=100)  
 	@Column(name="ID_EXERCICIO", length=10,  nullable=true)
 	private Long id;
 
 	@ManyToOne(targetEntity=Empresa.class, fetch=FetchType.EAGER) 
 	@JoinColumn(name="ID_EMPRESA", nullable=false, foreignKey = @ForeignKey(name="FK_EXERCICIO_EMPRESA"))
 	private Empresa empresa;
 
 	@Column(name="ANO", length=10, nullable=false)
 	private Integer ano;
 
 	@Column(name="FECHADO", length=10, nullable=false)
 	private Boolean fechado;
 
 	public Exercicio() {
		super();
	}
	
 	public Exercicio(Long id) {
		super();
		this.id = id;
	}
 	
 	public Exercicio(Empresa empresa, Integer ano) {
		super();
		this.empresa = empresa;
		this.ano = ano;
	}
 	
 	public Exercicio(Long id, Empresa empresa, Integer ano) {
		super();
		this.id = id;
		this.empresa = empresa;
		this.ano = ano;
	}
 	
 	public Long getId(){
 		return this.id;
	}
	public void setId(Long id){
 		this.id = id;
	}
	public Empresa getEmpresa(){
 		return this.empresa;
	}
	public void setEmpresa(Empresa empresa){
 		this.empresa = empresa;
	}
	public Integer getAno(){
 		return this.ano;
	}
	public void setAno(Integer ano){
 		this.ano = ano;
	}
	public Boolean getFechado() {
		return fechado;
	}
	public void setFechado(Boolean fechado) {
		this.fechado = fechado;
	}

	@Override
	public String toString() {
		return "Exercicio [id=" + id + ", empresa=" + empresa + ", ano=" + ano + "]";
	}
}
 