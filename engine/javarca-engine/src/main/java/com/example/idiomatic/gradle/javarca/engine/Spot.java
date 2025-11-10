package com.example.idiomatic.gradle.javarca.engine;

import com.example.idiomatic.gradle.javarca.model.ActorCollision;
import com.example.idiomatic.gradle.javarca.model.ActorProperty;
import com.example.idiomatic.gradle.javarca.model.ActorState;
import com.example.idiomatic.gradle.javarca.model.ActorStates;
import com.example.idiomatic.gradle.javarca.model.Stage;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.example.idiomatic.gradle.javarca.model.GameConstants.PRECISION;
import static com.example.idiomatic.gradle.javarca.model.GameConstants.STAGE_SIZE;
import static com.example.idiomatic.gradle.javarca.model.GameConstants.SYMBOL_EMPTY_SPOT;
import static com.example.idiomatic.gradle.javarca.model.ActorProperty.BLOCKING;
import static com.example.idiomatic.gradle.javarca.model.ActorProperty.DESTRUCTIBLE;
import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * The implementation of {@link ActorState}.
 */
public class Spot implements ActorState {
    private final Map<Character, ActorCollision> collisionFunctions = new LinkedHashMap<>();
    private final Map<ActorProperty, Integer> initialValues = new LinkedHashMap<>();
    private final Map<ActorProperty, Integer> values = new LinkedHashMap<>();

    private final char symbol;
    private char skin;
    private boolean alive;
    private int x;
    private int y;

    public static Stream<Spot> render(Stage stage) {
        List<Integer> symbols = Arrays.stream(stage.define().replace(" ", "").split("\n"))
                .flatMap(line -> line.chars().boxed())
                .toList();

        return IntStream.range(0, STAGE_SIZE * STAGE_SIZE).mapToObj(p ->
                new Spot((char) symbols.get(p).intValue(), p));
    }

    public Spot(char symbol, int x, int y) {
        this.symbol = symbol;
        this.skin = symbol;
        this.alive = true;
        this.x = PRECISION * x;
        this.y = PRECISION * y;
    }

    public Spot(char symbol, int posInStream) {
        this(
                symbol,
                posInStream - (posInStream / STAGE_SIZE) * STAGE_SIZE,
                posInStream / STAGE_SIZE);
    }

    public Spot clone(char newSymbol) {
        Spot clone = new Spot(newSymbol, x / PRECISION, y / PRECISION);
        clone.init(initialValues, collisionFunctions);
        return clone;
    }

    public Spot clone(char newSymbol, int x, int y) {
        Spot clone = new Spot(newSymbol, x, y);
        clone.init(initialValues, collisionFunctions);
        return clone;
    }


    @Override
    public String toString() {
        return symbol + "(" + (x * 1f) / PRECISION + "|" + (y * 1f) / PRECISION + ")";
    }

    public void init(Map<ActorProperty, Integer> initialValues, Map<Character, ActorCollision> collisionFunctions) {
        this.initialValues.putAll(initialValues);
        this.collisionFunctions.putAll(collisionFunctions);
    }

    public char getSkin() {
        return skin;
    }

    public char setSkin(char skin) {
        this.skin = skin;
        return this.skin;
    }

    public char getSymbol() {
        return symbol;
    }

    @Override
    public int getValue(ActorProperty p) {
        return values.getOrDefault(p, initialValues.getOrDefault(p, 0));
    }

    @Override
    public int setValue(ActorProperty p, int value) {
        values.put(p, value);
        return getValue(p);
    }

    @Override
    public int resetValue(ActorProperty p) {
        values.remove(p);
        return getValue(p);
    }

    @Override
    public int addToValue(ActorProperty p, int increment) {
        values.put(p, getValue(p) + increment);
        return getValue(p);
    }

    @Override
    public int multiplyValue(ActorProperty p, int multiplier) {
        values.put(p, getValue(p) * multiplier);
        return getValue(p);
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int setX(int x) {
        this.x = x;
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int setY(int y) {
        this.y = y;
        return y;
    }

    @Override
    public boolean isAlive() {
        return alive;
    }

    @Override
    public void destroy() {
        alive = false;
    }

    public float getPixelPositionX(int cellWidthInPixel) {
        if (!alive) {
            return Float.MIN_VALUE;
        }
        return (x * cellWidthInPixel) * 1f / PRECISION;
    }

    public float getPixelPositionY(int cellHeightInPixel) {
        if (!alive) {
            return Float.MIN_VALUE;
        }
        return (y * cellHeightInPixel) * 1f / PRECISION;
    }

    private boolean blocks() {
        return getValue(BLOCKING) == 1 || getValue(DESTRUCTIBLE) == 1;
    }

    public void move(int deltaX, int deltaY, List<Spot> allSpots, ActorStates all) {
        if (!alive) {
            return;
        }

        collideSelf(all);

        if (deltaX == 0 && deltaY == 0) {
            return;
        }

        int newX = min(max(0, x + deltaX), (STAGE_SIZE - 1) * PRECISION);
        int newY = min(max(0, y + deltaY), (STAGE_SIZE - 1) * PRECISION);

        List<Spot> collisions = allSpots.stream().filter(s -> s.alive && s.blocks() && s != this && doesCollide(deltaX, deltaY, s)).toList();
        if (!collisions.isEmpty()) {
            collisions.forEach(s -> collide(s, all));

            // maybe we can still move part of the way
            if (deltaX != 0) {
                x = deltaX < 0 ? snapFloor(x) : snapCeil(x);
            }
            if (deltaY != 0) {
                y = deltaX < 0 ? snapFloor(y) : snapCeil(y);
            }
            return;
        }

        x = newX;
        y = newY;
    }

    private int snapFloor(int value) {
        return (value / PRECISION) * PRECISION;
    }

    private int snapCeil(int value) {
        int ceil = ((value + PRECISION) / PRECISION) * PRECISION;
        if (ceil == value + PRECISION) {
            return value;
        }
        return ceil;
    }

    private boolean doesCollide(int deltaX, int deltaY, Spot other) {
        return x + deltaX < other.x + PRECISION &&
                x + deltaX + PRECISION > other.x &&
                y + deltaY < other.y + PRECISION &&
                y + deltaY + PRECISION > other.y;
    }

    private void collide(Spot other, ActorStates all) {
        if (collisionFunctions.containsKey(other.symbol)) {
            collisionFunctions.get(other.symbol).collide(this, other, all);
            if (symbol == other.symbol) {
                return; // do not compute the same collision logic twice
            }
        }
        if (other.collisionFunctions.containsKey(symbol)) {
            other.collisionFunctions.get(symbol).collide(other, this, all);
        }
    }

    private void collideSelf(ActorStates all) {
        if (collisionFunctions.containsKey(SYMBOL_EMPTY_SPOT)) {
            collisionFunctions.get(SYMBOL_EMPTY_SPOT).collide(this, this, all);
        }
    }
}
