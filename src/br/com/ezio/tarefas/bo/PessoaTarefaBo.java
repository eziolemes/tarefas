package br.com.ezio.tarefas.bo;


import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
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
import br.com.ezio.workcontrol.utils.Calcular;
import br.com.ezio.workcontrol.utils.ManipularData;

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

			formularioEditarPessoaTarefa(request, response);

		} else if(acao.equals("editar")) {

			editarPessoaTarefa(request, response);

		} else if(acao.equals("excluir") || acao.equals("ativar")) {

			AlterarStatusPessoaTarefa(request, response);

		} 
	}

	// try catch ok
	private void AlterarStatusPessoaTarefa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HtmlBo bo = new HtmlBo();

		PessoaTarefaDao dao = new PessoaTarefaDao();
		PessoaTarefaBean progresso = new PessoaTarefaBean();

		try {
			Integer id = Integer.parseInt( request.getParameter("id"));
			String acao = request.getParameter("acao");

			progresso.setId(id);
			progresso.setAtivo( (acao.equals("excluir") ? false : true) );
			dao.updateStatus(progresso);
			response.sendRedirect("index?logica=PessoaTarefaBo&acao=listar&alerta=Progresso Alterado com Sucesso!");
		} catch(SQLException e) {
			out.print( bo.criarMensagemJavascript("Erro ao tentar atualizar dados!") );
			e.printStackTrace();
		} catch(NumberFormatException e) {
			out.print( bo.criarMensagemJavascript("Erro ao recuperar código do progresso!") );
			e.printStackTrace();
		}

	}

	// try catch ok
	private void editarPessoaTarefa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HtmlBo bo = new HtmlBo();

		PessoaTarefaDao dao = new PessoaTarefaDao();
		PessoaTarefaBean progresso = new PessoaTarefaBean();

		try {
			Integer id = Integer.parseInt( request.getParameter("id"));
			Integer idTarefa = Integer.parseInt( request.getParameter("idTarefa"));
			Integer idPessoa = Integer.parseInt( request.getParameter("idPessoa"));
			BigDecimal percentual = new BigDecimal( request.getParameter("percentual").replace(',', '.') );
			String dataInicio = request.getParameter("dataInicio");
			String dataFim = request.getParameter("dataFinal");
			String finalizado = request.getParameter("finalizado");
			String ativo = request.getParameter("ativo");

			if(ativo == null) ativo = "N";
			if(finalizado == null) finalizado = "N";

			PessoaBean pessoa = new PessoaBean();
			TarefaBean tarefa = new TarefaBean(); 
			
			pessoa.setId(idPessoa);
			tarefa.setId(idTarefa);
			
			/*
			Boolean percValido = Calcular.validarIntervalo(percentual);
			
			if(percValido) {
				progresso.setPercentual(percentual);
			} else {
				out.print( bo.criarMensagemJavascript("Percentual é inválido!"));
			}
			*/
			progresso.setId(id);
			progresso.setTarefa(tarefa);
			progresso.setPessoa(pessoa);
			progresso.setPercentual(percentual);
			progresso.setDataInicio( ManipularData.converterData(dataInicio, ManipularData.US) );
			progresso.setDataFim( (!dataFim.isEmpty() ? (ManipularData.converterData(dataFim, ManipularData.US)) : null) );
			progresso.setFinalizado( (finalizado.equals("S") ? true : false) );
			progresso.setAtivo( (ativo.equals("S") ? true : false) );

			dao.update(progresso);
			response.sendRedirect("index?logica=PessoaTarefaBo&acao=listar&alerta=Progresso Alterado com Sucesso!");
		} catch (SQLException e) {
			out.print( bo.criarMensagemJavascript("Erro ao tentar atualizar dados!") );
			e.printStackTrace();
		} catch(NumberFormatException e) {
			out.print( bo.criarMensagemJavascript("Percentual informado é inválido!") );
			e.printStackTrace();
		} catch (NullPointerException e) {
			out.print( bo.criarMensagemJavascript("Informe data!") );
			e.printStackTrace();
		} catch (ParseException e) {
			out.print( bo.criarMensagemJavascript("Data inválida!") );
			e.printStackTrace();
		}
	}

	//try catch ok
	private void formularioEditarPessoaTarefa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		HtmlBo bo = new HtmlBo();
		PessoaDao pdao = new PessoaDao();
		TarefaDao tdao = new TarefaDao();
		PessoaTarefaDao ptdao = new PessoaTarefaDao();

		try {
			Integer id = Integer.parseInt( request.getParameter("id") );

			PessoaTarefaBean progresso = ptdao.findById( id );
			List<PessoaBean> listaPessoas = pdao.find( pdao.where("PES_ATIVO", "=", "'S'") );
			List<TarefaBean> listaTarefas = tdao.find( tdao.where("TAR_ATIVO", "=", "'S'") );

			out.print(bo.getProgressForm(progresso, listaTarefas, listaPessoas));
		} catch (SQLException e) {
			response.sendRedirect("index?logica=PessoaTarefaBo&alerta=Erro ao abrir cadastro!");
			e.printStackTrace();
		} catch(NumberFormatException e) {
			out.print( bo.criarMensagemJavascript("Erro ao recuperar código do progresso!") );
			e.printStackTrace();
		}
	}

	//try catch ok
	private void gravarPessoaTarefa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HtmlBo bo = new HtmlBo();

		PessoaTarefaDao dao = new PessoaTarefaDao();
		PessoaTarefaBean progresso = new PessoaTarefaBean();

		try {
			PessoaBean pessoa = new PessoaBean();
			pessoa.setId( Integer.parseInt( request.getParameter("idPessoa") ) );
			
			TarefaBean tarefa = new TarefaBean();
			tarefa.setId( Integer.parseInt( request.getParameter("idTarefa") ) );

			progresso.setDataInicio( ManipularData.converterData(request.getParameter("dataInicio"), ManipularData.US) );
			progresso.setPessoa( pessoa  );
			progresso.setTarefa( tarefa );
			progresso.setPercentual( new BigDecimal("0.0") );
			progresso.setFinalizado(false);
			progresso.setAtivo(true);

			dao.insert(progresso);
			response.sendRedirect("index?logica=PessoaTarefaBo&acao=listar&alerta=Progresso Cadastrado com Sucesso!");
		} catch (SQLException e) {
			out.print( bo.criarMensagemJavascript("Erro ao tentar gravar dados!") );
			e.printStackTrace();
		} catch (NullPointerException e) {
			out.print( bo.criarMensagemJavascript("Informe data!") );
			e.printStackTrace();
		} catch (ParseException e) {
			out.print( bo.criarMensagemJavascript("Data inválida!") );
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
			out.print(bo.criarMensagemJavascript("Erro ao consultar dados!"));
			e.printStackTrace();
		} catch (ParseException e) {
			out.print(bo.criarMensagemJavascript("Data informada é inválida!"));
			e.printStackTrace();
		}
	}

}