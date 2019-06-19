package br.com.ezio.tarefas.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 * @author Ezio Lemes
 *
 */
public class ConnectionFactory {
	
	/*
	 * Até a versão 3 do JDBC, antes de chamar o DriverManager.getConnection() era necessário 
	 * registrar o driver JDBC que iria ser utilizado através do método Class.forName("com.mysql.jdbc.Driver"), 
	 * no caso do MySQL, que carregava essa classe, e essa se comunicava com o DriverManager.
	 *
	 * A partir do JDBC 4, que está presente no Java 6, esse passo não é mais necessário. 
	 * Mas lembre-se: caso você utilize JDBC em um projeto com Java 5 ou anterior, será preciso 
	 * fazer o registro do Driver JDBC, carregando a sua classe, que vai se registrar no DriverManager.
	 *
	 * Isso também pode ser necessário em alguns servidores de aplicação e web, como no Tomcat 7 
	 * ou posterior, por proteção para possíveis vazamentos de memória.
	 */
	
	public Connection getConnection() throws ClassNotFoundException, SQLException{
		/*String driverName = "org.gjt.mm.mysql.Driver";
		Class.forName(driverName); */
		
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/tarefas","root","s3cr3ta");
	}
}