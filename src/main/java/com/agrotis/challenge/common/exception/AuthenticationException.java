package com.agrotis.challenge.common.exception;

import com.agrotis.challenge.common.messageerror.MessageError;
import lombok.Getter;

@Getter
public class AuthenticationException extends RuntimeException {
    private final MessageError messageError;

    public AuthenticationException(MessageError messageError) {
        this.messageError = messageError;
    }
}
