package br.com.ezio.tarefas.bo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Logica {

	public void executa(HttpServletRequest request, HttpServletResponse ressponse) throws Exception;
	
}