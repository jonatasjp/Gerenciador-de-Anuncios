package br.com.jonatas.aula.security;

import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import br.com.jonatas.aula.criptografia.Criptografia;

@Configuration
//@EnableWebMvc
public class AppWebConfiguration extends WebMvcConfigurerAdapter {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(autorizadorInterceptor());
	}
	
	@Bean
	public AutorizadorInterceptor autorizadorInterceptor() {
        return new AutorizadorInterceptor();
    } 
	
//	@Bean
//	public BasicTextEncryptor basicTextEncryptor() {
//		return new BasicTextEncryptor();
//	}
	
	@Bean
	public Criptografia criptografia(){
		return new Criptografia(new BasicTextEncryptor());
	}

}
