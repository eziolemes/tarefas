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
abstract class GenericDao<T extends BaseEntity> {
	
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
	
	protected abstract void insert(T bean) throws SQLException;
	
	protected abstract void update(T bean) throws SQLException;
	
	protected abstract List<T> find(String where) throws SQLException;
	
}