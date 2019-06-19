package br.com.ezio.tarefas.bo;

import java.util.List;

import br.com.ezio.tarefas.bean.PessoaTarefaBean;
import br.com.ezio.tarefas.view.ComponentsHtml;
import br.com.ezio.tarefas.view.HeaderHtml;

public class HtmlBo {

	public String getHomePage(List<PessoaTarefaBean> listaPessoasTarefas) throws NullPointerException {

		StringBuilder sb = new StringBuilder();

		//Cria o cabeçalho da página
		HeaderHtml header = new HeaderHtml();
		header.setTitulo("Atividades");
		header.setHrefIndex("index");
		header.setUrlIconImg("estilos/img/icon.png");

		// cria os itens do menu
		String[][] itensMenu = new String[5][5];
		itensMenu[0][0] = "Início";     itensMenu[0][1] = "index";
		itensMenu[1][0] = "Progresso";  itensMenu[1][1] = "index?logica=PessoaTarefaBo&acao=listarProgresso";
		itensMenu[2][0] = "Atividades"; itensMenu[2][1] = "index?logica=TarefaBo&acao=listarAtividades";
		itensMenu[3][0] = "Pessoas";    itensMenu[3][1] = "index?logica=PessoaBo&acao=listarPessoas";
		itensMenu[4][0] = "Contato";    itensMenu[4][1] = "index?acao=contato";

		String[] colunasTabela = new String[] {"Código","Atividade","Pessoa","Data", "% Atingido","Finalizado","Ações"};

		Object[][] dados = new Object[][]{};

		for (int i = 0; i < listaPessoasTarefas.size(); i++) {
			PessoaTarefaBean p = listaPessoasTarefas.get(i);
			dados[i][0] = p.getId();
			dados[i][1] = p.getTarefa().getDescricao();
			dados[i][2] = p.getPessoa().getNome();
			dados[i][3] = p.getDataInicio();
			dados[i][4] = p.getPercentual();
			dados[i][5] = p.getFinalizado();
			dados[i][6] = "<a href=\"\">Editar</a> <a href=\"\">Excluir</a>";
		}

		ComponentsHtml ch = new ComponentsHtml("Atividades");

		sb.append(ch.openDocumentHtml());
		sb.append(ch.openHeadHtml());
		sb.append(ch.getTitle("Início"));
		sb.append(ch.getLinkCSS("estilos/bootstrap/css/bootstrap.min.css"));
		sb.append(ch.getLinkCSS("estilos/css/estilo.css"));
		sb.append(ch.getLinkCSS("estilos/datatables/DataTables-1.10.18/css/dataTables.bootstrap.min.css"));
		sb.append(ch.getLinkCSS("estilos/bootstrap/css/bootstrap.min.css"));
		sb.append(ch.getLinkCSS("estilos/bootstrap/css/bootstrap.min.css"));
		sb.append(ch.getScript("estilos/jquery/jquery-3.3.1.js"));
		sb.append(ch.getScript("estilos/datatables/DataTables-1.10.18/js/jquery.dataTables.min.js"));
		sb.append(ch.getScript("estilos/datatables/DataTables-1.10.18/js/dataTables.bootstrap.min.js"));
		sb.append(ch.getScriptDataTable("tabelaAtividades"));
		sb.append(ch.getScriptConfirm("Deseja excluir esta atividade?", "index?acao=excluirAtividade&idAtividade="));
		sb.append(ch.closeHeadHtml());
		sb.append(ch.openBodyHtml());
		sb.append(ch.getDivHeaderHtml(header));
		sb.append(ch.getHorizontalMenuHtml(itensMenu));
		sb.append(ch.getH3("Lista de Progresso de Atividades"));
		sb.append(ch.getBotaoIncluir("index?acao=novaAtribuicao"));

		sb.append(ch.getTableHtml("tabelaAtividades", colunasTabela, dados));

		sb.append(ch.getDivFooterHtml("Todos os direitos reservados"));
		sb.append(ch.closeBody());
		sb.append(ch.closeDocumentHtml());

		return sb.toString();
	}

}