package com.example.idiomatic.gradle.fixtures;

import com.example.idiomatic.gradle.api.internal.IgApiService;
import com.example.idiomatic.gradle.data.Member;
import com.google.common.collect.ImmutableList;

import java.util.List;

import static com.example.idiomatic.gradle.data.Member.member;

public class TestIgService implements IgApiService {

    public static final List<Member> testData =
            ImmutableList.of(member("Aerith"), member("Zack"), member("Jessie"));

    @Override
    public List<Member> queryMembers() {
        return testData;
    }
}
