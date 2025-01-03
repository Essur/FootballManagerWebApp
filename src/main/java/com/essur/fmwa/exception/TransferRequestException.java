package com.essur.fmwa.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TransferRequestException extends RuntimeException {
    public TransferRequestException(String message) {
        super(message);
    }
}
