package com.example.idiomatic.gradle.server;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServerTest {

    @BeforeEach
    public void setup() throws Exception {
        IgServer server = new IgServer();
        server.start();
    }

    @Test
    void testMemberService() throws Exception {

        String url = "http://localhost:8090/members";
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);

        HttpResponse response = client.execute(request);

        System.out.println(new BufferedReader(new InputStreamReader(response.getEntity().getContent())).readLine());
        assertEquals(200, response.getStatusLine().getStatusCode());
    }
}
