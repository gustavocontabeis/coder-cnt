package br.com.cnt.model.entity.usuarios;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.cnt.model.entity.BaseEntity;

@XmlRootElement
@Entity @Table(name="USUARIO_PERFIL")
//@NamedQueries(value = {
//    @NamedQuery(name = "todosUsuarioPerfil", query = "select obj from UsuarioPerfil obj")
//})
public class UsuarioPerfil extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(generator="SEQ_USUARIO_PERFIL", strategy=GenerationType.SEQUENCE) 
    @SequenceGenerator(name="SEQ_USUARIO_PERFIL", sequenceName="SEQ_USUARIO_PERFIL", initialValue=100) 
    @Column(name = "ID_USUARIO_PERFIL")
    private Long id;

    //@NotNull 
    @ManyToOne(cascade={CascadeType.DETACH})
    @JoinColumn(name = "ID_USUARIO", nullable = false, foreignKey = @ForeignKey(name = "USUARIO_PERFIL_ACESSO_USUARIO_FK"))
    private Usuario usuario;

    //@NotNull 
    @ManyToOne(cascade={CascadeType.DETACH})
    @JoinColumn(name = "ID_PERFIL_ACESSO", nullable = false, foreignKey = @ForeignKey(name = "USUARIO_PERFIL_ACESSO_PERFIL_FK"))
    private PerfilAcesso perfil;
    
    
	//@NotEmpty 
	@Column(name="NOME_USUARIO", length=50, nullable=false)
	private String nomeUsuario;

	//@NotEmpty 
	@Column(name="NOME_PERFIL", length=50, nullable=false)
	private String nomePerfil;


    public UsuarioPerfil() {
        super();
    }

    public UsuarioPerfil(Long id, Usuario usuario, PerfilAcesso perfil) {
        super();
        this.id = id;
        this.usuario = usuario;
        this.perfil = perfil;
        this.nomeUsuario = usuario.getLogin();
        this.nomePerfil = perfil.getNome();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public PerfilAcesso getPerfil() {
        return this.perfil;
    }

    public void setPerfil(PerfilAcesso perfil) {
        this.perfil = perfil;
    }

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getNomePerfil() {
		return nomePerfil;
	}

	public void setNomePerfil(String nomePerfil) {
		this.nomePerfil = nomePerfil;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nomePerfil == null) ? 0 : nomePerfil.hashCode());
		result = prime * result + ((nomeUsuario == null) ? 0 : nomeUsuario.hashCode());
		result = prime * result + ((perfil == null) ? 0 : perfil.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		UsuarioPerfil other = (UsuarioPerfil) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nomePerfil == null) {
			if (other.nomePerfil != null)
				return false;
		} else if (!nomePerfil.equals(other.nomePerfil))
			return false;
		if (nomeUsuario == null) {
			if (other.nomeUsuario != null)
				return false;
		} else if (!nomeUsuario.equals(other.nomeUsuario))
			return false;
		if (perfil == null) {
			if (other.perfil != null)
				return false;
		} else if (!perfil.equals(other.perfil))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}

}
