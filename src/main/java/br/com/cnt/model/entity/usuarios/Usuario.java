//Adicionar no hibernate.cfg.xml <mapping class="br.com.imob.model.entity.Usuario"/>
//Ajuste os tamanhos dos campos.
package br.com.cnt.model.entity.usuarios;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotEmpty;

@XmlRootElement
@Entity @Table(name="usuario")
@NamedQueries(value={
		@NamedQuery(name="todosUsuario", query="from Usuario"),
		@NamedQuery(name="Usuario.getUsuarioComPerfis", query=
		"select obj "
		+ "from Usuario obj "
		+ "left join fetch obj.usuarioPerfis up "
		+ "left join fetch up.perfil p "
		+ "where obj.id=:id"),
		@NamedQuery(name="Usuario.getUsuarioComPerfisPorLogin", query=
		"select obj "
		+ "from Usuario obj "
		+ "left join fetch obj.usuarioPerfis up "
		+ "left join fetch up.perfil p "
		+ "where obj.login=:login")
})
public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id 
	//@GeneratedValue(strategy=GenerationType.AUTO)
	@GeneratedValue(generator="seq_usuario", strategy=GenerationType.SEQUENCE) 
	@SequenceGenerator(name="seq_usuario", sequenceName="seq_usuario", initialValue=100) 
	@Column(name="id_usuario") 
	private Long id;

	@Column(name="inativo", length=1, nullable=false)
	private Boolean inativo;

	@NotEmpty 
	@Column(name="login", length=50, nullable=false)
	private String login;

	@NotEmpty 
	@Column(name="senha", length=50, nullable=false)
	private String senha;

	@NotNull 
	@Temporal(TemporalType.DATE) 
	@Column(nullable=false, name="dt_senha")
	private Date dtSenha;

	@Temporal(TemporalType.DATE) 
	@Column(name="dt_ultimo_acesso", nullable=true)
	private Date dtUltimoAcesso;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	@JoinTable(
//			name = "usuario_perfil", 
//			joinColumns = { @JoinColumn(name = "id_usuario", nullable = false, updatable = false, foreignKey=@ForeignKey(name="usuario_perfi_usuario_fk") ) },
//			inverseJoinColumns = { @JoinColumn(name = "id_perfil", nullable = false, updatable = false, foreignKey=@ForeignKey(name="usuario_perfi_perfil_fk") ) }
//	)
	private List<UsuarioPerfil> usuarioPerfis;
	
	private String perfis;

	public Long getId(){
		return this.id;
	}
	public void setId(Long id){
		this.id = id;
	}
	public Boolean getInativo(){
		return this.inativo;
	}
	public void setInativo(Boolean inativo){
		this.inativo = inativo;
	}
	public String getLogin(){
		return this.login;
	}
	public void setLogin(String login){
		this.login = login;
	}
	public String getSenha(){
		return this.senha;
	}
	public void setSenha(String senha){
		this.senha = senha;
	}
	public Date getDtSenha(){
		return this.dtSenha;
	}
	public void setDtSenha(Date dtSenha){
		this.dtSenha = dtSenha;
	}
	public Date getDtUltimoAcesso(){
		return this.dtUltimoAcesso;
	}
	public void setDtUltimoAcesso(Date dtUltimoAcesso){
		this.dtUltimoAcesso = dtUltimoAcesso;
	}
	public List<UsuarioPerfil> getUsuarioPerfis() {
		return usuarioPerfis;
	}
	public void setUsuarioPerfis(List<UsuarioPerfil> usuarioPerfis) {
		this.usuarioPerfis = usuarioPerfis;
	}
	public String getPerfis() {
		return perfis;
	}
	public void setPerfis(String perfis) {
		this.perfis = perfis;
	}
	public String[] getPerfisArray() {
		return StringUtils.isNotBlank(perfis)? perfis.split(",") : new String[0];
	}
	public boolean isContemPerfil(String[] perfis) {
    	if(this.perfis!=null && perfis != null){
    		for (String perfil : getPerfisArray()) {
    			for (String perfil2 : perfis) {
    				if(perfil.toUpperCase().equals(perfil2.toUpperCase())){
    					return true;
    				}
    			}
    		}
    	}
        return false;
    }
	
}

