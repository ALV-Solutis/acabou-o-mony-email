package br.com.ms.email.dto;

import java.util.UUID;

public record Code2FADto(UUID userId,
                         String emailTo,
                         String code2FA) {
}
