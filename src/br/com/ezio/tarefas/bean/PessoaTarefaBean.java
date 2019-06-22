package br.com.ezio.tarefas.bean;

import java.math.BigDecimal;
import java.util.Date;

public class PessoaTarefaBean implements BaseEntity {
	
	private Integer id;
	private PessoaBean pessoa;
	private TarefaBean tarefa;
	private BigDecimal percentual;
	private Date dataInicio;
	private Date dataFim;
	private Boolean finalizado;
	private Boolean ativo;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public PessoaBean getPessoa() {
		return pessoa;
	}
	public void setPessoa(PessoaBean pessoa) {
		this.pessoa = pessoa;
	}
	public TarefaBean getTarefa() {
		return tarefa;
	}
	public void setTarefa(TarefaBean tarefa) {
		this.tarefa = tarefa;
	}
	public BigDecimal getPercentual() {
		return percentual;
	}
	public void setPercentual(BigDecimal percentual) {
		this.percentual = percentual;
	}
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	public Date getDataFim() {
		return dataFim;
	}
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	public Boolean getFinalizado() {
		return finalizado;
	}
	public void setFinalizado(Boolean finalizado) {
		this.finalizado = finalizado;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

}