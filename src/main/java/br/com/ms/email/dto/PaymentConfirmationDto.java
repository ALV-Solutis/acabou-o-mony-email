package br.com.ms.email.dto;

import java.util.UUID;

public record PaymentConfirmationDto(UUID userId,
                                     String emailTo,
                                     String paymentConfirmation) {
}
