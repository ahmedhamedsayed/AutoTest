package configuration.databaseConfiguration;

import java.util.HashMap;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import util.shape.Error;
import configuration.uiConfiguration.Message;

public class DatabaseEngine {

	private static DatabaseEngine databaseEngine;
	private HashMap<String, SessionFactory> databasesSessionFactory;
	
	public DatabaseEngine() {
		databasesSessionFactory = new HashMap<String, SessionFactory>();
	}
	
	public static synchronized DatabaseEngine getInstance() {
		if (databaseEngine == null)
			return databaseEngine = new DatabaseEngine();
		return databaseEngine;
	}

	public void configureDatabase(String configFile, String databasePath) {
		if (databasesSessionFactory.containsKey(databasePath)) {
			return;
		}
		try {
			Configuration configuration = new Configuration();
			configuration.configure("META-INF/" + configFile);
			configuration.setProperty("hibernate.connection.url", "jdbc:sqlite://" + databasePath);
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
			SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			databasesSessionFactory.put(databasePath, sessionFactory);
		} catch (Exception e) {
			Error.reportErrorMessageWithException(e, Message.OPEN_DATABASE_CONNECTION_ERROR.getValue());
			System.exit(0);
		}
	}
	
	public Session getMainDatabaseSession() {
		return getSession(DatabasesPath.mainDatabasePath);
	}
	
	public Session getStudentDatabaseSession() {
		return getSession(DatabasesPath.studentDatabasePath);
	}
	
	public Session getSession(String databasePath) {
		try {
			return databasesSessionFactory.get(databasePath).openSession();
		} catch (Exception e) {
			Error.reportErrorMessageWithException(e, Message.OPEN_DATABASE_SESSION_ERROR.getValue());
			System.exit(0);
		}
		return null;
	}
}
