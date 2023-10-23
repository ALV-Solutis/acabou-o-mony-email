package br.com.ms.email.dtos;

import java.util.UUID;

public record PaymentConfirmationDto(UUID userId,
                                     String emailTo,
                                     String paymentConfirmation) {
}
