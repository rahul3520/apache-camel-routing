package com.microservice.camel_microservice_a.routeA;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

//@Component
public class MyFirstRouter extends RouteBuilder {

    @Autowired
    private GetCurrentTimeBean getCurrentTimeBean;

    @Autowired
    private SimpleLoggingProcessingComponent simpleLoggingProcessingComponent;

    @Override
    public void configure() throws Exception {

        from("timer:MyFirstTimer")                                        // endpoint 1
                //.transform().constant("Time now: "+ LocalDateTime.now())
                .log("Initial message : ${body}")
                .bean(getCurrentTimeBean,"getTimeNow")                // transformation of message body
                .log("Transformed message : ${body}")
                .bean(simpleLoggingProcessingComponent,"logMessage")  // processing of message body
                .log("Processed message : ${body}")
                .process(new SimpleLoginProcessor())
                .to("log:MyFirstTimer");                                  // endpoint 2
    }
}

@Component
class GetCurrentTimeBean{

    public String getTimeNow(){

        return "Local date time now: "+LocalDateTime.now();
    }
}

@Component
class SimpleLoggingProcessingComponent{

    private Logger logger = LoggerFactory.getLogger(SimpleLoggingProcessingComponent.class);
    public void logMessage(String message){

        logger.info("SimpleLoggingProcessingComponent: {}",message);
    }
}

@Component
class SimpleLoginProcessor implements Processor {

    private Logger logger = LoggerFactory.getLogger(SimpleLoginProcessor.class);

    @Override
    public void process(Exchange exchange) throws Exception {

        logger.info("Simple login Processor message {}",exchange.getMessage().getBody());
    }
}