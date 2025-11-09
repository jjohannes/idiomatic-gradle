package com.example.idiomatic.gradle.javarca.model;

/**
 * Represents the state of an {@link Actor} on the {@link Stage} of a running game.
 */
@SuppressWarnings({"UnusedReturnValue", "unused"})
public interface ActorState {

    /**
     * Destroy this actor.
     */
    void destroy();

    /**
     * Is the actor still alive? Actors that have been destroyed are no longer alive
     * and considered during collision computation.
     */
    boolean isAlive();

    /**
     * Horizontal position of the actor on the stage.
     */
    int getX();

    /**
     * Set the horizontal position of the actor on the stage.
     */
    int setX(int x);

    /**
     * Vertical position of the actor on the stage.
     */
    int getY();

    /**
     * Set the vertical position of the actor on the stage.
     */
    int setY(int y);

    /**
     * Current value of the given {@link ActorProperty}.
     */
    int getValue(ActorProperty p);

    /**
     * Reset the given {@link ActorProperty} to its original value.
     */
    int resetValue(ActorProperty p);

    /**
     * Set the given {@link ActorProperty} to the given value.
     */
    int setValue(ActorProperty p, int value);

    /**
     * Increase the given {@link ActorProperty} by the given increment.
     * A negative increment may be used to decrease the value.
     */
    int addToValue(ActorProperty p, int increment);

    /**
     * Multiply the given {@link ActorProperty} by the given multiplier.
     */
    int multiplyValue(ActorProperty p, int multiplier);

    /**
     * Change the 'skin' character of the actor. While the actor is always referred
     * to by its given symbol in game logic (which cannot be changed) the skin gives
     * the actor another appearance by the {@link Asset} linked to the skin character.
     */
    char setSkin(char skin);
}
