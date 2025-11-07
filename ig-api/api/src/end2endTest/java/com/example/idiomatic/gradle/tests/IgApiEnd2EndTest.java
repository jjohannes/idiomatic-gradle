package com.example.idiomatic.gradle.tests;

import com.example.idiomatic.gradle.data.Member;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import com.example.idiomatic.gradle.api.IgApi;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IgApiEnd2EndTest {

    private static Process proc;

    @BeforeAll
    static void setup() throws IOException {
        String jarPath = System.getProperty("serverJar");
        String command = "java -jar " + jarPath;
        System.out.println(command);
        proc = Runtime.getRuntime().exec(command.split(" "));
        BufferedReader errOut = new BufferedReader(new InputStreamReader(proc.getErrorStream()));

        // Waiting for startup (or error)
        System.out.println(errOut.readLine());
        System.out.println(errOut.readLine());
        System.out.println(errOut.readLine());
    }

    @AfterAll
    static void tearDown() {
        proc.destroy();
    }

    @Test
    void testMemberService() {
        assertEquals(ImmutableList.of("M1", "M2", "M3"), IgApi.get().getAllMembers().stream().map(Member::getName).collect(Collectors.toList()));
    }
}
