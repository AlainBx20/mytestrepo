package com.example.webservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class WebConfig {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setPackagesToScan("com.example.webservice.model");
        return marshaller;
    }

    @Bean
    public MarshallingHttpMessageConverter marshallingHttpMessageConverter(Jaxb2Marshaller marshaller) {
        return new MarshallingHttpMessageConverter(marshaller);
    }
} 