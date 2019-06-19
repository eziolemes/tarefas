package br.com.ezio.tarefas.view;

public class InputHtml {
	
	private String label;
	private String id;
	private String classCss;
	private String type;
	private String name;
	private String value;
	private String maxlength;
	private String placeholder;
	private Boolean required;
	
	public String getId() {
		return id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getClassCss() {
		return classCss;
	}
	public void setClassCss(String classCss) {
		this.classCss = classCss;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getMaxlength() {
		return maxlength;
	}
	public void setMaxlength(String maxlength) {
		this.maxlength = maxlength;
	}
	public String getPlaceholder() {
		return placeholder;
	}
	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}
	public Boolean getRequired() {
		return required;
	}
	public void setRequired(Boolean required) {
		this.required = required;
	}
}