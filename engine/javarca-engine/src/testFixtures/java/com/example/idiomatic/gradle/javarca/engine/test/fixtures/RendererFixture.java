package com.example.idiomatic.gradle.javarca.engine.test.fixtures;

import com.example.idiomatic.gradle.javarca.engine.Renderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class RendererFixture {

    public static Screenshot launchAndWaitForFinish() {
        Logger logger = LoggerFactory.getLogger(RendererFixture.class);
        File file = new File(System.getenv("PRESENTATION_FOLDER") + "/screen.png");
        try {
            var renderThread = new Thread(Renderer::launch);

            renderThread.start();
            Thread.sleep(1000);
            renderThread.interrupt();
            return new Screenshot(file);
        } catch (Throwable e) {
            logger.error("Error in test execution", e);
            return new Screenshot(file);
        }

    }
}
