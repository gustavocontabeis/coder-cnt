package br.com.cnt.web.jsf.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;

@Named 
@SessionScoped
public class ConfigManagedBean extends BaseManagedBean {

	private static final long serialVersionUID = 1L;

	private String fontSize = "11px";
	private String template;
	
	private List<String[]> menu = new ArrayList<>();
	
	@PostConstruct
	private void init(){
		menu.add(new String[]{});
		menu.add(new String[]{});
		menu.add(new String[]{});
		menu.add(new String[]{});
		menu.add(new String[]{});
		//template = "desktop";
		template = "layout-2";
		//template = "layout-classic";
	}

	public String getFontSize() {
		return fontSize;
	}

	public void setFontSize(String fontSize) {
		this.fontSize = fontSize;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public List<String[]> getMenu() {
		return menu;
	}

	
}
