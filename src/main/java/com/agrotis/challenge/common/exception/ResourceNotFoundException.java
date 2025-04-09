package com.agrotis.challenge.common.exception;

import com.agrotis.challenge.common.messageerror.MessageError;
import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException {
  private final MessageError messageError;

  public ResourceNotFoundException(MessageError messageError) {
    this.messageError = messageError;
  }
}
