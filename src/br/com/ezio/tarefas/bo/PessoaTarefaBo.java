package br.com.ezio.tarefas.bo;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ezio.tarefas.bean.PessoaBean;
import br.com.ezio.tarefas.dao.PessoaDao;

public class PessoaTarefaBo implements Logica {

	@Override
	public void executa(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		PessoaDao dao = new PessoaDao();
		List<PessoaBean> lista = new ArrayList();
		
		lista = dao.findAll("PESSOA_HAS_TAREFAS");
		
		HtmlBo bo = new HtmlBo();
		
		bo.getHomePage(lista);
	}

}