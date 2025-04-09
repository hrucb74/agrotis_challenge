package com.agrotis.challenge.common.exception;

import com.agrotis.challenge.common.messageerror.MessageError;
import lombok.Getter;

@Getter
public class IllegalArgumentException extends RuntimeException {
    private final MessageError messageError;

    public IllegalArgumentException(MessageError messageError) {
        this.messageError = messageError;
    }
}
