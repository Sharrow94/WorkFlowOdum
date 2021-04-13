package pl.odum.workflowodum.email;

public interface EmailSender {
    EmailStatus send(MyMailMessage myMailMessage);
}
