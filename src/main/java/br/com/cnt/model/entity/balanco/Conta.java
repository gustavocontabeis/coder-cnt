/* ::::::::::::::::::::::::::::::::::::::::::: */
package br.com.cnt.model.entity.balanco;

import static javax.persistence.FetchType.LAZY;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.cnt.model.entity.BaseEntity;
import br.com.cnt.model.utils.ContaUtil;


@Entity
@Table(name="CONTAS", 
	indexes={
		@Index(name="INDEX_CONTA_NOME", columnList = "NOME"),
		@Index(name="INDEX_CONTA_ESTRUTURA", columnList = "ESTRUTURA"),
	})
@NamedQueries(value={
		@NamedQuery(name="Conta-buscar", 
				query="select obj from Conta obj "
						+ "left join fetch obj.empresa e "
						+ "left join fetch obj.planoContas pc "
						+ "where obj.id = :id")})
public class Conta extends BaseEntity implements Comparable<Conta>{
	
	private static final long serialVersionUID = 1L;
 	
 	@Id 
 	@GeneratedValue(generator="SEQ_CONTA", strategy=GenerationType.SEQUENCE) 
	@SequenceGenerator(name="SEQ_CONTA", sequenceName="SEQ_CONTA", initialValue=1000) 
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
 	
 	@ManyToOne(targetEntity=Empresa.class, fetch=LAZY, cascade={CascadeType.DETACH}) 
 	@JoinColumn(name="ID_EMPRESA", nullable=true, foreignKey = @ForeignKey(name="FK_CONTA_EMPRESA"))
 	private Empresa empresa;
 
 	@ManyToOne(targetEntity=PlanoContas.class, fetch=FetchType.LAZY, cascade={CascadeType.DETACH}) 
 	@JoinColumn(name="ID_PLANO_CONTAS", nullable=true, foreignKey = @ForeignKey(name="FK_CONTA_PLANO_CONTAS"))
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

	@Override
	public int compareTo(Conta conta) {
		return ContaUtil.compararNivel(this, conta);
	}
	
	public boolean isPai(Conta conta){
		return ContaUtil.isPai(this, conta);
	}
	
	public boolean isFilho(Conta conta){
		return ContaUtil.isFilho(this, conta);
	}
	
}