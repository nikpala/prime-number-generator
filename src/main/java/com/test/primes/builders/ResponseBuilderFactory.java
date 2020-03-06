package com.test.primes.builders;

import com.test.primes.service.ResponseEntityBuilder;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class ResponseBuilderFactory {

    public ResponseEntityBuilder getResponseEntityBuilder(MediaType mediaType){

        if(MediaType.TEXT_PLAIN.equals(mediaType)){
            return new TextResponseBuilder();
        } else{
            return new DefaultResponseBuilder();
        }

    }
}
