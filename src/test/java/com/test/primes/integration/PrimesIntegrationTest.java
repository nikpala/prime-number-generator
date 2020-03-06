package com.test.primes.integration;

import com.test.primes.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = Application.class
)
@AutoConfigureMockMvc
public class PrimesIntegrationTest {

    @Autowired
    MockMvc mvc;

    @Test
    public void testValidation() throws Exception{
        mvc.perform(get("/primes/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error", is("Please enter a positive integer greater than 1.")));
    }

    @Test
    public void testAlogorithmError() throws Exception{
        mvc.perform(get("/primes/1000000?algorithm=sundaram")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.error", is("Unable to generate primes for limit 1000000 using algorithm Sundaram. Please try again with a different algorithm.")));

    }


    @Test
    public void testAlogorithm() throws Exception{
        mvc.perform(get("/primes/20?algorithm=Eratosthenes")
                .accept(MediaType.APPLICATION_XML))
                .andExpect(status().isOk())
                .andExpect(xpath("PrimeResponse/primes").exists())
                .andExpect(xpath("PrimeResponse/primes[2]").string("3"));
    }

    @Test
    public void testAlogorithmDefault() throws Exception{
        mvc.perform(get("/primes/20")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.primes", hasSize(8)))
                .andExpect(jsonPath("$.primes[2]", is(5)));

    }

    @Test
    public void testAlogorithmCache() throws Exception{

        mvc.perform(get("/primes/10?algorithm=Eratosthenes")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.primes", hasSize(4)))
                .andExpect(jsonPath("$.primes[3]", is(7)));

    }

    @Test
    public void testAlogorithmTextResponse() throws Exception{

        MvcResult result = mvc.perform(get("/primes/10?algorithm=Eratosthenes")
                .accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("2, 3, 5, 7"));
    }

}
