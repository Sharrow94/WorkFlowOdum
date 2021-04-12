package pl.odum.workflowodum.email;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import pl.odum.workflowodum.model.Doc;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmailService implements EmailSender {
    private final static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);
    private final JavaMailSender javaMailSender;

    @Override
    public void send(MyMailMessage myMailMessage) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMultipart mimeMultipart = new MimeMultipart();
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            Receiver receiver = myMailMessage.getReceiver();
            Optional<Doc> attachment = Optional.ofNullable(myMailMessage.getAttachment());

            message.setRecipients(Message.RecipientType.TO, receiver.getEmail());
            message.setSubject(myMailMessage.getSubject());

            mimeBodyPart.setText(myMailMessage.getMessage());
            mimeMultipart.addBodyPart(mimeBodyPart);

            if (attachment.isPresent()) {
                Doc doc = attachment.get();
                mimeBodyPart = new MimeBodyPart();
                DataSource source = new FileDataSource(doc.fullPath());
                mimeBodyPart.setDataHandler(new DataHandler(source));
                mimeBodyPart.setFileName(doc.getDocName());
                mimeMultipart.addBodyPart(mimeBodyPart);
            }

            message.setContent(mimeMultipart);
            javaMailSender.send(message);

        } catch (MessagingException e) {
            LOGGER.error("Failed to send email", e);
            throw new IllegalStateException("Failed to send email");
        }

    }
}
