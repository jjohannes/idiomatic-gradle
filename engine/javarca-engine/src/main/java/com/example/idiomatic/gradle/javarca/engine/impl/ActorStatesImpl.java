package com.example.idiomatic.gradle.javarca.engine.impl;

import com.example.idiomatic.gradle.javarca.engine.Spot;
import com.example.idiomatic.gradle.javarca.model.ActorProperty;
import com.example.idiomatic.gradle.javarca.model.ActorState;
import com.example.idiomatic.gradle.javarca.model.ActorStates;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.example.idiomatic.gradle.javarca.model.GameConstants.PRECISION;
import static com.example.idiomatic.gradle.javarca.model.GameConstants.STAGE_SIZE;

public class ActorStatesImpl implements ActorStates {

    private final List<Spot> root;
    private final List<Spot> spots;
    private final Map<Character, Spot> prototypes;

    public ActorStatesImpl(List<Spot> spots, List<Spot> root, Map<Character, Spot> prototypes) {
        this.spots = spots;
        this.root = root;
        this.prototypes = prototypes;
    }

    @Override
    public ActorStates filter(char symbol) {
        return new ActorStatesImpl(spots.stream().filter(s -> s.isAlive() && s.getSymbol() == symbol).collect(Collectors.toList()), root, prototypes);
    }

    @Override
    public ActorStates filter(ActorProperty p, Predicate<Integer> predicate) {
        return new ActorStatesImpl(spots.stream().filter(s -> s.isAlive() && predicate.test(s.getValue(p))).collect(Collectors.toList()), root, prototypes);
    }

    @Override
    public void print(String value) {
        for (int i = 0; i < spots.size() && i < value.length(); i++) {
            spots.get(i).setSkin(value.charAt(i));
        }
    }

    @Override
    public void print(int value) {
        String s = String.format("%1$" + spots.size() + "s", value).replace(' ', '0');
        for (int i = 0; i < spots.size() && i < s.length(); i++) {
            spots.get(i).setSkin(s.charAt(i));
        }
    }

    @Override
    public void setSkin(char c) {
        spots.forEach(s -> s.setSkin(c));
    }

    @Override
    public void destroy() {
        spots.forEach(ActorState::destroy);
    }

    @Override
    public int setX(int x) {
        spots.forEach(s -> s.setX(x));
        return x;
    }

    @Override
    public int setY(int y) {
        spots.forEach(s -> s.setY(y));
        return y;
    }

    @Override
    public int getMinY() {
        return spots.stream().map(Spot::getY).min(Integer::compareTo).orElse(0);
    }

    @Override
    public int getMaxY() {
        return spots.stream().map(Spot::getY).max(Integer::compareTo).orElse((STAGE_SIZE -1) * PRECISION);
    }

    @Override
    public int getMinX() {
        return spots.stream().map(Spot::getX).min(Integer::compareTo).orElse(0);
    }

    @Override
    public int getMaxX() {
        return spots.stream().map(Spot::getX).max(Integer::compareTo).orElse((STAGE_SIZE -1) * PRECISION);
    }

    @Override
    public ActorState spawn(char symbol, int x, int y) {
        return spawn(symbol, x, y, symbol);
    }

    @Override
    public ActorState spawn(char symbol, int x, int y, char skin) {
        var prototype = prototypes.get(symbol);
        Spot newSpot;
        if (prototype == null) {
            newSpot = new Spot(symbol, x, y);
        } else {
            newSpot = prototype.clone(symbol, x, y);
        }
        newSpot.setSkin(skin);
        root.add(newSpot);
        return newSpot;
    }
}
