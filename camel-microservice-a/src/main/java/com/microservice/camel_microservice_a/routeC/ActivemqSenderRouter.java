package com.microservice.camel_microservice_a.routeC;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ActivemqSenderRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("timer:active-mq-timer?period=10000")
                .transform().constant("My message for activemq")
                .log("${body}")
                .to("activemq:my-activemq-queue");
    }
}
