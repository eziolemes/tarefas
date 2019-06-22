package br.com.ezio.tarefas.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.ezio.tarefas.bean.TarefaBean;

/**
 * 
 * @author Ezio Lemes
 *
 */
public class TarefaDao extends GenericDao<TarefaBean> {

	@Override
	public void insert(TarefaBean bean) throws SQLException {
		StringBuilder sql = new StringBuilder();

		sql.append("INSERT INTO TAREFA(TAR_DESCRICAO,TAR_ATIVO) VALUES(?,?);");

		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ps.setString(1, bean.getDescricao());
		ps.setString(2, "S");

		ps.execute();

		ps.close();
		conn.close();
	}

	@Override
	public void update(TarefaBean bean) throws SQLException {
		StringBuilder sql = new StringBuilder();

		sql.append("UPDATE TAREFA SET TAR_DESCRICAO=?,TAR_ATIVO=? WHERE TAR_ID=?;");

		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ps.setString(1, bean.getDescricao());
		ps.setString(2, (bean.getAtivo() ? "S" : "N" ));
		ps.setInt(3, bean.getId());

		ps.execute();

		ps.close();
		conn.close();
	}
	
	public void updateStatus(TarefaBean bean) throws SQLException {
		StringBuilder sql = new StringBuilder();

		sql.append("UPDATE TAREFA SET TAR_ATIVO=? WHERE TAR_ID=?;");

		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ps.setString(1, (bean.getAtivo() ? "S" : "N" ));
		ps.setInt(2, bean.getId());

		ps.execute();

		ps.close();
		conn.close();
	}
	
	public TarefaBean findById(Integer id) throws SQLException {
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT * FROM TAREFA WHERE TAR_ID = ?");

		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ps.setInt(1, id);

		ResultSet rs = ps.executeQuery();

		rs.next();
		TarefaBean tarefa = new TarefaBean();

		tarefa.setId(rs.getInt("TAR_ID"));
		tarefa.setDescricao(rs.getString("TAR_DESCRICAO"));
		tarefa.setAtivo( (rs.getString("TAR_ATIVO").equals("S") ? true : false) );

		rs.close();
		ps.close();
		conn.close();

		return tarefa;
	}

	@Override
	public List<TarefaBean> find(String where) throws SQLException {
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT * FROM TAREFA " + pl);
		sql.append( (where != null ? (where + pl) : "") );
		sql.append("ORDER BY TAR_DESCRICAO" + pl);

		PreparedStatement ps = conn.prepareStatement(sql.toString());

		ResultSet rs = ps.executeQuery();

		List<TarefaBean> lista = new ArrayList();

		while(rs.next()) {
			TarefaBean tarefa = new TarefaBean();

			tarefa.setId(rs.getInt("TAR_ID"));
			tarefa.setDescricao(rs.getString("TAR_DESCRICAO"));
			tarefa.setAtivo( (rs.getString("TAR_ATIVO").equals("S") ? true : false) );

			lista.add(tarefa);
		}

		rs.close();
		ps.close();
		conn.close();

		return lista;
	}
	
	public String where(String column, String comparator, String value) throws NullPointerException {
		StringBuilder sb = new StringBuilder();

		sb.append("WHERE " + column + comparator + value);
		
		return sb.toString();
	}

}
