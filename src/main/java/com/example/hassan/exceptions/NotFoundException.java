package com.example.hassan.exceptions;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String messageKey, MessageSource messageSource) {
        super(messageSource.getMessage(messageKey, null, LocaleContextHolder.getLocale()));
    }
}
