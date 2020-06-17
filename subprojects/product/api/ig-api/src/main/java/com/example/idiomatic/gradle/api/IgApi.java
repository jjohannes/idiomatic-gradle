package com.example.idiomatic.gradle.api;

import com.example.idiomatic.gradle.api.internal.IgApiService;
import com.example.idiomatic.gradle.api.internal.IgApiServiceImpl;
import com.example.idiomatic.gradle.data.Member;

import java.util.List;

/**
 * Integrate this API into your App to use the IdiomaticGradle services.
 */
public class IgApi {

    public static IgApi get() {
        return new IgApi(new IgApiServiceImpl());
    }

    private final IgApiService service;

    protected IgApi(IgApiService service) {
        this.service = service;
    }

    public List<Member> getAllMembers() {
        return service.queryMembers();
    }
}
