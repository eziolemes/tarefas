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
		
		sql.append("INSERT INTO PESSOA(PES_NOME) VALUES(?);");
		
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ps.setString(1, bean.getNome());
		
		ps.execute();
		
		ps.close();
		conn.close();
	}

	@Override
	public void update(PessoaBean bean) throws SQLException {
		// TODO Auto-generated method stub
		
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
			
			lista.add(pessoa);
		}
		
		rs.close();
		ps.close();
		conn.close();

		return lista;
	}

}