package com.example.idiomatic.gradle.javarca.engine;

import java.util.ServiceLoader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Interface to be implemented on top of a library or framework that renders a running game.
 * The implementation should use {@link GameLoop} and {@link GameState},
 */
public interface Renderer {
    static void launch() {
        Logger logger = LoggerFactory.getLogger(Renderer.class);
        logger.info("Welcome to Javarcade! (module={})", Renderer.class.getModule().getName());

        Thread.setDefaultUncaughtExceptionHandler((thread, throwable) -> {
            logger.error("Unexpected crash!", throwable);
            System.exit(1);
        });

        var impl = ServiceLoader.load(Renderer.class).findFirst();
        impl.ifPresentOrElse(Renderer::run, () -> logger.warn("No renderer implementation available"));
    }

    void run();
}
