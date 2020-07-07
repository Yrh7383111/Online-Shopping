package Back_end.Config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;



@Configuration
@ComponentScan(basePackages={"Back_end.DTO"})
@EnableTransactionManagement
public class HibernateConfig
{
	// Private
	private final static String DATABASE_URL = "jdbc:mysql://localhost:3306/Online_Shopping?useSSL=false&serverTimezone=UTC";
	private final static String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
	private final static String DATABASE_USERNAME = "root";
	private final static String DATABASE_PASSWORD = "Yrh@9695500";
	private final static String DATABASE_DIALECT = "org.hibernate.dialect.MySQLDialect";

	
	// Data source connection
	@Bean(name = "dataSource")
	public DataSource getDataSource()
	{
		BasicDataSource dataSource = new BasicDataSource();

		dataSource.setUrl(DATABASE_URL);
		dataSource.setDriverClassName(DATABASE_DRIVER);
		dataSource.setUsername(DATABASE_USERNAME);
		dataSource.setPassword(DATABASE_PASSWORD);
		
		return dataSource;
	}
	
	// Session factory
	@Bean
	public SessionFactory getSessionFactory(DataSource dataSource)
	{
		LocalSessionFactoryBuilder localSessionFactoryBuilder = new LocalSessionFactoryBuilder(dataSource);

		localSessionFactoryBuilder.addProperties(getHibernateProperties());
		localSessionFactoryBuilder.scanPackages("Back_end.DTO");

		SessionFactory sessionFactory = localSessionFactoryBuilder.buildSessionFactory();

		return sessionFactory;
	}

	// Hibernate properties
	private Properties getHibernateProperties()
	{
		Properties properties = new Properties();
		
		properties.put("hibernate.dialect", DATABASE_DIALECT);		
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.format_sql", "true");
		
		return properties;
	}

	// Transaction manager
	@Bean
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory)
	{
		HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager(sessionFactory);

		return hibernateTransactionManager;
	}
}