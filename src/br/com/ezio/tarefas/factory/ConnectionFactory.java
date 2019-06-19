package br.com.ezio.tarefas.factory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * 
 * @author Ezio Lemes
 *
 */
public class ConnectionFactory {
	
	private EntityManagerFactory emf;
	
	public EntityManager getConnection() {
		
		emf = Persistence.createEntityManagerFactory("meuPU");
		
		return emf.createEntityManager();
	}
}