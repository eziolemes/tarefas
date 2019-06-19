package br.com.ezio.tarefas.bean;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "TAREFA")
public class TarefaBean implements BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TAR_ID")
	private Integer id;
	
	@Column(name = "TAR_DESCRICAO")
	private String descricao;
	
	@ManyToMany(mappedBy="tarefas")
	private List<PessoaBean> pessoas;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public List<PessoaBean> getPessoas() {
		return pessoas;
	}
	public void setPessoas(List<PessoaBean> pessoas) {
		this.pessoas = pessoas;
	}
	
}