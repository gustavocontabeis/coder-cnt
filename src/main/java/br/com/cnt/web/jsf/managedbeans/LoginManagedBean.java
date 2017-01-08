package br.com.cnt.web.jsf.managedbeans;

import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.cnt.model.dao.balanco.EmpresaDAO;
import br.com.cnt.model.dao.balanco.ExercicioDAO;
import br.com.cnt.model.dao.usuarios.UsuarioDAO;
import br.com.cnt.model.entity.balanco.Empresa;
import br.com.cnt.model.entity.balanco.Exercicio;
import br.com.cnt.model.entity.usuarios.Usuario;
import br.com.tche.geradorcodigo.util.StringUtil;
@Named @javax.enterprise.context.SessionScoped
//@ManagedBean @SessionScoped
public class LoginManagedBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginManagedBean.class.getSimpleName());

	private String username;
	private String password;
	private Usuario usuario;
	private Empresa empresa;
	private Exercicio exercicio;
	private Date de, ate;
	private String periodo;
	
	private List<Empresa> empresas;
	private List<Exercicio> exercicios;
	@Inject 
	private EmpresaDAO empresaDAO;
	@Inject 
	private ExercicioDAO exercicioDAO;
	@Inject 
	private UsuarioDAO usuarioDAO;
	
	@PostConstruct
	private void init(){
		if(Boolean.TRUE){
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
			HttpSession session = request.getSession();
			
			if(session.getAttribute("usuario")==null){
				Usuario usuario = usuarioDAO.buscarComPerfis("gustavo");
				session.setAttribute("usuario", usuario);
				this.usuario = usuario;
			}
			empresas = getPopularComboEmpresa();
			exercicios = getPopularComboExercicio();
			
			
//			PessoaDAO pessoaDAO = new PessoaDAO();
//			if(usuario.getPessoa()!=null && usuario.getPessoa().getId()!=null){
//				pessoapessoaDAO.buscar(this.usuario.getPessoa().getId())
//				
//			}
		}
	}

	public List<Exercicio> getPopularComboExercicio() {
		if(empresa != null){
			exercicios = exercicioDAO.buscarExercicio(empresa);
		}else{
			exercicio = null;
			de = null;
			ate = null;
		}
		periodo = null;
		return exercicios;
	}

	public List<Empresa> getPopularComboEmpresa() {
		return empresaDAO.buscarTodos();
	}
	
	public void selecionarAno() throws IOException {
		this.de = new GregorianCalendar(exercicio.getAno(), Calendar.JANUARY, 1).getTime();
		this.ate = new GregorianCalendar(exercicio.getAno(), Calendar.DECEMBER, 31).getTime();
		periodo = null; 
	}

	public void selecionarPeriodo() throws IOException {
		if(!StringUtil.isBlank(periodo)){
			int valueOf = Integer.valueOf(periodo).intValue();
			switch (valueOf) {
			case 1:
				this.de = new GregorianCalendar(exercicio.getAno(), Calendar.JANUARY, 1).getTime();
				this.ate = new GregorianCalendar(exercicio.getAno(), Calendar.JUNE, 30).getTime();
				break;
			case 2:
				this.de = new GregorianCalendar(exercicio.getAno(), Calendar.JUNE, 1).getTime();
				this.ate = new GregorianCalendar(exercicio.getAno(), Calendar.DECEMBER, 31).getTime();
				break;
			case 3://Trimestre
				this.de = new GregorianCalendar(exercicio.getAno(), Calendar.JANUARY, 1).getTime();
				this.ate = new GregorianCalendar(exercicio.getAno(), Calendar.MARCH, 31).getTime();
				break;
			case 4:
				this.de = new GregorianCalendar(exercicio.getAno(), Calendar.APRIL, 1).getTime();
				this.ate = new GregorianCalendar(exercicio.getAno(), Calendar.JUNE, 30).getTime();
				break;
			case 5:
				this.de = new GregorianCalendar(exercicio.getAno(), Calendar.JULY, 1).getTime();
				this.ate = new GregorianCalendar(exercicio.getAno(), Calendar.SEPTEMBER, 30).getTime();
				break;
			case 6:
				this.de = new GregorianCalendar(exercicio.getAno(), Calendar.OCTOBER, 1).getTime();
				this.ate = new GregorianCalendar(exercicio.getAno(), Calendar.DECEMBER, 31).getTime();
				break;
			case 7://Bimestre
				this.de = new GregorianCalendar(exercicio.getAno(), Calendar.JANUARY, 1).getTime();
				Calendar cal = new GregorianCalendar(exercicio.getAno(), Calendar.MARCH, 1);
				cal.add(Calendar.DAY_OF_MONTH, -1);
				this.ate = cal.getTime(); 
				break;
			case 8:
				this.de = new GregorianCalendar(exercicio.getAno(), Calendar.MARCH, 1).getTime();
				this.ate = new GregorianCalendar(exercicio.getAno(), Calendar.APRIL, 30).getTime();
				break;
			case 9:
				this.de = new GregorianCalendar(exercicio.getAno(), Calendar.MAY, 1).getTime();
				this.ate = new GregorianCalendar(exercicio.getAno(), Calendar.JUNE, 30).getTime();
				break;
			case 10:
				this.de = new GregorianCalendar(exercicio.getAno(), Calendar.JULY, 1).getTime();
				this.ate = new GregorianCalendar(exercicio.getAno(), Calendar.AUGUST, 31).getTime();
				break;
			case 11:
				this.de = new GregorianCalendar(exercicio.getAno(), Calendar.SEPTEMBER, 1).getTime();
				this.ate = new GregorianCalendar(exercicio.getAno(), Calendar.OCTOBER, 31).getTime();
				break;
			case 12:
				this.de = new GregorianCalendar(exercicio.getAno(), Calendar.NOVEMBER, 1).getTime();
				this.ate = new GregorianCalendar(exercicio.getAno(), Calendar.DECEMBER, 31).getTime();
				break;
			}
		}
	}

	public void login(ActionEvent event) throws IOException {

		Usuario usuario = usuarioDAO.buscarComPerfis(this.username);
		
		if(usuario == null){
			LOGGER.debug("Usuario {} não confere.", username);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login inválido", "Login inválido"));
			redirecionarLogin();
		}
		
		if (this.password.equals(usuario.getSenha())) {
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			
			HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
			HttpSession session = request.getSession();
			session.setAttribute("usuario", usuario);
			// request.login(username, password);
			
			this.usuario = usuario;
			
			if(session.getAttribute("destino") != null){
				HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
				String destino = (String) session.getAttribute("destino");
				LOGGER.debug("Redirecionando para : "+ destino);
				response.sendRedirect(destino);
				session.removeAttribute("destino");
			}
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bem vindo", username));
		}else{
			LOGGER.debug("Usuario {} e senha {} não conferem.", username, password);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login inválido", "Login inválido"));
			redirecionarLogin();
		}

	}
	
	private void redirecionarLogin() throws IOException {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
		HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
		response.sendRedirect(request.getContextPath() + "/pages/login/login.jsf");
	}

	public void logout(ActionEvent event) throws IOException {
		LOGGER.debug("..>> Logout <<..");
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
		request.getSession().invalidate();
		HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
		response.sendRedirect(request.getContextPath() + "/pages/login/login.jsf");
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isLogado(){
		return this.usuario != null;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public List<Exercicio> getExercicios() {
		return exercicios;
	}

	public void setExercicios(List<Exercicio> exercicios) {
		this.exercicios = exercicios;
	}

	public Exercicio getExercicio() {
		return exercicio;
	}

	public void setExercicio(Exercicio exercicio) {
		this.exercicio = exercicio;
	}

	public Date getDe() {
		return de;
	}

	public void setDe(Date de) {
		this.de = de;
	}

	public Date getAte() {
		return ate;
	}

	public void setAte(Date ate) {
		this.ate = ate;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	
	

}