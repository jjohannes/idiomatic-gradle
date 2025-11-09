package com.example.idiomatic.gradle.renderer.lwjgl.test;

import com.example.idiomatic.gradle.javarca.engine.Renderer;
import com.example.idiomatic.gradle.renderer.lwjgl.LWJGLRenderer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LWJGLRendererTest {

    @Test
    public void testRendererCreation() {
        Renderer renderer = new LWJGLRenderer();

        assertNotNull(renderer, "Renderer should not be null");
    }
}