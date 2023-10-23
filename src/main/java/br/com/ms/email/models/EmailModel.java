package br.com.ms.email.models;

import br.com.ms.email.dtos.Code2FADto;
import br.com.ms.email.dtos.PaymentConfirmationDto;
import br.com.ms.email.enums.StatusEmail;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class EmailModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID emailId;
    private UUID userId;
    private String emailFrom;
    private String emailTo;
    private String subject;
    @Column(columnDefinition = "TEXT")
    private String text;
    private LocalDateTime sendDateEmail;
    private StatusEmail statusEmail;

    public UUID getEmailId() {
        return emailId;
    }

    public void setEmailId(UUID emailId) {
        this.emailId = emailId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getEmailFrom() {
        return emailFrom;
    }

    public void setEmailFrom(String emailFrom) {
        this.emailFrom = emailFrom;
    }

    public String getEmailTo() {
        return emailTo;
    }

    public void setEmailTo(String emailTo) {
        this.emailTo = emailTo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getSendDateEmail() {
        return sendDateEmail;
    }

    public void setSendDateEmail(LocalDateTime sendDateEmail) {
        this.sendDateEmail = sendDateEmail;
    }

    public StatusEmail getStatusEmail() {
        return statusEmail;
    }

    public void setStatusEmail(StatusEmail statusEmail) {
        this.statusEmail = statusEmail;
    }

    public static EmailModel createCodeEmail(Code2FADto code2FADto) {
        EmailModel emailModel = new EmailModel();
        emailModel.setUserId(code2FADto.userId());
        emailModel.setEmailTo(code2FADto.emailTo());
        emailModel.setSubject("Código de verificação: " + code2FADto.code2FA());
        emailModel.setText("O seu código de verificação é " + code2FADto.code2FA() + ".\n" +
                "Se você não solicitou isso, simplesmente ignore esta mensagem.\n\n" +
                "Atenciosamente,\n" +
                "A Equipe do Acabou o Mony!");
        return emailModel;
    }

    public static EmailModel createConfirmationEmail(PaymentConfirmationDto paymentConfirmationDto) {
        EmailModel emailModel = new EmailModel();
        emailModel.setUserId(paymentConfirmationDto.userId());
        emailModel.setEmailTo(paymentConfirmationDto.emailTo());
        emailModel.setSubject("Adicionar assunto");
        emailModel.setText("Adicionar texto");
        return emailModel;
    }
}
