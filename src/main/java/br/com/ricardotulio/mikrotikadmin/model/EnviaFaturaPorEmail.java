package br.com.ricardotulio.mikrotikadmin.model;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EnviaFaturaPorEmail implements AcaoAposGerarFatura {

	public void executa(Fatura fatura) {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("ledo.tulio@gmail.com", "gf155c44");
			}
		});
		
		session.setDebug(true);

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("ledo.tulio@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(fatura.getCliente().getContatos().iterator().next().getEmail()));
			message.setSubject("Boleto para pagamento da internet");
			message.setText("Prezado(a) Sr(a). " + fatura.getCliente().getNome() 
					+ ",\n\n Segue o enderçeo para efetuar o pagamento dos serviços de internet: " + fatura.getUrlParaPagamento() + "\n\n" 
					+ "Para efetuar o pagamento, clique no endereço acima e você será redirecionado para o site do PagSeguro. Ao acessar o site do PagSeguro, basta digitar o seu endereço de e-mail, selecionar a opção \"Não tenho conta no PagSeguro\" e clicar em \"Pagar com segurança\". Após isso, serão exibidas diversas opções para pagamento, como cartão de crédito, débito online ou boleto. Preencha o formulário completo e efetue o seu pagamento. Caso o seu pagamento já tenha sido feito, desconsidere esta mensagem.\n\n\n\n"
					+ "Obrigado por escolher o nosso serviço.");

			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}	
}
