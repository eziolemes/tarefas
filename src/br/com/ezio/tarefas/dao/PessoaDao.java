package br.com.ezio.tarefas.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.ezio.tarefas.bean.PessoaBean;

/**
 * 
 * @author Ezio Lemes
 *
 */
public class PessoaDao extends GenericDao<PessoaBean>{

	@Override
	public void insert(PessoaBean bean) throws SQLException {
		StringBuilder sql = new StringBuilder();

		sql.append("INSERT INTO PESSOA(PES_NOME,PES_ATIVO) VALUES(?,?);");

		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ps.setString(1, bean.getNome());
		ps.setString(2, "S");

		ps.execute();

		ps.close();
		conn.close();
	}

	@Override
	public void update(PessoaBean bean) throws SQLException {
		StringBuilder sql = new StringBuilder();

		sql.append("UPDATE PESSOA SET PES_NOME=?,PES_ATIVO=? WHERE PES_ID=?;");

		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ps.setString(1, bean.getNome());
		ps.setString(2, (bean.getAtivo() ? "S" : "N" ));
		ps.setInt(3, bean.getId());

		ps.execute();

		ps.close();
		conn.close();
	}

	public PessoaBean findById(Integer id) throws SQLException {
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT * FROM PESSOA WHERE PES_ID = ?");

		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ps.setInt(1, id);

		ResultSet rs = ps.executeQuery();

		rs.next();
		PessoaBean pessoa = new PessoaBean();

		pessoa.setId(rs.getInt("PES_ID"));
		pessoa.setNome(rs.getString("PES_NOME"));
		pessoa.setAtivo( (rs.getString("PES_ATIVO").equals("S") ? true : false) );

		rs.close();
		ps.close();
		conn.close();

		return pessoa;
	}

	@Override
	public List<PessoaBean> find(String where) throws SQLException {
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT * FROM PESSOA " + pl);
		sql.append( (where != null ? (where + pl) : "") );
		sql.append("ORDER BY PES_NOME" + pl);

		PreparedStatement ps = conn.prepareStatement(sql.toString());

		ResultSet rs = ps.executeQuery();

		List<PessoaBean> lista = new ArrayList();

		while(rs.next()) {
			PessoaBean pessoa = new PessoaBean();

			pessoa.setId(rs.getInt("PES_ID"));
			pessoa.setNome(rs.getString("PES_NOME"));
			pessoa.setAtivo( (rs.getString("PES_ATIVO").equals("S") ? true : false) );

			lista.add(pessoa);
		}

		rs.close();
		ps.close();
		conn.close();

		return lista;
	}

}