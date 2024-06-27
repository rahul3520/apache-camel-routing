package com.microservice.camel_microservice_a.routeB;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class MyFileRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("file:E:\\Apache-camel-projects\\camel-microservice-a\\files\\input")
                .log("${body}")
                .to("file:E:\\Apache-camel-projects\\camel-microservice-a\\files\\output");
    }
}
