package br.com.ezio.tarefas.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ezio.tarefas.bo.Logica;

/**
 * Servlet implementation class TarefaServlet
 */
@WebServlet("/index")
public class TarefaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TarefaServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doExecute(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doExecute(request, response);
	}
	
	protected void doExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=iso-8859-1");
		
		String parametro = request.getParameter("logica");
		
		if(parametro == null) {
			parametro = "PessoaTarefaBo";
		}
		
		String nomeDaClasse = "br.com.ezio.tarefas.bo." + parametro;
		
		try {
			Class<?> classe = Class.forName(nomeDaClasse);
			
			Logica logica = (Logica) classe.getConstructor().newInstance();
			logica.executa(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}