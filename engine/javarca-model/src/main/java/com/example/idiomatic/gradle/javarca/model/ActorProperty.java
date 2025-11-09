package com.example.idiomatic.gradle.javarca.model;

/**
 * Pre-defined properties each {@link Actor} can have. They decide if and how an {@link Actor} moves on
 * the {@link Stage} and if {@link Actor}s can collide to trigger {@link ActorCollision} logic.
 */
public enum ActorProperty {
    PLAYER,
    SPEEDX,
    SPEEDY,
    BLOCKING,
    DESTRUCTIBLE,
    POINTS
}
