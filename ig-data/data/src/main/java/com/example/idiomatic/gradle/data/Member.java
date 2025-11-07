package com.example.idiomatic.gradle.data;

/**
 * Represent a member of the community.
 */
public class Member {

    private final String name;

    private Member(String name) {
        this.name = name;
    }

    /**
     * Create a new member with the given name.
     *
     * @param name the name
     * @return new member instance
     */
    public static Member member(String name) {
        return new Member(name);
    }

    /**
     * The member's name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }
}
