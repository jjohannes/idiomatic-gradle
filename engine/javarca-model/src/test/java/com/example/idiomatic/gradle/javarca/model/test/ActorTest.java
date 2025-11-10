package com.example.idiomatic.gradle.javarca.model.test;

import com.example.idiomatic.gradle.javarca.model.Actor;
import com.example.idiomatic.gradle.javarca.model.ActorCollision;
import com.example.idiomatic.gradle.javarca.model.ActorPropertyModifier;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ActorTest {

    @Test
    public void testActorCreation() {
        // Arrange
        char symbol = 'A';
        Set<ActorPropertyModifier> modifiers = new HashSet<>();
        Map<Character, ActorCollision> collisionFunctions = new HashMap<>();

        Actor actor = new Actor(symbol, modifiers, collisionFunctions);

        assertEquals(symbol, actor.symbol());
        assertEquals(modifiers, actor.modifiers());
        assertEquals(collisionFunctions, actor.collisionFunctions());
    }
}