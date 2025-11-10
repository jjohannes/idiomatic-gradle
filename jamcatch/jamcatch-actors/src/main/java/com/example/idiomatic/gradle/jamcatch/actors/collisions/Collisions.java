package com.example.idiomatic.gradle.jamcatch.actors.collisions;

import com.example.idiomatic.gradle.javarca.model.ActorCollision;

import java.util.Map;
import java.util.Random;

import static com.example.idiomatic.gradle.javarca.model.GameConstants.PRECISION;
import static com.example.idiomatic.gradle.javarca.model.ActorProperty.POINTS;
import static com.example.idiomatic.gradle.javarca.model.ActorProperty.SPEEDY;
import static com.example.idiomatic.gradle.javarca.model.GameConstants.SYMBOL_EMPTY_SPOT;

public interface Collisions {
    String DEMO_MODE = System.getenv("DEMO_MODE");

    Map<Character, ActorCollision> p = Map.of(
            'J', (myState, otherState, allStates) -> {
                otherState.destroy();
                myState.addToValue(POINTS, otherState.getValue(POINTS));
                allStates.filter('0').print(myState.getValue(POINTS));
                char skin = 'H';
                skin += (char) new Random().nextInt(5);
                var newJar = allStates.spawn('J', new Random().nextInt(14) + 1, 0, skin);
                newJar.setValue(SPEEDY, otherState.getValue(SPEEDY) + 100);
            },
            SYMBOL_EMPTY_SPOT, (myState, otherState, allStates) -> {
                allStates.filter(':').setSkin(SYMBOL_EMPTY_SPOT); // make text row invisible
                var target = allStates.filter('J').filter(SPEEDY, v -> v > 0).getMaxX();
                if (DEMO_MODE != null) {
                    if (myState.getX() > target) {
                        myState.setX(myState.getX() - 1500);
                    } else if (myState.getX() < target) {
                        myState.setX(myState.getX() + 1500);
                    }
                }
            }
    );
    Map<Character, ActorCollision> j = Map.of(
            'J', (myState, otherState, allStates) -> {
                myState.setValue(SPEEDY, 0);
                var player = allStates.filter('p');
                player.setY(allStates.filter('J').filter(SPEEDY, v -> v == 0).getMinY() - PRECISION);
                if (player.getMinY() <= PRECISION * 2) {
                    player.destroy();
                    allStates.filter(':').print("GAME.OVER");
                } else {
                    allStates.spawn('J', new Random().nextInt(14) + 1, 0);
                }
            },
            'X', (myState, otherState, allStates) -> {
                myState.setValue(SPEEDY, 0);
                allStates.spawn('J', new Random().nextInt(14) + 1, 0);
                allStates.filter('p').setY(allStates.filter('J').filter(SPEEDY, v -> v == 0).getMinY() - PRECISION);
            }
    );

    Map<Character, Map<Character, ActorCollision>> ALL = Map.of('p', p, 'J', j);
}
