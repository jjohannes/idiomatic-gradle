package com.example.idiomatic.gradle.javarca.model;

/**
 * Represents the collision logic between two {@link Actor}s. A collision function has access
 * to the two colliding {@link ActorState}s, but may also access and modify the state of any other
 * actor on the stage.
 */
public interface ActorCollision {

    void collide(ActorState myState, ActorState otherState, ActorStates allStates);
}
