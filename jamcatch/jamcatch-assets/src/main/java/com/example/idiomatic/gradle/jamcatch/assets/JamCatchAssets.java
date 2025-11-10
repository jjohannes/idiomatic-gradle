package com.example.idiomatic.gradle.jamcatch.assets;

import com.example.idiomatic.gradle.javarca.model.Asset;
import com.example.idiomatic.gradle.javarca.model.AssetSet;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

import static java.util.Objects.requireNonNull;

public class JamCatchAssets implements AssetSet {

    private final Set<Asset> assets;

    public JamCatchAssets() {
        assets = Set.of(
                new Asset('.', readImage("bg")),
                new Asset('p', readImage("catcher")),
                new Asset('x', readImage("wall")),
                new Asset('X', readImage("solid")),
                new Asset('H', readImage("jar_4")),
                new Asset('I', readImage("jar_5")),
                new Asset('J', readImage("jar_0")),
                new Asset('K', readImage("jar_2")),
                new Asset('L', readImage("jar_3"))
        );
    }

    private byte[] readImage(String name) {
        try (InputStream is = getClass().getResourceAsStream("res/" + name + ".png")) {
            return IOUtils.toByteArray(requireNonNull(is));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Set<Asset> assets() {
        return assets;
    }
}
