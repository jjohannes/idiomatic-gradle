package com.example.idiomatic.gradle.javarca.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.example.idiomatic.gradle.javarca.model.GameConstants.TRUE;
import static com.example.idiomatic.gradle.javarca.model.ActorProperty.PLAYER;
import static com.example.idiomatic.gradle.javarca.model.ActorProperty.SPEEDX;
import static com.example.idiomatic.gradle.javarca.model.ActorProperty.SPEEDY;

/**
 * The main game loop that calls the update methods on each {@link Spot} in the active game in a regular interval.
 */
public class GameLoop {
    private static final Logger LOGGER = LoggerFactory.getLogger(GameLoop.class);

    private ScheduledExecutorService exec;
    private GameState gameState;

    public GameLoop() {
    }

    public void start(GameState gameState) {
        this.gameState = gameState;
        exec = Executors.newSingleThreadScheduledExecutor();
        exec.scheduleAtFixedRate(this::updateWithErrorLog, 0, 20, TimeUnit.MILLISECONDS);
    }

    private void updateWithErrorLog() {
        try {
            update();
        } catch (Exception e) {
            LOGGER.error("Unexpected error in game loop", e);
        }
    }

    private void update() {
        for (Spot spot : gameState.getSpots()) {
            // translate keypress into speed
            if (spot.isAlive() && spot.getValue(PLAYER) == TRUE) {
                if (gameState.isUp()) {
                    spot.resetValue(SPEEDY);
                    spot.multiplyValue(SPEEDY, -1);
                } else if (gameState.isDown()) {
                    spot.resetValue(SPEEDY);
                } else {
                    spot.setValue(SPEEDY, 0);
                }

                if (gameState.isLeft()) {
                    spot.resetValue(SPEEDX);
                    spot.multiplyValue(SPEEDX, -1);
                } else if (gameState.isRight()) {
                    spot.resetValue(SPEEDX);
                } else {
                    spot.setValue(SPEEDX, 0);
                }
            }
            if (spot.isAlive()) {
                spot.move(spot.getValue(SPEEDX), spot.getValue(SPEEDY), gameState.getSpots(), gameState.getAll());
            }
        }
    }

    public void stop() {
        exec.shutdown();
    }
}
