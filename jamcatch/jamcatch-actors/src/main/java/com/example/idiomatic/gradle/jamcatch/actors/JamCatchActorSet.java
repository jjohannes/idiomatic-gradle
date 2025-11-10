package com.example.idiomatic.gradle.jamcatch.actors;

import com.example.idiomatic.gradle.javarca.model.Actor;
import com.example.idiomatic.gradle.javarca.model.ActorProperty;
import com.example.idiomatic.gradle.javarca.model.ActorPropertyModifier;
import com.example.idiomatic.gradle.javarca.model.ActorSet;
import com.example.idiomatic.gradle.jamcatch.actors.collisions.Collisions;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Objects.requireNonNull;

public class JamCatchActorSet implements ActorSet {

    private final Set<Actor> actors;

    public JamCatchActorSet() {
        var itemsCsv = requireNonNull(JamCatchActorSet.class.getResourceAsStream("res/jamcatch.csv"));
        actors = parse(itemsCsv).stream()
                .map(record -> new Actor(
                        record.get("SYMBOL").charAt(0),
                        Arrays.stream(ActorProperty.values())
                                .map(property -> parsePropertyValue(record, property))
                                .filter(Objects::nonNull)
                                .collect(Collectors.toSet()),
                        Collisions.ALL.getOrDefault(record.get("SYMBOL").charAt(0), Map.of())
                )).collect(Collectors.toSet());
    }

    private static ActorPropertyModifier parsePropertyValue(CSVRecord record, ActorProperty property) {
        String value = record.get(property);
        if (!value.isBlank()) {
            return new ActorPropertyModifier(property, Integer.parseInt(value));
        }
        return null;
    }

    private static List<CSVRecord> parse(InputStream itemsCsv) {
        try {
            var format = CSVFormat.DEFAULT
                    .builder()
                    .setHeader()
                    .setSkipHeaderRecord(true)
                    .get();
            return CSVParser.parse(itemsCsv, UTF_8, format).getRecords();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Set<Actor> items() {
        return actors;
    }
}
