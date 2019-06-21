package br.com.ezio.tarefas.bo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ezio.tarefas.bean.PessoaBean;
import br.com.ezio.tarefas.dao.PessoaDao;

public class PessoaBo implements Logica{

	@Override
	public void executa(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String acao = request.getParameter("acao");

		if(acao == null) acao = "listarPessoas";

		if(acao.equals("listarPessoas")) {

			listaPessoas(request, response);

		} else if(acao.equals("formularioCadastroPessoa")) {

			formularioCadastroPessoa(request, response);

		} else if(acao.equals("gravarPessoa")) {
			
			gravarPessoa(request, response);
			
		}


	}
	
	private void gravarPessoa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HtmlBo bo = new HtmlBo();
		
		PessoaDao dao = new PessoaDao();
		PessoaBean pessoa = new PessoaBean();
		
		pessoa.setNome(request.getParameter("nome"));
		
		try {
			dao.insert(pessoa);
			response.sendRedirect("index?logica=PessoaBo&alerta=Pessoa Cadastrada com Sucesso!");
		} catch (SQLException e) {
			out.print( bo.criarMensagemJavascript("Erro ao tentar gravar dados!") );
			e.printStackTrace();
		}
		
		
	}

	private void formularioCadastroPessoa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HtmlBo bo = new HtmlBo();

		PrintWriter out = response.getWriter();

		out.print(bo.getPersonForm());
	}

	private void listaPessoas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		PessoaDao dao = new PessoaDao();
		HtmlBo bo = new HtmlBo();
		
		String alerta = request.getParameter("alerta");

		List<PessoaBean> lista = new ArrayList();

		try {
			lista = dao.find(null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		out.print(bo.getPersonList(lista, alerta));
	}



}
