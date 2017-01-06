/* ::::::::::::::::::::::::::::::::::::::::::: */
package br.com.cnt.model.entity.balanco;

import static javax.persistence.FetchType.LAZY;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

import br.com.cnt.model.entity.BaseEntity;
import br.com.cnt.model.utils.ContaUtil;


@Entity
@Table(name="CONTAS")
public class Conta extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
 	
 	@Id 
 	@GeneratedValue(generator="SEQ_CONTA", strategy=GenerationType.SEQUENCE) 
	@SequenceGenerator(name="SEQ_CONTA", sequenceName="SEQ_CONTA", initialValue=100) 
 	@Column(name="ID_CONTA", length=10,  nullable=false)
 	private Long id;
 
 	@Column(name="ESTRUTURA", length=20,  nullable=false)
 	private String estrutura;
 	
 	@Column(name="NIVEL", length=1,  nullable=false)
 	private Integer nivel;
 	
 	@Column(name="NOME", length=150,  nullable=false)
 	private String nome;
 
 	@Column(name="DESCRICAO", length=150,  nullable=true)
 	private String descricao;
 	
 	@Enumerated(EnumType.STRING)
 	@Column(name="CONTA_TIPO")
	private ContaTipo contaTipo;
 
 	@Enumerated(EnumType.STRING)
 	@Column(name="CONTA_ORIGEM")
	private ContaOrigem contaOrigem;
 	
 	@Deprecated
 	@ManyToOne(targetEntity=Empresa.class, fetch=LAZY) 
 	@JoinColumn(name="ID_EMPRESA", nullable=true)
 	@ForeignKey(name="FK_EMPRESA")
 	private Empresa empresa;
 
 	@Deprecated
 	@ManyToOne(targetEntity=Exercicio.class, fetch=LAZY) 
 	@JoinColumn(name="ID_EXERCICIO", nullable=true)
 	@ForeignKey(name="FK_EXERCICIO")
 	private Exercicio exercicio;
 
 	@ManyToOne(targetEntity=PlanoContas.class, fetch=FetchType.EAGER) 
 	@JoinColumn(name="ID_PLANO_CONTAS", nullable=true)
 	@ForeignKey(name="FK_PLANO_CONTAS")
 	private PlanoContas planoContas;
 
	public Conta() {
		super();
	}

	public Conta(Long id) {
		super();
		this.id = id;
	}
	
	public Conta(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
	
	public Conta(Empresa empresa, Exercicio exercicio, PlanoContas planoContas) {
		super();
		this.empresa = empresa;
		this.exercicio = exercicio;
		this.planoContas = planoContas;
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
	public String getDescricao(){
 		return this.descricao;
	}
	public void setDescricao(String descricao){
 		this.descricao = descricao;
	}
	public ContaTipo getContaTipo(){
 		return this.contaTipo;
	}
	public void setContaTipo(ContaTipo contaTipo){
 		this.contaTipo = contaTipo;
	}
	public Empresa getEmpresa(){
 		return this.empresa;
	}
	public void setEmpresa(Empresa empresa){
 		this.empresa = empresa;
	}
	public Exercicio getExercicio(){
 		return this.exercicio;
	}
	public void setExercicio(Exercicio exercicio){
 		this.exercicio = exercicio;
	}
	public ContaOrigem getContaOrigem(){
 		return this.contaOrigem;
	}
	public void setContaOrigem(ContaOrigem contaOrigem){
 		this.contaOrigem = contaOrigem;
	}
	public PlanoContas getPlanoContas(){
 		return this.planoContas;
	}
	public void setPlanoContas(PlanoContas planoContas){
 		this.planoContas = planoContas;
	}
	@Override
	public String toString() {
		return 
		"Conta [id=" + id + 
		", nome=" + nome + 
		", estrutura=" + estrutura + 
		", descricao=" + descricao + 
		", contaTipo=" + (contaTipo!=null?contaTipo:"") + 
		", contaOrigem=" + (contaOrigem!=null?contaOrigem:"") + 
		", empresa=" + (empresa!=null?empresa:"") + 
		", exercicio=" + (exercicio!=null?exercicio:"") + 
		", planoContas=" + (planoContas!=null?planoContas:"") + "]";
	}
	public String getEstrutura() {
		return estrutura;
	}
	public void setEstrutura(String estrutura) {
		this.estrutura = estrutura;
	}
	public Integer getNivel() {
		//return nivel;
		return ContaUtil.retornarNivel(this);
	}
	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}
	
	
}