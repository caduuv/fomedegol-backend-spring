package br.edu.ifce.fomedegolservico.core.config;

import br.edu.ifce.fomedegolservico.core.exception.MessageExceptionHandlerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageExceptionHandlerConfig {

    @Bean
    public MessageExceptionHandlerBuilder messageExceptionHandlerBuilder() {
        return new MessageExceptionHandlerBuilder();
    }

}
