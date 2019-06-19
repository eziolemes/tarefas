package br.com.ezio.tarefas.bo;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ezio.tarefas.bean.PessoaBean;
import br.com.ezio.tarefas.bean.TarefaBean;
import br.com.ezio.tarefas.dao.PessoaDao;
import br.com.ezio.tarefas.dao.TarefaDao;

public class PrimeiraLogica implements Logica{

	@Override
	public void executa(HttpServletRequest req, HttpServletResponse res) throws Exception {
		TarefaBean t1 = new TarefaBean();
		PessoaBean p1 = new PessoaBean();
		
		TarefaDao tdao = new TarefaDao();
		PessoaDao pdao = new PessoaDao();
		
		t1 = tdao.findById(TarefaBean.class, 3);
		p1 = pdao.findById(PessoaBean.class, 1);
		
		List<TarefaBean> tarefas = new ArrayList();
		tarefas.add(t1);
		p1.setTarefas(tarefas);
		
		pdao.save(p1);
	}

	
}