package br.com.ezio.tarefas.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ezio.tarefas.bean.PessoaBean;
import br.com.ezio.tarefas.bo.HtmlBo;

@WebServlet("/teste")
public class TesteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TesteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		//PessoaDao dao = new PessoaDao();
		HtmlBo bo = new HtmlBo();

		String alerta = request.getParameter("alerta");
		
		PessoaBean p = new PessoaBean();
		p.setId(1);
		p.setNome("Ezio");
		p.setAtivo(true);

		List<PessoaBean> lista = new ArrayList();
		lista.add(p);
		
		out.print(bo.getPersonList(lista, alerta));
	}

}
