package br.com.ezio.tarefas.view;
import java.util.List;

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
		sb.append( getTab(6) + "\"lengthMenu\": \"Exibindo _MENU_ registros por pÃ¡gina\"," + pl);
		sb.append( getTab(6) + "\"zeroRecords\": \"Nada encontrado\"," + pl);
		sb.append( getTab(6) + "\"info\": \"Mostrando pÃ¡gina _PAGE_ de _PAGES_\"," + pl);
		sb.append( getTab(6) + "\"infoEmpty\": \"Nenhum registro disponÃ­vel\"," + pl);
		sb.append( getTab(6) + "\"infoFiltered\": \"(Filtrados de _MAX_ registros)\"," + pl);
		sb.append( getTab(6) + "\"sSearch\": \"Pesquisar\"," + pl);
		sb.append( getTab(6) + "\"oPaginate\": {" + pl);
		sb.append( getTab(7) + "\"sNext\": \"PrÃ³ximo\"," + pl);
		sb.append( getTab(7) + "\"sPrevious\": \"Anterior\"," + pl);
		sb.append( getTab(7) + "\"sFirst\": \"Primeiro\"," + pl);
		sb.append( getTab(7) + "\"sLast\": \"Ãšltimo\"" + pl);
		sb.append( getTab(6) + "}" + pl);
		sb.append( getTab(5) + "}" + pl);
		sb.append( getTab(4) + "});" + pl);
		sb.append( getTab(3) + "} );" + pl);
		sb.append( getTab(2) + "</script>" + pl);

		return sb.toString();
	}

	/**
	 * Cria a div rodapÃ©.
	 * 
	 * @param value
	 * @return String
	 */
	public String getDivFooterHtml(String value) throws NullPointerException {
		StringBuilder sb = new StringBuilder();

		sb.append( getTab(2) + "<div class=\"footer\">" + pl);
		sb.append( getTab(2) + value + pl);
		sb.append( getTab(2) + "</div>" + pl);

		return sb.toString();
	}

	public String getDivHeaderHtml(HeaderHtml header) throws NullPointerException {
		StringBuilder sb = new StringBuilder();

		sb.append( getTab(3) + "<div class=\"header\">" + pl);
		sb.append( getTab(4) + "<h4><a href=\"" + header.getHrefIndex() + "\" id=\"buttonHome\"><img src=\"" + header.getUrlIconImg() + "\" alt=\"index\">" + header.getTitulo() + "</a></h4>" + pl);
		sb.append( getTab(3) + "</div>" + pl);

		return sb.toString();
	}

	public String openForm(String action, String method, String idCss, String classCss) throws NullPointerException {
		StringBuilder sb = new StringBuilder();

		sb.append(getTab(2) + "<form action=\"" + action + "\" method=\"" + method + "\" " + (idCss != null ? "id=\"" + idCss + "\"" : "") + (classCss != null ? " class=\"" + classCss + "\"" : "") + ">" + pl);

		return sb.toString();
	}

	public String closeForm() {
		StringBuilder sb = new StringBuilder();

		sb.append(getTab(2) + "</form>" + pl);

		return sb.toString();
	}

	public String openFormRow() {
		StringBuilder sb = new StringBuilder();

		sb.append(getTab(3) + "<div class=\"form-group row\">" + pl);

		return sb.toString();
	}

	public String closeFormRow() {
		StringBuilder sb = new StringBuilder();

		sb.append(getTab(3) + "</div>" + pl);

		return sb.toString();
	}

	public String openFormColumn(Integer size) throws NullPointerException {
		StringBuilder sb = new StringBuilder();

		sb.append(getTab(3) + "<div class=\"col-lg-" + size + "\">" + pl);

		return sb.toString();
	}

	public String closeFormColumn() {
		StringBuilder sb = new StringBuilder();

		sb.append(getTab(3) + "</div>" + pl);

		return sb.toString();
	}

	public String getLabel(String value, String forLabel) throws NullPointerException {
		StringBuilder sb = new StringBuilder();

		sb.append(getTab(3) + "<label for=\"" + forLabel + "\">" + value + "</label> " + pl);

		return sb.toString();
	}

	public String getInput(InputHtml input) throws NullPointerException {
		StringBuilder sb = new StringBuilder();

		sb.append(getTab(3) + "<input ");
		sb.append( (input.getType() != null ? " type=\"" + input.getType() + "\" " : "") );
		sb.append( (input.getName() != null ? " name=\"" + input.getName() + "\" ": "") );
		sb.append( (input.getId() != null ? " id=\"" + input.getId() + "\" ": "") );
		sb.append( (input.getClassCss() != null ? " class=\"" + input.getClassCss() + "\" ": "") );
		sb.append( (input.getPlaceholder() != null ? " placeholder=\"" + input.getPlaceholder() + "\" ": "") );
		sb.append( (input.getMaxlength() != null ? " maxlength=\"" + input.getMaxlength() + "\" ": "") );
		sb.append( (input.getValue() != null ? " value=\"" + input.getValue() + "\" ": "") );
		sb.append( (input.getRequired() != null ? (input.getRequired() ? " required " : "" ) : "") );
		sb.append(" >" + pl);

		return sb.toString();
	}

	public String getScriptConfirm(String message, String url) throws NullPointerException {
		StringBuilder sb = new StringBuilder();

		sb.append(getTab(2) + "<script language=\"Javascript\">" + pl);
		sb.append(getTab(3) + "function confirmacao(id) {" + pl);
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

		sb.append(getTab(3) + "<div id=\"menuHorizontal\">" + pl);

		sb.append(getTab(4));
		for(int i=0; i<itens.length; i++) {
			sb.append("<a href=\"" + itens[i][1] + "\">" + itens[i][0] + "</a>");
		}

		sb.append(getTab(3) + "</div>" + pl);

		return sb.toString();
	}

	public String getH3(String value) throws NullPointerException {
		StringBuilder sb = new StringBuilder();

		sb.append("<h3>" + value + "</h3>" + pl);

		return sb.toString();
	}

	public String getBotaoIncluir(String url) throws NullPointerException {
		StringBuilder sb = new StringBuilder();

		sb.append("<a href=\"" + url + "\" class=\"botao\"><img src=\"img/Add.png\"></span>Incluir</a>" + pl);

		return sb.toString();
	}

	public String openTableHtml(String id) throws NullPointerException {
		StringBuilder sb = new StringBuilder();

		sb.append("<table id=\"" + id + "\" class=\"table table-striped table-bordered\" style=\"width:100%\">" + pl);

		return sb.toString();
	}
	
	public String getTHeadHtml(String... columns) throws NullPointerException {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<thead>" + pl);
		sb.append("<tr>" + pl);
		
		for(String column : columns) {
			sb.append("<th>" + column + "</th>" + pl);
		}
		
		sb.append("<th>Ações</th>" + pl);
		sb.append("</tr>" + pl);
		sb.append("</thead>" + pl);
		
		return sb.toString();
	}
	
	public String getTBodyHtml(List<?> itens) throws NullPointerException {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<tbody>" + pl);
		
		sb.append("</tbody>" + pl);
		
		return sb.toString();
	}
	
	public String getTableHtml(String id, String[] columns, Object[][] data) throws NullPointerException {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<table id=\"" + id + "\" class=\"table table-striped table-bordered\" style=\"width:100%\">" + pl);
		sb.append("<thead>" + pl);
		sb.append("<tr>" + pl);
		
		for(String column : columns) {
			sb.append("<th>" + column + "</th>" + pl);
		}
		
		sb.append("<th>Ações</th>" + pl);
		sb.append("</tr>" + pl);
		sb.append("</thead>" + pl);
		sb.append("<tbody>" + pl);
		
		for(int i=0; i<data.length; i++) {
			
			sb.append("<tr>");
			
        	for(int y=0; y<data[i].length; y++) {
        		
        		sb.append("    <td>" + data[i][y] + "</td>");
        		
        	}
        	
        	sb.append("</tr>");
        	
        }
		
		sb.append("</tbody>" + pl);
		sb.append("</html>" + pl);
		
		return sb.toString();
	}
	
}