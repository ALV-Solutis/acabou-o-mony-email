package br.com.ms.email.consumers;

import br.com.ms.email.dtos.Code2FADto;
import br.com.ms.email.dtos.PaymentConfirmationDto;
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
    Método transforma os dados recebidos da fila do RabbitMQ em um emailModel
    e utiliza o método sendEmail para enviar o emailModel instanciado e atualizado.
     */
    @RabbitListener(queues = "${broker.queue.email.name}")
    public void listenCode2FA (@Payload Code2FADto code2FADto) {
        EmailModel emailModel = EmailModel.createCodeEmail(code2FADto);
        emailService.sendEmail(emailModel);
    }

    @RabbitListener(queues = "${broker.queue.email2.name}")
    public void listenPaymentConfirmation (@Payload PaymentConfirmationDto paymentConfirmationDto) {
        EmailModel emailModel = EmailModel.createConfirmationEmail(paymentConfirmationDto);
        emailService.sendEmail(emailModel);
    }


}
