package com.example.idiomatic.gradle.jamcatch.assets.test;

import com.example.idiomatic.gradle.jamcatch.assets.JamCatchAssets;
import com.example.idiomatic.gradle.javarca.model.Asset;
import com.example.idiomatic.gradle.javarca.model.AssetSet;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class JamCatchAssetsTest {

    @Test
    public void testAssetSetCreation() {
        AssetSet assetSet = new JamCatchAssets();

        assertNotNull(assetSet);
        Set<Asset> assets = assetSet.assets();
        assertNotNull(assets);
        assertFalse(assets.isEmpty(), "Asset set should not be empty");
    }
}