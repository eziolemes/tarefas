package br.com.ezio.tarefas.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.ezio.tarefas.bean.BaseEntity;
import br.com.ezio.tarefas.factory.ConnectionFactory;

/**
 * 
 * @author Ezio Lemes
 *
 */
public abstract class GenericDao<T extends BaseEntity> {
	
	protected String pl = "\n";
	protected Connection conn;
	
	public GenericDao() {
		try {
			conn = new ConnectionFactory().getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public abstract void insert(T bean) throws SQLException;
	
	public abstract void update(T bean) throws SQLException;
	
	public abstract List<T> find(String sql) throws SQLException;
	
	/*
	public void save(String sql) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.execute();
		ps.close();
		conn.close();
	}
	
	public abstract String queryInsert(T bean) throws NullPointerException;
	
	public abstract String queryUpdate(T bean) throws NullPointerException;
	
	public abstract String querySelect(T bean) throws NullPointerException;
	*/
	
}