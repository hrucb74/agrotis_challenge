package com.agrotis.challenge.common.exception;

import com.agrotis.challenge.common.messageerror.MessageError;
import lombok.Getter;

@Getter
public class InternalServerErrorException extends RuntimeException {
    private final MessageError messageError;

    public InternalServerErrorException(MessageError messageError) {
        this.messageError = messageError;
    }
}
