package com.example.idiomatic.gradle.javarca.engine;

/**
 * Entry point for any game that runs through this engine.
 */
public class Engine {

    private Engine() {}

    public static void main(String[] args) {
        Renderer.launch();
    }
}
