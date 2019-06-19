package br.com.ezio.tarefas.bo;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ezio.tarefas.dao.PessoaTarefaDao;

public class PessoaTarefaBo implements Logica {

	@Override
	public void executa(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		PessoaTarefaDao dao = new PessoaTarefaDao();
		
		List<PessoaTarefaBean> lista = dao.find(  )
		
	}
	
	
	
	

}