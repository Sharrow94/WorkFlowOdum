package pl.odum.workflowodum.email;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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
import java.io.File;
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
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            Receiver receiver = myMailMessage.getReceiver();
            Optional<Doc> attachment = Optional.ofNullable(myMailMessage.getAttachment());

            helper.setTo(receiver.getEmail());
            helper.setSubject(myMailMessage.getSubject());
            helper.setText(myMailMessage.getMessage(), true);

            if (attachment.isPresent()) {
                Doc doc = attachment.get();
                helper.addAttachment(doc.getDocName(), new File(doc.fullPath()));
            }

            javaMailSender.send(message);

        } catch (MessagingException e) {
            LOGGER.error("Failed to send email", e);
            throw new IllegalStateException("Failed to send email");
        }

    }
}
