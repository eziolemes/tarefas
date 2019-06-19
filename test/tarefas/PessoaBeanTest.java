package tarefas;

import java.util.ArrayList;
import java.util.List;

import br.com.ezio.tarefas.bean.PessoaBean;
import br.com.ezio.tarefas.bean.TarefaBean;
import br.com.ezio.tarefas.dao.PessoaDao;
import br.com.ezio.tarefas.dao.TarefaDao;

public class PessoaBeanTest {
	public static void main(String[] args) {

//		TarefaBean tarefa1 = new TarefaBean();
//		tarefa1.setDescricao("Estudar Hibernate");
//		
//		TarefaBean tarefa2 = new TarefaBean();
//		tarefa2.setDescricao("Estudar SpringMVC");
//		
//		TarefaBean tarefa3 = new TarefaBean();
//		tarefa3.setDescricao("Estudar Disign Patterns");
		
//		List<TarefaBean> tarefas = new ArrayList();	
//		tarefas.add(tarefa1);
//		tarefas.add(tarefa2);
//		tarefas.add(tarefa3);
		
//		PessoaBean pessoa = new PessoaBean();
//		pessoa.setNome("Ezio Lemes");
		//pessoa.setTarefas(tarefas);
		
//		PessoaDao dao = new PessoaDao();
//		
//		dao.save(pessoa);
		
//		TarefaDao dao = new TarefaDao();
//		dao.save(tarefa3);
		///////////////////////////////
		
		TarefaBean t1 = new TarefaBean();
		PessoaBean p1 = new PessoaBean();
		
		TarefaDao tdao = new TarefaDao();
		PessoaDao pdao = new PessoaDao();
		
		t1 = tdao.findById(TarefaBean.class, 1);
		p1 = pdao.findById(PessoaBean.class, 1);
		
		List<TarefaBean> tarefas = new ArrayList();
		tarefas.add(t1);
		p1.setTarefas(tarefas);
		
		pdao.save(p1);
	}
}