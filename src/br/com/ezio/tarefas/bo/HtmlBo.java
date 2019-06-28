package br.com.ezio.tarefas.bo;

import java.text.ParseException;
import java.util.List;

import br.com.ezio.tarefas.bean.PessoaBean;
import br.com.ezio.tarefas.bean.PessoaTarefaBean;
import br.com.ezio.tarefas.bean.TarefaBean;
import br.com.ezio.tarefas.view.ComponentsHtml;
import br.com.ezio.tarefas.view.DivHtml;
import br.com.ezio.tarefas.view.FormHtml;
import br.com.ezio.tarefas.view.HeaderHtml;
import br.com.ezio.tarefas.view.InputHtml;
import br.com.ezio.tarefas.view.LabelHtml;
import br.com.ezio.tarefas.view.OptionHtml;
import br.com.ezio.tarefas.view.SelectHtml;
import br.com.ezio.workcontrol.utils.ManipularData;

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
		itensMenu = new String[3][2];
		
		itensMenu[0][0] = "Progresso";  itensMenu[0][1] = "index?logica=PessoaTarefaBo&acao=listar";
		itensMenu[1][0] = "Tarefas";    itensMenu[1][1] = "index?logica=TarefaBo&acao=listar";
		itensMenu[2][0] = "Pessoas";    itensMenu[2][1] = "index?logica=PessoaBo&acao=listar";
		

		ch = new ComponentsHtml("Atividades");
	}

	// PessoaTarefa
	public String getProgressList(List<PessoaTarefaBean> listaPessoasTarefas, String alerta) throws NullPointerException, ParseException {
		StringBuilder sb = new StringBuilder();

		String[] colunasTabela = new String[] {"Código", "Descrição", "Pessoa", "Percentual", "Data Início", "Data Fim", "Finalizado", "Ativo", "Ações"};

		Object[][] dados = new Object[listaPessoasTarefas.size()][colunasTabela.length];

		for (int i = 0; i < listaPessoasTarefas.size(); i++) {
			PessoaTarefaBean progresso = listaPessoasTarefas.get(i);

			dados[i][0] = progresso.getId();
			dados[i][1] = progresso.getTarefa().getDescricao();
			dados[i][2] = progresso.getPessoa().getNome();
			dados[i][3] = progresso.getPercentual();
			dados[i][4] = ManipularData.converterData(progresso.getDataInicio(), ManipularData.BR);
			dados[i][5] = (progresso.getDataFim() != null ? (ManipularData.converterData(progresso.getDataFim(), ManipularData.BR)) : " - ");
			dados[i][6] = (progresso.getFinalizado() ? "Sim" : "Não");
			dados[i][7] = (progresso.getAtivo() ? "Sim" : "Não");
			dados[i][8] = "<a href=\"index?logica=PessoaTarefaBo&acao=formularioEditar&id=" + progresso.getId() + "\" class=\"botaoTabela\"><img src=\"estilos/img/pencil.png\"></a>\n     " + 

					"<a href=\"javascript:func()\" onclick=\"" + (progresso.getAtivo() ? "confirmaExclusao" : "confirmaAtivacao") + "('" + progresso.getId() + "')\" ><img src=\"estilos/img/" + (progresso.getAtivo() ? "delete" : "Apply") + ".png\"></a>";
		}

		sb.append(ch.openDocumentHtml());
		sb.append(ch.openHeadHtml());
		sb.append(ch.getTitle("Lista de Progressos"));
		sb.append(ch.getLinkCSS("estilos/bootstrap/css/bootstrap.min.css"));
		sb.append(ch.getLinkCSS("estilos/css/estilo.css"));
		sb.append(ch.getLinkCSS("estilos/datatables/DataTables-1.10.18/css/dataTables.bootstrap.min.css"));
		sb.append(ch.getScript("estilos/jquery/jquery-3.3.1.js"));
		sb.append(ch.getScript("estilos/datatables/DataTables-1.10.18/js/jquery.dataTables.min.js"));
		sb.append(ch.getScript("estilos/datatables/DataTables-1.10.18/js/dataTables.bootstrap.min.js"));
		sb.append(ch.getScriptDataTable("tabela"));
		sb.append(ch.getScriptAlertJQuery());
		sb.append(ch.getScriptConfirm("Deseja excluir este progresso?", "index?logica=PessoaTarefaBo&acao=excluir&id=", "confirmaExclusao"));
		sb.append(ch.getScriptConfirm("Deseja ativar este progresso?", "index?logica=PessoaTarefaBo&acao=ativar&id=", "confirmaAtivacao"));
		sb.append(ch.closeHeadHtml());
		sb.append(ch.openBodyHtml());
		sb.append(ch.getDivHeaderHtml(header));
		sb.append(ch.getHorizontalMenuHtml(itensMenu));
		sb.append(ch.openDivMain());
		sb.append(ch.getDivTopoConteudo("Lista de Progressos","index?logica=PessoaTarefaBo&acao=formularioCadastro"));

		sb.append(ch.getTableHtml("tabela", colunasTabela, dados));
		sb.append(ch.closeDivMain());
		sb.append(ch.getDivFooterHtml("Todos os direitos reservados"));
		if(alerta != null) {
			DivHtml	alertaDiv = new DivHtml();
			alertaDiv.setId("aviso");
			alertaDiv.setStyle("display: none;");

			sb.append(ch.openDivHtml(alertaDiv, 3));
			sb.append(alerta);
			sb.append(ch.closeDivHtml(3));
		}
		sb.append(ch.closeBody());
		sb.append(ch.closeDocumentHtml());

		return sb.toString();
	}

	public String getProgressForm(PessoaTarefaBean progresso, List<TarefaBean> listaTarefas, List<PessoaBean> listaPessoas) throws NullPointerException {
		StringBuilder sb = new StringBuilder();

		FormHtml form = new FormHtml();
		form.setMethod("post");
		form.setAction("index");
		form.setLegend("Cadastrar Progresso");

		InputHtml acaoInput = new InputHtml();
		acaoInput.setType("hidden");
		acaoInput.setName("acao");
		acaoInput.setValue("gravar");

		if(progresso != null) {
			form.setLegend("Editar Progresso");
			acaoInput.setValue("editar");
		}

		InputHtml logicaInput = new InputHtml();
		logicaInput.setType("hidden");
		logicaInput.setName("logica");
		logicaInput.setValue("PessoaTarefaBo");

		// Data Inicio
		InputHtml dataInicioInput = new InputHtml();
		dataInicioInput.setType("date");
		dataInicioInput.setName("dataInicio");
		dataInicioInput.setId("dataInicio");
		dataInicioInput.setRequired(true);
		dataInicioInput.setClassCss("form-control");

		LabelHtml dataLabel = new LabelHtml();
		dataLabel.setId(dataInicioInput.getId());
		dataLabel.setValue("Data Inicial:");

		// Tarefas
		OptionHtml[] tarefasArray = getTarefasList(listaTarefas, (progresso != null ? progresso.getTarefa().getId() : null) );

		SelectHtml tarefasSelect = new SelectHtml();
		tarefasSelect.setId("tarefas");
		tarefasSelect.setClassCss("form-control");
		tarefasSelect.setName("idTarefa");
		tarefasSelect.setRequired(true);
		tarefasSelect.setOpcoes(tarefasArray);

		LabelHtml tarefasLabel = new LabelHtml();
		tarefasLabel.setId(tarefasSelect.getId());
		tarefasLabel.setValue("Tarefas:");

		// Pessoas
		OptionHtml[] pessoasArray = getPessoasList(listaPessoas, (progresso != null ? progresso.getPessoa().getId() : null) );

		SelectHtml pessoasSelect = new SelectHtml();
		pessoasSelect.setId("pessoas");
		pessoasSelect.setClassCss("form-control");
		pessoasSelect.setName("idPessoa");
		pessoasSelect.setRequired(true);
		pessoasSelect.setOpcoes(pessoasArray);

		LabelHtml pessoasLabel = new LabelHtml();
		pessoasLabel.setId(pessoasSelect.getId());
		pessoasLabel.setValue("Pessoas:");

		InputHtml submitInput = new InputHtml();
		submitInput.setType("submit");
		submitInput.setValue("Gravar");
		submitInput.setName("btnSalvar");
		submitInput.setClassCss("botao");

		DivHtml divFormRow = new DivHtml();
		divFormRow.setClassCss("form-group row");

		DivHtml divFormCol = new DivHtml();

		DivHtml	alertaDiv = new DivHtml();
		alertaDiv.setId("aviso");
		alertaDiv.setStyle("display: none;");

		sb.append(ch.openDocumentHtml());
		sb.append(ch.openHeadHtml());
		sb.append(ch.getTitle("Cadastro de Tarefa"));
		sb.append(ch.getLinkCSS("estilos/bootstrap/css/bootstrap.min.css"));
		sb.append(ch.getLinkCSS("estilos/css/estilo.css"));
		sb.append(ch.getScript("estilos/jquery/jquery-3.3.1.js"));

		sb.append(ch.getScriptAlertJQuery());
		sb.append(ch.closeHeadHtml());
		sb.append(ch.openBodyHtml());
		sb.append(ch.getDivHeaderHtml(header));
		sb.append(ch.getHorizontalMenuHtml(itensMenu));
		sb.append(ch.openDivMain());

		sb.append(ch.openFormHtml(form));
		sb.append(ch.getInput(acaoInput));
		sb.append(ch.getInput(logicaInput));

		// linha código e data
		sb.append(ch.openDivHtml(divFormRow, 4));

		if(progresso != null) {
			
			dataInicioInput.setValue(progresso.getDataInicio().toString());
			
			// input código
			divFormCol.setClassCss("col-lg-2");

			LabelHtml idLabel = new LabelHtml();
			idLabel.setId("id");
			idLabel.setValue("Código:");

			InputHtml IdInput = new InputHtml();
			IdInput.setType("text");
			IdInput.setName("id");
			IdInput.setId("id");
			IdInput.setValue(progresso.getId().toString());
			IdInput.setPlaceholder("Informe Código");
			IdInput.setMaxlength("50");
			IdInput.setRequired(true);
			IdInput.setClassCss("form-control");
			IdInput.setReadonly(true);

			sb.append(ch.openDivHtml(divFormCol, 5));
			sb.append(ch.getLabel(idLabel));
			sb.append(ch.getInput(IdInput));
			sb.append(ch.closeDivHtml(5));
		}

		divFormCol.setClassCss("col-lg-2");

		sb.append(ch.openDivHtml(divFormCol, 5));
		sb.append(ch.getLabel(dataLabel));
		sb.append(ch.getInput(dataInicioInput));
		sb.append(ch.closeDivHtml(5));

		sb.append(ch.closeDivHtml(4));

		// Input tarefas
		divFormCol.setClassCss("col-lg-6");
		sb.append(ch.openDivHtml(divFormRow, 4));
		sb.append(ch.openDivHtml(divFormCol, 5));
		sb.append(ch.getLabel(tarefasLabel));
		sb.append(ch.getSelect(tarefasSelect));
		sb.append(ch.closeDivHtml(5));
		sb.append(ch.closeDivHtml(4));

		// Input pessoas
		divFormCol.setClassCss("col-lg-6");
		sb.append(ch.openDivHtml(divFormRow, 4));
		sb.append(ch.openDivHtml(divFormCol, 5));
		sb.append(ch.getLabel(pessoasLabel));
		sb.append(ch.getSelect(pessoasSelect));
		sb.append(ch.closeDivHtml(5));
		sb.append(ch.closeDivHtml(4));

		if(progresso != null) {
			
			// percentual
			InputHtml percentualInput = new InputHtml();
			percentualInput.setId("percentual");
			percentualInput.setName("percentual");
			percentualInput.setType("text");
			percentualInput.setClassCss("form-control");
			percentualInput.setMaxlength("10");
			percentualInput.setPlaceholder("Informe Percentual atingido");
			percentualInput.setRequired(true);
			percentualInput.setValue(progresso.getPercentual().toString());

			LabelHtml percentualLabel = new LabelHtml();
			percentualLabel.setId(percentualInput.getId());
			percentualLabel.setValue("% Atingido:");

			// data fim
			InputHtml dataFinalInput = new InputHtml();
			dataFinalInput.setClassCss("form-control");
			dataFinalInput.setId("dataFinal");
			dataFinalInput.setName("dataFinal");
			dataFinalInput.setType("date");
			dataFinalInput.setValue( (progresso.getDataFim() != null ? progresso.getDataFim().toString() : "" ) );

			LabelHtml dataFimLabel = new LabelHtml();
			dataFimLabel.setId(dataFinalInput.getId());
			dataFimLabel.setValue("Data Final:");

			// finalizado
			InputHtml finalizadoInput = new InputHtml();
			finalizadoInput.setId("finalizado");
			finalizadoInput.setName("finalizado");
			finalizadoInput.setType("checkbox");
			finalizadoInput.setValue("S");

			LabelHtml finalizadoLabel = new LabelHtml();
			finalizadoLabel.setId(finalizadoInput.getId());
			finalizadoLabel.setValue("Finalizado");

			if(progresso.getFinalizado()) {
				finalizadoInput.setChecked(true);
			} else {
				finalizadoInput.setChecked(false);
			}

			// ativo
			InputHtml ativoInput = new InputHtml();
			ativoInput.setId("ativo");
			ativoInput.setName("ativo");
			ativoInput.setType("checkbox");
			ativoInput.setValue("S");

			LabelHtml ativoLabel = new LabelHtml();
			ativoLabel.setId( ativoLabel.getId() );
			ativoLabel.setValue("Ativo");

			if(progresso.getAtivo()) {
				ativoInput.setChecked(true);
			} else {
				ativoInput.setChecked(false);
			}

			divFormCol.setClassCss("col-lg-2");
			sb.append(ch.openDivHtml(divFormRow, 4));

			sb.append(ch.openDivHtml(divFormCol, 5));
			sb.append(ch.getLabel(percentualLabel));
			sb.append(ch.getInput(percentualInput));
			sb.append(ch.closeDivHtml(5));

			sb.append(ch.openDivHtml(divFormCol, 5));
			sb.append(ch.getLabel(dataFimLabel));
			sb.append(ch.getInput(dataFinalInput));
			sb.append(ch.closeDivHtml(5));

			sb.append(ch.closeDivHtml(4));

			divFormCol.setClassCss("col-lg-2");

			sb.append(ch.openDivHtml(divFormCol, 5));
			sb.append(ch.getInput(finalizadoInput));
			sb.append(ch.getLabel(finalizadoLabel));
			sb.append(ch.closeDivHtml(5));

			sb.append(ch.openDivHtml(divFormRow, 4));
			sb.append(ch.openDivHtml(divFormCol, 5));
			sb.append(ch.getInput(ativoInput));
			sb.append(ch.getLabel(ativoLabel));
			sb.append(ch.closeDivHtml(5));

			sb.append(ch.closeDivHtml(4));
		}


		sb.append(ch.getInput(submitInput));
		sb.append(ch.closeFormHtml());
		sb.append(ch.closeDivMain());

		sb.append(ch.getDivFooterHtml("Todos os direitos reservados"));
		sb.append(ch.closeBody());
		sb.append(ch.closeDocumentHtml());

		return sb.toString();
	}

	// Tarefa
	public String getScheduleList(List<TarefaBean> listaTarefas, String alerta) throws NullPointerException {
		StringBuilder sb = new StringBuilder();

		String[] colunasTabela = new String[] {"Código", "Descrição", "Ativo", "Ações"};

		Object[][] dados = new Object[listaTarefas.size()][colunasTabela.length];

		for (int i = 0; i < listaTarefas.size(); i++) {
			TarefaBean tarefa = listaTarefas.get(i);

			dados[i][0] = tarefa.getId();
			dados[i][1] = tarefa.getDescricao();
			dados[i][2] = (tarefa.getAtivo() ? "Sim" : "Não");
			dados[i][3] = "<a href=\"index?logica=TarefaBo&acao=formularioEditar&id=" + tarefa.getId() + "\" class=\"botaoTabela\"><img src=\"estilos/img/pencil.png\"></a>\n     " + 

					"<a href=\"javascript:func()\" onclick=\"" + (tarefa.getAtivo() ? "confirmaExclusao" : "confirmaAtivacao") + "('" + tarefa.getId() + "')\" ><img src=\"estilos/img/" + (tarefa.getAtivo() ? "delete" : "Apply") + ".png\"></a>";
		}

		sb.append(ch.openDocumentHtml());
		sb.append(ch.openHeadHtml());
		sb.append(ch.getTitle("Lista de tarefas"));
		sb.append(ch.getLinkCSS("estilos/bootstrap/css/bootstrap.min.css"));
		sb.append(ch.getLinkCSS("estilos/css/estilo.css"));
		sb.append(ch.getLinkCSS("estilos/datatables/DataTables-1.10.18/css/dataTables.bootstrap.min.css"));
		sb.append(ch.getScript("estilos/jquery/jquery-3.3.1.js"));
		sb.append(ch.getScript("estilos/datatables/DataTables-1.10.18/js/jquery.dataTables.min.js"));
		sb.append(ch.getScript("estilos/datatables/DataTables-1.10.18/js/dataTables.bootstrap.min.js"));
		sb.append(ch.getScriptDataTable("tabela"));
		sb.append(ch.getScriptAlertJQuery());
		sb.append(ch.getScriptConfirm("Deseja excluir esta tarefa?", "index?logica=TarefaBo&acao=excluir&id=", "confirmaExclusao"));
		sb.append(ch.getScriptConfirm("Deseja ativar esta tarefa?", "index?logica=TarefaBo&acao=ativar&id=", "confirmaAtivacao"));
		sb.append(ch.closeHeadHtml());
		sb.append(ch.openBodyHtml());
		sb.append(ch.getDivHeaderHtml(header));
		sb.append(ch.getHorizontalMenuHtml(itensMenu));
		sb.append(ch.openDivMain());
		sb.append(ch.getDivTopoConteudo("Lista de Tarefas","index?logica=TarefaBo&acao=formularioCadastro"));

		sb.append(ch.getTableHtml("tabela", colunasTabela, dados));
		sb.append(ch.closeDivMain());
		sb.append(ch.getDivFooterHtml("Todos os direitos reservados"));
		if(alerta != null) {
			DivHtml	alertaDiv = new DivHtml();
			alertaDiv.setId("aviso");
			alertaDiv.setStyle("display: none;");

			sb.append(ch.openDivHtml(alertaDiv, 3));
			sb.append(alerta);
			sb.append(ch.closeDivHtml(3));
		}
		sb.append(ch.closeBody());
		sb.append(ch.closeDocumentHtml());

		return sb.toString();
	}

	public String getScheduleForm(TarefaBean tarefa) {
		StringBuilder sb = new StringBuilder();

		FormHtml form = new FormHtml();
		form.setMethod("post");
		form.setAction("index");
		form.setLegend("Cadastrar Tarefa");


		InputHtml acaoInput = new InputHtml();
		acaoInput.setType("hidden");
		acaoInput.setName("acao");
		acaoInput.setValue("gravar");

		InputHtml logicaInput = new InputHtml();
		logicaInput.setType("hidden");
		logicaInput.setName("logica");
		logicaInput.setValue("TarefaBo");

		InputHtml descricaoInput = new InputHtml();
		descricaoInput.setType("text");
		descricaoInput.setName("descricao");
		descricaoInput.setId("descricao");
		descricaoInput.setPlaceholder("Informe Descrição");
		descricaoInput.setMaxlength("50");
		descricaoInput.setRequired(true);
		descricaoInput.setClassCss("form-control");

		InputHtml submitInput = new InputHtml();
		submitInput.setType("submit");
		submitInput.setValue("Gravar");
		submitInput.setName("btnSalvar");
		submitInput.setClassCss("botao");

		DivHtml divFormRow = new DivHtml();
		divFormRow.setClassCss("form-group row");

		DivHtml divFormCol = new DivHtml();

		LabelHtml label = new LabelHtml();
		label.setId(descricaoInput.getId());
		label.setValue("Descrição:");

		DivHtml	alertaDiv = new DivHtml();
		alertaDiv.setId("aviso");
		alertaDiv.setStyle("display: none;");

		if(tarefa != null) {
			form.setLegend("Editar Tarefa");
			acaoInput.setValue("editar");
			descricaoInput.setValue(tarefa.getDescricao());
		}

		sb.append(ch.openDocumentHtml());
		sb.append(ch.openHeadHtml());
		sb.append(ch.getTitle("Cadastro de Tarefa"));
		sb.append(ch.getLinkCSS("estilos/bootstrap/css/bootstrap.min.css"));
		sb.append(ch.getLinkCSS("estilos/css/estilo.css"));
		sb.append(ch.getScript("estilos/jquery/jquery-3.3.1.js"));

		sb.append(ch.getScriptAlertJQuery());
		sb.append(ch.closeHeadHtml());
		sb.append(ch.openBodyHtml());
		sb.append(ch.getDivHeaderHtml(header));
		sb.append(ch.getHorizontalMenuHtml(itensMenu));
		sb.append(ch.openDivMain());

		sb.append(ch.openFormHtml(form));
		sb.append(ch.getInput(acaoInput));
		sb.append(ch.getInput(logicaInput));

		if(tarefa != null) {
			// input código

			divFormCol.setClassCss("col-lg-2");

			LabelHtml idLabel = new LabelHtml();
			idLabel.setId("id");
			idLabel.setValue("Código:");

			InputHtml IdInput = new InputHtml();
			IdInput.setType("text");
			IdInput.setName("id");
			IdInput.setId("id");
			IdInput.setValue(tarefa.getId().toString());
			IdInput.setPlaceholder("Informe Código");
			IdInput.setMaxlength("50");
			IdInput.setRequired(true);
			IdInput.setClassCss("form-control");
			IdInput.setReadonly(true);

			sb.append(ch.openDivHtml(divFormRow, 4));

			sb.append(ch.openDivHtml(divFormCol, 5));
			sb.append(ch.getLabel(idLabel));
			sb.append(ch.getInput(IdInput));
			sb.append(ch.closeDivHtml(5));
			sb.append(ch.closeDivHtml(4));
		}

		// Input nome
		divFormCol.setClassCss("col-lg-5");
		sb.append(ch.openDivHtml(divFormRow, 4));
		sb.append(ch.openDivHtml(divFormCol, 5));
		sb.append(ch.getLabel(label));
		sb.append(ch.getInput(descricaoInput));
		sb.append(ch.closeDivHtml(5));
		sb.append(ch.closeDivHtml(4));

		if(tarefa != null) {

			LabelHtml ativoLabel = new LabelHtml();
			ativoLabel.setId("ativo");
			ativoLabel.setValue("Ativo");

			InputHtml ativoInput = new InputHtml();
			ativoInput.setId("ativo");
			ativoInput.setName("ativo");
			ativoInput.setType("checkbox");
			ativoInput.setValue("S");

			if(tarefa.getAtivo()) {
				ativoInput.setChecked(true);
			} else {
				ativoInput.setChecked(false);
			}

			divFormCol.setClassCss("col-lg-2");
			sb.append(ch.openDivHtml(divFormRow, 4));
			sb.append(ch.openDivHtml(divFormCol, 5));
			sb.append(ch.getInput(ativoInput));
			sb.append(ch.getLabel(ativoLabel));
			sb.append(ch.closeDivHtml(5));
			sb.append(ch.closeDivHtml(4));
		}


		sb.append(ch.getInput(submitInput));
		sb.append(ch.closeFormHtml());
		sb.append(ch.closeDivMain());

		sb.append(ch.getDivFooterHtml("Todos os direitos reservados"));
		sb.append(ch.closeBody());
		sb.append(ch.closeDocumentHtml());

		return sb.toString();
	}


	// Pessoa
	public String getPersonForm(PessoaBean pessoa) {
		StringBuilder sb = new StringBuilder();

		FormHtml form = new FormHtml();
		form.setMethod("post");
		form.setAction("index");
		form.setLegend("Cadastrar Pessoa");


		InputHtml acaoInput = new InputHtml();
		acaoInput.setType("hidden");
		acaoInput.setName("acao");
		acaoInput.setValue("gravar");

		InputHtml logicaInput = new InputHtml();
		logicaInput.setType("hidden");
		logicaInput.setName("logica");
		logicaInput.setValue("PessoaBo");

		InputHtml nameInput = new InputHtml();
		nameInput.setType("text");
		nameInput.setName("nome");
		nameInput.setId("nome");
		nameInput.setPlaceholder("Informe Nome");
		nameInput.setMaxlength("50");
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

		LabelHtml label = new LabelHtml();
		label.setId(nameInput.getId());
		label.setValue("Nome:");

		DivHtml	alertaDiv = new DivHtml();
		alertaDiv.setId("aviso");
		alertaDiv.setStyle("display: none;");

		if(pessoa != null) {
			form.setLegend("Editar Pessoa");
			acaoInput.setValue("editar");
			nameInput.setValue(pessoa.getNome());
		}

		sb.append(ch.openDocumentHtml());
		sb.append(ch.openHeadHtml());
		sb.append(ch.getTitle("Cadastro de Pessoa"));
		sb.append(ch.getLinkCSS("estilos/bootstrap/css/bootstrap.min.css"));
		sb.append(ch.getLinkCSS("estilos/css/estilo.css"));
		sb.append(ch.getScript("estilos/jquery/jquery-3.3.1.js"));

		sb.append(ch.getScriptAlertJQuery());
		sb.append(ch.closeHeadHtml());
		sb.append(ch.openBodyHtml());
		sb.append(ch.getDivHeaderHtml(header));
		sb.append(ch.getHorizontalMenuHtml(itensMenu));
		sb.append(ch.openDivMain());

		sb.append(ch.openFormHtml(form));
		sb.append(ch.getInput(acaoInput));
		sb.append(ch.getInput(logicaInput));

		if(pessoa != null) {
			// input código

			divFormCol.setClassCss("col-lg-2");

			LabelHtml idLabel = new LabelHtml();
			idLabel.setId("id");
			idLabel.setValue("Código:");

			InputHtml IdInput = new InputHtml();
			IdInput.setType("text");
			IdInput.setName("id");
			IdInput.setId("id");
			IdInput.setValue(pessoa.getId().toString());
			IdInput.setPlaceholder("Informe Código");
			IdInput.setMaxlength("50");
			IdInput.setRequired(true);
			IdInput.setClassCss("form-control");
			IdInput.setReadonly(true);

			sb.append(ch.openDivHtml(divFormRow, 4));

			sb.append(ch.openDivHtml(divFormCol, 5));
			sb.append(ch.getLabel(idLabel));
			sb.append(ch.getInput(IdInput));
			sb.append(ch.closeDivHtml(5));
			sb.append(ch.closeDivHtml(4));
		}

		// Input nome
		divFormCol.setClassCss("col-lg-5");
		sb.append(ch.openDivHtml(divFormRow, 4));
		sb.append(ch.openDivHtml(divFormCol, 5));
		sb.append(ch.getLabel(label));
		sb.append(ch.getInput(nameInput));
		sb.append(ch.closeDivHtml(5));
		sb.append(ch.closeDivHtml(4));

		if(pessoa != null) {

			LabelHtml ativoLabel = new LabelHtml();
			ativoLabel.setId("ativo");
			ativoLabel.setValue("Ativo");

			InputHtml ativoInput = new InputHtml();
			ativoInput.setId("ativo");
			ativoInput.setName("ativo");
			ativoInput.setType("checkbox");
			ativoInput.setValue("S");

			if(pessoa.getAtivo()) {
				ativoInput.setChecked(true);
			} else {
				ativoInput.setChecked(false);
			}

			divFormCol.setClassCss("col-lg-2");
			sb.append(ch.openDivHtml(divFormRow, 4));
			sb.append(ch.openDivHtml(divFormCol, 5));
			sb.append(ch.getInput(ativoInput));
			sb.append(ch.getLabel(ativoLabel));
			sb.append(ch.closeDivHtml(5));
			sb.append(ch.closeDivHtml(4));
		}


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

		String[] colunasTabela = new String[] {"Código", "Nome", "Ativo", "Ações"};

		Object[][] dados = new Object[listaPessoas.size()][colunasTabela.length];

		for (int i = 0; i < listaPessoas.size(); i++) {
			PessoaBean pessoa = listaPessoas.get(i);

			dados[i][0] = pessoa.getId();
			dados[i][1] = pessoa.getNome();
			dados[i][2] = (pessoa.getAtivo() ? "Sim" : "Não");
			dados[i][3] = "<a href=\"index?logica=PessoaBo&acao=formularioEditar&id=" + pessoa.getId() + "\" class=\"botaoTabela\"><img src=\"estilos/img/pencil.png\"></a>\n     " + 

					"<a href=\"javascript:func()\" onclick=\"" + (pessoa.getAtivo() ? "confirmaExclusao" : "confirmaAtivacao") + "('" + pessoa.getId() + "')\" ><img src=\"estilos/img/" + (pessoa.getAtivo() ? "delete" : "Apply") + ".png\"></a>";
		}

		sb.append(ch.openDocumentHtml());
		sb.append(ch.openHeadHtml());
		sb.append(ch.getTitle("Lista de Pessoas"));
		sb.append(ch.getLinkCSS("estilos/bootstrap/css/bootstrap.min.css"));
		sb.append(ch.getLinkCSS("estilos/css/estilo.css"));
		sb.append(ch.getLinkCSS("estilos/datatables/DataTables-1.10.18/css/dataTables.bootstrap.min.css"));
		sb.append(ch.getScript("estilos/jquery/jquery-3.3.1.js"));
		sb.append(ch.getScript("estilos/datatables/DataTables-1.10.18/js/jquery.dataTables.min.js"));
		sb.append(ch.getScript("estilos/datatables/DataTables-1.10.18/js/dataTables.bootstrap.min.js"));
		sb.append(ch.getScriptDataTable("tabela"));
		sb.append(ch.getScriptAlertJQuery());
		sb.append(ch.getScriptConfirm("Deseja excluir esta pessoa?", "index?logica=PessoaBo&acao=excluir&id=", "confirmaExclusao"));
		sb.append(ch.getScriptConfirm("Deseja ativar esta pessoa?", "index?logica=PessoaBo&acao=ativar&id=", "confirmaAtivacao"));
		sb.append(ch.closeHeadHtml());
		sb.append(ch.openBodyHtml());
		sb.append(ch.getDivHeaderHtml(header));
		sb.append(ch.getHorizontalMenuHtml(itensMenu));
		sb.append(ch.openDivMain());
		sb.append(ch.getDivTopoConteudo("Lista de Pessoas","index?logica=PessoaBo&acao=formularioCadastro"));

		sb.append(ch.getTableHtml("tabela", colunasTabela, dados));
		sb.append(ch.closeDivMain());
		sb.append(ch.getDivFooterHtml("Todos os direitos reservados"));
		if(alerta != null) {
			DivHtml	alertaDiv = new DivHtml();
			alertaDiv.setId("aviso");
			alertaDiv.setStyle("display: none;");

			sb.append(ch.openDivHtml(alertaDiv, 3));
			sb.append(alerta);
			sb.append(ch.closeDivHtml(3));
		}
		sb.append(ch.closeBody());
		sb.append(ch.closeDocumentHtml());

		return sb.toString();
	}

	public String getHomePageHtml() throws NullPointerException {

		StringBuilder sb = new StringBuilder();

		return sb.toString();
	}

	private OptionHtml[] getTarefasList(List<TarefaBean> lista, Integer idRequired) {
		OptionHtml[] listaOption = new OptionHtml[lista.size()];

		for (int i = 0; i < lista.size(); i++) {
			OptionHtml option = new OptionHtml();
			option.setLabel(lista.get(i).getDescricao());
			option.setValue(lista.get(i).getId().toString());

			if(idRequired != null && idRequired == lista.get(i).getId()) {
				option.setSelected(true);
			}

			listaOption[i] = option;
		}

		return listaOption;
	}

	private OptionHtml[] getPessoasList(List<PessoaBean> lista, Integer idRequired) {
		OptionHtml[] listaOption = new OptionHtml[lista.size()];

		for (int i = 0; i < lista.size(); i++) {
			OptionHtml option = new OptionHtml();
			option.setLabel(lista.get(i).getNome());
			option.setValue(lista.get(i).getId().toString());

			if(idRequired != null && idRequired == lista.get(i).getId()) {
				option.setSelected(true);
			}

			listaOption[i] = option;
		}

		return listaOption;
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
		sb.append("  window.alert(\"" + mensagem + "\");\n");
		sb.append("  javascript:history.go(-1)\n");
		sb.append("</SCRIPT>\n");

		return sb.toString();
	}

}