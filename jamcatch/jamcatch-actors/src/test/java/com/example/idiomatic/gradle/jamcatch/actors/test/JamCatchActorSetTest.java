package com.example.idiomatic.gradle.jamcatch.actors.test;

import com.example.idiomatic.gradle.jamcatch.actors.JamCatchActorSet;
import com.example.idiomatic.gradle.javarca.model.Actor;
import com.example.idiomatic.gradle.javarca.model.ActorSet;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class JamCatchActorSetTest {

    @Test
    public void testActorSetCreation() {
        ActorSet actorSet = new JamCatchActorSet();

        assertNotNull(actorSet);
        Set<Actor> actors = actorSet.items();
        assertNotNull(actors);
        assertFalse(actors.isEmpty(), "Actor set should not be empty");
    }
}