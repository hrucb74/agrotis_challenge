package com.agrotis.challenge.common.exception;

import com.agrotis.challenge.common.messageerror.MessageError;
import lombok.Getter;

@Getter
public class ConflictException extends RuntimeException {
    public final MessageError messageError;

    public ConflictException(MessageError messageError) {
        this.messageError = messageError;
    }
}
