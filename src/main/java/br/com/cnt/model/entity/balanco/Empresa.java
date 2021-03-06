/* ::::::::::::::::::::::::::::::::::::::::::: */
package br.com.cnt.model.entity.balanco;

import static javax.persistence.FetchType.LAZY;

import java.util.*;
import java.math.*;
import javax.validation.constraints.*;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.validator.constraints.NotEmpty;
import br.com.cnt.model.entity.BaseEntity;
import br.com.cnt.model.entity.usuarios.Usuario;

@XmlRootElement
@Entity @Table(name="EMPRESA", 
	indexes={
		@Index(name="INDEX_EMPRESA_RAZAO_SOCIAL", columnList = "RAZAO_SOCIAL"),
		@Index(name="INDEX_EMPRESA_CNPJ", columnList = "CNPJ"),
	})
@NamedQueries(value={
		@NamedQuery(name="todosEmpresa", query="select obj from Empresa obj ")
	})
public class Empresa extends BaseEntity {
	
 	private static final long serialVersionUID = 1L;
 	
	@Id 
	@GeneratedValue(generator="SEQ_EMPRESA", strategy=GenerationType.SEQUENCE) 
	@SequenceGenerator(name="SEQ_EMPRESA", sequenceName="SEQ_EMPRESA", initialValue=100) 
 	@Column(name="ID_EMPRESA", length=10,  nullable=true)
 	private Long id;
 
	@ManyToOne(cascade=CascadeType.DETACH) 
	@JoinColumn(name="ID_EMPRESA_MATRIZ", nullable=true, foreignKey=@ForeignKey(name="EMPRESA_MATRIZ_fk"))
 	private Empresa matriz;
 
	@NotEmpty 
 	@Column(name="CNPJ", length=18,  nullable=false)
 	private String cnpj;
 
	@NotEmpty 
	@Column(name="RAZAO_SOCIAL", length=80,  nullable=false)
 	private String razaoSocial;
 
	@NotNull 
 	@OneToMany(targetEntity=Exercicio.class, fetch=LAZY, mappedBy="empresa", cascade={CascadeType.ALL}) 
 	private List<Exercicio> exercicios;
 
 	public Empresa() {
	}
 	
 	public Empresa(Long id) {
		this.id = id;
	}
 	
 	public Empresa(Long id, String razaoSocial) {
		this.id = id;
		this.razaoSocial = razaoSocial;
	}
 	
	public Long getId(){
 		return this.id;
	}
	public void setId(Long id){
 		this.id = id;
	}
	public Empresa getMatriz(){
 		return this.matriz;
	}
	public void setMatriz(Empresa matriz){
 		this.matriz = matriz;
	}
	public String getCnpj(){
 		return this.cnpj;
	}
	public void setCnpj(String cnpj){
 		this.cnpj = cnpj;
	}
	public String getRazaoSocial(){
 		return this.razaoSocial;
	}
	public void setRazaoSocial(String razaoSocial){
 		this.razaoSocial = razaoSocial;
	}
	public List<Exercicio> getExercicios(){
 		return this.exercicios;
	}
	public void setExercicios(List<Exercicio> exercicios){
 		this.exercicios = exercicios;
	}
	
}
 