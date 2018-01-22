package br.com.scgpoc.rotina;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.scgpoc.model.Email;
import br.com.scgpoc.service.EmailService;

@EnableScheduling
@Component
public class EnviarEmail {
	@Autowired
	private EmailService emailService;
	private static final String TIME_ZONE = "America/Sao_Paulo";
	//Utilize o código comentado para testes rapidos
	@Scheduled(fixedRate = 10000,zone = TIME_ZONE)
	//Será executado às 23:59:59
	//@Scheduled(cron = "59 59 23 * * *",zone = TIME_ZONE)
	public void enviarEmail() throws MessagingException, IOException {
		Email mail = new Email();
        mail.setFrom("no-reply@memorynotfound.com");
        mail.setTo("tiagoisrael77@hotmail.com");
        mail.setSubject("Aníversariantes do mês");

        Map<String,String> model = new HashMap<String,String>();
        model.put("name", "tiago");
        model.put("location", "Belgium");
        model.put("signature", "https://memorynotfound.com");
        mail.setModel(model);
        this.emailService.sendSimpleMessage(mail);
	}
	
}
