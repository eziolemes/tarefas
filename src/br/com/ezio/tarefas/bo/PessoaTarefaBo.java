package br.com.ezio.tarefas.bo;


import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ezio.tarefas.bean.PessoaTarefaBean;
import br.com.ezio.tarefas.dao.PessoaTarefaDao;

public class PessoaTarefaBo implements Logica {

	@Override
	public void executa(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		PessoaTarefaDao dao = new PessoaTarefaDao();
		HtmlBo bo = new HtmlBo();
		
		List<PessoaTarefaBean> lista = dao.find(null);
		
		PrintWriter out = response.getWriter();
		
		out.print(bo.getHomePageHtml(lista));
	}
	
	
	
	

}