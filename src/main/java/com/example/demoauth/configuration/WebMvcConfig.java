package com.example.demoauth.configuration;

import com.example.demoauth.service.util.TsqBeanHandlerInstantiator;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;

@Configuration
@AllArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {
    private  MappingJackson2HttpMessageConverter jacksonHttpMessageConverter;

    private  TsqBeanHandlerInstantiator tsqBeanHandlerInstantiator;

    @PostConstruct
    public void init(){
        ObjectMapper objectMapper = jacksonHttpMessageConverter.getObjectMapper();
        objectMapper.setHandlerInstantiator(tsqBeanHandlerInstantiator);
    }
}
