package nglab.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration 
@ComponentScan("nglab")
@EnableTransactionManagement
public class JPAConfig {

	@Bean
	public JpaTransactionManager jpaTransMan(){
		 return new JpaTransactionManager(
					getEntityManagerFactoryBean()
				   .getObject()
				);
	}
	
	@Bean
	public LocalEntityManagerFactoryBean getEntityManagerFactoryBean() {
		LocalEntityManagerFactoryBean lemfb = new LocalEntityManagerFactoryBean();
		lemfb.setPersistenceUnitName("ngLabPU");
		
		return lemfb;
	}	
}
