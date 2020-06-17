package com.example.idiomatic.gradle.api.internal;

import com.example.idiomatic.gradle.data.Member;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class IgApiServiceImpl implements IgApiService {

    private final static String MEMBERS_ENDPOINT = "http://localhost:8090/members";

    @Override
    public List<Member> queryMembers() {
        HttpClient client = HttpClientBuilder.create().build();
        String result;

        try {
            HttpResponse response = client.execute(new HttpGet(MEMBERS_ENDPOINT));
            result = new BufferedReader(new InputStreamReader(response.getEntity().getContent())).readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Arrays.stream(result.split(",")).map(Member::member).collect(Collectors.toList());
    }
}
