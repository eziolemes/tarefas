package br.com.ezio.tarefas.bo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.ezio.tarefas.bean.TarefaBean;
import br.com.ezio.tarefas.dao.TarefaDao;

public class TarefaBo implements Logica{

	@Override
	public void executa(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String acao = request.getParameter("acao");

		if(acao == null) acao = "listar";

		if(acao.equals("listar")) {

			listaTarefas(request, response);

		} else if(acao.equals("formularioCadastro")) {

			formularioCadastroTarefa(request, response);

		} else if(acao.equals("gravar")) {

			gravarTarefa(request, response);

		} else if(acao.equals("formularioEditar")) {

			formularioEditarTarefa(request, response);

		} else if(acao.equals("editar")) {

			editarTarefa(request, response);

		} else if(acao.equals("excluir") || acao.equals("ativar")) {

			AlterarStatusTarefa(request, response);

		} 
	}
	
	// try catch ok
		private void AlterarStatusTarefa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			PrintWriter out = response.getWriter();
			HtmlBo bo = new HtmlBo();

			TarefaDao dao = new TarefaDao();
			TarefaBean tarefa = new TarefaBean();

			try {
				Integer id = Integer.parseInt( request.getParameter("id"));
				String acao = request.getParameter("acao");

				tarefa.setId(id);
				tarefa.setAtivo( (acao.equals("excluir") ? false : true) );
				dao.updateStatus(tarefa);
				response.sendRedirect("index?logica=TarefaBo&acao=listar&alerta=Tarefa Alterada com Sucesso!");
			} catch(SQLException e) {
				out.print( bo.criarMensagemJavascript("Erro ao tentar atualizar dados!") );
				e.printStackTrace();
			} catch(NumberFormatException e) {
				out.print( bo.criarMensagemJavascript("Erro ao recuperar código da tarefa!") );
				e.printStackTrace();
			}

		}

	// try catch ok
	private void editarTarefa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HtmlBo bo = new HtmlBo();

		TarefaDao dao = new TarefaDao();
		TarefaBean tarefa = new TarefaBean();

		try {
			Integer id = Integer.parseInt( request.getParameter("id"));
			String ativo = request.getParameter("ativo");

			if(ativo == null) ativo = "N";

			tarefa.setId(id);
			tarefa.setDescricao(request.getParameter("descricao"));
			tarefa.setAtivo( (ativo.equals("S") ? true : false) );

			dao.update(tarefa);
			response.sendRedirect("index?logica=TarefaBo&acao=listar&alerta=Tarefa Alterada com Sucesso!");
		} catch (SQLException e) {
			out.print( bo.criarMensagemJavascript("Erro ao tentar atualizar dados!") );
			e.printStackTrace();
		} catch(NumberFormatException e) {
			out.print( bo.criarMensagemJavascript("Erro ao recuperar código da tarefa!") );
			e.printStackTrace();
		}
	}

	//try catch ok
	private void formularioEditarTarefa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		HtmlBo bo = new HtmlBo();
		TarefaDao dao = new TarefaDao();

		try {
			Integer id = Integer.parseInt( request.getParameter("id") );

			TarefaBean tarefa = dao.findById( id );
			out.print(bo.getScheduleForm(tarefa));
		} catch (SQLException e) {
			response.sendRedirect("index?logica=TarefaBo&alerta=Erro ao abrir cadastro!");
			e.printStackTrace();
		} catch(NumberFormatException e) {
			out.print( bo.criarMensagemJavascript("Erro ao recuperar código da tarefa!") );
			e.printStackTrace();
		}
	}

	//try catch ok
	private void gravarTarefa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HtmlBo bo = new HtmlBo();

		TarefaDao dao = new TarefaDao();
		TarefaBean tarefa = new TarefaBean();

		tarefa.setDescricao(request.getParameter("descricao"));

		try {
			dao.insert(tarefa);
			response.sendRedirect("index?logica=TarefaBo&acao=listar&alerta=Tarefa Cadastrada com Sucesso!");
		} catch (SQLException e) {
			out.print( bo.criarMensagemJavascript("Erro ao tentar gravar dados!") );
			e.printStackTrace();
		}
	}

	// try catch ok
	private void formularioCadastroTarefa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HtmlBo bo = new HtmlBo();

		PrintWriter out = response.getWriter();

		out.print(bo.getScheduleForm(null));
	}

	// try catch ok
	private void listaTarefas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		TarefaDao dao = new TarefaDao();
		HtmlBo bo = new HtmlBo();

		String alerta = request.getParameter("alerta");

		List<TarefaBean> lista = new ArrayList();

		try {
			lista = dao.find(null);
			out.print(bo.getScheduleList(lista, alerta));
		} catch (SQLException e) {
			out.print(bo.getScheduleList(lista, "Erro ao buscar dados!\nTente novamente."));
			e.printStackTrace();
		}
	}

}