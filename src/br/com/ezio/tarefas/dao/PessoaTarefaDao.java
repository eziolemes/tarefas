package br.com.ezio.tarefas.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.ezio.tarefas.bean.PessoaTarefaBean;
import br.com.ezio.tarefas.bean.TarefaBean;

public class PessoaTarefaDao extends GenericDao<PessoaTarefaBean>{

	@Override
	public void insert(PessoaTarefaBean bean) throws SQLException {
		StringBuilder sql = new StringBuilder();

		sql.append("INSERT INTO PESSOA_TAREFA(PESTAR_TAREFA_ID, PESTAR_PESSOA_ID, PESTAR_PERCENTUAL, PESTAR_DATA_INICIO, PESTAR_DATA_FIM, PESTAR_FINALIZADO)");
		sql.append(" VALUES(?, ?, ?, ?, ?, ?);");

		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ps.setInt(1, bean.getTarefa().getId());
		ps.setInt(2, bean.getPessoa().getId());
		ps.setBigDecimal(3, bean.getPercentual());
		ps.setDate(4, new java.sql.Date( bean.getDataInicio().getTime() ));
		ps.setDate(5, new java.sql.Date( bean.getDataFim().getTime() ));
		ps.setString(6, (bean.getFinalizado() ? "S" : "N") );

		ps.execute();
		ps.close();
		conn.close();
	}

	@Override
	public void update(PessoaTarefaBean bean) throws SQLException {
		StringBuilder sql = new StringBuilder();

		sql.append("UPDATE PESSOA_TAREFA SET " + pl);
		sql.append("       PESTAR_TAREFA_ID=?, " + pl);
		sql.append("       PESTAR_PESSOA_ID=?, " + pl);
		sql.append("       PESTAR_PERCENTUAL=?, " + pl);
		sql.append("       PESTAR_DATA_INICIO=?, " + pl);
		sql.append("       PESTAR_DATA_FIM=?, " + pl);
		sql.append("       PESTAR_FINALIZADO=?, " + pl);
		sql.append("       PESTAR_ATIVO=? " + pl);
		sql.append("WHERE TAR_ID=?;");
		
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ps.setInt(1, bean.getTarefa().getId());
		ps.setInt(2, bean.getPessoa().getId());
		ps.setBigDecimal(3, bean.getPercentual());
		ps.setDate(4, new java.sql.Date(bean.getDataInicio().getTime()));
		ps.setDate(5, (bean.getDataFim()!= null ? new java.sql.Date(bean.getDataFim().getTime()) : null));
		ps.setString(6, (bean.getFinalizado() ? "S" : "N"));
		ps.setString(7, (bean.getAtivo() ? "S" : "N" ));
		ps.setInt(8, bean.getId());

		ps.execute();

		ps.close();
		conn.close();
	}
	
	public void updateStatus(PessoaTarefaBean bean) throws SQLException {
		StringBuilder sql = new StringBuilder();

		sql.append("UPDATE PESSOA_TAREFA SET PESTAR_ATIVO=? WHERE PESTAR_ID=?;");

		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ps.setString(1, (bean.getAtivo() ? "S" : "N" ));
		ps.setInt(2, bean.getId());

		ps.execute();

		ps.close();
		conn.close();
	}

	public PessoaTarefaBean findById(Integer id) throws SQLException {
		StringBuilder sb = new StringBuilder();

		sb.append("SELECT PESTAR_ID," + pl);
		sb.append("       PESTAR_TAREFA_ID," + pl);
		sb.append("       TAR_DESCRICAO," + pl);
		sb.append("       PESTAR_PESSOA_ID," + pl);
		sb.append("       PES_NOME," + pl);
		sb.append("       PESTAR_PERCENTUAL," + pl);
		sb.append("       PESTAR_DATA_INICIO," + pl);
		sb.append("       PESTAR_DATA_FIM," + pl);
		sb.append("       PESTAR_FINALIZADO," + pl);
		sb.append("       PESTAR_ATIVO" + pl);
		sb.append("FROM PESSOA_TAREFA" + pl);
		sb.append("INNER JOIN PESSOA ON PESSOA.PES_ID = PESSOA_TAREFA.PESTAR_PESSOA_ID" + pl);
		sb.append("INNER JOIN TAREFA ON TAREFA.TAR_ID = PESSOA_TAREFA.PESTAR_TAREFA_ID" + pl);
		sb.append("WHERE PESTAR_ID = ?");

		PreparedStatement ps = conn.prepareStatement(sb.toString());
		ps.setInt(1, id);

		ResultSet rs = ps.executeQuery();

		PessoaTarefaBean bean = new PessoaTarefaBean();

		bean.setId(rs.getInt("PESTAR_ID"));
		bean.getTarefa().setId(rs.getInt("PESTAR_TAREFA_ID"));
		bean.getTarefa().setDescricao(rs.getString("TAR_DESCRICAO"));
		bean.getPessoa().setId(rs.getInt("PESTAR_PESSOA_ID"));
		bean.getPessoa().setNome(rs.getString("PES_NOME"));
		bean.setPercentual(rs.getBigDecimal("PESTAR_PERCENTUAL"));
		bean.setDataInicio(rs.getDate("PESTAR_DATA_INICIO"));
		bean.setDataFim(rs.getDate("PESTAR_DATA_FIM"));
		bean.setFinalizado( (rs.getString("PESTAR_FINALIZADO").equals("S") ? true : false) );
		bean.setAtivo( (rs.getString("PESTAR_ATIVO").equals("S") ? true : false) );

		rs.close();
		ps.close();
		conn.close();

		return bean;
	}

	@Override
	public List<PessoaTarefaBean> find(String where) throws SQLException {
		StringBuilder sb = new StringBuilder();

		sb.append("SELECT PESTAR_ID," + pl);
		sb.append("       PESTAR_TAREFA_ID," + pl);
		sb.append("       TAR_DESCRICAO," + pl);
		sb.append("       PESTAR_PESSOA_ID," + pl);
		sb.append("       PES_NOME," + pl);
		sb.append("       PESTAR_PERCENTUAL," + pl);
		sb.append("       PESTAR_DATA_INICIO," + pl);
		sb.append("       PESTAR_DATA_FIM," + pl);
		sb.append("       PESTAR_FINALIZADO," + pl);
		sb.append("       PESTAR_ATIVO" + pl);
		sb.append("FROM PESSOA_TAREFA" + pl);
		sb.append("INNER JOIN PESSOA ON PESSOA.PES_ID = PESSOA_TAREFA.PESTAR_PESSOA_ID" + pl);
		sb.append("INNER JOIN TAREFA ON TAREFA.TAR_ID = PESSOA_TAREFA.PESTAR_TAREFA_ID" + pl);
		sb.append( (where != null ? (where + pl) : "") );
		sb.append("ORDER BY TAR_DESCRICAO");

		PreparedStatement ps = conn.prepareStatement(sb.toString());

		ResultSet rs = ps.executeQuery();

		List<PessoaTarefaBean> lista = new ArrayList();

		while(rs.next()) {
			PessoaTarefaBean bean = new PessoaTarefaBean();

			bean.setId(rs.getInt("PESTAR_ID"));
			bean.getTarefa().setId(rs.getInt("PESTAR_TAREFA_ID"));
			bean.getTarefa().setDescricao(rs.getString("TAR_DESCRICAO"));
			bean.getPessoa().setId(rs.getInt("PESTAR_PESSOA_ID"));
			bean.getPessoa().setNome(rs.getString("PES_NOME"));
			bean.setPercentual(rs.getBigDecimal("PESTAR_PERCENTUAL"));
			bean.setDataInicio(rs.getDate("PESTAR_DATA_INICIO"));
			bean.setDataFim(rs.getDate("PESTAR_DATA_FIM"));
			bean.setFinalizado( (rs.getString("PESTAR_FINALIZADO").equals("S") ? true : false) );
			bean.setAtivo( (rs.getString("PESTAR_ATIVO").equals("S") ? true : false) );

			lista.add(bean);
		}

		rs.close();
		ps.close();
		conn.close();

		return lista;
	}

}