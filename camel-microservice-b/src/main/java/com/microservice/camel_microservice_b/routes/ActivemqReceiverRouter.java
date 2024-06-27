package com.microservice.camel_microservice_b.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ActivemqReceiverRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("activemq:my-activemq-queue")
                .to("log:active-mq-message-receiver");
    }
}
