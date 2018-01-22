package br.com.scgpoc.configuracao;

import java.nio.charset.StandardCharsets;

import org.springframework.context.annotation.Bean;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.StandardTemplateModeHandlers;

public class ConfiguracaoThymeleaf {
	 @Bean
	    public SpringTemplateEngine springTemplateEngine() {
	        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
	        templateEngine.addTemplateResolver(htmlTemplateResolver());
	        return templateEngine;
	    }

	    @Bean
	    public SpringResourceTemplateResolver htmlTemplateResolver(){
	        SpringResourceTemplateResolver emailTemplateResolver = new SpringResourceTemplateResolver();
	        emailTemplateResolver.setPrefix("/templates/");
	        emailTemplateResolver.setSuffix(".html");
	        emailTemplateResolver.setTemplateMode(StandardTemplateModeHandlers.HTML5.getTemplateModeName());
	        emailTemplateResolver.setCharacterEncoding(StandardCharsets.UTF_8.name());
	        return emailTemplateResolver;
	    }
}
