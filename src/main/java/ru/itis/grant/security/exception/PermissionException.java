package ru.itis.grant.security.exception;

import org.springframework.security.core.AuthenticationException;

public class PermissionException extends AuthenticationException {
    public PermissionException(String msg) {
        super(msg);
    }
}
