package com.example.idiomatic.gradle.javarca.model;

import java.util.Map;
import java.util.Set;

/**
 * An actor is represented by a 'symbol' and has some fundamental {@link ActorProperty}s.
 * It may provide {@link ActorCollision}s which essential make up the behavior of a game.
 * If two actors collide on the {@link Stage}, the corresponding collision logic is triggered.
 */
public record Actor(char symbol,
                    Set<ActorPropertyModifier> modifiers,
                    Map<Character, ActorCollision> collisionFunctions) {}
