package sample.snippets.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSnippets {

	public static void main(String[] args) {

		String toEmail = "receiver.bob@example2.org";

		String fromEmail = "sender.alice@example1.com";

		String smtpHost = "localhost";// smtp server: eg. smtp.gmail.com

		Properties sessionProperties = new Properties();
		sessionProperties.setProperty("mail.smtp.host", smtpHost);
		sessionProperties.setProperty("mail.smtp.port", "25");

		Session session = Session.getDefaultInstance(sessionProperties);

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromEmail));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
			message.setSubject("Mail Subject - This is the Subject Line!");
			message.setText("Email Body Content - Loren Ipsum" + sessionProperties);

			Transport.send(message);
			System.out.println("Sent Message!");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}
}