package br.com.ezio.tarefas.view;

import java.util.List;

public class DivFormGroupRowHtml {
	
	private String classCss;
	private List<InputHtml> InputArray;
	
	public DivFormGroupRowHtml(String classCss) {
		this.classCss = classCss;
	}

	public List<InputHtml> getInputArray() {
		return InputArray;
	}

	public void setInputArray(List<InputHtml> inputArray) {
		InputArray = inputArray;
	}

	public String getClassCss() {
		return classCss;
	}

}