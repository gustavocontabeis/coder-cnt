package br.com.cnt.web.jsf.managedbeans;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.beanutils.BeanPropertyValueEqualsPredicate;
import org.apache.commons.collections.CollectionUtils;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.cnt.model.dao.BaseDAOSerializable;
import br.com.cnt.model.dao.DaoException;
import br.com.cnt.model.dao.usuarios.PerfilAcessoDAO;
import br.com.cnt.model.dao.usuarios.UsuarioDAO;
import br.com.cnt.model.entity.usuarios.PerfilAcesso;
import br.com.cnt.model.entity.usuarios.Usuario;
import br.com.cnt.model.entity.usuarios.UsuarioPerfil;
import br.com.cnt.model.utils.Filtro;

@ManagedBean
@ViewScoped
public class UsuarioManagedBean extends BaseManagedBean {

	/**
	 */
	private static final long serialVersionUID = 1L;

	private Usuario usuario;
	private UsuarioDAO dao;
	protected LazyDataModel<Usuario> model;
	protected List<PerfilAcesso> perfis;
	private List<String> idsPerfisSelecionados;
	private List<UsuarioPerfil> usuariosPerfil;
	private List<Long> usuariosPerfilSelx;
	private String confirmeSenha;
	public List<Long> getUsuariosPerfilSelx() {
		return usuariosPerfilSelx;
	}

	public void setUsuariosPerfilSelx(List<Long> usuariosPerfilSelx) {
		this.usuariosPerfilSelx = usuariosPerfilSelx;
	}

	protected Filtro filtro;
	private PerfilAcessoDAO perfilDAO;
	
	@PostConstruct
	private void init() {
		dao = new UsuarioDAO();
		perfilDAO = new PerfilAcessoDAO();
		novo(null);
		perfis = carregarPerfis();
		usuariosPerfil = gerarUsuarioPerfis(perfis); 
		loadLazyModel();
	}

	private List<UsuarioPerfil> gerarUsuarioPerfis(List<PerfilAcesso> perfis) {
		List<UsuarioPerfil> list = new ArrayList<>();
		for (PerfilAcesso perfil : perfis) {
			list.add(new UsuarioPerfil((long)list.size(), usuario, perfil));
		}
		return list;
	}

	private void loadLazyModel() {
		model = new LazyDataModel<Usuario>() {
			private static final long serialVersionUID = 1L;
			@Override
			public List<Usuario> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
				filtro = new Filtro(Usuario.class, first, pageSize, sortField, sortOrder, filters);
				setRowCount(dao.getQuantidade(filtro));
				return dao.buscar(filtro);
			}
		    public Usuario getRowData(String rowKey) {
		    	Usuario obj = new Usuario();
		    	obj.setId(new Long(rowKey));
		    	 try {
					obj = dao.buscarComPerfis(new Long(rowKey));
					selecionarPerfis(obj);
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
		    	return obj;
		    }
			public Object getRowKey(Usuario object) {
		    	return String.valueOf(object.getId());
		    }
		};
	}
	
    private void selecionarPerfis(Usuario obj) {
		List<PerfilAcesso> usuarioPerfisSelecionado = new ArrayList<>();
		idsPerfisSelecionados = new ArrayList<>();
		List<PerfilAcesso> carregarPerfis = carregarPerfis();
		for(PerfilAcesso perfil : carregarPerfis){
			PerfilAcesso usuarioPerfil = null;
			List<UsuarioPerfil> usuarioPerfis = obj.getUsuarioPerfis();
			for (UsuarioPerfil usuarioPerfilItem : usuarioPerfis) {
				if(usuarioPerfilItem.getPerfil().getId().equals(perfil.getId())){
					usuarioPerfil = usuarioPerfilItem.getPerfil();
					idsPerfisSelecionados.add(usuarioPerfil.getId().toString());
				}
			}
			if(usuarioPerfil == null){
				//usuarioPerfil = new Perfil(null, obj, perfil);
			}
			usuarioPerfisSelecionado.add(usuarioPerfil);
		}
		//usuariosPerfilSel = usuarioPerfisSelecionado;
	}
	private List<PerfilAcesso> carregarPerfis() {
		return perfilDAO.buscarTodos();
	}
	
	public void novo(ActionEvent evt) {
		usuario = new Usuario();
		usuario.setInativo(false);
		usuario.setUsuarioPerfis(new ArrayList<UsuarioPerfil>());
	}

	public void salvar(ActionEvent evt) throws DaoException {
		if(usuario.getId() == null){
			if(!usuario.getSenha().equals(confirmeSenha)){
				messageError(null, "As senhas não conferem.");
				return;
			}
		}
		
		if(usuario.getId()==null){
			usuario.setDtSenha(new Date());
		}
		
		try {
			List<String>perfisAdd=new ArrayList<>(); 
			List<String>perfisRemove=new ArrayList<>(); 
			for(String idString : idsPerfisSelecionados){
				Long id = new Long(idString);
				BeanPropertyValueEqualsPredicate find = new BeanPropertyValueEqualsPredicate("perfil.id", id);
				UsuarioPerfil up2 = (UsuarioPerfil) CollectionUtils.find(usuariosPerfil, find);
				UsuarioPerfil up3 = (UsuarioPerfil) CollectionUtils.find(usuario.getUsuarioPerfis(), find);
				
				if(up3 == null){
					//adiciona
					PerfilAcesso perfil = new PerfilAcesso();
					perfil = (PerfilAcesso) dao.buscar(perfil, id);
					UsuarioPerfil usuarioPerfil = new UsuarioPerfil(null, usuario, perfil);
					
					usuario.getUsuarioPerfis().add(usuarioPerfil);
					perfisAdd.add(usuarioPerfil.getNomePerfil());
				}
			}
			
			usuario.setPerfis(perfisAdd.toArray(new String[perfisAdd.size()]).toString());
			//Remover os que não foram selecionandos
			this.dao.salvar(usuario);
			
			BaseDAOSerializable<UsuarioPerfil> dao = new BaseDAOSerializable<>();
			Iterator<UsuarioPerfil> iterator = usuario.getUsuarioPerfis().iterator();
			while (iterator.hasNext()) {
				UsuarioPerfil usuarioPerfil = (UsuarioPerfil) iterator.next();
				boolean contem = false;
				for(String idString : idsPerfisSelecionados){
					if(new Long(idString).equals(usuarioPerfil.getPerfil().getId())){
						contem = true;
					}
				}
				if(!contem){
					dao.excluir(usuarioPerfil);
					iterator.remove();
				}
			}
			
			message(null, "Registro salvo com sucesso.");
		} catch (Exception e) {
			message(e);
		}
	}


	public void excluir(ActionEvent evt) throws DaoException {
		try {
			dao.excluir(usuario);
			novo(null);
			message(null, "Registro excluído com sucesso.");
		} catch (Exception e) {
			message(e);
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public LazyDataModel<Usuario> getModel() {
		return model;
	}

	public void setModel(LazyDataModel<Usuario> model) {
		this.model = model;
	}
	
	public List<PerfilAcesso> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<PerfilAcesso> perfis) {
		this.perfis = perfis;
	}

	public List<UsuarioPerfil> getUsuariosPerfil() {
		return usuariosPerfil;
	}

	public void setUsuariosPerfil(List<UsuarioPerfil> usuariosPerfil) {
		this.usuariosPerfil = usuariosPerfil;
	}

	public List<String> getSel() {
		return idsPerfisSelecionados;
	}

	public void setSel(List<String> obj) {
		this.idsPerfisSelecionados = obj;
	}

	public String getConfirmeSenha() {
		return confirmeSenha;
	}

	public void setConfirmeSenha(String confirmeSenha) {
		this.confirmeSenha = confirmeSenha;
	}
	
}