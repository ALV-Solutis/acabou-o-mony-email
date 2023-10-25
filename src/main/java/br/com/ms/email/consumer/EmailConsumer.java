package br.com.ms.email.consumer;

import br.com.ms.email.dto.Code2FADto;
import br.com.ms.email.dto.PaymentConfirmationDto;
import br.com.ms.email.model.EmailModel;
import br.com.ms.email.service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {

    private final EmailService emailService;

    public EmailConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = "${broker.queue.email.name.authentication}")
    public void listenCode2FA (@Payload Code2FADto code2FADto) {
        EmailModel emailModel = emailService.createCodeEmail(code2FADto);
        emailService.sendEmail(emailModel);
    }

    @RabbitListener(queues = "${broker.queue.email.name.confirmation}")
    public void listenPaymentConfirmation (@Payload PaymentConfirmationDto paymentConfirmationDto) {
        EmailModel emailModel = emailService.createConfirmationEmail(paymentConfirmationDto);
        emailService.sendEmail(emailModel);
    }


}
