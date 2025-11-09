package com.example.idiomatic.gradle.jamcatch.assets.end2end;

import com.example.idiomatic.gradle.javarca.engine.test.fixtures.RendererFixture;
import com.example.idiomatic.gradle.javarca.engine.test.fixtures.Screenshot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class JamCatchAssetsEnd2EndTest {

    @Test
    public void test() {
        Screenshot result = RendererFixture.launchAndWaitForFinish();

        assertTrue(result.file().exists(), "Screenshot should exist");
    }
}
