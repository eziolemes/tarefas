package br.com.ezio.tarefas.bean;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "PESSOA")
//@SecondaryTable(name="TAREFA", pkJoinColumns = { @PrimaryKeyJoinColumn(name="id")})
public class PessoaBean implements BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PES_ID")
	private Integer id;
	
	@Column(name="PES_NOME")
	private String nome;
	
	@ManyToMany
	@JoinTable(name="PESSOA_HAS_TAREFAS", joinColumns=
	{@JoinColumn(name="PESSOA_ID")}, inverseJoinColumns=
	  {@JoinColumn(name="TAREFA_ID")})
	private List<TarefaBean> tarefas;
	
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

	public List<TarefaBean> getTarefas() {
		return tarefas;
	}

	public void setTarefas(List<TarefaBean> tarefas) {
		this.tarefas = tarefas;
	}

}
