package com.agrotis.challenge.common.exception;

import com.agrotis.challenge.common.messageerror.MessageError;
import lombok.Getter;

@Getter
public class BadRequestException extends RuntimeException {
    private final MessageError messageError;

    public BadRequestException(MessageError messageError) {
        this.messageError = messageError;
    }
}
