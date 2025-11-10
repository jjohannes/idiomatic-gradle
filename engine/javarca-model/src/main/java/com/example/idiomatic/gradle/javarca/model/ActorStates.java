package com.example.idiomatic.gradle.javarca.model;

import java.util.function.Predicate;

/**
 * Represents the state of a set of {@link Actor}s on the {@link Stage} of a running game.
 * This allows for matching and modifying a set of {@link ActorState}s in a {@link ActorCollision} function.
 */
@SuppressWarnings({"UnusedReturnValue", "unused"})
public interface ActorStates {

    /**
     * A subset of characters in this set that are represented by the given 'symbol'.
     */
    ActorStates filter(char symbol);

    /**
     * A subset of characters in this set that have the given property matches the give predicate.
     */
    ActorStates filter(ActorProperty p, Predicate<Integer> value);

    /**
     * Set the horizontal position of all actors in this set.
     */
    int setX(int x);

    /**
     * Set the vertical position of all actors in this set.
     */
    int setY(int y);

    /**
     * Vertical position of the actor that is the closest to the top.
     */
    int getMinY();

    /**
     * Vertical position of the actor that is the closest to the bottom.
     */
    int getMaxY();

    /**
     * Horizontal position of the actor that is the farthest left.
     */
    int getMinX();

    /**
     * Horizontal position of the actor that is the farthest right.
     */
    int getMaxX();

    /**
     * Skin this set of actors with character assets that show a text/number on the stage.
     */
    void print(String value);

    /**
     * Skin this set of actors with character assets that show a text/number on the stage.
     */
    void print(int value);

    /**
     * Change the skin of all actors in the set.
     */
    void setSkin(char c);

    /**
     * Destroy all actors in this set.
     */
    void destroy();

    /**
     * Spawn a new actor on the stage.
     */
    ActorState spawn(char symbole, int x, int y);

    /**
     * Spawn a new actor on the stage.
     */
    ActorState spawn(char symbole, int x, int y, char skin);
}
