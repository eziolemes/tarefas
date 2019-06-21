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

		if(acao == null) acao = "listar";

		if(acao.equals("listar")) {

			listaPessoas(request, response);

		} else if(acao.equals("formularioCadastro")) {

			formularioCadastroPessoa(request, response);

		} else if(acao.equals("gravar")) {

			gravarPessoa(request, response);

		} else if(acao.equals("formularioEditar")) {

			formularioEditarPessoa(request, response);

		} else if(acao.equals("editar")) {
			
			editarPessoa(request, response);
			
		} else if(acao.equals("excluir")) {
			
			excluirPessoa(request, response);
			
		}

	}
	
	private void excluirPessoa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HtmlBo bo = new HtmlBo();
		
		PessoaDao dao = new PessoaDao();
		
		Integer id = Integer.parseInt( request.getParameter("id"));
		
	}
	
	private void editarPessoa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HtmlBo bo = new HtmlBo();

		PessoaDao dao = new PessoaDao();
		PessoaBean pessoa = new PessoaBean();

		try {
			Integer id = Integer.parseInt( request.getParameter("id"));
			
			pessoa.setId(id);
			pessoa.setNome(request.getParameter("nome"));

			dao.update(pessoa);
			response.sendRedirect("index?logica=PessoaBo&acao=listar&alerta=Pessoa Alterada com Sucesso!");
		} catch (SQLException e) {
			out.print( bo.criarMensagemJavascript("Erro ao tentar gravar dados!") );
			e.printStackTrace();
		} catch(NumberFormatException e) {
			out.print( bo.criarMensagemJavascript("Erro ao recuperar código da pessoa!") );
			e.printStackTrace();
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
			response.sendRedirect("index?logica=PessoaBo&acao=listar&alerta=Pessoa Cadastrada com Sucesso!");
		} catch (SQLException e) {
			out.print( bo.criarMensagemJavascript("Erro ao tentar gravar dados!") );
			e.printStackTrace();
		}
	}

	private void formularioEditarPessoa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		HtmlBo bo = new HtmlBo();
		PessoaDao dao = new PessoaDao();

		try {
			Integer id = Integer.parseInt( request.getParameter("id") );

			PessoaBean pessoa = dao.findById( id );
			out.print(bo.getPersonForm(pessoa));
		} catch (SQLException e) {
			response.sendRedirect("index?logica=PessoaBo&alerta=Erro ao abrir cadastro!");
			e.printStackTrace();
		} catch(NumberFormatException e) {
			out.print( bo.criarMensagemJavascript("Erro ao recuperar código da pessoa!") );
			e.printStackTrace();
		}
	}

	// try catch ok
	private void formularioCadastroPessoa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HtmlBo bo = new HtmlBo();

		PrintWriter out = response.getWriter();

		out.print(bo.getPersonForm(null));
	}

	// try catch ok
	private void listaPessoas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		PessoaDao dao = new PessoaDao();
		HtmlBo bo = new HtmlBo();

		String alerta = request.getParameter("alerta");

		List<PessoaBean> lista = new ArrayList();

		try {
			lista = dao.find(null);
			out.print(bo.getPersonList(lista, alerta));
		} catch (SQLException e) {
			out.print(bo.getPersonList(lista, "Erro ao buscar dados!\nTente novamente."));
			e.printStackTrace();
		}
	}



}
