package com.murdock.examples.dropwizard.resources;
import com.fasterxml.jackson.databind.*;
import com.murdock.examples.dropwizard.api.*;
import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

//import javax.json.Json;
//import javax.json.JsonObject;
//import  org.glassfish.json;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import java.io.*;
import java.util.*;

//import javax.xml.bind.annotation.XmlAccessType;

@Path("/fibonacci")
public class fibonacci {

    @GET
    @Timed
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String fibonacci(@Context HttpServletRequest request, InputStream requestBody) throws IOException {
       // read requestBody context
        BufferedReader reader = new BufferedReader(new InputStreamReader(requestBody));
        StringBuilder out = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            out.append(line);
        }
        System.out.println(out.toString());
        // creatd read readobjectMapper
        ObjectMapper readobjectMapper = new ObjectMapper();
        JsonNode jsonNode = readobjectMapper.readTree(out.toString());
        //get elements
        JsonNode name = jsonNode.get("elements");
        String eleNum = name.asText();
        System.out.println(eleNum);
        //convert string to int
        try {
            int ele=Integer.parseInt(eleNum);
        } catch (NumberFormatException e) {
            return "Input is not Integer";
        }
        int ele=Integer.parseInt(eleNum);
        if(ele > 100 || ele < 1){
            return "Input num is range from 1 to 100";
        }

        // creatd build objectMapper
        ObjectMapper mapper = new ObjectMapper();

        // create three JSON objects
        ObjectNode vendorFib = mapper.createObjectNode();

        // create nested arrays
        calculateFibonacci Fibonacci = new calculateFibonacci(ele);
        System.out.println(Arrays.toString(Fibonacci.FibonacciArr()));
        // sort Fibonacci
        int[] afterSortedFib = Fibonacci.SortedFibArr().clone();

        System.out.println(Arrays.toString(afterSortedFib));
        // add nested arrays to JSON objects
        ArrayNode fibarrWhole = mapper.createArrayNode();
            for(int i = 0 ; i <ele ; i++){
                fibarrWhole.add(Fibonacci.FibonacciArr()[i]);
            }
        ArrayNode fibarrSorted = mapper.createArrayNode();
        for(int i = 0 ; i <ele ; i++){

            fibarrSorted.add(afterSortedFib[i]);
        }
        vendorFib.set("Fibonacci", fibarrWhole);
        vendorFib.set("Sorted", fibarrSorted);

            // create `ArrayNode` object
        ArrayNode arrayNode = mapper.createArrayNode();

            // add JSON objects to array
        arrayNode.addAll(Arrays.asList(vendorFib));

            // convert `ArrayNode` to pretty-print JSON
        String fibJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(arrayNode);

            // print json
        System.out.println(fibJson);

        return fibJson;

    }


}

