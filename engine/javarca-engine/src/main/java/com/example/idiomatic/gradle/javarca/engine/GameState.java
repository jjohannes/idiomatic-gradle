package com.example.idiomatic.gradle.javarca.engine;

import com.example.idiomatic.gradle.javarca.engine.impl.ActorStatesImpl;
import com.example.idiomatic.gradle.javarca.model.Asset;
import com.example.idiomatic.gradle.javarca.model.AssetSet;
import com.example.idiomatic.gradle.javarca.model.Actor;
import com.example.idiomatic.gradle.javarca.model.ActorSet;
import com.example.idiomatic.gradle.javarca.model.ActorStates;
import com.example.idiomatic.gradle.javarca.model.Stage;
import com.example.idiomatic.gradle.javarca.model.ActorPropertyModifier;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

import static com.example.idiomatic.gradle.javarca.model.GameConstants.STAGE_SIZE;
import static com.example.idiomatic.gradle.javarca.model.GameConstants.SYMBOL_EMPTY_SPOT;
import static java.util.function.Function.identity;

/**
 * Represents the state of the currently running game. The state is primarily composed of a number of {@link Spot}s,
 * initialized through a {@link Stage}, that may change their state over time.
 */
public class GameState {
    Stage EMPTY_DEFAULT = () -> ("x".repeat(STAGE_SIZE))
            + ("x" + ".".repeat(STAGE_SIZE-2) + "x\n").repeat(STAGE_SIZE-2)
            + ("x".repeat(STAGE_SIZE));

    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;

    private final Map<Character, Actor> actors = new LinkedHashMap<>();

    private final List<Spot> spots = new CopyOnWriteArrayList<>();
    private final Map<Character, byte[]> images = new HashMap<>();
    private ActorStates all;

    public GameState() {
        init();
    }

    private void init() {
        ServiceLoader.load(ActorSet.class).forEach(set ->
                actors.putAll(set.items().stream().collect(Collectors.toMap(Actor::symbol, identity()))));
        ServiceLoader.load(AssetSet.class)
                .forEach(set ->
                        images.putAll(set.assets().stream().collect(Collectors.toMap(Asset::symbol, Asset::image))));
        var stage = ServiceLoader.load(Stage.class).findFirst().orElse(EMPTY_DEFAULT);
        List<Spot> renderedStage = Spot.render(stage).toList();
        Map<Character, Spot> prototypes = new LinkedHashMap<>();
        renderedStage.forEach(spot -> {
            if (actors.containsKey(spot.getSymbol())) {
                spots.addFirst(spot.clone(SYMBOL_EMPTY_SPOT));
                var item = actors.get(spot.getSymbol());
                spot.init(
                        item.modifiers().stream().collect(Collectors.toMap(ActorPropertyModifier::p, ActorPropertyModifier::value)),
                        item.collisionFunctions()
                );
                spots.add(spot);
                prototypes.put(spot.getSymbol(), spot);
            } else {
                spots.addFirst(spot);
            }
        });
        all = new ActorStatesImpl(spots, spots, prototypes);
    }

    public List<Spot> getSpots() {
        return spots;
    }

    public ActorStates getAll() {
        return all;
    }

    public Map<Character, byte[]> getImages() {
        return images;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void action() {
        System.out.println("Boom!");
    }
}
