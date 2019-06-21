package br.com.ezio.tarefas.bo;

import java.util.List;

import br.com.ezio.tarefas.bean.PessoaBean;
import br.com.ezio.tarefas.bean.PessoaTarefaBean;
import br.com.ezio.tarefas.view.AHtml;
import br.com.ezio.tarefas.view.ComponentsHtml;
import br.com.ezio.tarefas.view.DivHtml;
import br.com.ezio.tarefas.view.FormHtml;
import br.com.ezio.tarefas.view.HeaderHtml;
import br.com.ezio.tarefas.view.InputHtml;
import br.com.ezio.tarefas.view.LabelHtml;

public class HtmlBo {

	private HeaderHtml header;
	private String[][] itensMenu;
	private ComponentsHtml ch;


	public HtmlBo() {
		//Cria o cabeçalho da página
		header = new HeaderHtml();
		header.setTitulo("Atividades");
		header.setHrefIndex("index");
		header.setUrlIconImg("estilos/img/icone.png");

		// cria os itens do menu
		itensMenu = new String[5][5];
		itensMenu[0][0] = "Início";     itensMenu[0][1] = "index";
		itensMenu[1][0] = "Progresso";  itensMenu[1][1] = "index?logica=PessoaTarefaBo&acao=listarProgresso";
		itensMenu[2][0] = "Atividades"; itensMenu[2][1] = "index?logica=TarefaBo&acao=listarAtividades";
		itensMenu[3][0] = "Pessoas";    itensMenu[3][1] = "index?logica=PessoaBo&acao=listarPessoas";
		itensMenu[4][0] = "Contato";    itensMenu[4][1] = "index?acao=contato";

		ch = new ComponentsHtml("Atividades");
	}
	
	public String getPersonForm() {
		StringBuilder sb = new StringBuilder();

		FormHtml form = new FormHtml();
		form.setMethod("post");
		form.setAction("index");
		form.setLegend("Cadastrar Pessoa");

		InputHtml hiddenInput = new InputHtml();
		hiddenInput.setType("hidden");
		hiddenInput.setName("acao");
		hiddenInput.setValue("gravarPessoa");

		InputHtml logicaInput = new InputHtml();
		logicaInput.setType("hidden");
		logicaInput.setName("logica");
		logicaInput.setValue("PessoaBo");

		InputHtml nameInput = new InputHtml();
		nameInput.setType("text");
		nameInput.setName("nome");
		nameInput.setId("nome");
		nameInput.setPlaceholder("Informe Nome");
		nameInput.setMaxlength("100");
		nameInput.setRequired(true);
		nameInput.setClassCss("form-control");

		InputHtml submitInput = new InputHtml();
		submitInput.setType("submit");
		submitInput.setValue("Gravar");
		submitInput.setName("btnSalvar");
		submitInput.setClassCss("botao");

		DivHtml divFormRow = new DivHtml();
		divFormRow.setClassCss("form-group row");

		DivHtml divFormCol = new DivHtml();
		divFormCol.setClassCss("col-lg-5");

		LabelHtml label = new LabelHtml();
		label.setId(nameInput.getId());
		label.setValue("Nome:");

		DivHtml	alertaDiv = new DivHtml();
		alertaDiv.setId("aviso");
		alertaDiv.setStyle("display: none;");

		sb.append(ch.openDocumentHtml());
		sb.append(ch.openHeadHtml());
		sb.append(ch.getTitle("Início"));
		sb.append(ch.getLinkCSS("estilos/bootstrap/css/bootstrap.min.css"));
		sb.append(ch.getLinkCSS("estilos/css/estilo.css"));
		sb.append(ch.getScript("estilos/jquery/jquery-3.3.1.js"));

		//teste
		sb.append(ch.getScriptAlertJQuery());
		sb.append(ch.closeHeadHtml());
		sb.append(ch.openBodyHtml());
		sb.append(ch.getDivHeaderHtml(header));
		sb.append(ch.getHorizontalMenuHtml(itensMenu));
		sb.append(ch.openDivMain());

		sb.append(ch.openFormHtml(form));
		sb.append(ch.getInput(hiddenInput));
		sb.append(ch.getInput(logicaInput));
		sb.append(ch.openDivHtml(divFormRow, 4));
		sb.append(ch.openDivHtml(divFormCol, 5));
		sb.append(ch.getLabel(label));
		sb.append(ch.getInput(nameInput));
		sb.append(ch.closeDivHtml(5));
		sb.append(ch.closeDivHtml(4));

		sb.append(ch.getInput(submitInput));
		sb.append(ch.closeFormHtml());
		//		sb.append(ch.openDivHtml(alertaDiv, 3));
		//		sb.append("UM AVISO AQUI!<br>FadeOut depois de 10 segundos!\n");
		//		sb.append(ch.closeDivHtml(3));
		sb.append(ch.closeDivMain());

		sb.append(ch.getDivFooterHtml("Todos os direitos reservados"));
		sb.append(ch.closeBody());
		sb.append(ch.closeDocumentHtml());

		return sb.toString();
	}

	public String getPersonList(List<PessoaBean> listaPessoas, String alerta) throws NullPointerException {
		StringBuilder sb = new StringBuilder();

		String[] colunasTabela = new String[] {"Código", "Nome", "Ações"};

		Object[][] dados = new Object[listaPessoas.size()][colunasTabela.length];

		for (int i = 0; i < listaPessoas.size(); i++) {
			PessoaBean pessoa = listaPessoas.get(i);

			dados[i][0] = pessoa.getId();
			dados[i][1] = pessoa.getNome();
			dados[i][2] = "<a href=\"index?logica=PessoaBo&acao=editar&id=" + pessoa.getId() + "\" class=\"botaoTabela\"><img src=\"estilos/img/note_add.png\"></a>\n     " + 
			              "<a href=\"javascript:func()\" onclick=\"confirmacao('" + pessoa.getId() + "')\" ><img src=\"estilos/img/delete.png\"></a>";
		}

		sb.append(ch.openDocumentHtml());
		sb.append(ch.openHeadHtml());
		sb.append(ch.getTitle("Início"));
		sb.append(ch.getLinkCSS("estilos/bootstrap/css/bootstrap.min.css"));
		sb.append(ch.getLinkCSS("estilos/css/estilo.css"));
		sb.append(ch.getLinkCSS("estilos/datatables/DataTables-1.10.18/css/dataTables.bootstrap.min.css"));
		sb.append(ch.getScript("estilos/jquery/jquery-3.3.1.js"));
		sb.append(ch.getScript("estilos/datatables/DataTables-1.10.18/js/jquery.dataTables.min.js"));
		sb.append(ch.getScript("estilos/datatables/DataTables-1.10.18/js/dataTables.bootstrap.min.js"));
		sb.append(ch.getScriptDataTable("tabela"));
		sb.append(ch.getScriptAlertJQuery());
		sb.append(ch.getScriptConfirm("Deseja excluir esta pessoa?", "index?acao=excluirAtividade&idAtividade="));
		sb.append(ch.closeHeadHtml());
		sb.append(ch.openBodyHtml());
		sb.append(ch.getDivHeaderHtml(header));
		sb.append(ch.getHorizontalMenuHtml(itensMenu));
		sb.append(ch.openDivMain());
		sb.append(ch.getDivTopoConteudo("Lista de Pessoas","index?logica=PessoaBo&acao=formularioCadastroPessoa"));

		sb.append(ch.getTableHtml("tabela", colunasTabela, dados));

		if(alerta != null) {
			DivHtml	alertaDiv = new DivHtml();
			alertaDiv.setId("aviso");
			alertaDiv.setStyle("display: none;");

			sb.append(ch.openDivHtml(alertaDiv, 3));
			sb.append(alerta);
			sb.append(ch.closeDivHtml(3));
		}

		sb.append(ch.closeDivMain());
		sb.append(ch.getDivFooterHtml("Todos os direitos reservados"));
		sb.append(ch.closeBody());
		sb.append(ch.closeDocumentHtml());

		return sb.toString();
	}

	public String getHomePageHtml(List<PessoaTarefaBean> listaPessoasTarefas) throws NullPointerException {

		StringBuilder sb = new StringBuilder();

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

		sb.append(ch.openDocumentHtml());
		sb.append(ch.openHeadHtml());
		sb.append(ch.getTitle("Início"));
		sb.append(ch.getLinkCSS("estilos/bootstrap/css/bootstrap.min.css"));
		sb.append(ch.getLinkCSS("estilos/css/estilo.css"));
		sb.append(ch.getLinkCSS("estilos/datatables/DataTables-1.10.18/css/dataTables.bootstrap.min.css"));
		sb.append(ch.getScript("estilos/jquery/jquery-3.3.1.js"));
		sb.append(ch.getScript("estilos/datatables/DataTables-1.10.18/js/jquery.dataTables.min.js"));
		sb.append(ch.getScript("estilos/datatables/DataTables-1.10.18/js/dataTables.bootstrap.min.js"));
		sb.append(ch.getScriptDataTable("tabela"));
		sb.append(ch.getScriptConfirm("Deseja excluir esta atividade?", "index?acao=excluirAtividade&idAtividade="));
		sb.append(ch.closeHeadHtml());
		sb.append(ch.openBodyHtml());
		sb.append(ch.getDivHeaderHtml(header));
		sb.append(ch.getHorizontalMenuHtml(itensMenu));
		sb.append(ch.openDivMain());
		sb.append(ch.getDivTopoConteudo("Lista de Progresso de Atividades","index?acao=novaAtribuicao"));

		sb.append(ch.getTableHtml("tabela", colunasTabela, dados));
		sb.append(ch.closeDivMain());
		sb.append(ch.getDivFooterHtml("Todos os direitos reservados"));
		sb.append(ch.closeBody());
		sb.append(ch.closeDocumentHtml());

		return sb.toString();
	}
	
	public String criarMensagemJavascript(String mensagem) {

		if(mensagem == null) {
			mensagem = "Ocorreu um erro! Verifique os dados informados.";
		}

		StringBuilder sb = new StringBuilder();
		
		
		DivHtml	alertaDiv = new DivHtml();
		alertaDiv.setId("aviso");
		alertaDiv.setStyle("display: none;");

		sb.append("<SCRIPT LANGUAGE=\"JavaScript\" TYPE=\"text/javascript\">\n");
		//sb.append("  window.alert(\"" + mensagem + "\");" + pl);
		sb.append("  javascript:history.go(-1)\n");
		sb.append(ch.openDivHtml(alertaDiv, 3));
		sb.append(mensagem);
		sb.append(ch.closeDivHtml(3));
		sb.append("</SCRIPT>\n");

		return sb.toString();
	}

}