package br.com.ezio.tarefas.view;

public class SelectHtml {
	
	private String id;
	private String name;
	private String classCss;
	private Boolean disable;
	private Boolean required;
	
	// pagina carrega com o objeto selecionado
	private Boolean autoFocus;
	
	// define quantos componentes pode ficar visíveis
	private Integer size;
	
	// permite selecionar vários options
	private Boolean multiple;
	
	//pode atribuir a um formulário mesmo estando fora dele.
	private String form;
	
	private OptionHtml[] opcoes;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getDisable() {
		return disable;
	}

	public void setDisable(Boolean disable) {
		this.disable = disable;
	}

	public Boolean getRequired() {
		return required;
	}

	public void setRequired(Boolean required) {
		this.required = required;
	}

	public Boolean getAutoFocus() {
		return autoFocus;
	}

	public void setAutoFocus(Boolean autoFocus) {
		this.autoFocus = autoFocus;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Boolean getMultiple() {
		return multiple;
	}

	public void setMultiple(Boolean multiple) {
		this.multiple = multiple;
	}

	public String getForm() {
		return form;
	}

	public void setForm(String form) {
		this.form = form;
	}

	public String getClassCss() {
		return classCss;
	}

	public void setClassCss(String classCss) {
		this.classCss = classCss;
	}

	public OptionHtml[] getOpcoes() {
		return opcoes;
	}

	public void setOpcoes(OptionHtml[] opcoes) {
		this.opcoes = opcoes;
	}
	
}