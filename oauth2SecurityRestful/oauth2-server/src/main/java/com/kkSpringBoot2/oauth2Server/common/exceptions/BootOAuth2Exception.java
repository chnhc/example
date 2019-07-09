package com.kkSpringBoot2.oauth2Server.common.exceptions;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.kkSpringBoot2.oauth2Server.common.exceptions.serializer.BootOauthExceptionSerializer;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 *
 */
@JsonSerialize(using = BootOauthExceptionSerializer.class)
public class BootOAuth2Exception extends OAuth2Exception {


    public BootOAuth2Exception(String msg, Throwable t) {
        super(msg, t);
    }

    public BootOAuth2Exception(String msg) {
        super(msg);
    }
}
