package br.com.ezio.tarefas.bean;

public class PessoaBean implements BaseEntity{
	
	private Integer id;
	private String nome;
	
	@Override
	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
