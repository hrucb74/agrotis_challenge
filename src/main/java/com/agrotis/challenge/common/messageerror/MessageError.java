package com.agrotis.challenge.common.messageerror;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;

@Getter
@JsonSerialize(using = MessageErrorSerializer.class)
public enum MessageError {
    ACCESS_DENIED(403, "ACCESS_DENIED", "Access Denied"),
    EXPIRED_TOKEN(401, "EXPIRED_TOKEN", "Token has expired"),
    TOKEN_NOT_FOUND(401, "TOKEN_NOT_FOUND", "Token not found"),
    MALFORMED_TOKEN(401, "MALFORMED_TOKEN", "The provided token is malformed or invalid"),
    LABORATORY_NOT_FOUND(404, "LABORATORY_NOT_FOUND", "Laboratory not found"),
    LABORATORY_ALREADY_EXISTS(409, "LABORATORY_ALREADY_EXISTS", "Laboratory already exists"),
    PERSON_NOT_FOUND(404, "PERSON_NOT_FOUND", "Person not found"),
    PERSON_ALREADY_EXISTS(409, "PERSON_ALREADY_EXISTS", "Person already exists"),
    PROPERTY_NOT_FOUND(404, "PROPERTY_NOT_FOUND", "Property not found"),
    PROPERTY_ALREADY_EXISTS(409, "PROPERTY_ALREADY_EXISTS", "Property already exists"),
    CANNOT_DELETE_LABORATORY_HAS_PEOPLE(409, "CANNOT_DELETE_LABORATORY_HAS_PEOPLE", "Cannot delete Laboratory with people assigned"),
    BAD_REQUEST(400, "BAD_REQUEST", "Bad Request"),
    PERSON_DELETED(200, "PERSON_DELETED", "Person deleted successfully"),
    LABORATORY_DELETED(200, "LABORATORY_DELETED", "Laboratory deleted successfully"),
    PROPERTY_DELETED(200, "PROPERTY_DELETED", "Property deleted successfully"),;

    private final int status;
    private final String code;
    private final String message;

    MessageError(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
