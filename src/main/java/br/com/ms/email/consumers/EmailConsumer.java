package br.com.ms.email.consumers;

import br.com.ms.email.dtos.Code2FADto;
import br.com.ms.email.models.EmailModel;
import br.com.ms.email.services.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {

    private final EmailService emailService;

    public EmailConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    /*
    Método transforma os dados recebidos da fila do RabbitMQ em um emailModel e utiliza
     */
    @RabbitListener(queues = "${broker.queue.email.name}")
    public void listenCode2FA (@Payload Code2FADto code2FADto) {
        EmailModel emailModel = new EmailModel();
        emailModel.setUserId(code2FADto.userId());
        emailModel.setEmailTo(code2FADto.emailTo());
        emailModel.setSubject("Código de verificação: " + code2FADto.code2FA());
        emailModel.setText("O seu código de verificação é " + code2FADto.code2FA() + ".\n" +
                "Se você não solicitou isso, simplesmente ignore esta mensagem.\n\n" +
                "Atenciosamente,\n" +
                "A Equipe do Acabou o Mony!");
        emailService.sendEmail(emailModel);
    }
}
