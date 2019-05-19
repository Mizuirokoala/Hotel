//package pl.mizuirokoala.app;
//
//import java.util.Locale;
//
//import javax.persistence.EntityManagerFactory;
//import javax.validation.Validator;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.format.FormatterRegistry;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
//import org.springframework.web.servlet.LocaleContextResolver;
//import org.springframework.web.servlet.ViewResolver;
//import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//import org.springframework.web.servlet.i18n.SessionLocaleResolver;
//import org.springframework.web.servlet.view.InternalResourceViewResolver;
//import org.springframework.web.servlet.view.JstlView;
//
//import pl.mizuirokoala.conventer.HotelConverter;
//import pl.mizuirokoala.conventer.PetConverter;
//
////Testing
////test again
//
//@Configuration
//@ComponentScan(basePackages = {"pl.mizuirokoala.bean", "pl.mizuirokoala.controller", "pl.mizuirokoala.entity", "pl.mizuirokoala.repository"})
//@EnableWebMvc
//@EnableTransactionManagement
//@EnableJpaRepositories(basePackages= {"pl.mizuirokoala.repository"})
//public class AppConfig extends WebMvcConfigurerAdapter {
//	@Bean
//	public ViewResolver viewResolver() {
//		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//		viewResolver.setViewClass(JstlView.class);
//		viewResolver.setPrefix("/WEB-INF/views/");
//		viewResolver.setSuffix(".jsp");
//		return viewResolver;
//	}
//
//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		super.addResourceHandlers(registry);
//		registry.addResourceHandler("/resources/**").addResourceLocations("/WEB-INF/resources/");
//	}
//
//	@Override
//	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
//		configurer.enable();
//	}
//
//	@Bean
//	public LocalEntityManagerFactoryBean entityManagerFactory() {
//		LocalEntityManagerFactoryBean emfb = new LocalEntityManagerFactoryBean();
//		emfb.setPersistenceUnitName("petHotel");
//		return emfb;
//	}
//
//	@Bean
//	public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
//		JpaTransactionManager tm = new JpaTransactionManager(emf);
//		return tm;
//	}
//
//	@Override
//	public void addFormatters(FormatterRegistry registry) {
//		registry.addConverter(petConverter());
//		registry.addConverter(hotelConverter());
//	}
//
//	@Bean
//	public PetConverter petConverter() {
//		return new PetConverter();
//	}
//
//	@Bean
//	public HotelConverter hotelConverter() {
//		return new HotelConverter();
//	}
//
//	@Bean(name = "localeResolver")
//	public LocaleContextResolver getLocaleContextResolver() {
//		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
//		localeResolver.setDefaultLocale(new Locale("pl", "PL"));
//		return localeResolver;
//	}
//
//	@Bean
//	public Validator validator() {
//		return new LocalValidatorFactoryBean();
//	}
//}
