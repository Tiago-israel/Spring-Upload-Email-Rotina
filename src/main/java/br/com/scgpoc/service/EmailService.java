package br.com.scgpoc.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import br.com.scgpoc.model.Email;
import br.com.scgpoc.repository.ClienteRepository;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender emailSender;
	@Autowired
	private SpringTemplateEngine templateEngine;
	@Autowired
	private ClienteRepository clienteRepository;

	public void sendSimpleMessage(Email mail) throws MessagingException, IOException {
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,StandardCharsets.UTF_8.name());

		helper.addAttachment("aniversariantes.png", new ClassPathResource("aniversariantes.png"));

		Context context = new Context();
		context.setVariable("name", "tiago");
		context.setVariable("location", "JF");
		context.setVariable("signature", "STEFANINI");
		context.setVariable("clientes", this.clienteRepository.buscarAniversariantes());
		String html = templateEngine.process("email-template", context);

		helper.setTo(mail.getTo());
		helper.setText(html, true);
		helper.setSubject(mail.getSubject());
		helper.setFrom(mail.getFrom());

		emailSender.send(message);
	}

}
