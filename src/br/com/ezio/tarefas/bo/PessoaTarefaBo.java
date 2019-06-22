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
import br.com.ezio.tarefas.bean.PessoaTarefaBean;
import br.com.ezio.tarefas.bean.TarefaBean;
import br.com.ezio.tarefas.dao.PessoaDao;
import br.com.ezio.tarefas.dao.PessoaTarefaDao;
import br.com.ezio.tarefas.dao.TarefaDao;

public class PessoaTarefaBo implements Logica {

	@Override
	public void executa(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String acao = request.getParameter("acao");

		if(acao == null) acao = "listar";

		if(acao.equals("listar")) {

			listaPessoaTarefa(request, response);

		} else if(acao.equals("formularioCadastro")) {

			formularioCadastroPessoaTarefa(request, response);

		} else if(acao.equals("gravar")) {

			gravarPessoaTarefa(request, response);

		} else if(acao.equals("formularioEditar")) {

			formularioEditarTarefa(request, response);

		} else if(acao.equals("editar")) {

			editarTarefa(request, response);

		} else if(acao.equals("excluir") || acao.equals("ativar")) {

			AlterarStatusTarefa(request, response);

		} 
	}

	//try catch ok
	private void gravarPessoaTarefa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HtmlBo bo = new HtmlBo();

		PessoaTarefaDao dao = new PessoaTarefaDao();
		PessoaTarefaBean progresso = new PessoaTarefaBean();

		progresso.setDataInicio( request.getParameter("dataInicio") );
		progresso.getPessoa().setId( Integer.parseInt( request.getParameter("idPessoa") ) );
		progresso.getTarefa().setId( Integer.parseInt( request.getParameter("idTarefa") ) );
		

		try {
			dao.insert(progresso);
			response.sendRedirect("index?logica=TarefaBo&acao=listar&alerta=Tarefa Cadastrada com Sucesso!");
		} catch (SQLException e) {
			out.print( bo.criarMensagemJavascript("Erro ao tentar gravar dados!") );
			e.printStackTrace();
		}
	}

	// try catch ok
	private void formularioCadastroPessoaTarefa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HtmlBo bo = new HtmlBo();

		PessoaDao pdao = new PessoaDao();
		TarefaDao tdao = new TarefaDao();

		try {

			List<PessoaBean> listaPessoas = pdao.find( pdao.where("PES_ATIVO", "=", "'S'") );
			List<TarefaBean> listaTarefas = tdao.find( tdao.where("TAR_ATIVO", "=", "'S'") );

			out.print(bo.getProgressForm(null, listaTarefas, listaPessoas));
		} catch(SQLException e) {
			response.sendRedirect("index?logica=PessoaTarefaBo&acao=listar&alerta=Erro ao recuperar listas");
			e.printStackTrace();
		} 
	}

	// try catch ok
	private void listaPessoaTarefa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		PessoaTarefaDao dao = new PessoaTarefaDao();
		HtmlBo bo = new HtmlBo();

		String alerta = request.getParameter("alerta");

		List<PessoaTarefaBean> lista = new ArrayList();

		try {
			lista = dao.find(null);
			out.print(bo.getProgressList(lista, alerta));
		} catch (SQLException e) {
			out.print(bo.getProgressList(lista, "Erro ao buscar dados!\nTente novamente."));
			e.printStackTrace();
		}
	}

}