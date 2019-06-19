package br.com.ezio.tarefas.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.ezio.tarefas.bean.BaseEntity;
import br.com.ezio.tarefas.factory.ConnectionFactory;

/**
 * 
 * @author Ezio Lemes
 *
 */
public class GenericDao<T extends BaseEntity> {
	
	//TODO criar um update, pois todo insert na tab de rel terá uma id
	public T save(T t) {
		EntityManager em = new ConnectionFactory().getConnection();
		
		try {
			
			em.getTransaction().begin();
			
			if(t.getId() == null) {
				
				System.out.println("persist");
				em.persist(t);
			} else {
				System.out.println("merge");
				em.merge(t);
			}
			
			em.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		//	em.getTransaction().rollback();
		}finally {
			em.close();
		}
		
		return t;
	}
	
	public T findById(Class<T> clazz, Integer id) {
		EntityManager em = new ConnectionFactory().getConnection();
		T t = null;
		
		try {
			
			t = em.find(clazz, id);
			
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			em.close();
		}
		
		return t;
	}
	
	public List<T> findAll(String table) {
		EntityManager em = new ConnectionFactory().getConnection();
		List<T> produtos = null;
		
		try {
			
			produtos = em.createQuery("from " + table + " t").getResultList();
			
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			em.close();
		}
		
		return produtos;
	}
	
	public T remove(Class<T> clazz, Integer id) {
		EntityManager em = new ConnectionFactory().getConnection();
		T t = null;
		
		try {
			
			t = em.find(clazz, id);
			em.getTransaction().begin();
			em.remove(t);
			em.getTransaction().commit();
			
		} catch (Exception e) {
			System.err.println(e);
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		
		return t;
	}
	
}