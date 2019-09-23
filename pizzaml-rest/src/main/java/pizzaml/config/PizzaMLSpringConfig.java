package pizzaml.config;

import java.beans.PropertyVetoException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@ComponentScan(value="pizzaml")
@EnableTransactionManagement
@PropertySource({"classpath:persistance-mysql.properties"})
public class PizzaMLSpringConfig implements WebMvcConfigurer {
	
	@Autowired
	private Environment env;
	
	private Logger logger = Logger.getLogger(getClass().getName());
	
	@Bean("pizzaMLDataSource")
	public DataSource datasourceBean() {
		
		// create connection pool
		ComboPooledDataSource myDataSource = new ComboPooledDataSource();

		// set the jdbc driver
		try {
				myDataSource.setDriverClass("com.mysql.jdbc.Driver");		
			}
		catch (PropertyVetoException exc) {
			throw new RuntimeException(exc);
			}
		
		// for sanity's sake, let's log url and user ... just to make sure we are reading the data
		logger.info("jdbc.url=" + env.getProperty("jdbc.url"));
		logger.info("jdbc.user=" + env.getProperty("jdbc.user"));
		
		// set database connection props
		myDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		myDataSource.setUser(env.getProperty("jdbc.user"));
		myDataSource.setPassword(env.getProperty("jdbc.password"));
		
		// set connection pool props
		myDataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
		myDataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
		myDataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));		
		myDataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));
		
		return myDataSource;
	}
	
	// need a helper method 
	// read environment property and convert to int
	
	private int getIntProperty(String propName) {
		
		String propVal = env.getProperty(propName);
		
		// now convert to int
		int intPropVal = Integer.parseInt(propVal);
		
		return intPropVal;
	}
	
	private Properties getHibernateProperties() {

		// set hibernate properties
		Properties props = new Properties();

		props.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
		props.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		
		return props;				
	}
	
	
	@Bean(name="pizzaMLEntityManagerFactory")
	@Autowired
	public LocalContainerEntityManagerFactoryBean containerManagedEMFactoryBean(
			@Qualifier("pizzaMLDataSource") DataSource myDataSource
			) {
		
		LocalContainerEntityManagerFactoryBean emFactory = new LocalContainerEntityManagerFactoryBean();
		
		emFactory.setDataSource(myDataSource);
		emFactory.setPackagesToScan(env.getProperty("hibernate.packagesToScan"));
		emFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		
		emFactory.setJpaProperties(getHibernateProperties());
		
		return emFactory;
	}
	
	@Bean(name="pizzaMLTransactionManager")
	@Autowired
	public JpaTransactionManager transactionManager(@Qualifier("pizzaMLEntityManagerFactory") EntityManagerFactory emFactory) {
		JpaTransactionManager trxManager = new JpaTransactionManager();
		trxManager.setEntityManagerFactory(emFactory);
		return trxManager;
	}
	
	
}
