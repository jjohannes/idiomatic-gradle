package com.example.idiomatic.gradle.jamcatch.stage.test;

import com.example.idiomatic.gradle.jamcatch.stage.JamCatchStage;
import com.example.idiomatic.gradle.javarca.model.Stage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JamCatchStageTest {

    @Test
    public void testStageDefinition() {
        // Arrange
        Stage stage = new JamCatchStage();

        String definition = stage.define();

        assertNotNull(definition);
        assertTrue(definition.contains("p"), "Stage should contain player character");
        assertTrue(definition.contains("J"), "Stage should contain jar character");
        assertTrue(definition.contains("X"), "Stage should contain solid blocks");
        assertTrue(definition.contains("x"), "Stage should contain wall blocks");
    }
}