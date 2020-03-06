package com.test.primes.builders;

import com.test.primes.service.ResponseEntityBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
public class ResponseBuilderFactoryTest {

    @TestConfiguration
    static class TestConfig {
        @Bean
        public ResponseBuilderFactory responseBuilderFactoryBuilder() {

            return new ResponseBuilderFactory();
        }
    }

    @Autowired
    ResponseBuilderFactory responseBuilderFactory;

    @Test
    public void testTextResponseEntityBuilder(){
        ResponseEntityBuilder res = responseBuilderFactory.getResponseEntityBuilder(MediaType.TEXT_PLAIN);
        assertTrue(res instanceof TextResponseBuilder);
    }

    @Test
    public void testXMLResponseEntityBuilder(){
        ResponseEntityBuilder res = responseBuilderFactory.getResponseEntityBuilder(MediaType.APPLICATION_XML);
        assertTrue(res instanceof DefaultResponseBuilder);
    }

    @Test
    public void testJSONResponseEntityBuilder(){
        ResponseEntityBuilder res = responseBuilderFactory.getResponseEntityBuilder(MediaType.APPLICATION_JSON);
        assertTrue(res instanceof DefaultResponseBuilder);
    }

}
