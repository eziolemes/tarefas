package br.com.ezio.tarefas.view;

/**
 * Classe que constroi os componentes para formar um documento HTML.
 * 
 * @author ezio
 * @since 10/06/2019
 *
 */
public class ComponentsHtml {

	private final String tab = "\t";
	private final String pl = "\n";
	private final String name;

	public ComponentsHtml(String name) throws NullPointerException {
		this.name = name;
	}

	/**
	 * Inclui tabulação do conteúdo do documento html.
	 * 
	 * @param qtd
	 * @return String
	 */
	private String getTab(Integer qtd) {
		if(qtd == null || qtd == 1) qtd = 1;

		String tabulacao = "";

		for(int i=0; i<qtd; i++) {
			tabulacao += tab;
		}

		return tabulacao;
	}

	public String openDocumentHtml() {
		StringBuilder sb = new StringBuilder();

		sb.append("<!DOCTYPE html>" + pl);
		sb.append("<html>" + pl);

		return sb.toString();
	}

	public String closeDocumentHtml() {
		StringBuilder sb = new StringBuilder();

		sb.append("</html>" + pl);

		return sb.toString();
	}

	public String openHeadHtml() {
		StringBuilder sb = new StringBuilder();

		sb.append(getTab(1) + "<head>" + pl);
		sb.append(getTab(2) + "<meta http-equiv=\"Content-type\" content=\"text/html; charset=utf-8\">" + pl);

		return sb.toString();
	}

	public String closeHeadHtml() {
		StringBuilder sb = new StringBuilder();

		sb.append(getTab(1) + "</head>" + pl);

		return sb.toString();
	}

	public String openBodyHtml() {
		StringBuilder sb = new StringBuilder();

		sb.append(getTab(1) + "<body>" + pl);

		return sb.toString();
	}

	public String closeBody() {
		StringBuilder sb = new StringBuilder();

		sb.append(getTab(1) + "</body>" + pl);

		return sb.toString();
	}	

	public String getTitle(String title) {

		if(title == null) title = "Sistemas";

		StringBuilder sb = new StringBuilder();

		sb.append(getTab(2) + "<title>" + name + " - " + title + "</title>" + pl);

		return sb.toString();
	}

	public String getLinkCSS(String href) throws NullPointerException {
		StringBuilder sb = new StringBuilder();

		sb.append(getTab(2) + "<link rel=\"stylesheet\" type=\"text/css\" href=\"" + href + "\">" + pl);

		return sb.toString();
	}

	public String getScript(String src) throws NullPointerException {
		StringBuilder sb = new StringBuilder();

		sb.append(getTab(2) + "<script type=\"text/javascript\" language=\"javascript\" src=\"" + src + "\"></script>" + pl);

		return sb.toString();
	}

	public String getScriptAlertJQuery() throws NullPointerException {
		StringBuilder sb = new StringBuilder();

		sb.append( getTab(2) + "<script type=\"text/javascript\" class=\"init\">" + pl);
		sb.append( getTab(3) + "$(function(){" + pl);
		sb.append( getTab(4) + "$(\"#aviso\").fadeIn(700, function(){" + pl);
		sb.append( getTab(5) + "window.setTimeout(function(){" + pl);
		sb.append( getTab(6) + "$('#aviso').fadeOut();" + pl);
		sb.append( getTab(5) + "}, 6000);" + pl);
		sb.append( getTab(4) + "});" + pl);
		sb.append( getTab(3) + "});" + pl);
		sb.append( getTab(2) + "</script>" + pl);

		return sb.toString();
	}

	/**
	 * Cria o script que configura uma tabela com o DataTable. 
	 * 
	 * @param tableName
	 * @return String
	 */
	public String getScriptDataTable(String tableName) throws NullPointerException {
		StringBuilder sb = new StringBuilder();

		sb.append( getTab(2) + "<script type=\"text/javascript\" class=\"init\">" + pl);
		sb.append( getTab(3) + "$(document).ready(function() {" + pl);
		sb.append( getTab(4) + "$('#" + tableName + "').DataTable({" + pl);
		sb.append( getTab(5) + "\"language\": {" + pl);
		sb.append( getTab(6) + "\"lengthMenu\": \"Exibindo _MENU_ registros por página\"," + pl);
		sb.append( getTab(6) + "\"zeroRecords\": \"Nada encontrado\"," + pl);
		sb.append( getTab(6) + "\"info\": \"Mostrando página _PAGE_ de _PAGES_\"," + pl);
		sb.append( getTab(6) + "\"infoEmpty\": \"Nenhum registro disponível\"," + pl);
		sb.append( getTab(6) + "\"infoFiltered\": \"(Filtrados de _MAX_ registros)\"," + pl);
		sb.append( getTab(6) + "\"sSearch\": \"Pesquisar\"," + pl);
		sb.append( getTab(6) + "\"oPaginate\": {" + pl);
		sb.append( getTab(7) + "\"sNext\": \"Próximo\"," + pl);
		sb.append( getTab(7) + "\"sPrevious\": \"Anterior\"," + pl);
		sb.append( getTab(7) + "\"sFirst\": \"Primeiro\"," + pl);
		sb.append( getTab(7) + "\"sLast\": \"Último\"" + pl);
		sb.append( getTab(6) + "}" + pl);
		sb.append( getTab(5) + "}" + pl);
		sb.append( getTab(4) + "});" + pl);
		sb.append( getTab(3) + "} );" + pl);
		sb.append( getTab(2) + "</script>" + pl);

		return sb.toString();
	}

	/**
	 * Cria a div rodapé.
	 * 
	 * @param value
	 * @return String
	 */
	public String getDivFooterHtml(String value) throws NullPointerException {
		StringBuilder sb = new StringBuilder();

		sb.append( getTab(2) + "<div id=\"area-rodape\" class=\"footer navbar-fixed-bottom\">" + pl);
		sb.append( getTab(2) + value + pl);
		sb.append( getTab(2) + "</div>" + pl);

		return sb.toString();
	}

	public String getDivHeaderHtml(HeaderHtml header) throws NullPointerException {
		StringBuilder sb = new StringBuilder();

		sb.append( getTab(3) + "<div id=\"area-cabecalho\">" + pl);
		sb.append( getTab(4) + "<div id=\"area-logo\">" + pl);
		sb.append( getTab(5) + "<h1><a href=\"" + header.getHrefIndex() + "\" id=\"buttonHome\"><img src=\"" + header.getUrlIconImg() + "\" alt=\"index\">" + header.getTitulo() + "</a></h1>" + pl);
		sb.append( getTab(4) + "</div>" + pl);
		sb.append( getTab(3) + "</div>" + pl);

		return sb.toString();
	}

	public String openFormHtml(FormHtml form) throws NullPointerException {
		StringBuilder sb = new StringBuilder();

		sb.append(getTab(2) + "<form action=\"" + form.getAction() + "\" method=\"" + form.getMethod() + "\" " + (form.getId() != null ? "id=\"" + form.getId() + "\"" : "") + (form.getClassCss() != null ? " class=\"" + form.getClassCss() + "\"" : "") + ">" + pl);
		sb.append(getTab(3) + "<fieldset>" + pl);
		sb.append(getTab(4) + "<legend>" + form.getLegend() + "</legend>" + pl);

		return sb.toString();
	}

	public String closeFormHtml() {
		StringBuilder sb = new StringBuilder();

		sb.append(getTab(3) + "</fieldset>" + pl);
		sb.append(getTab(2) + "</form>" + pl);

		return sb.toString();
	}

	public String openDivHtml(DivHtml div, Integer tabs) {
		StringBuilder sb = new StringBuilder();

		sb.append(getTab(tabs) + "<div ");
		sb.append( (div.getClassCss() != null ? " class=\"" + div.getClassCss() + "\"" : "" ) );
		sb.append( (div.getId() != null ? "id=\"" + div.getId() + "\"" : "" ) );
		sb.append( (div.getStyle() != null ? "style=\"" + div.getStyle() + "\"" : "" ) );
		sb.append(">" + pl);

		return sb.toString();
	}

	public String closeDivHtml(Integer tabs) {
		StringBuilder sb = new StringBuilder();

		sb.append(getTab(tabs) + "</div>" + pl);

		return sb.toString();
	}

	public String getLabel(LabelHtml label) throws NullPointerException {
		StringBuilder sb = new StringBuilder();

		sb.append(getTab(4) + "<label for=\"" + label.getId() + "\">" + label.getValue() + "</label> " + pl);

		return sb.toString();
	}
	
	public String getSelect(SelectHtml select) {
		StringBuilder sb = new StringBuilder();
		
		sb.append(getTab(4) + "<select ");
		sb.append( (select.getId() != null ? " id=\"" + select.getId() + "\" " : "" ) );
		sb.append( (select.getName() != null ? " name=\"" + select.getName() + "\" " : "" ) );
		sb.append( (select.getClassCss() != null ? " class=\""+ select.getClassCss() + "\" " : "" ) );
		sb.append( (select.getDisable() != null ? (select.getDisable() ? " disable " : "" ) : "" ) );
		sb.append( (select.getRequired() != null ? (select.getRequired() ? " required " : "" ) : "" ) );
		sb.append(" >" + pl);
		
		for(OptionHtml option : select.getOpcoes()) {
			sb.append(getTab(5) + "<option ");
			sb.append( (option.getValue() != null ? " value=\"" + option.getValue() + "\"" : "" ) );
			sb.append( (option.getDisabled() != null ? (option.getDisabled() ? " disabled  " : "" ) : "" ) );
			sb.append( (option.getSelected() != null ? (option.getSelected() ? " selected " : "" ) : "" ) );
			sb.append(">");
			sb.append( (option.getLabel() != null ? option.getLabel() : "" ) );
			sb.append("</option>" + pl);
		}
		
		sb.append(getTab(4) + "</select>" + pl);
		return sb.toString();
	}

	public String getInput(InputHtml input) throws NullPointerException {
		StringBuilder sb = new StringBuilder();

		sb.append(getTab(4) + "<input ");
		sb.append( (input.getType() != null ? " type=\"" + input.getType() + "\" " : "") );
		sb.append( (input.getName() != null ? " name=\"" + input.getName() + "\" ": "") );
		sb.append( (input.getId() != null ? " id=\"" + input.getId() + "\" ": "") );
		sb.append( (input.getClassCss() != null ? " class=\"" + input.getClassCss() + "\" ": "") );
		sb.append( (input.getPlaceholder() != null ? " placeholder=\"" + input.getPlaceholder() + "\" ": "") );
		sb.append( (input.getMaxlength() != null ? " maxlength=\"" + input.getMaxlength() + "\" ": "") );
		sb.append( (input.getValue() != null ? " value=\"" + input.getValue() + "\" ": "") );
		sb.append( (input.getRequired() != null ? (input.getRequired() ? " required " : "" ) : "") );
		sb.append( (input.getReadonly() != null ? (input.getReadonly() ? " readonly " : "" ) : "") );
		sb.append( (input.getChecked() != null ? (input.getChecked() ? " checked " : "" ) : "" ) );
		sb.append(" >" + pl);

		return sb.toString();
	}

	public String getScriptConfirm(String message, String url, String nameFunction) throws NullPointerException {
		StringBuilder sb = new StringBuilder();

		sb.append(getTab(2) + "<script language=\"Javascript\">" + pl);
		sb.append(getTab(3) + "function " + nameFunction + "(id) {" + pl);
		sb.append(getTab(4) + "var resposta = confirm(\"" + message + " Código: \"+id);" + pl);
		sb.append(getTab(4) + "if(resposta == true) {" + pl);
		sb.append(getTab(5) + "window.location.href = \"" + url + "\"+id;" + pl);
		sb.append(getTab(4) + "}" + pl);
		sb.append(getTab(3) + "}" + pl);
		sb.append(getTab(2) + "</script>" + pl);

		return sb.toString();
	}

	public String getHorizontalMenuHtml(String[][] itens ) throws NullPointerException {
		StringBuilder sb = new StringBuilder();

		sb.append(getTab(3) + "<div id=\"area-menu\">" + pl);

		sb.append(getTab(4));
		for(int i=0; i<itens.length; i++) {
			sb.append("<a href=\"" + itens[i][1] + "\">" + itens[i][0] + "</a>");
		}

		sb.append(pl + getTab(3) + "</div>" + pl);

		return sb.toString();
	}

	public String openDivMain() {
		StringBuilder sb = new StringBuilder();

		sb.append(getTab(2) + "<div id=\"area-principal\">" + pl);

		return sb.toString();
	}

	public String closeDivMain() {
		StringBuilder sb = new StringBuilder();

		sb.append(getTab(2) + "</div>" + pl);

		return sb.toString();
	}

	public String getDivTopoConteudo(String label, String urlButton) throws NullPointerException {
		StringBuilder sb = new StringBuilder();

		sb.append(getTab(3) + "<div id=\"topo-conteudo\">" + pl);
		sb.append(getTab(4) + "<h3>" + label + "</h3>" + pl);
		sb.append(getTab(4) + "<a href=\"" + urlButton + "\" class=\"botao\"><img src=\"estilos/img/Add.png\"></span>Incluir</a>" + pl);
		sb.append(getTab(3) + "</div>" + pl);

		return sb.toString();
	}

	//	public String openTableHtml(String id) throws NullPointerException {
	//		StringBuilder sb = new StringBuilder();
	//
	//		sb.append("<table id=\"" + id + "\" class=\"table table-striped table-bordered\" style=\"width:100%\">" + pl);
	//
	//		return sb.toString();
	//	}

	//	public String getTHeadHtml(String... columns) throws NullPointerException {
	//		StringBuilder sb = new StringBuilder();
	//
	//		sb.append("<thead>" + pl);
	//		sb.append("<tr>" + pl);
	//
	//		for(String column : columns) {
	//			sb.append("<th>" + column + "</th>" + pl);
	//		}
	//
	//		sb.append("<th>Ações</th>" + pl);
	//		sb.append("</tr>" + pl);
	//		sb.append("</thead>" + pl);
	//
	//		return sb.toString();
	//	}

	//	public String getTBodyHtml(List<?> itens) throws NullPointerException {
	//		StringBuilder sb = new StringBuilder();
	//
	//		sb.append("<tbody>" + pl);
	//
	//		sb.append("</tbody>" + pl);
	//
	//		return sb.toString();
	//	}

	public String getTableHtml(String id, String[] columns, Object[][] data) throws NullPointerException {
		StringBuilder sb = new StringBuilder();

		sb.append("<table id=\"" + id + "\" class=\"table table-striped table-bordered\" style=\"width:100%\">" + pl);
		sb.append("<thead>" + pl);
		sb.append("<tr>" + pl);

		for(String column : columns) {
			sb.append("<th>" + column + "</th>" + pl);
		}

		sb.append("</tr>" + pl);
		sb.append("</thead>" + pl);
		sb.append("<tbody>" + pl);

		for(int i=0; i<data.length; i++) {

			sb.append("<tr>");

			for(int y=0; y<data[i].length; y++) {

				sb.append("    <td" + ( y==(data[i].length -1) ? " class=\"centralizarConteudo\"" : "") + ">" + data[i][y] + "</td>");

			}

			sb.append("</tr>");

		}

		sb.append("</tbody>" + pl);
		sb.append("</form>" + pl);

		return sb.toString();
	}

	public String criarMensagemJavascript(String mensagem) {

		if(mensagem == null) {
			mensagem = "Ocorreu um erro! Verifique os dados informados.";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("<SCRIPT LANGUAGE=\"JavaScript\" TYPE=\"text/javascript\">" + pl);
		//sb.append("  window.alert(\"" + mensagem + "\");" + pl);
		sb.append("  javascript:history.go(-1)" + pl);
		
		sb.append("</SCRIPT>" + pl);

		return sb.toString();
	}



}